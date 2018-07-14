package com.greenproject.carteiradeclientes2;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ActCadCliente extends AppCompatActivity {

    private EditText edtName;
    private EditText edtAdress;
    private EditText edtEmail;
    private EditText edtPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_cad_cliente);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        edtName = (EditText)findViewById(R.id.edtName);
        edtAdress = (EditText)findViewById(R.id.edtAdress);
        edtEmail = (EditText)findViewById(R.id.edtEmail);
        edtPhone = (EditText)findViewById(R.id.edtPhone);
    }

    private void validaCampos(){

        boolean res;

        String name = edtName.getText().toString();
        String adress = edtAdress.getText().toString();
        String email = edtEmail.getText().toString();
        String phone = edtPhone.getText().toString();

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
            dlg.setTitle(R.string.title_alert);
            dlg.setMessage(R.string.message_invalid);
            dlg.setNeutralButton(R.string.action_ok, null);
            dlg.show();
        }
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
        inflater.inflate(R.menu.menu_act_cad_cliente, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch(id){
            case R.id.action_ok:
                validaCampos();
                //Toast.makeText(this, "Botão Ok Selecionado", Toast.LENGTH_SHORT).show();

                break;

            case R.id.action_cancel:
                finish();

                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
