package com.greenproject.ufopamap;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;

import com.greenproject.ufopamap.database.UfmapOpenHelper;
import com.greenproject.ufopamap.dominio.entidades.Cliente;
import com.greenproject.ufopamap.dominio.repositorio.ClienteRepositorio;

public class ActCadCliente extends AppCompatActivity {

    private EditText edtName;
    private EditText edtAdress;
    private EditText edtEmail;
    private EditText edtPhone;


    private ConstraintLayout layoutContentActCadCliente;
    public ClienteRepositorio clienteRepositorio;
    private SQLiteDatabase conexao;
    private UfmapOpenHelper ufmapOpenHelper;
    private Cliente cliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.greenproject.ufopamap.R.layout.act_cad_cliente);
        Toolbar toolbar = (Toolbar) findViewById(com.greenproject.ufopamap.R.id.toolbar);
        setSupportActionBar(toolbar);

        edtName = (EditText)findViewById(com.greenproject.ufopamap.R.id.edtName);
        edtAdress = (EditText)findViewById(com.greenproject.ufopamap.R.id.edtAdress);
        edtEmail = (EditText)findViewById(com.greenproject.ufopamap.R.id.edtEmail);
        edtPhone = (EditText)findViewById(com.greenproject.ufopamap.R.id.edtPhone);

        layoutContentActCadCliente = (ConstraintLayout)findViewById(R.id.layoutContentActCadCliente);

        criarConexao();
    }

    private void criarConexao(){

        try{

            ufmapOpenHelper = new UfmapOpenHelper(this);

            conexao = ufmapOpenHelper.getWritableDatabase();

            Snackbar.make(layoutContentActCadCliente, com.greenproject.ufopamap.R.string.db_message_correct, Snackbar.LENGTH_SHORT)
                    .setAction(com.greenproject.ufopamap.R.string.action_ok, null).show();

            clienteRepositorio = new ClienteRepositorio(conexao);

        } catch (SQLiteException ex){

            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setTitle(com.greenproject.ufopamap.R.string.title_erro);
            dlg.setMessage(ex.getMessage());
            dlg.setNeutralButton(com.greenproject.ufopamap.R.string.action_ok, null);
            dlg.show();

        }
    }

    private void confirmar(){

        cliente = new Cliente();

        if(!validaCampos()){

            try{
                clienteRepositorio.inserir(cliente);
                finish();

            } catch (SQLiteException ex) {

                AlertDialog.Builder dlg = new AlertDialog.Builder(this);
                dlg.setTitle(com.greenproject.ufopamap.R.string.title_erro);
                dlg.setMessage(ex.getMessage());
                dlg.setNeutralButton(com.greenproject.ufopamap.R.string.action_ok, null);
                dlg.show();

            }
        }

    }

    private boolean validaCampos(){

        boolean res;

        String name = edtName.getText().toString();
        String adress = edtAdress.getText().toString();
        String email = edtEmail.getText().toString();
        String phone = edtPhone.getText().toString();

        cliente.name   = name;
        cliente.adress = adress;
        cliente.email  = email;
        cliente.phone  = phone;

        if(res = isCampoVazio(name)){
            edtName.requestFocus();
        }
        else
            if (res = isCampoVazio(adress)){
                edtAdress.requestFocus();
            }
            else
                if (res = !isEmailValidate(email)){
                    edtEmail.requestFocus();
                }
                else
                    if (res = isCampoVazio(phone)){
                        edtPhone.requestFocus();
                    }

        if(res){

            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setTitle(com.greenproject.ufopamap.R.string.title_alert);
            dlg.setMessage(com.greenproject.ufopamap.R.string.message_invalid);
            dlg.setNeutralButton(com.greenproject.ufopamap.R.string.action_ok, null);
            dlg.show();
        }

        return res;
    }

    private boolean isCampoVazio(String valor){
        boolean result = (TextUtils.isEmpty(valor) || valor.trim().isEmpty());

        return result;
    }

    private boolean isEmailValidate(String email){
        boolean result = (!isCampoVazio(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());

        return result;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(com.greenproject.ufopamap.R.menu.menu_act_cad_cliente, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch(id){
            case com.greenproject.ufopamap.R.id.action_ok:
                confirmar();
                //Toast.makeText(this, "Botão Ok Selecionado", Toast.LENGTH_SHORT).show();

                break;

            case com.greenproject.ufopamap.R.id.action_cancel:
                finish();

                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
