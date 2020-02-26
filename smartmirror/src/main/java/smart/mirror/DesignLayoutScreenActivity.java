package smart.mirror;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;

import android.content.ClipData;
import android.graphics.drawable.Drawable;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View.OnTouchListener;
import android.widget.LinearLayout;
import android.widget.Toast;

import static java.lang.Thread.sleep;

class Monitor{
    int row;
    int column;
    String moduleName;

    public Monitor(){

    }

    public Monitor(int row, int column, String moduleName) {
        this.row = row;
        this.column = column;
        this.moduleName = moduleName;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public String getModuleName() {
        return moduleName;
    }
}

public class DesignLayoutScreenActivity extends AppCompatActivity {

    int images2[] = {R.drawable.motion,
            R.drawable.music,
            R.drawable.clock,
            R.drawable.news,
            R.drawable.pokemen,
    };

    int position = 0;
    int images[] ={R.drawable.sky,R.drawable.mountains,R.drawable.ocean};
    String[] store = new String[0];

    String imageDescriptionModules[] = {
            "Motion and gesture dection module",
            "Play your favourite music",
            "Display time of date as digital or analog clock",
            "Learn more about the world around",
            "Display random pokeman and their stats",

    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_design_layout_screen);

        int imageIndex = getIntent().getIntExtra("image", 0);
        GridLayout gl = (GridLayout) findViewById(R.id.gridlayout);




        ImageView mv = findViewById(R.id.backgroundimagedesginlayout);

        mv.setImageResource(images[imageIndex]);
        ImageView im =(ImageView) findViewById(R.id.motionmodule);


        //Create  a touch listener for each module image
        findViewById(R.id.motionmodule).setOnTouchListener(new MyTouchListener());

        findViewById(R.id.musicmodule).setOnTouchListener(new MyTouchListener());
        findViewById(R.id.clockmodule).setOnTouchListener(new MyTouchListener());
        findViewById(R.id.googleassistancemodule).setOnTouchListener(new MyTouchListener());
        findViewById(R.id.complimentmodule).setOnTouchListener(new MyTouchListener());

        //Create a drag listener for first row
        findViewById(R.id.topleft).setOnDragListener(new MyDragListener());
        findViewById(R.id.topleft1).setOnDragListener(new MyDragListener());
        findViewById(R.id.topleft3).setOnDragListener(new MyDragListener());
        findViewById(R.id.topleft4).setOnDragListener(new MyDragListener());

        ///
        findViewById(R.id.topleftmodulebox).setOnDragListener(new MyDragListener());
        findViewById(R.id.toprightthirdmodulebox).setOnDragListener(new MyDragListener());
        findViewById(R.id.toprightsecondmodulebox).setOnDragListener(new MyDragListener());
        findViewById(R.id.topmiddlemodulebox).setOnDragListener(new MyDragListener());
        findViewById(R.id.toprightforthmodulebox).setOnDragListener(new MyDragListener());

        ///Create a drag listener for the second row
        findViewById(R.id.middletopleft1).setOnDragListener(new MyDragListener());
        findViewById(R.id.middletopleft2).setOnDragListener(new MyDragListener());
        findViewById(R.id.middletopleft3).setOnDragListener(new MyDragListener());
        findViewById(R.id.middletopleft4).setOnDragListener(new MyDragListener());
        findViewById(R.id.middletopleft5).setOnDragListener(new MyDragListener());


        findViewById(R.id.secondmiddletopleft1).setOnDragListener(new MyDragListener());
        findViewById(R.id.secondmiddletopleft2).setOnDragListener(new MyDragListener());
        findViewById(R.id.secondmiddletopleft3).setOnDragListener(new MyDragListener());
        findViewById(R.id.secondmiddletopleft4).setOnDragListener(new MyDragListener());


        findViewById(R.id.thirdmiddletopleft1).setOnDragListener(new MyDragListener());
        findViewById(R.id.thirdmiddletopleft2).setOnDragListener(new MyDragListener());
        findViewById(R.id.thirdmiddletopleft3).setOnDragListener(new MyDragListener());
        findViewById(R.id.thirdmiddletopleft4).setOnDragListener(new MyDragListener());


