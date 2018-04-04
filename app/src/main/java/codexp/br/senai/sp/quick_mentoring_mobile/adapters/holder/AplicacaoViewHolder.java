package codexp.br.senai.sp.quick_mentoring_mobile.adapters.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import codexp.br.senai.sp.quick_mentoring_mobile.R;
import codexp.br.senai.sp.quick_mentoring_mobile.adapters.adapter.AplicacaoAdapter;
import codexp.br.senai.sp.quick_mentoring_mobile.adapters.interfaces.AplicacaoOnClickListener;
import codexp.br.senai.sp.quick_mentoring_mobile.model.Aplicacao;

/**
 * Created by tpetrone on 03/04/18.
 */

public class AplicacaoViewHolder extends RecyclerView.ViewHolder {

    public final TextView nome;
    public final TextView status;
    public final TextView nomeMentorado;

    public final AplicacaoAdapter adapter;
    private int aplicacaoId;

    public AplicacaoViewHolder(final View view, final AplicacaoAdapter adapter) {
        super(view);
        this.adapter = adapter;

        nome = view.findViewById(R.id.tvNomeMentoria);
        status = view.findViewById(R.id.tvStatus);
        nomeMentorado = view.findViewById(R.id.tvNomeMentorado);
    }

    public void preencher(Aplicacao aplicacao) {
        aplicacaoId = aplicacao.getId();

        nome.setText("Aplicação para: " + aplicacao.getMentoria().getNome());
        nomeMentorado.setText("Aplicante: " + aplicacao.getUsuario().getPerfil().getNome());


        if (aplicacao.getAceite()) {
            status.setText("Aceita");
        } else {
            status.setText("Recusada");
        }
    }

    public void bind(final Aplicacao aplicacao, final AplicacaoOnClickListener listener) {
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                listener.onItemClick(aplicacao);
            }
        });
    }
}
