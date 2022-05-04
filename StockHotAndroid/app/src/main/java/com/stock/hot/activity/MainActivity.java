package com.stock.hot.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.stock.hot.R;
import com.stock.hot.adapter.AdapterProdutosList;
import com.stock.hot.model.Produto;
import com.stock.hot.model.ProdutoLista;
import com.stock.hot.resource.RecyclerItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button novoProduto;
    private RecyclerView recyclerViewProduto;
    private List<ProdutoLista> listaProduto = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       ConfigurationLayout();


        novoProduto = findViewById(R.id.newProdutoId);
        novoProduto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CadastroProdutoActivity.class);

                //Produto produto = new Produto();

                //Envia Dados
                //intent.putExtra("produtoSerializado", produto);

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

    private  void  ConfigurationLayout(){
        ConfigRecycleView();
    }

    private void  ConfigRecycleView(){
        recyclerViewProduto = findViewById(R.id.recycleId);

        // Dados para lista
        RecuperarLista();
        //Configurando Adapter => RecyclerView
        AdapterProdutosList adapter = new AdapterProdutosList(listaProduto);

        //Configurando RecyclerView LayoutManager
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());

        recyclerViewProduto.setLayoutManager(layoutManager);
        recyclerViewProduto.setHasFixedSize(true);
        recyclerViewProduto.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));
        recyclerViewProduto.setAdapter(adapter);

        //Configurando Evento de Click recyclerView
        recyclerViewProduto.addOnItemTouchListener(
                new RecyclerItemClickListener
                (
                        getApplicationContext(),
                        recyclerViewProduto,
                        new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                             ProdutoLista produtoOnClick =   listaProduto.get(position);
                             Toast.makeText(getApplicationContext(),
                                     produtoOnClick.getNome(),
                                         Toast.LENGTH_SHORT).show();

                            }

                            @Override
                            public void onLongItemClick(View view, int position) {

                            }

                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                            }
                        }
                )
        );
    }

    private  void RecuperarLista(){
        listaProduto.add(new ProdutoLista("Coca Cola","14784", 0.0, 0, "xfdiFwerc" ));
        listaProduto.add(new ProdutoLista("Guarana","1459821", 0.0, 12, "FFrcdr18pe" ));
    }

}