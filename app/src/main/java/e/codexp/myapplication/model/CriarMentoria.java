package e.codexp.myapplication.model;

import java.util.Date;

/**
 * Created by CodeXP on 20/03/2018.
 */

public class CriarMentoria {
    private long id;
    private String nomementoria;
    private String categoria;
    private String checkbox, checkbox2;
    private String endereço;
    private Date data;
    private int hora;
    private String sobre;
    private String checkbox3, checkbox4;

    public CriarMentoria(){
    }
    public CriarMentoria(Long id){this.id = id;}
    public CriarMentoria(Long id, String nomementoria, String categoria, String checkbox, String checkbox2, String endereço, Date data, int hora, String sobre, String checkbox3, String checkbox4){
        this.id = id;
        this.nomementoria = nomementoria;
        this.categoria = categoria;
        this.checkbox = checkbox;
        this.checkbox2 = checkbox2;
        this.endereço = endereço;
        this.data = data;
        this.hora = hora;
        this.sobre = sobre;
        this.checkbox3 = checkbox3;
        this.checkbox4 = checkbox4;
    }
    public Long getId(){return id;}
    public void setId(Long id){this.id = id;}

    public String getNomementoria(){return nomementoria;}
    public void setNomementoria(String nomementoria){this.nomementoria = nomementoria;}

    public String getCategoria(){return categoria;}
    public void setCategoria(String categoria){this.categoria = categoria;}

    public String getCheckbox(){return checkbox;}
    public void setCheckbox(String checkbox){this.checkbox = checkbox;}

    public String getCheckbox2(){return checkbox2;}
    public void setCheckbox2(String checkbox2){this.checkbox2 = checkbox2;}

    public String getEndereço(){return endereço;}
    public void setEndereço(String endereço){this.endereço = endereço;}

    public Date getdata(){return data;}
    public void setData(Date data){this.data = data;}

    public String getSobre(){return sobre;}
    public void setSobre(String sobre){this.sobre = sobre;}

    public String getCheckbox3(){return checkbox3;}
    public void setCheckbox3(String checkbox3){this.checkbox3 = checkbox3;}

    public String getCheckbox4(){return checkbox4;}
    public void setCheckbox4(String checkbox4){this.checkbox4 = checkbox4;}
}