package com.macardo.servicedemo;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button startBtn,stopBtn,bindBtn,unbindBtn;

    MyService.MyBinder myBinder;

    //在Activity与Service建立关联和解除关联的时候调用
    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            //实例化Service的内部类myBinder
            //通过向下转型得到了MyBinder的实例
            myBinder = (MyService.MyBinder) service;
            //在Activity调用Service类的方法
            myBinder.service_connect_Activity();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            //在Activity与Service解除关联的时候调用
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startBtn = findViewById(R.id.startBtn);
        stopBtn = findViewById(R.id.stopBtn);
        bindBtn = findViewById(R.id.bindBtn);
        unbindBtn = findViewById(R.id.unbindBtn);
        startBtn.setOnClickListener(this);
        stopBtn.setOnClickListener(this);
        bindBtn.setOnClickListener(this);
        unbindBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.startBtn:
                Intent startIntent = new Intent(this,MyService.class);//构建启动Myservice的Intent
                startService(startIntent);//调用startService()传入startIntent来启动MyService
                break;
            case R.id.stopBtn:
                Intent stopIntent = new Intent(this,MyService.class);//构建停止Myservice的Intent
                stopService(stopIntent);//调用stopService()传入stopIntent来停止MyService
                break;
            case R.id.bindBtn:
                Intent bindIntent = new Intent(this, MyService.class);
                bindService(bindIntent,serviceConnection,BIND_AUTO_CREATE);
                break;
            case R.id.unbindBtn:
                unbindService(serviceConnection);
                break;
            default:
                break;
        }
    }
}
