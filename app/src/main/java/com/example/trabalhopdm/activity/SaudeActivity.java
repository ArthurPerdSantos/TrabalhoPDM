package com.example.trabalhopdm.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import com.example.trabalhopdm.DataBase.DAOBatimentos;
import com.example.trabalhopdm.DataBase.DAOPressao;
import com.example.trabalhopdm.DataBase.DAOUsuario;
import com.example.trabalhopdm.DataBase.DAOVacina;
import com.example.trabalhopdm.R;
import com.example.trabalhopdm.utils.RetornoQuerys;
import com.google.firebase.auth.FirebaseAuth;

import java.util.HashMap;
import java.util.List;

public class SaudeActivity extends AppCompatActivity {

    private EditText pressao, batimento, imc, vacinas;
    private FirebaseAuth mAuth;
    private DAOPressao dao_pressao;
    private DAOBatimentos dao_batimentos;
    private DAOVacina dao_vacinas;
    private DAOUsuario dao_pessoal;
    private List list;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saude);

        pressao = (EditText) findViewById(R.id.pressao_campo);
        batimento = (EditText) findViewById(R.id.campo_batimentos);
        imc = (EditText) findViewById(R.id.campo_imc);
        vacinas = (EditText) findViewById(R.id.campo_vacinacao);

        mAuth = FirebaseAuth.getInstance();

        dao_pressao = new DAOPressao();
        dao_batimentos = new DAOBatimentos();
        dao_vacinas = new DAOVacina();
        dao_pessoal = new DAOUsuario();


    }

    @Override
    protected void onStart() {
        super.onStart();
        dao_batimentos.Query(mAuth.getUid(), new RetornoQuerys() {
            @Override
            public void onCallback(HashMap<String, String> value) {
                Log.d("teste", "onStart: "+value.size());


            }
        });



    }
}