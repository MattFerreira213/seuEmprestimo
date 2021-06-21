package com.afcompany.seucredito;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import static android.widget.Toast.*;

public class SolicitacaoActivity extends AppCompatActivity {

    private EditText edtNome;
    private EditText edtCpf;
    private EditText edtIdade;
    private EditText edtRendaMensal;
    private EditText edtValorEmprestimo;

    private static final double SALARIO_MINIMO = 1100.00;
    private static final int IDADE = 18;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solicitacao);

        Toolbar();

        edtNome = findViewById(R.id.edt_nome);
        edtCpf = findViewById(R.id.edt_cpf);
        edtIdade = findViewById(R.id.edt_idade);
        edtRendaMensal = findViewById(R.id.edt_renda_mensal);
        edtValorEmprestimo = findViewById(R.id.edt_valor_emprestimo);

        Button buttonOfertas = findViewById(R.id.btn_oferta_credito);
        buttonOfertas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (validate()) {
                    Toast.makeText(SolicitacaoActivity.this, R.string.error_msg2, LENGTH_SHORT).show();
                    return;
                }

                String sIdade = edtIdade.getText().toString();
                String sRendaMensal = edtRendaMensal.getText().toString();
                String sValorEmprestimo = edtValorEmprestimo.getText().toString();

                double valorEmprestimo = Double.parseDouble(sValorEmprestimo);
                double rendaMesnal = Double.parseDouble(sRendaMensal);
                int idade = Integer.parseInt(sIdade);


                if (idade >= IDADE && rendaMesnal >= SALARIO_MINIMO) {
                    if (rendaMesnal >= SALARIO_MINIMO && rendaMesnal < (3 * SALARIO_MINIMO) && valorEmprestimo >= 150 && valorEmprestimo <= 4400) {
                        Intent in = new Intent(SolicitacaoActivity.this, Proposta1Activity.class);
                        in.putExtra("valorEmprestimo", edtValorEmprestimo.getText().toString());
                        startActivity(in);
                        finish();
                    } else if (rendaMesnal >= (3 * SALARIO_MINIMO) && rendaMesnal < (6 * SALARIO_MINIMO) && valorEmprestimo > 4400 && valorEmprestimo <= 13200) {
                        Intent in = new Intent(SolicitacaoActivity.this, Proposta2Activity.class);
                        in.putExtra("valorEmprestimo", edtValorEmprestimo.getText().toString());
                        startActivity(in);
                        finish();
                    } else if (rendaMesnal >= (6 * SALARIO_MINIMO) && rendaMesnal < (8 * SALARIO_MINIMO) && valorEmprestimo > 13200 && valorEmprestimo <= 26400) {
                        Intent in = new Intent(SolicitacaoActivity.this, Proposta3Activity.class);
                        in.putExtra("valorEmprestimo", edtValorEmprestimo.getText().toString());
                        startActivity(in);
                        finish();
                    } else if (rendaMesnal >= (8 * SALARIO_MINIMO) && valorEmprestimo > 26400 && valorEmprestimo <= 40000) {
                        Intent in = new Intent(SolicitacaoActivity.this, Proposta4Activity.class);
                        in.putExtra("valorEmprestimo", edtValorEmprestimo.getText().toString());
                        startActivity(in);
                        finish();
                    } else {
                        Toast.makeText(SolicitacaoActivity.this, R.string.msg_negado, LENGTH_SHORT).show();
                        return;
                    }
                } else {
                    Toast.makeText(SolicitacaoActivity.this, R.string.error_msg, LENGTH_SHORT).show();
                    return;
                }
            }
        });
    }

    private void Toolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24);
    }

    private boolean validate(){
        return(edtIdade.getText().toString().isEmpty() && edtRendaMensal.getText().toString().isEmpty()
                && edtNome.getText().toString().isEmpty() && edtCpf.getText().toString().isEmpty()
                && edtValorEmprestimo.getText().toString().isEmpty());
    }
}
