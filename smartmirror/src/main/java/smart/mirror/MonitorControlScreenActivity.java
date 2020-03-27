package smart.mirror;

import android.app.TimePickerDialog;
import android.content.Intent;
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

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.Calendar;

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
        img2.setImageDrawable(getDrawable(R.drawable.computeroff));
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
                    img2.setImageDrawable(getDrawable(R.drawable.computeron));
                } else {
                    img2.setImageDrawable(getDrawable(R.drawable.computeroff));
                }
            }
        });


        eText3=(EditText) findViewById(R.id.editText3);
        eText3.setInputType(InputType.TYPE_NULL);
        eText3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
        });

        eText4=(EditText) findViewById(R.id.editText4);
        eText4.setInputType(InputType.TYPE_NULL);
        eText4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
        });
        /*btnGet=(Button)findViewById(R.id.button1);
        btnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvw.setText("Selected Time: "+ eText.getText());
            }
        });*/

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