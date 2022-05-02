package com.stock.hot.activity;

import androidx.appcompat.app.AppCompatActivity;
import com.stock.hot.R;
import com.stock.hot.model.Produto;

import android.os.Bundle;

public class CadastroProdutoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_produto);

        //Recupera Dados
        Bundle dados = getIntent().getExtras();
        Produto produto = (Produto)  dados.getSerializable("produtoSerializado");
    }
}