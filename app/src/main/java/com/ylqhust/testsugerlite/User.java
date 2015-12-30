package com.ylqhust.testsugerlite;

import com.orm.SugarRecord;
import com.orm.dsl.Table;
import com.orm.dsl.Unique;
import com.orm.util.NamingHelper;

import java.lang.reflect.InvocationTargetException;


/**
 * Created by apple on 15/12/30.
 */
@Table
public class User extends SugarRecord {
    public String name;
    @Unique
    public String userId;
    public String headImg;
    public User(){}

    public User(String headImg, String name, String userId) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        this.headImg = headImg;
        this.name = name;
        this.userId = userId;
    }
}
