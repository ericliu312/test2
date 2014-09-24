package com.example.ztestapp;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity
{
    
    private TextView content;
    private int changeView = 9999;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        content = (TextView)findViewById(R.id.content);
        Timer timer = new Timer();  
        timer.scheduleAtFixedRate(new MyTime(this), 1, 60000);  
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    Handler handler = new Handler()
    {

        @Override
        public void handleMessage(Message msg)
        {
            if(msg.what == changeView)
            {
                String days = msg.obj.toString();
                content.setText(days+"");
            }
        }
        
    };
    
    private class MyTime extends TimerTask{  
          
        public MyTime(Context context){  
            
            
        }  
        public void run() {  
              
            Date date = new Date();  
            Calendar calendar = new GregorianCalendar(2014,9,23);  
            long days = (((calendar.getTimeInMillis()-date.getTime())/1000))/86400;  
            Message msg = Message.obtain();
            msg.what = changeView;
            msg.obj = days+"";
            handler.sendMessage(msg);
            
        }  
          
    }  

}
