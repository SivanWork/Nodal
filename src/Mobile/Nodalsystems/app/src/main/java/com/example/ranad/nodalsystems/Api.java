package com.example.ranad.nodalsystems;


public class Api {

    public final static String HOST = "http://cellordering.com";

    public final static String LOGIN_API = HOST + "/api/User/Login";
    public final static String ORDER_CREATE = HOST + "/api/Order/Create";
    public final static String BULKORDER_CREATE = HOST + "/api/Order/CreateBulkOrder";
    public final static String USER_CREATE = HOST + "/api/User/CreateUser";
    public final static String USER_UPDATE = HOST + "/api/User/UpdateUser";
    public final static String USER_GETALL = HOST + "/api/User/GetAllUsers";
    public final static String USER_DELETE = HOST + "/api/User/DeleteUser";
    public final static String USER_GET = HOST + "/api/User/GetUser";
    public final static String CUSTOMER_GETALL = HOST + "/api/Customer/GetAllCustomers";
    public final static String CUSTOMER_CREATE = HOST + "/api/Customer/CreateCustomer";
    public final static String FORGOT_PASSWORD = HOST + "/api/User/ForgotPassword";
    public final static String CHANGE_PASSWORD = HOST + "/api/User/ChangePassword";

    public final static String CUSTOMER_UPDATE = HOST + "/api/Customer/UpdateCustomer";

    public final static String CREATE_PRODUCT = HOST + "/api/Product/CreateProduct";
    public final static String GET_PRODUCT = HOST + "/api/Product/GetProduct";
    public final static String UPDATE_PRODUCT = HOST + "/api/Product/UpdateProduct";
    public final static String DELETE_PRODUCT = HOST + "/api/Product/DeleteProduct";
    public final static String GET_ALL_PRODUCT = HOST + "/api/Product/GetAllProducts";


    public final static String GETALL_GROUPTYPES = HOST + "/api/GroupType/GetAllGroupTypes";

    public final static String GETALL_ELEMENT_TYPES = HOST + "/api/GroupElementCode/GetAllGroupElementCodes";

}
