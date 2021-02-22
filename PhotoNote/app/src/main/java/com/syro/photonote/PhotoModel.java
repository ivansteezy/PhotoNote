package com.syro.photonote;

public class PhotoModel {
    public PhotoModel(String photoReference, String cameraModel, String cameraFilm,    String iso,
                      String speedShutter,   String aperture,    String lightComments, String lens,
                      String focalLenght,    String numberOfShoot)
    {
        this.photoReference = photoReference;
        this.cameraModel = cameraModel;
        this.cameraFilm = cameraFilm;
        this.iso = iso;
        this.speedShutter = speedShutter;
        this.aperture = aperture;
        this.lightComments = lightComments;
        this.lens = lens;
        this.focalLenght = focalLenght;
        this.numberOfShoot = numberOfShoot;
    }

    public String getPhotoReference() {
        return photoReference;
    }

    public void setPhotoReference(String photoReference) {
        this.photoReference = photoReference;
    }

    public String getCameraModel() {
        return cameraModel;
    }

    public void setCameraModel(String cameraModel) {
        this.cameraModel = cameraModel;
    }

    public String getCameraFilm() {
        return cameraFilm;
    }

    public void setCameraFilm(String cameraFilm) {
        this.cameraFilm = cameraFilm;
    }

    public String getIso() {
        return iso;
    }

    public void setIso(String iso) {
        this.iso = iso;
    }

    public String getSpeedShutter() {
        return speedShutter;
    }

    public void setSpeedShutter(String speedShutter) {
        this.speedShutter = speedShutter;
    }

    public String getAperture() {
        return aperture;
    }

    public void setAperture(String aperture) {
        this.aperture = aperture;
    }

    public String getLightComments() {
        return lightComments;
    }

    public void setLightComments(String lightComments) {
        this.lightComments = lightComments;
    }

    public String getLens() {
        return lens;
    }

    public void setLens(String lens) {
        this.lens = lens;
    }

    public String getFocalLenght() {
        return focalLenght;
    }

    public void setFocalLenght(String focalLenght) {
        this.focalLenght = focalLenght;
    }

    public String getNumberOfShoot() {
        return numberOfShoot;
    }

    public void setNumberOfShoot(String numberOfShoot) {
        this.numberOfShoot = numberOfShoot;
    }

    String photoReference;
    String cameraModel;
    String cameraFilm;
    String iso;
    String speedShutter;
    String aperture;
    String lightComments;
    String lens;
    String focalLenght;
    String numberOfShoot;
}
