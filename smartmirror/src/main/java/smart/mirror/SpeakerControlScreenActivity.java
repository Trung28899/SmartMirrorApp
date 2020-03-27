package smart.mirror;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
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

    private Switch switchTimeSpeaker;
    private SeekBar sBar;
    private TimePicker timePicker;
    private Button buttonSubmit;
    private String mirrorSerial;
    private TextView tView;
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
        switchTimeSpeaker = (Switch) findViewById(R.id.switchTimeSpeaker);
        final ImageView img7 = (ImageView) findViewById(R.id.imageView7);
        img7.setImageDrawable(getDrawable(R.drawable.timeoff));
        switchTimeSpeaker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ( switchTimeSpeaker.isChecked()){
                    img7.setImageDrawable(getDrawable(R.drawable.timeon));
                } else {
                    img7.setImageDrawable(getDrawable(R.drawable.timeoff));
                }
            }
        });
        sBar = (SeekBar) findViewById(R.id.seekBarVolume);
        timePicker = (TimePicker) findViewById(R.id.TimePickerSpeaker);
        tView = (TextView) findViewById(R.id.textView3);
        tView.setText(sBar.getProgress() + "/" + sBar.getMax());
        sBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int pval = 0;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                pval = progress;
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //write custom code to on start progress
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                tView.setText(pval + "/" + seekBar.getMax());
            }
        });


        buttonSubmit = (Button) findViewById(R.id.buttonSubmit);
        buttonSubmit.setOnClickListener(this);
    }


    private void upDateData(){
        int currentHour = timePicker.getHour();
        int currentMinute = timePicker.getMinute();

        //String id = speakerDatabase.push().getKey();
        String id = "-LuctwHiO7OlJIAP5ljR";

        boolean switchStateBluetooth = switchTimeSpeaker.isChecked();
        String time = ""+currentHour+":"+currentMinute;
        int volume = sBar.getProgress();

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

