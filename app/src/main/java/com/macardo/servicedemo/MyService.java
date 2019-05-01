package com.macardo.servicedemo;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {

    private MyBinder myBinder = new MyBinder();
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("MyService","onCreate()");
        //构建延迟打开的PendingIntent对象
        Intent notificationIntent = new Intent(this,MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,notificationIntent,0);
        //新建Builer对象
        Notification.Builder builder = new Notification.Builder(this);
        builder.setContentTitle("前台服务通知的标题");//设置通知的标题
        builder.setContentText("前台服务通知的内容");//设置通知的内容
        builder.setSmallIcon(R.mipmap.ic_launcher);//设置通知的图标
        builder.setContentIntent(pendingIntent);//设置点击通知后的操作
        Notification notification = builder.getNotification();//将builder对象变成notification对象
        startForeground(1,notification);//将服务变为前台服务，显示自状态栏
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("MyService","onStartCommand()");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("MyService","onDestroy()");

    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d("MyService","onBind()");
        return myBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d("MyService","onUnbind()");
        return super.onUnbind(intent);
    }

    //新建一个子类继承自Binder类
    class MyBinder extends Binder {
        public void service_connect_Activity() {
            Log.d("MyService","Service关联了Activity,并在Activity执行了Service的方法");
        }
    }
}
