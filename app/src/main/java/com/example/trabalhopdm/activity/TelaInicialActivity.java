package com.example.trabalhopdm.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.trabalhopdm.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TelaInicialActivity extends AppCompatActivity {

    private Button pressao, dados_pessoais, batimento, consulta, sair, vacinas;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_inicial);


        pressao = (Button) findViewById(R.id.pressao);
        vacinas = (Button) findViewById(R.id.vacinas_covid);
        dados_pessoais = (Button) findViewById(R.id.dados_pessoais);
        batimento = (Button) findViewById(R.id.batimentos);
        consulta = (Button) findViewById(R.id.consulta_dados);
        sair = (Button) findViewById(R.id.deslogar);
        mAuth = FirebaseAuth.getInstance();


        vacinas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AbrirTelaVacinas();
            }
        });

        pressao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AbrirTelaPressao();
            }
        });
        dados_pessoais.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AbrirTelaDadosPessoais();
            }
        });

        batimento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AbrirTelaBatimentos();
            }
        });

        consulta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AbrirTelaSaude();
            }
        });

        sair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseUser usuario_atual = mAuth.getCurrentUser();
                mAuth.signOut();
                if (usuario_atual == null){
                    Toast.makeText(TelaInicialActivity.this, "Sessão encerrada com sucesso", Toast.LENGTH_SHORT).show();
                    AbrirTelaLogin();
                }
                Toast.makeText(TelaInicialActivity.this, "houve um erro ao tentar encerrar a sessão", Toast.LENGTH_SHORT).show();

            }

        });
    }
    public void AbrirTelaLogin(){
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
    }

    public void AbrirTelaDadosPessoais(){
        Intent intent = new Intent(getApplicationContext(), DadosPessoaisactivity.class);
        startActivity(intent);
    }

    public void AbrirTelaVacinas(){
        Intent intent = new Intent(getApplicationContext(), VacinasActivity.class);
        startActivity(intent);
    }

    public void AbrirTelaPressao(){
        Intent intent = new Intent(getApplicationContext(), PressaoActivity.class);
        startActivity(intent);
    }

    public void AbrirTelaBatimentos(){
        Intent intent = new Intent(getApplicationContext(), BatimentosActivity.class);
        startActivity(intent);
    }

    public void AbrirTelaSaude(){
        Intent intent = new Intent(getApplicationContext(), SaudeActivity.class);
        startActivity(intent);
    }


}