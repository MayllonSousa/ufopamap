package com.greenproject.carteiradeclientes2.dominio.repositorio;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.greenproject.carteiradeclientes2.dominio.entidades.Cliente;

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

        return null;
    }

    public Cliente buscarCliente(int codigo){

        return null;
    }
}
