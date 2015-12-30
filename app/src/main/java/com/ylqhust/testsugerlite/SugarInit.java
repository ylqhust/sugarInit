package com.ylqhust.testsugerlite;

import android.database.sqlite.SQLiteDatabase;

import com.orm.SugarContext;
import com.orm.SugarDb;
import com.orm.util.NamingHelper;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static com.orm.SugarContext.getSugarContext;

/**
 * Created by apple on 15/12/30.
 * 由于sugarOrm自己不会创建数据库的表结构，因此写出的这个类，用于创建一般常见的数据库的
 * 表结构
 * 仅支持string，int，long三中类型
 */
public class SugarInit {

    public static final String VARCHAR_TYPE = " varcher(255)";
    public static final String VARCHAR_ORM_TYPE = "java.lang.String";
    public static final String BIGINT_TYPE = " bigint";
    public static final String BIGINT_ORM_TYPE = "long";
    public static final String BLOB_TYPE = " blob";
    public static final String INTEGER_TYPE = " int";
    public static final String INTEGER_ORM_TYPE = "int";
    public static final String COMMA_SEP = ",";

    /**
     * 为每一个model创建一个数据库的表
     * @param models
     */
    public static void CreateTable(Class[] models) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        for(Class model:models){
            StringBuilder createTable = new StringBuilder();
            createTable.append("CREATE TABLE ");
            createTable.append(getTableName(model));
            createTable.append(" (");
            createTable.append("ID"+" INTEGER PRIMARY KEY AUTOINCREMENT"+COMMA_SEP);
            Field[] columns = model.getFields();
            for(Field column:columns){
                String columnName = NamingHelper.toSQLName(column);
                createTable.append(columnName);
                createTable.append(getColumnType(column));
                createTable.append(COMMA_SEP);
            }
            createTable.deleteCharAt(createTable.length()-1);
            createTable.append(")");
            System.out.println(createTable.toString());
            CreateTable(createTable.toString());
        }
    }

    private static String getColumnType(Field column) {
        System.out.println(column.getType().getName());
        if (BIGINT_ORM_TYPE.equals(column.getType().getName()))
            return BIGINT_TYPE;
        if (INTEGER_ORM_TYPE.equals(column.getType().getName()))
            return INTEGER_TYPE;
        return VARCHAR_TYPE;
    }

    private static String getTableName(Class model) {
        return NamingHelper.toSQLName(model);
    }

    private static void CreateTable(String createTable) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method getSugarDb = SugarContext.class.getDeclaredMethod("getSugarDb", new Class[]{});
        getSugarDb.setAccessible(true);
        SugarDb db = (SugarDb) getSugarDb.invoke(getSugarContext());
        SQLiteDatabase sqLiteDatabase = db.getDB();
        try{
            sqLiteDatabase.execSQL(createTable);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
