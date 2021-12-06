package com.example.trabalhopdm.activity;

import androidx.annotation.NonNull;
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

import com.example.trabalhopdm.DataBase.DAOUsuario;
import com.example.trabalhopdm.R;
import com.example.trabalhopdm.models.UsuarioModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class CadastroActivity extends AppCompatActivity {

    private Button cadastro, ja_possui_conta;
    private EditText email, senha, conf_senha, nome;
    private FirebaseAuth mAuth;
    private CheckBox ver_senha;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        mAuth = FirebaseAuth.getInstance();
        nome = (EditText) findViewById(R.id.nome);
        email = (EditText) findViewById(R.id.email);
        senha = (EditText) findViewById(R.id.senha);
        conf_senha = (EditText) findViewById(R.id.conf_senha);
        ja_possui_conta = (Button) findViewById(R.id.ja_possui_conta);
        cadastro = (Button) findViewById(R.id.cadastro);
        ver_senha = (CheckBox) findViewById(R.id.ver_senha);
        DAOUsuario dao = new DAOUsuario();

        ver_senha.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    senha.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    conf_senha.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }else{
                    senha.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    conf_senha.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });

        ja_possui_conta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

        cadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UsuarioModel usuario = new UsuarioModel();

                usuario.setEmail(email.getText().toString());
                usuario.setNome(nome.getText().toString());
                String senha_cadastro = senha.getText().toString();
                String conf_senha_cadastro = conf_senha.getText().toString();

                //verifica se os campos não são nulos
                if (usuario.getNome().equals("")||usuario.getEmail().equals("") || senha_cadastro.equals("") || conf_senha_cadastro.equals(""))
                    Toast.makeText(CadastroActivity.this, "Existe um ou mais campos vazios", Toast.LENGTH_SHORT).show();
                else {
                    if (ValidaEmailComRegex(usuario.getEmail())) {
                        if (conf_senha_cadastro.equals(senha_cadastro)) {
                            if (senha_cadastro.length() >= 8) {
                                mAuth.createUserWithEmailAndPassword(usuario.getEmail(), senha_cadastro).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()) {
                                            usuario.setId(mAuth.getUid());
                                            dao.add(usuario).addOnSuccessListener(suc->{
                                                Toast.makeText(CadastroActivity.this, "Cadastro concluído", Toast.LENGTH_SHORT).show();
                                                AbrirTelaInicial();
                                            }).addOnFailureListener(er->{
                                                Toast.makeText(CadastroActivity.this, ""+er.getMessage(), Toast.LENGTH_SHORT).show();
                                            });
                                        } else {
                                            String erro = task.getException().getMessage();
                                            Toast.makeText(CadastroActivity.this, "" + erro, Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                            } else
                                Toast.makeText(CadastroActivity.this, "Senhas precisa ter 8 ou mais caracteres", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(CadastroActivity.this, "Senhas não são idênticas", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(CadastroActivity.this, "Email não é válido", Toast.LENGTH_SHORT).show();
                    }


                }
            }

        });
    }

    public void AbrirTelaInicial(){
        Intent intent = new Intent(getApplicationContext(), TelaInicialActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        //valida se o usuario já não esta conectado
        FirebaseUser usuario = mAuth.getCurrentUser();
        if (usuario != null) {
            usuario.reload();
        }
    }


    public static boolean ValidaEmailComRegex(String email) {
        boolean email_eh_valido = false;
        if (email != null && email.length() > 0) {
            String re = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
            Pattern pattern = Pattern.compile(re, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(email);
            if (matcher.matches()) {
                email_eh_valido = true;
            }
        }
        return email_eh_valido;
    }
}

