package com.greenproject.ufopamap.dominio.repositorio;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.greenproject.ufopamap.dominio.entidades.Cliente;

import java.util.ArrayList;
import java.util.List;

public class ClienteRepositorio {

    private SQLiteDatabase conexao;

    public ClienteRepositorio(SQLiteDatabase conexao){
        this.conexao = conexao;
    }

    public void inserir(Cliente cliente){

        ContentValues contentValues = new ContentValues();
        contentValues.put("NAME", cliente.name);
        contentValues.put("ADRESS", cliente.adress);
        contentValues.put("EMAIL", cliente.email);
        contentValues.put("PHONE", cliente.phone);

        conexao.insertOrThrow("CLIENTE", null, contentValues);

    }

    public void excluir(int codigo){

        String[] parameters = new String[1];
        parameters[0] = String.valueOf(codigo);

        conexao.delete("CLIENTE", "CODIGO = ?" , parameters);
    }

    public void alterar(Cliente cliente){

        ContentValues contentValues = new ContentValues();
        contentValues.put("NAME", cliente.name);
        contentValues.put("ADRESS", cliente.adress);
        contentValues.put("EMAIL", cliente.email);
        contentValues.put("PHONE", cliente.phone);

        String[] parameters = new String[1];
        parameters[0] = String.valueOf(cliente.codigo);

        conexao.update("CLIENTE", contentValues, "CODIGO = ?", parameters);

    }

    public List<Cliente> consultar(){

        List<Cliente> clientes = new ArrayList<Cliente>();

        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT CODIGO, NAME, ADRESS, EMAIL, PHONE ");
        sql.append("   FROM CLIENTE");


        @SuppressLint("Recycle") Cursor resultado = conexao.rawQuery(sql.toString(), null);

        if(resultado.getCount() > 0){

            resultado.moveToFirst();

            do {

                Cliente cli = new Cliente();

                cli.codigo    = resultado.getInt(resultado.getColumnIndexOrThrow("CODIGO"));
                cli.name      = resultado.getString(resultado.getColumnIndexOrThrow("NAME"));
                cli.adress    = resultado.getString(resultado.getColumnIndexOrThrow("ADRESS"));
                cli.email     = resultado.getString(resultado.getColumnIndexOrThrow("EMAIL"));
                cli.phone     = resultado.getString(resultado.getColumnIndexOrThrow("PHONE"));

                clientes.add(cli);

            } while(resultado.moveToNext());
        }

        return clientes;
    }

    public Cliente buscarCliente(int codigo){

        Cliente cliente = new Cliente();

        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT CODIGO, NAME, ADRESS, EMAIL, PHONE ");
        sql.append("   FROM CLIENTE ");
        sql.append(" WHERE CODIGO = ? ");

        String[] parameters = new String[1];
        parameters[0] = String.valueOf(codigo);

        @SuppressLint("Recycle") Cursor resultado = conexao.rawQuery(sql.toString(), parameters);

        if(resultado.getCount() > 0){

            resultado.moveToFirst();

                cliente.codigo    = resultado.getInt(resultado.getColumnIndexOrThrow("CODIGO"));
                cliente.name      = resultado.getString(resultado.getColumnIndexOrThrow("NAME"));
                cliente.adress    = resultado.getString(resultado.getColumnIndexOrThrow("ADRESS"));
                cliente.email     = resultado.getString(resultado.getColumnIndexOrThrow("EMAIL"));
                cliente.phone     = resultado.getString(resultado.getColumnIndexOrThrow("PHONE"));

               return cliente;
        }

        return null;
    }
}
