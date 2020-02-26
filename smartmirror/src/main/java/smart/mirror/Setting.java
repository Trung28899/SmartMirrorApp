package smart.mirror;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

import java.util.ArrayList;

class Settings{
    String settingId;
    boolean notiState;
    boolean vibrationState;
    ArrayList<String> intArray;

    public Settings(){

    }

    public Settings(String settingId, boolean notiState, boolean vibrationState, ArrayList<String> intArray) {
        this.settingId = settingId;
        this.notiState = notiState;
        this.vibrationState = vibrationState;
        this.intArray = intArray;
    }

    public ArrayList<String> getIntArray() {
        return intArray;
    }

    public String getSettingId() {
        return settingId;
    }

    public boolean getNotiState() {
        return notiState;
    }

    public boolean getVibrationState() {
        return vibrationState;
    }
}

//Smart Device (Haider Ibrahim, Minh Nguyen , Trung Trinh)
public class Setting extends AppCompatActivity implements View.OnClickListener{

    private Switch notificationSwitch;
    private Switch vibrationSwitch;
    private Button doneButton;

    private DatabaseReference settingDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        ActionBar lmao = getSupportActionBar();
        lmao.setDisplayHomeAsUpEnabled(true);

        settingDatabase = FirebaseDatabase.getInstance().getReference("settings");

        notificationSwitch = (Switch) findViewById(R.id.switchNotification);
        vibrationSwitch = (Switch) findViewById(R.id.switchVibration);
        doneButton = (Button) findViewById(R.id.buttonDone);
        CheckSwitches sw = new CheckSwitches(this);
        sw.checkSwitch(notificationSwitch.isChecked());
        sw.checkSwitchf(vibrationSwitch.isChecked());
        doneButton.setOnClickListener(this);
        // Havent Implemented these 2
        notificationSwitch.setOnClickListener(this);
        vibrationSwitch.setOnClickListener(this);

    }
    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

    private void sendUpdate(){
        //String switchStateNoti = Boolean.toString(notificationSwitch.isChecked());
        //String switchStateVibration = Boolean.toString(vibrationSwitch.isChecked());
        // See firebase to see the id
        boolean switchStateNoti = notificationSwitch.isChecked();
        boolean switchStateVibration = vibrationSwitch.isChecked();
        String id = "-LucB-OH8TEep4Wdrgit";
        ArrayList<String> numbers = new ArrayList<String>();
        numbers.add("343");
        numbers.add("4343");
        numbers.add("234");
        numbers.add("767");

        Settings settings = new Settings(id, switchStateNoti, switchStateVibration, numbers);

        settingDatabase.child(id).setValue(settings);

        Toast.makeText(this, "Settings Changed", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View v) {
        if(v == doneButton){
            sendUpdate();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        // Pulling info from database
        settingDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                ArrayList<String> numbers = new ArrayList<String>();
                numbers.add("343");
                numbers.add("4343");
                numbers.add("234");
                numbers.add("767");

                DataSnapshot settingSnapshot = dataSnapshot;
                Settings setting = new Settings("-LucB-OH8TEep4Wdrgit", true, true, numbers);

                setting = dataSnapshot.child("-LucB-OH8TEep4Wdrgit").getValue(Settings.class);

                notificationSwitch.setChecked(setting.getNotiState());
                vibrationSwitch.setChecked(setting.getVibrationState());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), "Something Wrong", Toast.LENGTH_SHORT).show();
            }

        });
    }

    public boolean buttonStatusNotif(){
        return notificationSwitch.isChecked();
    }
    public boolean buttonStatusVibra(){
        return vibrationSwitch.isChecked();
    }



}
