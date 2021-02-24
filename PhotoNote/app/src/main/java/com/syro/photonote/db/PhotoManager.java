package com.syro.photonote.db;

import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.syro.photonote.models.PhotoModel;

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


}
