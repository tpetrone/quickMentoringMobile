package e.codexp.myapplication.model;

import java.util.Date;

/**
 * Created by CodeXP on 20/03/2018.
 */

public class Mentoria {
    private long id;
    private String nomeMentoria;
    private String nome;
    private String endereco;
    private Date data;
    private int hora;
    private String sobre;

    public Mentoria(){
    }
    public Mentoria(Long id){this.id = id;}
    public Mentoria(Long id, String nomeMentoria, String nome, String endereco, Date data, int hora, String sobre){
        this.id = id;
        this.nomeMentoria = nomeMentoria;
        this.nome = nome;
        this.endereco = endereco;
        this.data = data;
        this.hora = hora;
        this.sobre = sobre;

    }
    public Long getId(){return id;}
    public void setId(Long id){this.id = id;}

    public String getNomementoria(){return nomeMentoria;}
    public void setNomementoria(String nomeMentoria){this.nomeMentoria = nomeMentoria;}

    public String getNome(){return nome;}
    public void setNome(String nome){this.nome = nome;}

    public String getEndereco(){return endereco;}
    public void setEndereco(String endereco){this.endereco = endereco;}

    public Date getdata(){return data;}
    public void setData(Date data){this.data = data;}

    public int getHora(){return hora;}
    public void setHora(int hora){this.hora = hora;}

    public String getSobre(){return sobre;}
    public void setSobre(String sobre){this.sobre = sobre;}
}