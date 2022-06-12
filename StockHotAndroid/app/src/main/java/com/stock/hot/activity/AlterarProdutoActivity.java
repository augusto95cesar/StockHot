package com.stock.hot.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.stock.hot.R;
import com.stock.hot.model.Produto;
import com.stock.hot.model.ProdutoLista;
import com.stock.hot.service.DbaSourceService;

import java.util.HashMap;
import java.util.Map;

public class AlterarProdutoActivity extends AppCompatActivity {
    DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

    private  Button acaoBtn;
    private TextView nomeProduto;
    private TextView quantidadeProduto;
    private TextView codeBarra;
    private TextView valorProduto;
    ProdutoLista produtoID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alterar_produto);

        RecuperarDadosView();
        //Recupera Dados
        Bundle dados = getIntent().getExtras();
        this.produtoID = (ProdutoLista)  dados.getSerializable("produtoSerializado");
        nomeProduto.setText(produtoID.getNome());
        codeBarra.setText(produtoID.getCodeBarra());
        quantidadeProduto.setText(produtoID.getQuantidade().toString());
        valorProduto.setText(produtoID.getValor().toString());
        key = produtoID.getIdentificado();

        acaoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlterarrProduto();
            }
        });

    }


    public void AlterarrProduto(){
        if(TextUtils.isEmpty(nomeProduto.getText().toString())){
            Toast.makeText(getApplicationContext(), "Digite o Produto!", Toast.LENGTH_LONG).show();
        }else {
            // montar Objeto Produto
            Produto produto = ValidarCadastro();
            reference.getDatabase();
            reference.child(DbaSourceService.getInstance().getNoEstoqueProduto())
                    .child(produtoID.getIdentificado().toString())
                    .setValue(produto)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(getApplicationContext(), "Sucesso ao Alterar Produto!", Toast.LENGTH_LONG).show();
                            finish();
                        }
                    });
            Toast.makeText(getApplicationContext(), "Sem Internet no momento!", Toast.LENGTH_LONG).show();
            finish();
        }
    }

    public  Produto ValidarCadastro(){
        Produto produto = new Produto();

        produto.setNome(nomeProduto.getText().toString().toLowerCase());

        if (TextUtils.isEmpty(codeBarra.getText().toString())) {
            produto.setCodeBarra("");
        } else {
            produto.setCodeBarra(codeBarra.getText().toString());
        }

        if (TextUtils.isEmpty(valorProduto.getText().toString())) {
            produto.setValor(0.0);
        } else {
            produto.setValor(Double.parseDouble(valorProduto.getText().toString()));
        }

        if (TextUtils.isEmpty(quantidadeProduto.getText().toString())) {
            produto.setQuantidade(0);
        } else {
            produto.setQuantidade(Integer.parseInt(quantidadeProduto.getText().toString()));
        }

        return produto;
    }

    public void RecuperarDadosView(){
        nomeProduto =  findViewById(R.id.AltnomeProduto);
        codeBarra = findViewById(R.id.AltcodeProduto);
        quantidadeProduto = findViewById(R.id.AltqtdProduto);
        valorProduto = findViewById(R.id.AltvalorProduto);
        acaoBtn = findViewById(R.id.AltacaoBtnProduto);
        delBtn = findViewById(R.id.AltdeleteId);
    }

    public void DeleteProduto(View view){
        reference.getDatabase();
        reference.child(DbaSourceService.getInstance().getNoEstoqueProduto())
                 .child(produtoID.getIdentificado().toString())
                 .removeValue()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(getApplicationContext(), "Sucesso ao Deletar Produto!", Toast.LENGTH_LONG).show();
                        finish();
                    }
                });
        Toast.makeText(getApplicationContext(), "Sem Internet no momento!", Toast.LENGTH_LONG).show();
        finish();
    }
}