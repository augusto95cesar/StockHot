package com.stock.hot.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.stock.hot.R;
import com.stock.hot.model.ProdutoLista;

import java.util.List;

public class AdapterProdutosList extends RecyclerView.Adapter<AdapterProdutosList.ProdutosListViewHolder> {

    private List<ProdutoLista> listaProduto;

    public AdapterProdutosList(List<ProdutoLista> list){
        this.listaProduto = list;
    }


    @NonNull
    @Override
    public ProdutosListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemLista = LayoutInflater.from(parent.getContext())
                                       .inflate(R.layout.adapter_produtos_list, parent, false);
        return new ProdutosListViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull ProdutosListViewHolder holder, int position) {
        ProdutoLista produto = listaProduto.get(position);
        holder.produto.setText(produto.getNome());
        holder.codeBarra.setText(produto.getCodeBarra());
    }

    @Override
    public int getItemCount() {
        //return 7;
        return  listaProduto.size();
    }


    class  ProdutosListViewHolder extends RecyclerView.ViewHolder {

        TextView produto;
        TextView codeBarra;

        ProdutosListViewHolder(@NonNull View itemView) {
            super(itemView);
            produto = itemView.findViewById(R.id.prodIdList);
            codeBarra = itemView.findViewById(R.id.codIdList);
        }
    }

}
