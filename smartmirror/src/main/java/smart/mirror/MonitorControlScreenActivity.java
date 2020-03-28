package smart.mirror;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.app.TimePickerDialog;
import android.text.InputType;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TimePicker;
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

import java.util.Calendar;

class Monitor{
    String timerState;
    String monitorState;
    String timeOn;
    String timeOff;

    public Monitor(){

    }

    public Monitor( String timerState, String monitorState, String timeOn, String timeOff) {
        this.timerState = timerState;
        this.monitorState = monitorState;
        this.timeOn = timeOn;
        this.timeOff = timeOff;
    }

    public String getTimerState() {
        return timerState;
    }

    public String getMonitorState() {
        return monitorState;
    }

    public String getTimeOn() {
        return timeOn;
    }

    public String getTimeOff() {
        return timeOff;
    }
}

public class MonitorControlScreenActivity extends AppCompatActivity implements View.OnClickListener{

    private Switch switchMonitor;
    private Switch switchSensorMonitor;
    private RadioGroup radioGroupLights;
    private Button buttonUpdate;
    private RadioButton chosenButton;
    private String mirrorSerial;
    private TimePickerDialog picker3;
    private TimePickerDialog picker4;
    private EditText eText3;
    private EditText eText4;
    private ImageView img3, img4;
    private DatabaseReference monitorDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.monitorcontrol_activity);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        switchMonitor = (Switch) findViewById(R.id.switchTimeMonitor);
        switchSensorMonitor = (Switch) findViewById(R.id.switchSensorMonitor);
        img3 = (ImageView) findViewById(R.id.imageView29);
        img4 = (ImageView) findViewById(R.id.imageView30);
        buttonUpdate = (Button) findViewById(R.id.buttonUpdate);
        eText3=(EditText) findViewById(R.id.editText3);
        eText3.setInputType(InputType.TYPE_NULL);
        eText4=(EditText) findViewById(R.id.editText4);
        eText4.setInputType(InputType.TYPE_NULL);
        setSupportActionBar(myToolbar);
        ActionBar lmao = getSupportActionBar();
        lmao.setDisplayHomeAsUpEnabled(true);
        mirrorSerial = getIntent().getStringExtra("mirrorSerial");
        monitorDatabase = FirebaseDatabase.getInstance().getReference("Mirror_Serial_Numbers/"+mirrorSerial+"/Monitor");


        img3.setImageDrawable(getDrawable(R.drawable.timeoff));
        img4.setImageDrawable(getDrawable(R.drawable.computeroff));


        buttonUpdate.setOnClickListener(this);
        switchMonitor.setOnClickListener(this);
        switchSensorMonitor.setOnClickListener(this);
        eText3.setOnClickListener(this);
        eText4.setOnClickListener(this);

    }

    private void updateData(){
        String timerState = Boolean.toString(switchMonitor.isChecked());
        String monitorState = Boolean.toString(switchSensorMonitor.isChecked());
        String timerOn = eText3.getText().toString().trim();
        String timerOff = eText4.getText().toString().trim();

        Monitor monitor = new Monitor(timerState, monitorState, timerOn, timerOff);

        monitorDatabase.setValue(monitor);

        Toast.makeText(this, "Data Uploaded Successfully !", Toast.LENGTH_LONG).show();
    }


    // Button listener action
    @Override
    public void onClick(View v) {
        if(v == buttonUpdate){
            updateData();
        }

        if (v == switchMonitor){
            setImg3(img3, switchMonitor);
        }

        if(v == switchSensorMonitor){
            setImg4(img4, switchSensorMonitor);
        }

        if(v == eText3){
            final Calendar cldr3 = Calendar.getInstance();
            int hour3 = cldr3.get(Calendar.HOUR_OF_DAY);
            int minutes3 = cldr3.get(Calendar.MINUTE);
            // time picker dialog
            picker3 = new TimePickerDialog(MonitorControlScreenActivity.this,
                    new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker tp3, int sHour3, int sMinute3) {
                            eText3.setText(sHour3 + ":" + sMinute3);
                        }
                    }, hour3, minutes3, true);
            picker3.show();
        }

        if(v == eText4){
            final Calendar cldr4 = Calendar.getInstance();
            int hour4 = cldr4.get(Calendar.HOUR_OF_DAY);
            int minutes4 = cldr4.get(Calendar.MINUTE);
            // time picker dialog
            picker4 = new TimePickerDialog(MonitorControlScreenActivity.this,
                    new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker tp4, int sHour4, int sMinute4) {
                            eText4.setText(sHour4 + ":" + sMinute4);
                        }
                    }, hour4, minutes4, true);
            picker4.show();
        }
    }

    private void setImg3(ImageView img3, Switch switchMonitor) {
        if (switchMonitor.isChecked()){
            img3.setImageDrawable(getDrawable(R.drawable.timeon));
        } else {
            img3.setImageDrawable(getDrawable(R.drawable.timeoff));
        }
    }
    private void setImg4(ImageView img4, Switch switchSensorMonitor) {
        if (switchSensorMonitor.isChecked()){
            img4.setImageDrawable(getDrawable(R.drawable.computeron));
        } else {
            img4.setImageDrawable(getDrawable(R.drawable.computeroff));
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        monitorDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                DataSnapshot settingSnapshot = dataSnapshot;
                Monitor monitor = new Monitor("false", "false", "00:00", "00:00");
                monitor = dataSnapshot.getValue(Monitor.class);

                switchMonitor.setChecked(Boolean.parseBoolean(monitor.getTimerState()));
                switchSensorMonitor.setChecked(Boolean.parseBoolean(monitor.getMonitorState()));
                eText3.setText(monitor.getTimeOn());
                eText4.setText(monitor.getTimeOff());
                setImg3(img3, switchMonitor);
                setImg4(img4, switchSensorMonitor);
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