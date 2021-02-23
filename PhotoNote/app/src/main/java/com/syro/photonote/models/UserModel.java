package com.syro.photonote.models;

import android.widget.EditText;

public class UserModel
{
    public UserModel() {}
    public String getNameEdit() {
        return nameEdit;
    }

    public void setNameEdit(String nameEdit) {
        this.nameEdit = nameEdit;
    }

    public String getLastNameEdit() {
        return lastNameEdit;
    }

    public void setLastNameEdit(String lastNameEdit) {
        this.lastNameEdit = lastNameEdit;
    }

    public String getEmailEdit() {
        return emailEdit;
    }

    public void setEmailEdit(String emailEdit) {
        this.emailEdit = emailEdit;
    }

    public String getPasswordEdit() {
        return passwordEdit;
    }

    public void setPasswordEdit(String passwordEdit) {
        this.passwordEdit = passwordEdit;
    }

    public String getConfirmPasswordEdit() {
        return confirmPasswordEdit;
    }

    public void setConfirmPasswordEdit(String confirmPasswordEdit) {
        this.confirmPasswordEdit = confirmPasswordEdit;
    }

    private String nameEdit;
    private String lastNameEdit;
    private String emailEdit;
    private String passwordEdit;
    private String confirmPasswordEdit;
}
