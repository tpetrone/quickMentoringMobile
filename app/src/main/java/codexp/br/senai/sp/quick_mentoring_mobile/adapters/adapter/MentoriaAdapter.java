package codexp.br.senai.sp.quick_mentoring_mobile.adapters.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import codexp.br.senai.sp.quick_mentoring_mobile.R;
import codexp.br.senai.sp.quick_mentoring_mobile.adapters.holder.MentoriaViewHolder;
import codexp.br.senai.sp.quick_mentoring_mobile.model.Mentoria;

/**
 * Created by Helena Strada on 22/03/2018.
 */

public class MentoriaAdapter extends RecyclerView.Adapter{

    private List<Mentoria> mentorias;
    private Context context;

    public MentoriaAdapter(List<Mentoria> mentorias, Context context) {
        this.mentorias = mentorias;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.mentoria_item_lista, parent, false);
        MentoriaViewHolder holder = new MentoriaViewHolder(view, this);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        MentoriaViewHolder viewHolder = (MentoriaViewHolder) holder;
        Mentoria mentoria = mentorias.get(position);
        viewHolder.preencher(mentoria);

    }

    @Override
    public int getItemCount() {
        return mentorias.size();
    }
}
