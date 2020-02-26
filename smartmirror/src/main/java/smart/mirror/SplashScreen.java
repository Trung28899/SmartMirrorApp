package smart.mirror;
//Smart Device (Haider Ibrahim, Minh Nguyen , Trung Trinh)
// Nice Splash Screen
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
//Smart Device (Haider Ibrahim, Minh Nguyen , Trung Trinh)
public class SplashScreen extends AppCompatActivity {


    ProgressBar pb = null;
    ImageView mv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);
        pb = (ProgressBar)findViewById(R.id.progressBar2);
        mv = (ImageView) findViewById(R.id.imagelogo11);

       // getActionBar().hide();
        logoLuncher ll = new logoLuncher();
        ll.start();

    }

    private class logoLuncher  extends Thread{
        @Override
        public void run() {

            try{
                 int a =0;
                 for(int i=0; a<100; i++){
                    pb.setProgress(a+=10);
                    sleep(700);
                }

            }catch (InterruptedException e)
            {
                e.printStackTrace();

            }
            Intent intent = new Intent(SplashScreen.this,loginScreenActivity.class);
            startActivity(intent);
            SplashScreen.this.finish();
        }
    }
}
