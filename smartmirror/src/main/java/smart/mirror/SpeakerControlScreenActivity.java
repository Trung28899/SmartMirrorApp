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

    String timerState;
    String volume;
    String timeSet;

    public Speaker(){

    }

    public Speaker(String timerState, String volume, String timeSet) {
        this.timerState = timerState;
        this.volume = volume;
        this.timeSet = timeSet;
    }

    public String getTimerState() {
        return timerState;
    }

    public String getVolume() {
        return volume;
    }

    public String getTimeSet() {
        return timeSet;
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
    private ImageView img7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.speakercontrol_activity);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        ActionBar lmao = getSupportActionBar();
        lmao.setDisplayHomeAsUpEnabled(true);
        mirrorSerial = getIntent().getStringExtra("mirrorSerial");
        speakerDatabase = FirebaseDatabase.getInstance().getReference("Mirror_Serial_Numbers/"+mirrorSerial+"/Speaker");
        switchTimeSpeaker = (Switch) findViewById(R.id.switchTimeSpeaker);
        img7 = (ImageView) findViewById(R.id.imageView7);
        img7.setImageDrawable(getDrawable(R.drawable.timeoff));
        sBar = (SeekBar) findViewById(R.id.seekBarVolume);
        timePicker = (TimePicker) findViewById(R.id.TimePickerSpeaker);
        tView = (TextView) findViewById(R.id.textView3);
        tView.setText(sBar.getProgress() + "%");
        buttonSubmit = (Button) findViewById(R.id.buttonSubmit);
        switchTimeSpeaker.setOnClickListener(this);
        buttonSubmit.setOnClickListener(this);

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
                tView.setText(pval + "%");
            }
        });
    }


    private void upDateData(){
        String timerState = Boolean.toString(switchTimeSpeaker.isChecked());
        String volume = Integer.toString(sBar.getProgress());
        String time = ""+timePicker.getCurrentHour()+":"+ timePicker.getCurrentMinute();

        //String id = speakerDatabase.push().getKey();

        Speaker speaker = new Speaker(timerState, volume, time);

        speakerDatabase.setValue(speaker);

        Toast.makeText(this, "Data Uploaded Successfully !", Toast.LENGTH_LONG).show();
    }


    @Override
    public void onClick(View v) {
        if(v == buttonSubmit){
            upDateData();
        }

        if(v == switchTimeSpeaker){
            setImg(img7, switchTimeSpeaker);
        }
    }

    private void setImg(ImageView img1, Switch switchMonitor) {
        if (switchMonitor.isChecked()){
            img1.setImageDrawable(getDrawable(R.drawable.timeon));
        } else {
            img1.setImageDrawable(getDrawable(R.drawable.timeoff));
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        // Pulling info from database
        speakerDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                DataSnapshot settingSnapshot = dataSnapshot;
                Speaker speaker = new Speaker("false", "0", "00:00");

                speaker = dataSnapshot.getValue(Speaker.class);

                switchTimeSpeaker.setChecked(Boolean.parseBoolean(speaker.getTimerState()));
                int progress = Integer.parseInt(speaker.getVolume());
                sBar.setProgress(progress);
                tView.setText(speaker.getVolume()+"%");
                setImg(img7, switchTimeSpeaker);
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

