package e.codexp.myapplication.model;

/**
 * Created by CodeXP on 21/03/2018.
 */

public class Usuário {
    private Long id;
    private String email;
    private String senha;
    private String role;
    private Boolean ativo;
    private String ano;
    private byte[] foto;

    public Usuário() {}
    public Usuário(Long id) { this.id = id; }
    public Usuário(Long id, String email, String senha, String role, Boolean ativo) {
        this.id = id;
        this.email = email;
        this.senha = senha;
        this.role = role;
        this.ativo = ativo;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getemail() { return email; }
    public void setemail(String email) { this.email = email; }

    public String getsenha() { return senha; }
    public void setsenha(String senha) { this.senha = senha; }

    public String getrole() { return role; }
    public void setrole(String role) { this.role = role; }

    public Boolean getativo() { return ativo; }
    public void setativo(Boolean ativo) { this.ativo = ativo; }
}
