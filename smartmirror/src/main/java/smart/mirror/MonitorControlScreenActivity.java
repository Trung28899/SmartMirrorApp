package smart.mirror;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MonitorControlScreenActivity extends AppCompatActivity implements View.OnClickListener{

    private Switch switchMonitor;
    private Switch switchSensorMonitor;
    private RadioGroup radioGroupLights;
    private Button buttonUpdate;
    private RadioButton chosenButton;
    private String mirrorSerial;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.monitorcontrol_activity);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        ActionBar lmao = getSupportActionBar();
        lmao.setDisplayHomeAsUpEnabled(true);
        mirrorSerial = getIntent().getStringExtra("mirrorSerial");

        switchMonitor = (Switch) findViewById(R.id.switchTimeMonitor);
        switchSensorMonitor = (Switch) findViewById(R.id.switchSensorMonitor);
        final ImageView img1 = (ImageView) findViewById(R.id.imageView29);
        final ImageView img2 = (ImageView) findViewById(R.id.imageView30);
        img1.setImageDrawable(getDrawable(R.drawable.timeoff));
        img2.setImageDrawable(getDrawable(R.drawable.sensoroff));
        buttonUpdate = (Button) findViewById(R.id.buttonUpdate);
        buttonUpdate.setOnClickListener(this);
        switchMonitor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (switchMonitor.isChecked()){
                    img1.setImageDrawable(getDrawable(R.drawable.timeon));
                } else {
                    img1.setImageDrawable(getDrawable(R.drawable.timeoff));
                }
            }
        });

        switchSensorMonitor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (switchSensorMonitor.isChecked()){
                    img2.setImageDrawable(getDrawable(R.drawable.sensoron));
                } else {
                    img2.setImageDrawable(getDrawable(R.drawable.sensoroff));
                }
            }
        });


    }



    @Override
    public void onClick(View v) {
        if(v == buttonUpdate){
            Intent intent = new Intent(MonitorControlScreenActivity.this, navigation.class);
            startActivity(intent);
        }
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
}