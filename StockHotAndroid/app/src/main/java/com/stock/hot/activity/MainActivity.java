package com.stock.hot.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.stock.hot.R;
import com.stock.hot.model.Produto;

public class MainActivity extends AppCompatActivity {

    private Button novoProduto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        novoProduto = findViewById(R.id.newProdutoId);

        novoProduto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CadastroProdutoActivity.class);

                Produto produto = new Produto();

                //Envia Dados
                intent.putExtra("produtoSerializado", produto);

                startActivity(intent);



            }
        });

        /*
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        reference.getDatabase();

        Produto produto = new Produto();
        produto.setNome("CocaCola");
        produto.setQuantidade(11);
        produto.setValor(7.55);
        reference.child("estoqueProduto").child("001").setValue(produto);
        */
    }


}