package com.tools.activity.material;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.tools.R;

public class MaterialActivity extends AppCompatActivity {
    private Toolbar mToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material);
        mToolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        mToolbar.setTitle(getString(R.string.app_name));
    }
    public void click(View v){
        switch (v.getId()){
            case R.id.cardList1:
                startActivity(new Intent(this,ToolbarAnimationActivity.class));
                break;
            case R.id.cardList2:
                startActivity(new Intent(this,TabActivity.class));
                break;
            case R.id.cardList3:
                startActivity(new Intent(this,ParallaxScrollingTabsActivity.class));
                break;


        }
    }
}
