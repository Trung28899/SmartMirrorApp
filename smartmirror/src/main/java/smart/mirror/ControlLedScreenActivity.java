package smart.mirror;
//Smart Device (Haider Ibrahim, Minh Nguyen , Trung Trinh)
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
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

class LEDandSensor{
    String id;
    boolean switchStateLight;
    boolean switchStateSensor;
    String lightColor;

    public LEDandSensor(){

    }

    public LEDandSensor(String id, boolean switchStateLight, boolean switchStateSensor, String lightColor) {
        this.id = id;
        this.switchStateLight = switchStateLight;
        this.switchStateSensor = switchStateSensor;
        this.lightColor = lightColor;
    }

    public String getId() {
        return id;
    }

    public boolean isSwitchStateLight() {
        return switchStateLight;
    }

    public boolean isSwitchStateSensor() {
        return switchStateSensor;
    }

    public String getLightColor() {
        return lightColor;
    }
}

public class ControlLedScreenActivity extends AppCompatActivity implements View.OnClickListener{

    private Switch switchLight;
    private Switch switchSensor;
    private RadioGroup radioGroupLights;
    private Button buttonUpdate;
    private RadioButton chosenButton;

    private DatabaseReference ledDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ledcontrolscreen_activity);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        ActionBar lmao = getSupportActionBar();
        lmao.setDisplayHomeAsUpEnabled(true);

        ledDatabase = FirebaseDatabase.getInstance().getReference("LED_Sensor");

        switchLight = (Switch) findViewById(R.id.switchLight);
        switchSensor = (Switch) findViewById(R.id.switchSensor);
        radioGroupLights = (RadioGroup) findViewById(R.id.radioGroup);
        int radioIds = radioGroupLights.getCheckedRadioButtonId() ;
        chosenButton = findViewById(radioIds);
        final ImageView img1 = (ImageView) findViewById(R.id.imageView2);
        final ImageView img2 = (ImageView) findViewById(R.id.imageView10);
        img1.setImageDrawable(getDrawable(R.drawable.lightoff1));
        img2.setImageDrawable(getDrawable(R.drawable.sensoroff));
        buttonUpdate = (Button) findViewById(R.id.buttonUpdate);
        buttonUpdate.setOnClickListener(this);
        switchLight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (switchLight.isChecked()){
                    img1.setImageDrawable(getDrawable(R.drawable.lighton1));
                } else {
                    img1.setImageDrawable(getDrawable(R.drawable.lightoff1));
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

        LEDandSensor ledandSensor = new LEDandSensor(id, switchStateLight, switchStateSensor, "blue");

        ledDatabase.child(id).setValue(ledandSensor);

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
                LEDandSensor ledandSensor = new LEDandSensor("-LucaKVQIte9Lkamrdw-", true, true, "green");

                ledandSensor = dataSnapshot.child("-LucaKVQIte9Lkamrdw-").getValue(LEDandSensor.class);

                switchLight.setChecked(ledandSensor.isSwitchStateLight());
                switchSensor.setChecked(ledandSensor.isSwitchStateSensor());
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