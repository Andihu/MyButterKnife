package com.microfountain.butterknifeapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("NonConstantResourceId")
@BindLayoutId(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

    @BindView(R.id.textView)
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyButterKnifeUtil.injectLayoutId(this);
        MyButterKnifeUtil.injectViewId(this);
        MyButterKnifeUtil.injectListener(this);
    }

    @BindClickListener({R.id.textView})
    public void on(View view){
        Toast.makeText(this,"onClick",Toast.LENGTH_SHORT).show();
    }

}