package com.example.trabalhopdm.activity;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.trabalhopdm.DataBase.DAOBatimentos;
import com.example.trabalhopdm.R;
import com.example.trabalhopdm.models.BatimentosModel;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Calendar;
import java.util.Date;

public class BatimentosActivity extends AppCompatActivity {

    private EditText numero, numero_aviso;
    private Button salvar;
    private BatimentosModel batimentos;
    private FirebaseAuth mAuth;
    private DAOBatimentos dao;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_batimentos);

        numero = (EditText) findViewById(R.id.numero_batimentos);
        numero_aviso = (EditText) findViewById(R.id.numero_batimentos_aviso);

        salvar = (Button) findViewById(R.id.salvar_dados);
        mAuth = FirebaseAuth.getInstance();
        dao = new DAOBatimentos();

        batimentos = new BatimentosModel();
        salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (numero == null || numero_aviso == null)
                    Toast.makeText(BatimentosActivity.this, "Todos os campos devem ser preenchidos", Toast.LENGTH_LONG).show();
                else{
                    batimentos.setId_usuario(mAuth.getUid());
                    batimentos.setBatimentos_por_segundo(Integer.parseInt(numero.getText().toString()));
                    batimentos.setNumero_de_batimentos_para_alerta(Integer.parseInt(numero_aviso.getText().toString()));
                    batimentos.setCriacao(GetHoje());
                    dao.add(batimentos);
                    Toast.makeText(BatimentosActivity.this, "Dados foram salvos com sucesso", Toast.LENGTH_LONG).show();
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