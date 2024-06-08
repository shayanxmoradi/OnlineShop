package org.example.util;

public class ApplicationProperties {


    public static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres?currentSchema=online_shop";
    //    public static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
//    public static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres?currentSchema=public";
    public static final String DB_USERNAME = "postgres";
    public static final String DB_PASSWORD = "12345678";

    private ApplicationProperties() {
    }

}
