package smart.mirror;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

//Smart Device (Haider Ibrahim, Minh Nguyen , Trung Trinh)
public class navigation extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainmenu_activity);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        ActionBar lmao = getSupportActionBar();
        lmao.setDisplayHomeAsUpEnabled(true);
        lmao.setHomeAsUpIndicator(R.drawable.ic_menu);

        firebaseAuth = FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser() == null){
            finish();
            startActivity(new Intent(this, loginScreenActivity.class));
        }

        mDrawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        int id = menuItem.getItemId();
                        Bundle bundle = new Bundle();
                        if (id == R.id.monitor10) {
                            Intent intent = new Intent(navigation.this, CreatenewScreenActivity.class);
                            startActivity(intent);
                        } else if (id == R.id.led10) {
                            Intent intent = new Intent(navigation.this, ControlLedScreenActivity.class);
                            startActivity(intent);
                        } else if (id == R.id.speaker10) {
                            Intent intent = new Intent(navigation.this, SpeakerControlScreenActivity.class);
                            startActivity(intent);
                        } else if (id == R.id.setting10) {
                            Intent intent = new Intent(navigation.this, Setting.class);
                            startActivity(intent);
                        } else if (id == R.id.appbar) {
                            firebaseAuth.signOut();
                            finish();
                            Intent intent = new Intent(navigation.this, loginScreenActivity.class);
                            startActivity(intent);
                        }
                        else if(id == R.id.home){
                            mDrawerLayout.closeDrawer(GravityCompat.START);
                        }
                        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                        drawer.closeDrawer(GravityCompat.START);
                        return true;
                    }
                });
        mDrawerLayout.addDrawerListener(
                new DrawerLayout.DrawerListener() {
                    @Override
                    public void onDrawerSlide(View drawerView, float slideOffset) {
                        // Respond when the drawer's position changes
                    }

                    @Override
                    public void onDrawerOpened(View drawerView) {
                        // Respond when the drawer is opened
                    }

                    @Override
                    public void onDrawerClosed(View drawerView) {
                        // Respond when the drawer is closed
                    }

                    @Override
                    public void onDrawerStateChanged(int newState) {
                        // Respond when the drawer motion state changes
                    }
                }
        );

/*
        ImageView button3 = (ImageView) findViewById(R.id.monitor);
        ImageView button4 = (ImageView) findViewById(R.id.led);
        ImageView button5 = (ImageView) findViewById(R.id.speaker);
        ImageView button6 = (ImageView) findViewById(R.id.setting);
        button3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(navigation.this, CreatenewScreenActivity.class);
                startActivity(intent);
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(navigation.this, ControlLedScreenActivity.class);
                startActivity(intent);
            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(navigation.this, SpeakerControlScreenActivity.class);
                startActivity(intent);
            }
        });
        button6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(navigation.this, Setting.class);
                startActivity(intent);
            }
        });
*/
    }
    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

/*    @Override
public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu, menu);
    return true;}*/

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    /*public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.appbar:
                Intent intent = new Intent(navigation.this, loginScreenActivity.class);
                startActivity(intent);
                return true;
            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);
        }
    }*/
    /*@Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }*/
}