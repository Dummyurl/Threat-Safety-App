package com.example.root.readpermissions.util;

public class PermissionConstants {

    //threat
    public static String PRECISE_LOCATION="precise location";
    public static String DELETE_SD_CONTENTS="delete SD card contents";
    public static String READ_SD_CONTENTS="read the contents of your SD card";
    public static String FIND_ACCOUNTS_ON_DEVICE="find accounts on the device";
    public static String DRAW_OVER_OTHER_APPS="draw over other apps";
    public static String MODIFY_SYSTEM_SETTINGS="modify system settings";
    public static String FIND_ACCOUNTS="find accounts on the device";
    public static String WITHOUT_OWNERS_KNOWLEDGE="add or modify calender events and send email to guests without owners' knowledge";
    public static String READ_CONFIDENTIAL_INFORMATION="read calender events plus confidential information";
    public static String SMART_CARD="Smart card";


    //power consuming
    public static String VIBRATE="control vibration";
    public static String SLEEPING="prevent phone from sleeping";
    public static String RUN_AT_STARTUP="run at startup";
    public static String REORDER_RUNNING_APPS="reorder running apps";


    //security strength
    public static String FINGERPRINT="fingerprint hardware";
    public static String SET_DEVICE_SECURITY="Set device security";
    public static String NETWORK_ACCESS="full network access";
    public static String STORAGE_SPACE="measure storage space";
    public static String SMART_CARD_ENC="Smart card encryption";



    public static String[] threatPermissions={
            PRECISE_LOCATION,
            DELETE_SD_CONTENTS,
            READ_SD_CONTENTS,
            FIND_ACCOUNTS_ON_DEVICE,
            SMART_CARD,
            DRAW_OVER_OTHER_APPS,
            MODIFY_SYSTEM_SETTINGS,
            FIND_ACCOUNTS,
            WITHOUT_OWNERS_KNOWLEDGE,
            READ_CONFIDENTIAL_INFORMATION
    };

    public static String[] securityStrength={
            FINGERPRINT,
            SET_DEVICE_SECURITY,
            NETWORK_ACCESS,
            STORAGE_SPACE,
            SMART_CARD_ENC

    };

    public static String[] powerConsuming={
            VIBRATE,
            SLEEPING,
            RUN_AT_STARTUP,
            STORAGE_SPACE,
            REORDER_RUNNING_APPS
    };



}
