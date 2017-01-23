package com.tools.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewStub;
import android.widget.TextView;
import android.widget.Toast;

import com.tools.R;

public class ViewStubActivity extends AppCompatActivity {

    private View mViewById;
    private TextView mTv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_stub);
    }
    public void click(View view){
        ViewStub viewStub = (ViewStub) findViewById(R.id.empt_stub_view);
        if (mViewById==null){
            mViewById = viewStub.inflate();

            mTv = (TextView) mViewById.findViewById(R.id.tv);
            Toast.makeText(this, ""+ mTv.getText(), Toast.LENGTH_SHORT).show();
        }else if(mViewById.getVisibility()==View.VISIBLE){
            mViewById.setVisibility(View.GONE);
        }else{
            mViewById.setVisibility(View.VISIBLE);
        }
    }
}
