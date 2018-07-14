package com.greenproject.carteiradeclientes2;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.greenproject.carteiradeclientes2.database.UfmapOpenHelper;

public class ActMain extends AppCompatActivity {

    private RecyclerView lstDados;
    private FloatingActionButton fab;
    private ConstraintLayout layoutContentMain;

    private SQLiteDatabase conexao;

    private UfmapOpenHelper ufmapOpenHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        lstDados = (RecyclerView) findViewById(R.id.lstDados);

        layoutContentMain = (ConstraintLayout)findViewById(R.id.layoutContentMain);

        criarConexao();
    }

    private void criarConexao(){

        try{

            ufmapOpenHelper = new UfmapOpenHelper(this);

            conexao = ufmapOpenHelper.getWritableDatabase();

            Snackbar.make(layoutContentMain, R.string.db_message_correct, Snackbar.LENGTH_SHORT)
                    .setAction(R.string.action_ok, null).show();

        } catch (SQLiteException ex){

            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setTitle(R.string.title_erro);
            dlg.setMessage(ex.getMessage());
            dlg.setNeutralButton(R.string.action_ok, null);
            dlg.show();

        }
    }

    public void cadastrar(View view){
        Intent it = new Intent(ActMain.this, ActCadCliente.class);
        startActivity(it);
    }

}
