package com.gmail.s8521444.activities;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.gmail.s8521444.R;
import com.gmail.s8521444.adapters.TabsAdapter;
import com.gmail.s8521444.other.AlertHelper;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        final ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
        viewPager.setAdapter(new TabsAdapter(getSupportFragmentManager(), this));
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setBackgroundColor(getResources().getColor(R.color.primary));

        findViewById(R.id.search_button).setOnClickListener(this);
        findViewById(R.id.new_message_button).setOnClickListener(this);
        findViewById(R.id.add_contact_button).setOnClickListener(this);
        findViewById(R.id.menu_button).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.search_button:
                AlertHelper.showAlertDialog(this, R.string.button_pressed, "Search");
                break;
            case R.id.new_message_button:
                AlertHelper.showAlertDialog(this, R.string.button_pressed, "New message");
                break;
            case R.id.add_contact_button:
                AlertHelper.showAlertDialog(this, R.string.button_pressed, "Add contact");
                break;
            case R.id.menu_button:
                AlertHelper.showAlertDialog(this, R.string.button_pressed, "Menu");
                break;
        }
    }

    @Override
    protected int getLayoutResourceIdentifier() {
        return R.layout.activity_main;
    }

    @Override
    protected String getTitleToolBar() {
        return getString(R.string.app_name);
    }

    @Override
    protected boolean getDisplayHomeAsUp() {
        return false;
    }

    @Override
    protected boolean getHomeButtonEnabled() {
        return false;
    }
}
