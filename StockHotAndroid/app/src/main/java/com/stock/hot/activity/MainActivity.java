package com.stock.hot.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.stock.hot.R;
import com.stock.hot.adapter.AdapterProdutosList;
import com.stock.hot.model.Produto;
import com.stock.hot.model.ProdutoLista;
import com.stock.hot.resource.RecyclerItemClickListener;
import com.stock.hot.service.DbaSourceService;

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

        // Dados para lista
        RecuperarLista();

       // ConfigurationLayout();


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

        //Configurando Adapter => RecyclerView => Passar lista de Dados
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
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        reference.getDatabase();
        DatabaseReference estoqueProdutos  = reference.child(DbaSourceService.getInstance().getNoEstoqueProduto());
        Query listaProdutos = estoqueProdutos.orderByKey();

        listaProdutos.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listaProduto.clear();
                for (DataSnapshot postSnapshot: snapshot.getChildren()) {
                    ProdutoLista post = postSnapshot.getValue(ProdutoLista.class);
                    post.setIdentificado(postSnapshot.getKey());
                    listaProduto.add(post);
                }
                //Log.e("GetLista", "" + listaProduto.size());
                ConfigurationLayout();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}