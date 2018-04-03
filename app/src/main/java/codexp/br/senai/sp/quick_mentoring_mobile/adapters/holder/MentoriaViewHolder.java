package codexp.br.senai.sp.quick_mentoring_mobile.adapters.holder;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import codexp.br.senai.sp.quick_mentoring_mobile.R;
import codexp.br.senai.sp.quick_mentoring_mobile.adapters.adapter.MentoriaAdapter;
import codexp.br.senai.sp.quick_mentoring_mobile.model.Mentoria;
import codexp.br.senai.sp.quick_mentoring_mobile.views.mentorado.VisualizarMentoria;

/**
 * Created by Helena Strada on 22/03/2018.
 */

public class MentoriaViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public final TextView nome;
    public final TextView categoria;
    private int mentoriaId;
    public final MentoriaAdapter adapter;

    public MentoriaViewHolder(final View view, final MentoriaAdapter adapter) {
        super(view);
        this.adapter = adapter;

        nome = view.findViewById(R.id.tvNomeMentoria);
        categoria = view.findViewById(R.id.tvCategoriaMentoria);
        view.setOnClickListener(this);
    }

    public void preencher(Mentoria mentoria) {
        mentoriaId = mentoria.getMentoriaId();
        nome.setText(mentoria.getNome());
        categoria.setText(mentoria.getCategoria().getNome());
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(v.getContext(), VisualizarMentoria.class);
        v.getContext().startActivity(intent);
    }
}
