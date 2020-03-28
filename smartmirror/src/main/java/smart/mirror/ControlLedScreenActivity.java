package smart.mirror;
//Smart Device (Haider Ibrahim, Minh Nguyen , Trung Trinh)
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;
import android.app.TimePickerDialog;
import android.text.InputType;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.widget.TimePicker;
import java.util.Calendar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

class Sensor{
    String timerState;
    String sensorState;
    String timeOn;
    String timeOff;

    public Sensor(){

    }

    public Sensor(String timerState, String sensorState, String timeOn, String timeOff) {
        this.timerState = timerState;
        this.sensorState = sensorState;
        this.timeOn = timeOn;
        this.timeOff = timeOff;
    }

    public String getTimerState() {
        return timerState;
    }

    public String getSensorState() {
        return sensorState;
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
    private TimePickerDialog picker;
    private TimePickerDialog picker1;
    private String mirrorSerial;
    private EditText eText;
    private EditText eText1;
    private DatabaseReference ledDatabase;
    private ImageView img1, img2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ledcontrolscreen_activity);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        ActionBar lmao = getSupportActionBar();
        lmao.setDisplayHomeAsUpEnabled(true);
        mirrorSerial = getIntent().getStringExtra("mirrorSerial");
        ledDatabase = FirebaseDatabase.getInstance().getReference("Mirror_Serial_Numbers/"+mirrorSerial+"/Sensor");
        switchLight = (Switch) findViewById(R.id.switchTimeSensor);
        switchSensor = (Switch) findViewById(R.id.switchSensor);
        img1 = (ImageView) findViewById(R.id.imageView2);
        img2 = (ImageView) findViewById(R.id.imageView10);
        img1.setImageDrawable(getDrawable(R.drawable.timeoff));
        img2.setImageDrawable(getDrawable(R.drawable.sensoroff));
        eText=(EditText) findViewById(R.id.editText);
        eText.setInputType(InputType.TYPE_NULL);
        eText1=(EditText) findViewById(R.id.editText2);
        eText1.setInputType(InputType.TYPE_NULL);

        buttonUpdate = (Button) findViewById(R.id.buttonUpdate);
        buttonUpdate.setOnClickListener(this);
        switchLight.setOnClickListener(this);
        switchSensor.setOnClickListener(this);
        eText.setOnClickListener(this);
        eText1.setOnClickListener(this);
    }

    private void updateSensor(){
        String timerState = Boolean.toString(switchLight.isChecked());
        String sensorState = Boolean.toString(switchSensor.isChecked());
        String timerOn = eText.getText().toString().trim();
        String timerOff = eText1.getText().toString().trim();

        Sensor Sensor = new Sensor(timerState, sensorState, timerOn, timerOff);

        ledDatabase.setValue(Sensor);

        Toast.makeText(this, "Setting Updated !", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View v) {
        if(v == buttonUpdate){
            updateSensor();
        }

        if (v == switchLight){
            setImg1(img1, switchLight);
        }

        if (v == switchSensor) {
            setImg2(img2, switchSensor);
        }

        if (v == eText){
            final Calendar cldr = Calendar.getInstance();
            int hour = cldr.get(Calendar.HOUR_OF_DAY);
            int minutes = cldr.get(Calendar.MINUTE);
            // time picker dialog
            picker = new TimePickerDialog(ControlLedScreenActivity.this,
                    new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker tp, int sHour, int sMinute) {
                            eText.setText(sHour + ":" + sMinute);
                        }
                    }, hour, minutes, true);
            picker.show();
        }

        if (v == eText1){
            final Calendar cldr1 = Calendar.getInstance();
            int hour1 = cldr1.get(Calendar.HOUR_OF_DAY);
            int minutes1 = cldr1.get(Calendar.MINUTE);
            // time picker dialog
            picker1 = new TimePickerDialog(ControlLedScreenActivity.this,
                    new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker tp1, int sHour1, int sMinute1) {
                            eText1.setText(sHour1 + ":" + sMinute1);
                        }
                    }, hour1, minutes1, true);
            picker1.show();
        }
    }

    private void setImg1(ImageView im1, Switch switchM1) {
        if (switchM1.isChecked()){
            im1.setImageDrawable(getDrawable(R.drawable.timeon));
        } else {
            im1.setImageDrawable(getDrawable(R.drawable.timeoff));
        }
    }

    private void setImg2(ImageView im2, Switch switchM2) {
        if (switchM2.isChecked()){
            im2.setImageDrawable(getDrawable(R.drawable.sensoron));
        } else {
            im2.setImageDrawable(getDrawable(R.drawable.sensoroff));
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        ledDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                DataSnapshot settingSnapshot = dataSnapshot;
                Sensor sensor = new Sensor("false", "false", "00:00", "00:00");

                sensor = dataSnapshot.getValue(Sensor.class);

                switchLight.setChecked(Boolean.parseBoolean(sensor.getTimerState()));
                switchSensor.setChecked(Boolean.parseBoolean(sensor.getSensorState()));
                eText.setText(sensor.timeOn);
                eText.setText(sensor.timeOff);
                setImg1(img1, switchLight);
                setImg2(img2, switchSensor);
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


/*btnGet=(Button)findViewById(R.id.button1);
        btnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvw.setText("Selected Time: "+ eText.getText());
            }
        });*/