package com.example.trabalhopdm.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.trabalhopdm.DataBase.DAOUsuario;
import com.example.trabalhopdm.R;
import com.google.firebase.auth.FirebaseAuth;

import java.io.ObjectStreamException;
import java.util.HashMap;


public class DadosPessoaisactivity extends AppCompatActivity {

    private Button salvar;
    private EditText idade, peso, altura;
    private DAOUsuario dao;
    private FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dados_pessoais);

        idade = (EditText) findViewById(R.id.idade);
        peso = (EditText) findViewById(R.id.peso);
        altura = (EditText) findViewById(R.id.altura);
        dao = new DAOUsuario();
        mAuth = FirebaseAuth.getInstance();

        salvar = (Button) findViewById(R.id.salvar_dados);

        salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String idade_aux = idade.getText().toString();
                String peso_aux = peso.getText().toString();
                String altura_aux = altura.getText().toString();
                if (idade_aux.equals("")||peso_aux.equals("")||altura_aux.equals("")){
                    Toast.makeText(DadosPessoaisactivity.this, "Todos os campos devem ser preenchidos", Toast.LENGTH_SHORT).show();
                }else{
                    HashMap<String, Object> hashMap = new HashMap<>();
                    hashMap.put("idade", idade_aux);
                    hashMap.put("peso", peso_aux);
                    hashMap.put("altura", altura_aux);
                    dao.update(mAuth.getUid(), hashMap);
                    Toast.makeText(DadosPessoaisactivity.this, "Dados foram salvos com sucesso", Toast.LENGTH_SHORT).show();
                    AbrirTelaInicial();
                }
            }
        });

    }
    public void AbrirTelaInicial() {
        Intent intent = new Intent(getApplicationContext(), TelaInicialActivity.class);
        startActivity(intent);
    }
}