package smart.mirror;
//Smart Device (Haider Ibrahim, Minh Nguyen , Trung Trinh)
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

class Mirror{
    String serialNumber;

    public Mirror(){

    }

    public Mirror(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getSerialNumber() {
        return serialNumber;
    }
}

public class WaitingListActivity extends AppCompatActivity implements View.OnClickListener{

    private DatabaseReference accountDatabase;
    private DatabaseReference addSerialDatabase;
    private TextView textViewHello;
    private EditText editTextSerialNo;
    private Button buttonLoadingIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.waitinglistactivity);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        ActionBar lmao = getSupportActionBar();
        lmao.setDisplayHomeAsUpEnabled(true);

        accountDatabase = FirebaseDatabase.getInstance().getReference("Accounts");
        textViewHello = (TextView) findViewById(R.id.textViewHello);
        editTextSerialNo = (EditText) findViewById(R.id.editTextSerialNo);
        buttonLoadingIn = (Button) findViewById(R.id.button28);

        buttonLoadingIn.setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();

        accountDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                DataSnapshot accountSnapshot = dataSnapshot;
                Account account = new Account("Username", "email@gmail.com", "000001");
                String id = getIntent().getStringExtra("ID");
                //String id = "trung3@gmail:com";

                account = dataSnapshot.child(id).getValue(Account.class);

                final String helloText = "Hello " + account.userName + " !!";
                textViewHello.setText(helloText);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        if(v == buttonLoadingIn){
            addSerialDatabase = FirebaseDatabase.getInstance().getReference("Mirror_Serial_Numbers");

                /* CODE TO ADD MIRROR SERIAL NO TO FIREBASE

                String serialNo = editTextSerialNo.getText().toString().trim();
                String idMirror = serialNo;
                Mirror newMirror = new Mirror(serialNo);
                addSerialDatabase.child(idMirror).setValue(newMirror);
                 */
            addSerialDatabase.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    int counter=0;
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        Mirror mirror = snapshot.getValue(Mirror.class);
                        System.out.println(mirror.serialNumber);

                        String input = editTextSerialNo.getText().toString().trim();

                        if(mirror.serialNumber.equals(input)){
                            Intent intent = new Intent(WaitingListActivity.this, navigation.class);
                            startActivity(intent);
                            counter++;
                        }
                    }

                    if(counter == 0 ){
                        Toast.makeText(WaitingListActivity.this, "Invalid Mirror Serial Number", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }


    }
}
