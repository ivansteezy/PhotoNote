package com.syro.photonote;

public class PhotoModel {
    public PhotoModel(){}

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

    public String getPhotoLocation() {
        return photoLocation;
    }

    public void setPhotoLocation(String photoLocation) {
        this.photoLocation = photoLocation;
    }

    private String photoReference;
    private String cameraModel;
    private String iso;
    private String speedShutter;
    private String aperture;
    private String lightComments;
    private String lens;
    private String focalLenght;
    private String numberOfShoot;
    private String photoLocation;
}
