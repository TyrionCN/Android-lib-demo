package me.tyrion.rxdemo.activity;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import me.tyrion.rxdemo.R;
import me.tyrion.rxdemo.fragment.DbFragment;
import me.tyrion.rxdemo.fragment.RetrofitFragment;
import me.tyrion.rxdemo.fragment.RxFragment;
import me.tyrion.rxdemo.fragment.RxRetrofitFragment;

public class MainActivity extends AppCompatActivity {
    private NavigationView mMainNv;
    private FragmentTransaction mFragmentTransaction;
    private DrawerLayout mMainDl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFragmentTransaction = getSupportFragmentManager().beginTransaction();
        initView();
    }

    private void initView() {
        mMainDl = (DrawerLayout) findViewById(R.id.main_dl);
        mMainNv = (NavigationView) findViewById(R.id.main_nv);
        mMainNv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                mFragmentTransaction = getSupportFragmentManager().beginTransaction();
                switch (item.getItemId()) {
                    case R.id.main_rx_menu:
                        RxFragment rxFragment = new RxFragment();
                        mFragmentTransaction.replace(R.id.main_fragment_fl, rxFragment);
                        break;

                    case R.id.main_retrofit_menu:
                        RetrofitFragment retrofitFragment = new RetrofitFragment();
                        mFragmentTransaction.replace(R.id.main_fragment_fl, retrofitFragment);
                        break;

                    case R.id.main_rxretrofit_menu:
                        RxRetrofitFragment rxRetrofitFragment = new RxRetrofitFragment();
                        mFragmentTransaction.replace(R.id.main_fragment_fl, rxRetrofitFragment);
                        break;

                    case R.id.main_db_menu:
                        DbFragment dbFragment = new DbFragment();
                        mFragmentTransaction.replace(R.id.main_fragment_fl, dbFragment);
                        break;
                }
                mFragmentTransaction.commit();
                mMainDl.closeDrawers();
                return false;
            }
        });
    }

}
