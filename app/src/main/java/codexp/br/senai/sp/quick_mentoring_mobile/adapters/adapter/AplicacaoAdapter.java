package codexp.br.senai.sp.quick_mentoring_mobile.adapters.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import codexp.br.senai.sp.quick_mentoring_mobile.R;
import codexp.br.senai.sp.quick_mentoring_mobile.adapters.holder.AplicacaoViewHolder;
import codexp.br.senai.sp.quick_mentoring_mobile.adapters.holder.MentoriaViewHolder;
import codexp.br.senai.sp.quick_mentoring_mobile.adapters.interfaces.AplicacaoOnClickListener;
import codexp.br.senai.sp.quick_mentoring_mobile.adapters.interfaces.OnClickListener;
import codexp.br.senai.sp.quick_mentoring_mobile.model.Aplicacao;
import codexp.br.senai.sp.quick_mentoring_mobile.model.Mentoria;

/**
 * Created by tpetrone on 03/04/18.
 */

public class AplicacaoAdapter extends RecyclerView.Adapter{

    private final AplicacaoOnClickListener listener;
    private List<Aplicacao> aplicacoes;
    private Context context;

    public AplicacaoAdapter(List<Aplicacao> aplicacoes, AplicacaoOnClickListener listener, Context context) {
        this.aplicacoes = aplicacoes;
        this.context = context;
        this.listener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.aplicacao_item_lista, parent, false);
        AplicacaoViewHolder holder = new AplicacaoViewHolder(view, this);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        AplicacaoViewHolder viewHolder = (AplicacaoViewHolder) holder;
        viewHolder.bind(aplicacoes.get(position), listener);
        Aplicacao aplicacao = aplicacoes.get(position);
        viewHolder.preencher(aplicacao);
    }

    @Override
    public int getItemCount() {
        return aplicacoes.size();
    }
}
