package com.library.pages;

public class DataPage {
   public static String query_getlastid = "select *\n" +
            "from users order by id desc limit 1";

    public static String query_getstatus = "select distinct status\n" +
            "from users";

    public  static String urlDB = "jdbc:mysql://34.230.35.214:3306/library1";
    public static String usernameDB = "library1_client";
    public static String passDB = "WVF4NdGXCKHeE6VQ";

    public static String urlWeb = "http://library1.cydeo.com/login.html";

    public  static String student = "student55@library";
    public  static String librarin = "librarian51@library";
    public  static String pass = "libraryUser";

    public  static  String urlApi = "http://library1.cydeo.com/rest/v1";

    public static String token;

}
