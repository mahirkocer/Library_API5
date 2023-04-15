package memo;

public class Datas {
    static String query_getlastid = "select *\n" +
            "from users order by id desc limit 1";

    static String query_getstatus = "select distinct status\n" +
            "from users";

    static String urlDB = "jdbc:mysql://34.230.35.214:3306/library1";
    static String usernameDB = "library1_client";
    static String passDB = "WVF4NdGXCKHeE6VQ";

    static String urlWeb = "http://library1.cydeo.com/login.html";

    static String student = "student55@library";
    static String librarin = "librarian51@library";
    static String pass = "libraryUser";

    static  String urlApi = "http://library1.cydeo.com/rest/v1";

    static String createdName = "mylibDeleteme";
    static String createdmail = "librarian111@library";

    static String token;

}
