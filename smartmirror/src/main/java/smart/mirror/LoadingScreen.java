package smart.mirror;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

//Smart Device (Haider Ibrahim, Minh Nguyen , Trung Trinh)
public class LoadingScreen extends AppCompatActivity {

    ProgressBar pb;
    TextView tv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_screen);

        pb = (ProgressBar)findViewById(R.id.progressBar);
       ImageView mv = (ImageView) findViewById(R.id.imagelogo11);
         tv = (TextView) findViewById(R.id.text);


    }
    private class logoLuncher  extends Thread{
        @Override
        public void run() {

            try{
                int a =0;
                for(int i=0; a<100; i++){
                    pb.setProgress(a+=10);
                    sleep(3000);
                }
                for(int i=0; a<100; i++){

                    Toast.makeText(LoadingScreen.this, "", Toast.LENGTH_SHORT).show();
                    sleep(1000);
                }
                
            }catch (InterruptedException e)
            {
                e.printStackTrace();

            }

            Intent intent = new Intent(LoadingScreen.this,navigation.class);
            startActivity(intent);
            LoadingScreen.this.finish();
        }
  }
}
