package smart.mirror;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

//Smart Device (Haider Ibrahim, Minh Nguyen , Trung Trinh)
class Account{
    String userName;
    String email;
    String loggedinMirror;

    public Account(){

    }

    public Account(String userName, String email, String loggedinMirror) {
        this.userName = userName;
        this.email = email;
        this.loggedinMirror = loggedinMirror;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public String getLoggedinMirror() {
        return loggedinMirror;
    }
}
public class SignUp extends AppCompatActivity implements View.OnClickListener{

    private EditText editTextUserName;
    private Button buttonRegister;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private TextView textViewSignIn;
    private EditText editTextRetypePassword;

    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;

    private DatabaseReference accountDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        ActionBar lmao = getSupportActionBar();
        lmao.setTitle("Sign up");
        lmao.setDisplayHomeAsUpEnabled(true);

        accountDatabase = FirebaseDatabase.getInstance().getReference("Accounts");

        firebaseAuth = FirebaseAuth.getInstance();

        /*
        if(firebaseAuth.getCurrentUser() != null){
            finish();
            startActivity(new Intent(getApplicationContext(), WaitingListActivity.class));
        }*/

        progressDialog = new ProgressDialog(this);

        buttonRegister = (Button) findViewById(R.id.buttonRegister);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        textViewSignIn = (TextView) findViewById(R.id.textViewSignIn);
        editTextRetypePassword = (EditText) findViewById(R.id.editTextRetypePassword);
        editTextUserName = (EditText) findViewById(R.id.editTextUserName);

        buttonRegister.setOnClickListener(this);
        textViewSignIn.setOnClickListener(this);
    }

    private void registerUser(){
        final String email = editTextEmail.getText().toString().trim();
        final String password = editTextPassword.getText().toString().trim();
        final String password1 = editTextPassword.getText().toString();
        final String retypePassword = editTextRetypePassword.getText().toString();

        if(TextUtils.isEmpty(email)){
            // email is empty
            Toast.makeText(this, "Please enter your email", Toast.LENGTH_SHORT).show();
            // Stop the function from executing further
            return;
        }

        if(TextUtils.isEmpty(password)){
            //password is empty
            Toast.makeText(this, "Please enter your Password", Toast.LENGTH_SHORT).show();
            // Stop the function from executing further
            return;
        }

        if(TextUtils.isEmpty(retypePassword)){
            //password is empty
            Toast.makeText(this, "Please retype your Password", Toast.LENGTH_SHORT).show();
            // Stop the function from executing further
            return;
        }

        if(!password1.equals(retypePassword)){
            Toast.makeText(this, "The re-type password didn't match the password entered", Toast.LENGTH_SHORT).show();
            return;
        }

        // if validations are ok
        // we will first show a progressDialog

        progressDialog.setMessage("Registering User...");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(SignUp.this, "User Successfully Registered", Toast.LENGTH_SHORT).show();
                            // finish() to close the activity to go to the new activity
                            finish();
                            sendUpdate();
                        } else {
                            String error = "Something Went Wrong, Please re-Register !";
                            Toast.makeText(SignUp.this, error, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void sendUpdate(){
        //String switchStateNoti = Boolean.toString(notificationSwitch.isChecked());
        //String switchStateVibration = Boolean.toString(vibrationSwitch.isChecked());
        // See firebase to see the id
        final String userName = editTextUserName.getText().toString().trim();
        final String email = editTextEmail.getText().toString().trim();
        final String loggedinMirror = "0000000";
        StringBuilder id = new StringBuilder(email);

        // this is because firebase id cant contain dot , have to change to colon
        int counter;
        for(counter = 0; counter < id.length(); counter++){
            if(id.charAt(counter) == '.'){
                char colon = ':';
                id.setCharAt(counter, ':');
            }
        }

        Account accounts = new Account(userName, email, loggedinMirror);

        accountDatabase.child(id.toString()).setValue(accounts);

        // passing id to the waitlist activity
        Intent intent =new Intent(getApplicationContext(), WaitingListActivity.class);
        intent.putExtra("ID", id.toString());
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        if(view == buttonRegister){
            registerUser();
        }

        if(view == textViewSignIn){
            //Will open login activity here
            startActivity(new Intent(this, loginScreenActivity.class));
        }
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
}