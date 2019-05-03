package id.ecodev.wikramabogor.ui.HomeScreen;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.gdacciaro.iOSDialog.iOSDialog;
import com.gdacciaro.iOSDialog.iOSDialogBuilder;
import com.gdacciaro.iOSDialog.iOSDialogClickListener;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import id.ecodev.wikramabogor.R;
import id.ecodev.wikramabogor.base.BaseActivity;
import id.ecodev.wikramabogor.helper.PrefManager;
import id.ecodev.wikramabogor.ui.CredentialScreen.CredentialActivity;
import id.ecodev.wikramabogor.ui.FragmentSurveyScreen.SurveyFragment;
import id.ecodev.wikramabogor.ui.FragmentSurveyScreen.WebsiteFragment;
import id.ecodev.wikramabogor.ui.OfferMapsScreen.MapsSurveyActivity;
import id.ecodev.wikramabogor.ui.PrepareSurveyScreen.PrepareActivity;

public class HomeActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    TextView textName,textEmail;
    CircleImageView imageUser;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;
    @BindView(R.id.nav_view)
    NavigationView navigationView;
    GoogleSignInClient mGoogleSignInClient;

    PrefManager prefManager;
    ActionBar actionBar;

    // untuk tombol keluar
    private static final int TIME_INTERVAL = 2000;
    private long mBackPressed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        PrefManager prefManager = new PrefManager(this);
        Intent mainIntent;
        if (!prefManager.getIsLogged())
        {
            mainIntent = new Intent(this, HomeActivity.class);
            startActivity(mainIntent);
            finish();
        }



        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);


        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.server_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(getApplicationContext(),gso);
        actionBar = getSupportActionBar();
        actionBar.setTitle("Penelusuran Potensi");
        actionBar.setElevation(0);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                startActivity(new Intent(getApplicationContext(), PrepareActivity.class));
                Intent intent = new Intent(view.getContext(), PrepareActivity.class);
                startActivity(intent);
            }
        });

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        View hView = navigationView.getHeaderView(0);
        textName   = hView.findViewById(R.id.nav_name);
        textEmail  = hView.findViewById(R.id.nav_email);
        imageUser  = hView.findViewById(R.id.nav_image);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.getMenu().getItem(0).setChecked(true);
        setCurrentUser();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.frame_replace,new SurveyFragment()).commit();
    }


    private void setCurrentUser()
    {
        prefManager = new PrefManager(this);
        textName.setText(prefManager.getUserName());
        textEmail.setText(prefManager.getUserEmail());
        Picasso.get()
                .load(prefManager.getUserImage())
                .into(imageUser);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (mBackPressed + TIME_INTERVAL > System.currentTimeMillis())
            {
                super.onBackPressed();
                return;
            }
            else { Toast.makeText(getBaseContext(), "Tekan back sekali lagi untuk keluar", Toast.LENGTH_SHORT).show(); }

            mBackPressed = System.currentTimeMillis();
        }

    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.home, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId())
//        {
//            case R.id.action_settings :
//                Toast.makeText(this, "Coming soon", Toast.LENGTH_SHORT).show();
//                break;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        Fragment fragment = null;
        switch (id)
        {
            case R.id.nav_survey:
                fragment = new SurveyFragment();
                fragmentChange(fragment);
                actionBar.setTitle("Penelusuran Potensi");
                break;
            case R.id.nav_map:
                startActivity(new Intent(getApplicationContext(), MapsSurveyActivity.class));
                break;
            case R.id.nav_logout:
                alert();
                break;
            case R.id.nav_saved :
                fragment = new WebsiteFragment();
                fragmentChange(fragment);
                actionBar.setTitle("Website");
                break;
//            case R.id.nav_setting:
//                Toast.makeText(this, "Coming soon", Toast.LENGTH_SHORT).show();
//                break;
        }

        DrawerLayout drawer =  findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    void alert(){
        new iOSDialogBuilder(HomeActivity.this)
                .setTitle("Logout")
                .setSubtitle("Yakin ingin keluar dari akun ini!")
                .setBoldPositiveLabel(true)
                .setCancelable(false)
                .setPositiveListener("Ya",new iOSDialogClickListener() {
                    @Override
                    public void onClick(iOSDialog dialog) {
                        signOut();
                    }
                })
                .setNegativeListener("Tidak", new iOSDialogClickListener() {
                    @Override
                    public void onClick(iOSDialog dialog) {
                            dialog.dismiss();
                    }
                }).build().show();
    }

    private void signOut()
    {
        prefManager.resetCurrentUser();
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        startActivity(new Intent(getApplicationContext(), CredentialActivity.class));
                        finish();
                    }
                });
    }

    private void fragmentChange(Fragment fragment)
    {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.frame_replace,fragment).commit();
    }

}
