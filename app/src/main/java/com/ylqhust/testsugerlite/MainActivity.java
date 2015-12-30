package com.ylqhust.testsugerlite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.orm.SugarContext;

import java.lang.reflect.InvocationTargetException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SugarContext.init(getApplicationContext());
        SugarInit.CreateTable(new Class[]{User.class,Book.class});
        User user = null;
        Book book = null;
        try {
            user = new User("sss1","ppp1","ssss1");
            book = new Book("12345","计算机操作系统",System.currentTimeMillis(),25);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        user.save();
        book.save();


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        SugarContext.terminate();
    }
}
