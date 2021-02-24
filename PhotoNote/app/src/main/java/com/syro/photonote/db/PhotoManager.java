package com.syro.photonote.db;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;

import com.syro.photonote.models.PhotoModel;

import java.util.ArrayList;
import java.util.List;

public class PhotoManager
{
    public PhotoManager(){}

    public void SavePhotoInDb(Activity activity, PhotoModel photoModel)
    {
        SQLiteConnectionHelper conn = new SQLiteConnectionHelper(activity, "db_photoNote", null, 1);
        SQLiteDatabase db = conn.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("fkUser", 1);
        values.put("photoReference", photoModel.getPhotoReference());
        values.put("cameraModel", photoModel.getCameraModel());
        values.put("iso", photoModel.getIso());
        values.put("shutterSpeed", photoModel.getSpeedShutter());
        values.put("aperture", photoModel.getAperture());
        values.put("lightComments", photoModel.getLightComments());
        values.put("lens", photoModel.getLens());
        values.put("focalLenght", photoModel.getFocalLenght());
        values.put("numberofShoot", photoModel.getNumberOfShoot());
        values.put("photoLocation", photoModel.getPhotoLocation());

        Long idResult = db.insert("photos", "id", values);
        db.close();

        System.out.println("Photo saved with id: " + idResult);
    }

    public List<PhotoModel> GetAllPhotos(Activity activity)
    {
        SQLiteConnectionHelper conn = new SQLiteConnectionHelper(activity, "db_photoNote", null, 1);
        SQLiteDatabase db = conn.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM photos", null);
        List<PhotoModel> results = new ArrayList<PhotoModel>();

        if(cursor.moveToFirst())
        {
            while(!cursor.isAfterLast())
            {
                PhotoModel photoModel = new PhotoModel();
                photoModel.setPhotoReference(cursor.getString(2));
                photoModel.setCameraModel(cursor.getString(3));
                photoModel.setIso(cursor.getString(4));
                photoModel.setSpeedShutter(cursor.getString(5));
                photoModel.setAperture(cursor.getString(6));
                photoModel.setLightComments(cursor.getString(7));
                photoModel.setLens(cursor.getString(8));
                photoModel.setFocalLenght(cursor.getString(9));
                photoModel.setNumberOfShoot(cursor.getString(10));
                photoModel.setPhotoLocation(cursor.getString(11));

                results.add(photoModel);
                cursor.moveToNext();
            }
        }

        return results;
    }

    public PhotoModel GetPhotoById(int id)
    {
        return new PhotoModel();
    }
}
