package com.example.root.readpermissions.model;

import android.graphics.drawable.Drawable;

public class App {
    private String name;
    private Drawable photo;
    private String package_name;
    private String[] permissions;

    public App(String name, Drawable photo, String package_name, String[] permissions) {
        this.name = name;
        this.photo = photo;
        this.package_name = package_name;
        this.permissions = permissions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Drawable getPhoto() {
        return photo;
    }

    public void setPhoto(Drawable photo) {
        this.photo = photo;
    }

    public String getPackage_name() {
        return package_name;
    }

    public void setPackage_name(String package_name) {
        this.package_name = package_name;
    }

    public String[] getPermissions() {
        return permissions;
    }

    public void setPermissions(String[] permissions) {
        this.permissions = permissions;
    }
}
