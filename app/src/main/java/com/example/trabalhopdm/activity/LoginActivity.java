package com.example.trabalhopdm.activity;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.example.trabalhopdm.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;


public class LoginActivity extends AppCompatActivity {

    private EditText email, senha;
    private Button login;
    private FirebaseAuth mAuth;
    private CheckBox ver_senha;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = (EditText) findViewById(R.id.email_login);
        senha = (EditText) findViewById(R.id.senha_login);

        login = (Button) findViewById(R.id.login);
        mAuth = FirebaseAuth.getInstance();
        ver_senha = (CheckBox) findViewById(R.id.ver_senha);

        ver_senha.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    senha.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }else{
                    senha.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email_login = email.getText().toString();
                String senha_login = senha.getText().toString();
                if (email_login.equals("") || senha_login.equals(""))
                    Toast.makeText(LoginActivity.this, "Todos os campos devem ser preenchidos", Toast.LENGTH_SHORT).show();
                else {
                    mAuth.signInWithEmailAndPassword(email_login, senha_login)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(LoginActivity.this, "Login feito com sucesso", Toast.LENGTH_SHORT).show();
                                        AbrirTelaInicial();
                                    } else {
                                        String erro = task.getException().getMessage();
                                        Toast.makeText(LoginActivity.this, "" + erro, Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });
    }

    public void AbrirTelaInicial() {
        Intent intent = new Intent(getApplicationContext(), TelaInicialActivity.class);
        startActivity(intent);
    }


}