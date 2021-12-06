package com.example.trabalhopdm.activity;

import androidx.appcompat.app.AppCompatActivity;


import java.util.Calendar;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.trabalhopdm.DataBase.DAOVacina;
import com.example.trabalhopdm.R;
import com.example.trabalhopdm.models.VacinaModel;
import com.google.firebase.auth.FirebaseAuth;

import java.util.HashMap;


public class VacinasActivity extends AppCompatActivity {

    private Button salvar, adicionar_outra;
    private EditText vacina, laboratorio;
    private Spinner doses;
    private DatePicker data_feita;
    private FirebaseAuth mAuth;
    private String[] dose = {"Primeira", "Segunda", "Terceira"};
    private DAOVacina dao;
    private VacinaModel vacinas;
    private ArrayAdapter<CharSequence> array;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vacinas);

        doses = (Spinner) findViewById(R.id.lista_doses);
        vacina = (EditText) findViewById(R.id.nome_vacina);
        laboratorio = (EditText) findViewById(R.id.laboratorio);
        salvar = (Button) findViewById(R.id.salvar_dados);
        adicionar_outra = (Button) findViewById(R.id.adicionar_outra);
        data_feita = (DatePicker) findViewById(R.id.data_feita) ;
        vacinas = new VacinaModel();

        dao = new DAOVacina();
        mAuth = FirebaseAuth.getInstance();

        array = ArrayAdapter.createFromResource(this, R.array.categoria_dose, android.R.layout.simple_spinner_item);
        doses.setAdapter(array);
        salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean estado = SalvarNoBanco();
                if (estado)
                    AbrirTelaInicial();

            }
        });
        adicionar_outra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean estado = SalvarNoBanco();
                if (estado)
                    AbrirTelaVacinas();
            }
        });
    }

    public void AbrirTelaInicial() {
        Intent intent = new Intent(getApplicationContext(), TelaInicialActivity.class);
        startActivity(intent);
    }
    public void AbrirTelaVacinas() {
        Intent intent = new Intent(getApplicationContext(), VacinasActivity.class);
        startActivity(intent);
    }

    private boolean SalvarNoBanco() {
        vacinas.setDose(doses.getSelectedItem().toString());
        vacinas.setData_aplicacao(get_data(data_feita));
        vacinas.setNome_vacina(vacina.getText().toString());
        vacinas.setNome_laboratorio(laboratorio.getText().toString());
        vacinas.setId_usario(mAuth.getUid());
        if (vacinas.getDose().equals("") || vacinas.getNome_vacina().equals("") || vacinas.getNome_laboratorio().equals("") || vacinas.getData_aplicacao().equals("")) {
            Toast.makeText(VacinasActivity.this, "Todos os campos devem ser preenchidos", Toast.LENGTH_LONG).show();
            return false;
        } else {
            dao.add(vacinas);
            Toast.makeText(VacinasActivity.this, "Dados foram salvos com sucesso", Toast.LENGTH_LONG).show();
            return true;
        }
    }
    public static String get_data(DatePicker datePicker){
        int ano = datePicker.getYear();
        int mes = datePicker.getMonth();
        int dia = datePicker.getDayOfMonth();

        Calendar calendar = Calendar.getInstance();
        calendar.set(ano, mes, dia);

        ano = calendar.get(Calendar.YEAR);
        mes = calendar.get(Calendar.MONTH) + 1;
        dia = calendar.get(Calendar.DAY_OF_MONTH);

        String dateString = String.format("%d/%d/%d", dia, mes, ano);


        return dateString;
    }
}