        findViewById(R.id.fourmiddletopleft1).setOnDragListener(new MyDragListener());
        findViewById(R.id.fourmiddletopleft2).setOnDragListener(new MyDragListener());
        findViewById(R.id.fourmiddletopleft3).setOnDragListener(new MyDragListener());
        findViewById(R.id.fourmiddletopleft4).setOnDragListener(new MyDragListener());


        findViewById(R.id.fivemiddletopleft1).setOnDragListener(new MyDragListener());
        findViewById(R.id.fivemiddletopleft2).setOnDragListener(new MyDragListener());
        findViewById(R.id.fivemiddletopleft3).setOnDragListener(new MyDragListener());
        findViewById(R.id.fivemiddletopleft4).setOnDragListener(new MyDragListener());


        Button btn = (Button) findViewById(R.id.submitnewscreen);
        ImageView view = (ImageView) findViewById(R.id.information);


        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder1 = new AlertDialog.Builder(DesignLayoutScreenActivity.this);
                builder1.setCancelable(true);
                builder1.setTitle("From left to right");

                String imageDescriptionModules[] = {
                        "Motion and gesture dection module",
                        "Play your favourite music",
                        "Display the time",
                        "Learn more about the world around",
                        "Display random pokeman and their stats",

                };
                builder1.setItems(imageDescriptionModules, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0: //motion module
                            case 1: //music module
                            case 2: //clock module
                            case 3: //learning module
                            case 4: // pokeman module
                        }
                    }
                });

                builder1.setPositiveButton(
                        "Exist",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert11 = builder1.create();
                alert11.show();



            }
        });


        btn.setOnClickListener( new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Toast.makeText(DesignLayoutScreenActivity.this, "Monitor View Successfully Sent", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(DesignLayoutScreenActivity.this, navigation.class);
                startActivity(intent);
                finish();
            }
        });
    }
    private final class MyTouchListener implements OnTouchListener {
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                ClipData data = ClipData.newPlainText("", "dsdadasd");
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(
                        view);
                view.startDrag(data, shadowBuilder, view, 0);
                view.setVisibility(View.VISIBLE);
                return true;
            } else {
                return false;
            }

        }
    }

    class MyDragListener implements View.OnDragListener {
        Drawable enterShape = getResources().getDrawable(
                R.drawable.ic_launcher_background);
        Drawable normalShape = getResources().getDrawable(R.drawable.ic_launcher_background);

        @Override
        public boolean onDrag(View v, DragEvent event) {
            int action = event.getAction();
            switch (event.getAction()) {
                case DragEvent.ACTION_DRAG_STARTED:
                    // do nothing
                    break;
                case DragEvent.ACTION_DRAG_ENTERED:



                    v.setBackgroundDrawable(enterShape);
                    break;
                case DragEvent.ACTION_DRAG_EXITED:

                   // if((v.getX()==0 )&&(v.getY()>24 && v.getY()<27))
                  //  {
                   //     Log.d("Action drop","col 0 and row 0");
                  //  }

                    v.setBackgroundDrawable(normalShape);
                    break;
                case DragEvent.ACTION_DROP:
                    // Dropped, reassign View to ViewGroup

                    // Log.d("drop  the X value :", String.valueOf(v.getX()));
                   // Log.d("drop : the Y value :", String.valueOf(v.getY()));
                    View view = (View) event.getLocalState();
                    ViewGroup owner = (ViewGroup) view.getParent();
                    owner.removeView(view);
                    LinearLayout container = (LinearLayout) v;
                    Log.d("drop: the X value :", String.valueOf(v.getX()));
                    Log.d("drop: the Y value :", String.valueOf(v.getY()));
                    container.addView(view);
                    Log.d("Name: ",GetModuleName(view.getId()));

                    int col;
                    int row;
                    String modulename;

                    if((v.getX()==0 &&v.getY()>24 && v.getY()<27))
                    {

                        col  =0;
                        row = 0;
                        modulename =GetModuleName(v.getId());

                        //Log.d("id", String.valueOf(view.getId()));
                        Log.d("Action drop","col 0 and row 0");
                        Log.d("drop: the X value :", String.valueOf(v.getX()));
                        Log.d("drop: the Y value :", String.valueOf(v.getY()));
                    }

                    else if((v.getX()==131.0) && (v.getY()>24 && v.getY()<27))
                    {

                        col  =1;
                        row = 0;
                        modulename =GetModuleName(v.getId());
                       // Log.d("id", GetModuleName(view.getId()));
                        Log.d("Action drop","col 1 and row 0");
                        Log.d("drop: the X value :", String.valueOf(v.getX()));
                        Log.d("drop: the Y value :", String.valueOf(v.getY()));
                    }
                    else if((v.getX()==262.0) && (v.getY()>24 && v.getY()<27))
                    {
                        col  =2;
                        row = 0;
                        modulename =GetModuleName(v.getId());
                       // Log.d("id", GetModuleName(view.getId()));
                        Log.d("Action drop","col 2 and row 0");
                        Log.d("drop: the X value :", String.valueOf(v.getX()));
                        Log.d("drop: the Y value :", String.valueOf(v.getY()));
                    }

                    else if((v.getX()==393.0) && (v.getY()>24 && v.getY()<27))
                    {
                      //  Log.d("id", GetModuleName(view.getId()));
                    Log.d("Action drop","col 3 and row 0");
                    Log.d("drop: the X value :", String.valueOf(v.getX()));
                    Log.d("drop: the Y value :", String.valueOf(v.getY()));
                     }


                    ////////////////////////// second row \\\\\\\\\\\\\\\\\\\\\\\\\\\\
                    else if((v.getX()==0 &&v.getY()>156&& v.getY()<158))
                    {
                       // Log.d("id", GetModuleName(view.getId()));

                        Log.d("Action drop","col 0 and row 1");
                        Log.d("drop: the X value :", String.valueOf(v.getX()));
                        Log.d("drop: the Y value :", String.valueOf(v.getY()));
                    }

                    else if((v.getX()==131.0 &&v.getY()>156&& v.getY()<158))
                    {
                       /// Log.d("id", GetModuleName(view.getId()));
                        Log.d("Action drop","col 1 and row 1");
                        Log.d("drop: the X value :", String.valueOf(v.getX()));
                        Log.d("drop: the Y value :", String.valueOf(v.getY()));
                    }
                    else if((v.getX()==262.0 &&v.getY()>156&& v.getY()<158))
                    {
                       // Log.d("id", GetModuleName(view.getId()));
                        Log.d("Action drop","col 2 and row 1");
                        Log.d("drop: the X value :", String.valueOf(v.getX()));
                        Log.d("drop: the Y value :", String.valueOf(v.getY()));
                    }
                    else if((v.getX()==393.0 &&v.getY()>156&& v.getY()<158))
                    {
                       // Log.d("id", GetModuleName(view.getId()));
                        Log.d("Action drop","col 3 and row 1");
                        Log.d("drop: the X value :", String.valueOf(v.getX()));
                        Log.d("drop: the Y value :", String.valueOf(v.getY()));
                    }

                    ///////////////////// Third row \\\\\\\\\\\\\\\\\\\\\\\\\\\

                    else if((v.getX()==0 &&v.getY()>287.0&& v.getY()<289))
                    {
                       // Log.d("id", GetModuleName(view.getId()));
                        Log.d("Action drop","col 0 and row 2");
                        Log.d("drop: the X value :", String.valueOf(v.getX()));
                        Log.d("drop: the Y value :", String.valueOf(v.getY()));
                    }

                    else if((v.getX()==131.0 && v.getY()>287.0&& v.getY()<289))
                    {
                       // Log.d("id", GetModuleName(view.getId()));
                        Log.d("Action drop","col 1 and row 2");
                        Log.d("drop: the X value :", String.valueOf(v.getX()));
                        Log.d("drop: the Y value :", String.valueOf(v.getY()));
                    }
                    else if((v.getX()==262.0 && v.getY()>287.0&& v.getY()<289))
                    {
                        //Log.d("id", String.valueOf(v.getId()));
                        Log.d("Action drop","col 2 and row 2");
                        Log.d("drop: the X value :", String.valueOf(v.getX()));
                        Log.d("drop: the Y value :", String.valueOf(v.getY()));
                    }
                    else if((v.getX()==393.0 &&v.getY()>287.0&& v.getY()<289))
                    {
                        Log.d("id", String.valueOf(v.getId()));
                        Log.d("Action drop","col 3 and row 2");
                        Log.d("drop: the X value :", String.valueOf(v.getX()));
                        Log.d("drop: the Y value :", String.valueOf(v.getY()));
                    }

                    //////////////////////////fourth row \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
                    else if((v.getX()==0 &&v.getY()==419.0))
                    {

                        Log.d("Action drop","col 0 and row 3");
                        Log.d("drop: the X value :", String.valueOf(v.getX()));
                        Log.d("drop: the Y value :", String.valueOf(v.getY()));
                    }

                    else if((v.getX()==131.0 && v.getY()==419.0))
                    {

                        Log.d("Action drop","col 1 and row 3");
                        Log.d("drop: the X value :", String.valueOf(v.getX()));
                        Log.d("drop: the Y value :", String.valueOf(v.getY()));
                    }
                    else if((v.getX()==262.0 && v.getY()==419.0))
                    {

                        Log.d("Action drop","col 2 and row 3");
                        Log.d("drop: the X value :", String.valueOf(v.getX()));
                        Log.d("drop: the Y value :", String.valueOf(v.getY()));
                    }
                    else if((v.getX()==393.0 && v.getY()==419.0))
                    {

                        Log.d("Action drop","col 3 and row 3");
                        Log.d("drop: the X value :", String.valueOf(v.getX()));
                        Log.d("drop: the Y value :", String.valueOf(v.getY()));
                    }

                    /////////////////////////// Fifth row \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

                    else if((v.getX()==0 &&v.getY()==550.0))
                    {

                        Log.d("Action drop","col 0 and row 5");
                        Log.d("drop: the X value :", String.valueOf(v.getX()));
                        Log.d("drop: the Y value :", String.valueOf(v.getY()));
                    }

                    else if((v.getX()==131.0 && v.getY()==550.0))
                    {

                        Log.d("Action drop","col 1 and row 5");
                        Log.d("drop: the X value :", String.valueOf(v.getX()));
                        Log.d("drop: the Y value :", String.valueOf(v.getY()));
                    }
                    else if((v.getX()==262.0 && v.getY()==550.0))
                    {

                        Log.d("Action drop","col 2 and row 5");
                        Log.d("drop: the X value :", String.valueOf(v.getX()));
                        Log.d("drop: the Y value :", String.valueOf(v.getY()));
                    }
                    else if((v.getX()==393.0 && v.getY()==550.0))
                    {

                        Log.d("Action drop","col 3 and row 5");
                        Log.d("drop: the X value :", String.valueOf(v.getX()));
                        Log.d("drop: the Y value :", String.valueOf(v.getY()));
                    }

                   //////////////////////////sixth row \\\\\\\\\\\\\\\\\\\\\\\\\\\

                    else if((v.getX()==0 &&v.getY()== 681.0))
                    {

                        Log.d("Action drop","col 0 and row 6");
                        Log.d("drop: the X value :", String.valueOf(v.getX()));
                        Log.d("drop: the Y value :", String.valueOf(v.getY()));
                    }

                    else if((v.getX()==131.0 && v.getY() == 681.0))
                    {

                        Log.d("Action drop","col 1 and row 6");
                        Log.d("drop: the X value :", String.valueOf(v.getX()));
                        Log.d("drop: the Y value :", String.valueOf(v.getY()));
                    }
                    else if((v.getX()==262.0 && v.getY() ==  681.0))
                    {

                        Log.d("Action drop","col 2 and row 6");
                        Log.d("drop: the X value :", String.valueOf(v.getX()));
                        Log.d("drop: the Y value :", String.valueOf(v.getY()));
                    }
                    else if((v.getX()==393.0 && v.getY() == 681.0))
                    {

                        Log.d("Action drop","col 3 and row 6");
                        Log.d("drop: the X value :", String.valueOf(v.getX()));
                        Log.d("drop: the Y value :", String.valueOf(v.getY()));
                    }


















                    view.setVisibility(View.VISIBLE);
                    break;
                case DragEvent.ACTION_DRAG_ENDED:


                    v.getId();
                    v.setBackgroundDrawable(normalShape);
                default:
                    break;
            }
            return true;
        }
    }

public String GetModuleName(int id)
{

        if(id==2131230871){
            return "motion sensor module";
        }
        else if(id==2131230875){
            return "music  module";

        }
        else if(id==2131230776){
            return "clock module";

        }
        else if(id==2131230823 ){
            return "motion module";

        }
        else if(id==2131230778){
            return "compliment module";

        }
return "";
}


}
