package smart.mirror;

//Smart Device (Haider Ibrahim, Minh Nguyen , Trung Trinh)
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class CreatenewScreenActivity extends AppCompatActivity {

    ListView myListView;

    int images[] ={R.drawable.sky,R.drawable.mountains,R.drawable.ocean};
    String imageDescription[] = {"The sky","The mountains","The blue ocean"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.createnewcreen_activity);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(myToolbar);
        ActionBar lmao = getSupportActionBar();
        lmao.setDisplayHomeAsUpEnabled(true);

        customAdapter myadapter = new customAdapter();

        myListView = (ListView) findViewById(R.id.listviewimages);

        myListView.setAdapter(myadapter);

        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), DesignLayoutScreenActivity.class);
                intent.putExtra("image",i);
                startActivity(intent);


            }
        });




    }
    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

    public class customAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return images.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int position, View view, ViewGroup viewGroup) {

            View mView = getLayoutInflater().inflate(R.layout.customlayoutscreen,null);

            ImageView mimage = (ImageView) mView.findViewById(R.id.backgroundimagelistview);
            TextView  tv    = (TextView) mView.findViewById(R.id.imageDescription);

            mimage.setImageResource(images[position]);
            tv.setText(imageDescription[position]);



            return mView;
        }
    }

}