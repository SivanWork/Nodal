package com.example.ranad.nodalsystems;

/**
 * Created by Rana D on 1/30/2018.
 */

public class Api {

    public final static String HOST = "http://cellordering.com";

    public final static String LOGIN_API = HOST + "/api/User/Login";
    public final static String ORDER_CREATE = HOST + "/api/Order/Create";
    public final static String BULKORDER_CREATE = HOST + "/api/Order/Create";
    public final static String USER_CREATE = HOST + "/api/User/CreateUser";
    public final static String USER_UPDATE = HOST + "/api/User/UpdateUser";
    public final static String USER_GETALL = HOST + "/api/User/GetAllUsers";
    public final static String USER_DELETE = HOST + "/api/User/DeleteUser";
    public final static String USER_GET = HOST + "/api/User/GetUser";
    public final static String CUSTOMER_GETALL = HOST + "/api/Customer/GetAllCustomers";
    public final static String CUSTOMER_CREATE = HOST + "/api/Customer/CreateCustomer";
    public final static String GETALL_GROUPTYPES = HOST + "/api/GroupType/GetAllGroupTypes";

    public final static String GETALL_ELEMENT_TYPES = HOST + "/api/GroupElementCode/GetAllGroupElementCodes";

}
