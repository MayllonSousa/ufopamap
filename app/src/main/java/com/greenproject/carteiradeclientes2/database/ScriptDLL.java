package com.greenproject.carteiradeclientes2.database;

public class ScriptDLL {

    public static String getCreateTableCliente(){

        StringBuilder sql= new StringBuilder();

        sql.append("  CREATE TABLE IF NOT EXISTS CLIENTE( ");
        sql.append("        CODIGO INTEGER       PRIMARY KEY AUTOINCREMENT NOT NULL,");
        sql.append("        NAME   VARCHAR (250) NOT NULL DEFAULT (''), ");
        sql.append("        ADRESS VARCHAR (255) NOT NULL DEFAULT (''), ");
        sql.append("        EMAIL  VARCHAR (200) NOT NULL DEFAULT (''), ");
        sql.append("        PHONE  VARCHAR (20)  NOT NULL DEFAULT ('') ) ");

        return sql.toString();
    }
}
