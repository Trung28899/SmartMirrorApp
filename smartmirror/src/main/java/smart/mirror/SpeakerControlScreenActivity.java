package smart.mirror;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
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

import java.sql.Time;
import java.util.Calendar;

class Speaker{

    String id;
    String timeOn;
    String timeOff;
    int volume;

    public Speaker(){

    }

    public Speaker(String id, String timeOn, String timeOff, int volume) {
        this.id = id;
        this.timeOn = timeOn;
        this.timeOff = timeOff;
        this.volume = volume;
    }

    public String getId() {
        return id;
    }

    public String getTimeOn() {
        return timeOn;
    }

    public String getTimeOff() {
        return timeOff;
    }

    public int getVolume() {
        return volume;
    }
}

//Smart Device (Haider Ibrahim, Minh Nguyen , Trung Trinh)
public class SpeakerControlScreenActivity extends AppCompatActivity implements View.OnClickListener{

    private Switch switchBluetooth;
    private Switch switchCable;
    private SeekBar seekBarVolume;
    private TimePicker timePicker;
    private Button buttonSubmit;
    private String mirrorSerial;

    private DatabaseReference speakerDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.speakercontrol_activity);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        ActionBar lmao = getSupportActionBar();
        lmao.setDisplayHomeAsUpEnabled(true);
        mirrorSerial = getIntent().getStringExtra("mirrorSerial");

        speakerDatabase = FirebaseDatabase.getInstance().getReference("Mirror_Serial_Numbers/"+mirrorSerial+"/SpeakerDatabase");

        switchBluetooth = (Switch) findViewById(R.id.switchBluetooth);
        switchCable = (Switch) findViewById(R.id.switchCable);
        seekBarVolume = (SeekBar) findViewById(R.id.seekBarVolume);
        timePicker = (TimePicker) findViewById(R.id.simpleTimePicker);
        buttonSubmit = (Button) findViewById(R.id.buttonSubmit);

        buttonSubmit.setOnClickListener(this);
    }


    private void upDateData(){
        int currentHour = timePicker.getHour();
        int currentMinute = timePicker.getMinute();

        //String id = speakerDatabase.push().getKey();
        String id = "-LuctwHiO7OlJIAP5ljR";

        boolean switchStateBluetooth = switchBluetooth.isChecked();
        boolean switchStateCable = switchCable.isChecked();
        String time = ""+currentHour+":"+currentMinute;
        int volume = seekBarVolume.getProgress();

        Speaker speaker = new Speaker(id, "23:58", "23:59", volume);

        speakerDatabase.child(id).setValue(speaker);

        Toast.makeText(this, "Data Uploaded Successfully !", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onStart() {
        super.onStart();

        // Pulling info from database
        speakerDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                DataSnapshot settingSnapshot = dataSnapshot;
                Speaker speaker = new Speaker("-LuctwHiO7OlJIAP5ljR", "23:51", "23:59", 100);

                speaker = dataSnapshot.child("-LuctwHiO7OlJIAP5ljR").getValue(Speaker.class);

                //switchBluetooth.setChecked(speaker.isBluetoothState());
                //switchCable.setChecked(speaker.isCableState());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), "Something Wrong", Toast.LENGTH_SHORT).show();
            }

        });
    }

    @Override
    public void onClick(View v) {
        if(v == buttonSubmit){
            upDateData();
        }
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
}

