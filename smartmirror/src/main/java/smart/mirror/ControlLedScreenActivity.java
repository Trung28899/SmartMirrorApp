package smart.mirror;
//Smart Device (Haider Ibrahim, Minh Nguyen , Trung Trinh)
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

class Sensor{
    String id;
    boolean switchStateSensor;
    String timeOn;
    String timeOff;

    public Sensor(){

    }

    public Sensor(String id, boolean switchStateSensor, String timeOn, String timeOff) {
        this.id = id;
        this.switchStateSensor = switchStateSensor;
        this.timeOn = timeOn;
        this.timeOff = timeOff;
    }

    public String getId() {
        return id;
    }

    public boolean isSwitchStateSensor() {
        return switchStateSensor;
    }

    public String getTimeOn() {
        return timeOn;
    }

    public String getTimeOff() {
        return timeOff;
    }
}

public class ControlLedScreenActivity extends AppCompatActivity implements View.OnClickListener{

    private Switch switchLight;
    private Switch switchSensor;
    private RadioGroup radioGroupLights;
    private Button buttonUpdate;
    private RadioButton chosenButton;
    private String mirrorSerial;

    private DatabaseReference ledDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ledcontrolscreen_activity);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        ActionBar lmao = getSupportActionBar();
        lmao.setDisplayHomeAsUpEnabled(true);
        mirrorSerial = getIntent().getStringExtra("mirrorSerial");

        ledDatabase = FirebaseDatabase.getInstance().getReference("Mirror_Serial_Numbers/"+mirrorSerial+"/LED_Sensor");

        switchLight = (Switch) findViewById(R.id.switchTimeSensor);
        switchSensor = (Switch) findViewById(R.id.switchSensor);
        final ImageView img1 = (ImageView) findViewById(R.id.imageView2);
        final ImageView img2 = (ImageView) findViewById(R.id.imageView10);
        img1.setImageDrawable(getDrawable(R.drawable.timeoff));
        img2.setImageDrawable(getDrawable(R.drawable.sensoroff));
        buttonUpdate = (Button) findViewById(R.id.buttonUpdate);
        buttonUpdate.setOnClickListener(this);
        switchLight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (switchLight.isChecked()){
                    img1.setImageDrawable(getDrawable(R.drawable.timeon));
                } else {
                    img1.setImageDrawable(getDrawable(R.drawable.timeoff));
                }
            }
        });

        switchSensor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (switchSensor.isChecked()){
                    img2.setImageDrawable(getDrawable(R.drawable.sensoron));
                } else {
                    img2.setImageDrawable(getDrawable(R.drawable.sensoroff));
                }
            }
        });


    }

    private void updateLights(){
        boolean switchStateLight = switchLight.isChecked();
        boolean switchStateSensor = switchSensor.isChecked();
        //String lightColor = chosenButton.getText().toString();
        //String id = ledDatabase.push().getKey();

        String id = "-LucaKVQIte9Lkamrdw-";

        Sensor Sensor = new Sensor(id, switchStateSensor, "23:59", "23:59");

        ledDatabase.child(id).setValue(Sensor);

        Toast.makeText(this, "Setting Updated !", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View v) {
        if(v == buttonUpdate){
            updateLights();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        ledDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                DataSnapshot settingSnapshot = dataSnapshot;
                Sensor Sensor = new Sensor("-LucaKVQIte9Lkamrdw-", true, "23:59", "23:59");

                Sensor = dataSnapshot.child("-LucaKVQIte9Lkamrdw-").getValue(Sensor.class);

                //switchLight.setChecked(ledandSensor.isSwitchStateLight());
                switchSensor.setChecked(Sensor.isSwitchStateSensor());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), "Something Wrong", Toast.LENGTH_SHORT).show();
            }

        });
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
}