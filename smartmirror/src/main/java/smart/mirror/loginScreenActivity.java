package smart.mirror;
//Smart Device (Haider Ibrahim, Minh Nguyen , Trung Trinh)

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
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

public class loginScreenActivity extends AppCompatActivity implements  View.OnClickListener {

    private Button buttonSignIn;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private TextView textViewSignUp;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;

    private String input="";
    private UserNameCheck usernamecheck = null;
    private  UserPassswordCheck  checkPassword = null;
    private EditText something;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginscreen_activity);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        ActionBar lmao = getSupportActionBar();



        Button button = (Button) findViewById(R.id.button);
        TextView button14 = (TextView) findViewById(R.id.register);

        if( usernamecheck== null){
            usernamecheck = new UserNameCheck(this);
            checkPassword = new UserPassswordCheck(this);
        }

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                final EditText something = (EditText) findViewById(R.id.editTextEmail);
                 String input = something.getText().toString();
                final EditText something1 = (EditText) findViewById(R.id.editTextPassword);
                String input1 = something1.getText().toString();

                  usernamecheck.checkusername(input);
                 usernamecheck.checkUserIsEmpty(input);
                 usernamecheck.checkUserIsnumber(input);
                 checkPassword.checkPassword(input1);
                 checkPassword.checkPassword(input1);



                if (input.length() == 0) {
                    something.requestFocus();
                } else if (input1.length() == 0) {
                    something1.requestFocus();
                    something1.setError("PLEASE PROVIDE YOUR PASSWORD");
                } else {
                    Intent intent = new Intent(loginScreenActivity.this, WaitingListActivity.class);
                    intent.putExtra("name", input);
                    startActivity(intent);
                }
            }
        });

        button14.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                    Intent intent = new Intent(loginScreenActivity.this, SignUp.class);
                    startActivity(intent);
            }
        });

        firebaseAuth = FirebaseAuth.getInstance();

        /*
        CODE TO SAVE LOGIN WHEN RE-OPEN THE APP AFTER CLOSE THE APPLICATION
        if(firebaseAuth.getCurrentUser()!=null){
            finish();
            startActivity(new Intent(getApplicationContext(), WaitingListActivity.class));
        }*/

        buttonSignIn = (Button) findViewById(R.id.button);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        textViewSignUp = (TextView) findViewById(R.id.register);

        progressDialog = new ProgressDialog(this);

        buttonSignIn.setOnClickListener(loginScreenActivity.this);
        textViewSignUp.setOnClickListener(this);

    }

    private void userLogin(){
        final String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

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

        // if validations are ok
        // we will first show a progressDialog

        progressDialog.setMessage("Logging In....");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();

                        if(task.isSuccessful()){
                            // start the profile activity
                            finish();
                            intentFunction(email);
                        } else {
                            String error = "Incorrect email or password";
                            Toast.makeText(loginScreenActivity.this, error, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    // function to format and pass the id to waitlist activity
    public void intentFunction(String email){
        StringBuilder id = new StringBuilder(email);

        // this is because firebase id cant contain dot , have to change to colon
        int counter;
        for(counter = 0; counter < id.length(); counter++){
            if(id.charAt(counter) == '.'){
                char colon = ':';
                id.setCharAt(counter, ':');
            }
        }

        Intent intent = new Intent(getApplicationContext(), WaitingListActivity.class);
        intent.putExtra("ID", id.toString());
        startActivity(intent);
    }


    @Override
    public void onClick(View view){
        if(view == buttonSignIn){
            userLogin();
        }

        if(view == textViewSignUp){
            finish();
            Intent intent = new Intent(getApplicationContext(), SignUp.class);
            startActivity(intent);
        }
    }



    public String getUserName() {

        return input;
    }



    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    public void onBackPressed(){
        final Context context = this;
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setTitle("EXIT");
        alertDialogBuilder
                .setMessage("Are you sure you want to exit ?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Toast.makeText(getApplicationContext(), "Yes is clicked",
                                Toast.LENGTH_LONG).show();
                        System.exit(0);
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        Toast.makeText(getApplicationContext(), "No is clicked",
                                Toast.LENGTH_LONG).show();
                    }
                });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }


}
