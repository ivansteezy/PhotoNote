package com.syro.photonote.db;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.syro.photonote.models.UserModel;

public class UsersManager
{
    public UsersManager(){}
    public void RegisterNewUser(Activity activity, UserModel userModel)
    {
        SQLiteConnectionHelper conn = new SQLiteConnectionHelper(activity, "db_photoNote", null, 1);
        SQLiteDatabase db = conn.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("ID", 1);;
        values.put("name", userModel.getName());
        values.put("secondName", userModel.getLastName());
        values.put("email", userModel.getEmail());
        values.put("password", userModel.getPassword());

        Long idResult = db.insert("users", "id", values);
        db.close();

        System.out.println("Registered with id: " + idResult);
    }

    public UserModel GetUserByEmail(Activity activity, String email)
    {
        SQLiteConnectionHelper conn = new SQLiteConnectionHelper(activity, "db_photoNote", null, 1);
        SQLiteDatabase db = conn.getReadableDatabase();
        UserModel result = new UserModel();

        String[] params = {email};
        String[] fields = {"id", "name", "secondName", "email", "password"};

        try
        {
            Cursor cursor = db.query("users", fields, "email=?", params, null, null, null);
            cursor.moveToFirst();
            result.setName(cursor.getString(1));
            result.setLastName(cursor.getString(2));
            result.setEmail(cursor.getString(3));
            result.setPassword(cursor.getString(4));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return result;
    }
}
