package com.example.trabalhopdm.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.trabalhopdm.DataBase.DAOPressao;
import com.example.trabalhopdm.R;
import com.example.trabalhopdm.models.PressaoModel;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Calendar;
import java.util.Date;

public class PressaoActivity extends AppCompatActivity {

    private PressaoModel pressao;
    private FirebaseAuth mAuth;
    private DAOPressao dao;
    private EditText sistolica, diastolica;
    private Button salvar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pressao);

        mAuth = FirebaseAuth.getInstance();
        dao = new DAOPressao();
        pressao = new PressaoModel();

        sistolica = (EditText) findViewById(R.id.pressao_sisto);
        diastolica = (EditText) findViewById(R.id.pressao_diasto);

        salvar = (Button) findViewById(R.id.salvar_dados);

        salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sistolica == null || diastolica == null)
                    Toast.makeText(PressaoActivity.this, "Todos os campos devem ser preenchidos", Toast.LENGTH_LONG).show();
                else{
                    pressao.setId_usuario(mAuth.getUid());
                    pressao.setPressao_diastolica(Integer.parseInt(diastolica.getText().toString()));
                    pressao.setPressao_sistolica(Integer.parseInt(sistolica.getText().toString()));
                    pressao.setCriacao(GetHoje());
                    dao.add(pressao);
                    Toast.makeText(PressaoActivity.this, "Dados foram salvos com sucesso", Toast.LENGTH_LONG).show();
                    AbrirTelaInicial();
                }
            }
        });
    }


    public String GetHoje(){
        Date data = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(data);
        int dia = calendar.get(Calendar.DAY_OF_MONTH);
        int mes = calendar.get(Calendar.MONTH)+1;
        int ano = calendar.get(Calendar.YEAR);

        String dateString = String.format("%d/%d/%d", dia, mes, ano);

        return dateString;
    }

    public void AbrirTelaInicial() {
        Intent intent = new Intent(getApplicationContext(), TelaInicialActivity.class);
        startActivity(intent);
    }
}