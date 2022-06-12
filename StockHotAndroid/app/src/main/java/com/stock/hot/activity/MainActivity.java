package com.stock.hot.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.stock.hot.R;
import com.stock.hot.adapter.AdapterProdutosList;
import com.stock.hot.model.ProdutoLista;
import com.stock.hot.resource.RecyclerItemClickListener;
import com.stock.hot.service.DbaSourceService;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private Button novoProduto;
    private RecyclerView recyclerViewProduto;
    private List<ProdutoLista> listaProduto = new ArrayList<>();
    private TextView pesquisa;
    private AdapterProdutosList adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Dados para lista
        RecuperarLista("");
        ConfigRecycleView();
        ConfigEvent();
    }

    public  void ConfigEvent() {
        novoProduto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CadastroProdutoActivity.class);
                startActivity(intent);
            }
        });

        pesquisa.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                RecuperarLista(charSequence.toString());
            }
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void afterTextChanged(Editable editable) {}
        });
    }

    private void ConfigRecycleView (){
        recyclerViewProduto = findViewById(R.id.recycleId);
        novoProduto = findViewById(R.id.newProdutoId);
        pesquisa = findViewById(R.id.pesquisaId);
        //init adapter produto
        this.adapter = new AdapterProdutosList();
        this.layoutManager = new LinearLayoutManager(getApplicationContext());
        //Configurando RecyclerView LayoutManager
        recyclerViewProduto.setLayoutManager(layoutManager);
        recyclerViewProduto.setHasFixedSize(true);
        recyclerViewProduto.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));
    }

    private void  UpdateRecycleView(){
        adapter.setListAdapterProduto(listaProduto);
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
                                Intent intent = new Intent(getApplicationContext(), AlterarProdutoActivity.class);
                                //Envia Dados
                                intent.putExtra("produtoSerializado", produtoOnClick);
                                startActivity(intent);
                            }

                            @Override
                            public void onLongItemClick(View view, int position) { }
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) { }
                        }
                )
        );
    }

    private  void RecuperarLista(String pesquisa ) {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        reference.getDatabase();
        DatabaseReference estoqueProdutos  = reference.child(DbaSourceService.getInstance().getNoEstoqueProduto());
        Query listaProdutos ;
        if(pesquisa.length() == 0) {
              listaProdutos = estoqueProdutos.orderByKey();
        }else{
            listaProdutos = estoqueProdutos.orderByChild("nome")
                                             .startAt(pesquisa.toLowerCase())
                                             .endAt(pesquisa.toLowerCase() + "\uf8ff");
        }
        listaProdutos.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listaProduto.clear();
                for (DataSnapshot postSnapshot: snapshot.getChildren()) {
                    ProdutoLista post = postSnapshot.getValue(ProdutoLista.class);
                    post.setIdentificado(postSnapshot.getKey());
                    //palavra = palavra.substring(0,1).toUpperCase().concat(palavra.substring(1));
                    String nome = post.getNome().toLowerCase();
                    nome = nome.substring(0,1).toUpperCase().concat(nome.substring(1));
                    post.setNome(nome);
                    listaProduto.add(post);
                }
                UpdateRecycleView();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) { }
        });
    }
}