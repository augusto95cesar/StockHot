package com.stock.hot.activity;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.stock.hot.R;
import com.stock.hot.model.Produto;
import com.stock.hot.service.DbaSourceService;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CadastroProdutoActivity extends AppCompatActivity {

    DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

    private  String chaveProduto;

    private TextView nomeProduto;
    private TextView quantidadeProduto;
    private TextView codeBarra;
    private TextView valorProduto;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_produto);
        RecuperarDadosView();
        //Recupera Dados
        //Bundle dados = getIntent().getExtras();
        //Produto produto = (Produto)  dados.getSerializable("produtoSerializado");
    }

    public void CadastrarProduto(View view){
        if(TextUtils.isEmpty(nomeProduto.getText().toString())){
            Toast.makeText(getApplicationContext(), "Digite o Produto!", Toast.LENGTH_LONG).show();
        }else {
            // montar Objeto Produto
            Produto produto = ValidarCadastro();
            reference.getDatabase();
            reference.child(DbaSourceService.getInstance().getNoEstoqueProduto())
                     .push()
                     .setValue(produto)
                     .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(getApplicationContext(), "Sucesso ao Cadastrar Produto!", Toast.LENGTH_LONG).show();
                            finish();
                        }
                     });
            Toast.makeText(getApplicationContext(), "Sem Internet no momento!", Toast.LENGTH_LONG).show();
            finish();
        }
    }

    public  Produto ValidarCadastro(){
        Produto produto = new Produto();

        produto.setNome(nomeProduto.getText().toString());

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
        nomeProduto =  findViewById(R.id.nomeProduto);
        codeBarra = findViewById(R.id.codeProduto);
        quantidadeProduto = findViewById(R.id.qtdProduto);
        valorProduto = findViewById(R.id.valorProduto);
    }


}