package com.example.ranad.nodalsystems;

/**
 * Created by Rana D on 1/30/2018.
 */

public class Api {

    public final static String HOST="http://cellordering.com";
    public final static String
            LOGIN_API = HOST+"/api/User/Login",
            FORGOT_PASSWORD = HOST + "/api/User/ForgotPassword?recipientEmail",
            ORDER_CREATE = HOST+"/api/Order/Create",
    BULKORDER_CREATE = HOST+"/api/Order/Create";

}
