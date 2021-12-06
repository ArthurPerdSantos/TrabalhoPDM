package com.example.trabalhopdm.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.example.trabalhopdm.DataBase.DAOBatimentos;
import com.example.trabalhopdm.DataBase.DAOPressao;
import com.example.trabalhopdm.DataBase.DAOUsuario;
import com.example.trabalhopdm.DataBase.DAOVacina;
import com.example.trabalhopdm.R;
import com.example.trabalhopdm.utils.RetornoQuerys;
import com.example.trabalhopdm.utils.TextosParaAjuda;
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
    private String estado_saude;
    private TextosParaAjuda texto;
    private String instrucoes;




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

        texto = new TextosParaAjuda();


    }

    @Override
    protected void onStart() {
        super.onStart();
        OrganizaDadosBatimentos();
        OrganizaDadosIMC();
    }


    public void OrganizaDadosBatimentos(){
        dao_batimentos.QueryPegaDadosModel(mAuth.getUid(), new RetornoQuerys() {
            @Override
            public void onCallback(HashMap<String, String> value) {
                int batimentos = Integer.parseInt(value.get("batimentos_por_segundo"));
                if (batimentos<60)
                    estado_saude = "Bradicardia";
                else if (batimentos>100)
                    estado_saude = "Taquicardia";
                else
                    estado_saude = "Normacardia";
                instrucoes = texto.Batimentos(estado_saude);

                batimento.setText("No último registro seus batimentos foram: "+ batimentos +"bpm.\n" +
                        "O status atual do seu batimento é: "+estado_saude + "\n\n" +
                        "Instruções a se seguir:\n" +instrucoes);
            }
        });
    }

    public void OrganizaDadosPressao(){
        dao_batimentos.QueryPegaDadosModel(mAuth.getUid(), new RetornoQuerys() {
            @Override
            public void onCallback(HashMap<String, String> value) {
                int batimentos = Integer.parseInt(value.get("batimentos_por_segundo"));
                if (batimentos<60)
                    estado_saude = "Bradicardia";
                else if (batimentos>100)
                    estado_saude = "Taquicardia";
                else
                    estado_saude = "Normacardia";
                instrucoes = texto.Batimentos(estado_saude);

                batimento.setText("No último registro seus batimentos foram: "+ batimentos +"bpm.\n" +
                        "O status atual do seu batimento é: "+estado_saude + "\n\n" +
                        "Instruções a se seguir:\n" +instrucoes);
            }
        });
    }

    public void OrganizaDadosIMC(){
        dao_pessoal.QueryPegaDadosModel(mAuth.getUid(), new RetornoQuerys() {
            @Override
            public void onCallback(HashMap<String, String> value) {
                double peso = Integer.parseInt(value.get("peso"));
                double altura = Integer.parseInt(value.get("altura"));
                altura = altura/100;


                double imc_calculo = peso/(altura*altura);

                if (imc_calculo < 18.5)
                    estado_saude = "Magreza";
                if (imc_calculo < 24.9 & imc_calculo > 18.5)
                    estado_saude = "Normal";
                if (imc_calculo < 29.9 & imc_calculo > 24.9)
                    estado_saude = "Sobrepeso";
                if (imc_calculo < 40 & imc_calculo > 29.9)
                    estado_saude = "Obesidade";


                imc.setText("O seu IMC atual é: "+imc_calculo+"\n" +
                        "Seu estado atual de massa corporal representa: "+estado_saude);

            }
        });
    }
}