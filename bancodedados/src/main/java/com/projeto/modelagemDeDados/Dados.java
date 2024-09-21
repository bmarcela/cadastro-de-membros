package com.projeto.modelagemDeDados;

import java.sql.Date;

public class Dados {

    private int cod;
    private String nome;
    private Date dataNasc;
    private String endereco;
    private String telefone;
    private String ministerio;

    public Dados(int cod, String nome, Date dataNasc, String endereco, String telefone, String ministerio) {
        this.cod = cod;
        this.nome = nome;
        this.dataNasc = dataNasc;
        this.endereco = endereco;
        this.telefone = telefone;
        this.ministerio = ministerio;
    }

    public int getCod() { return cod; }
    public void setCod(int cod) { this.cod = cod; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public Date getDataNasc() { return dataNasc; }
    public void setDataNasc(Date dataNasc) { this.dataNasc = dataNasc; }

    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }

    public String getMinisterio() { return ministerio; }
    public void setMinisterio(String ministerio) { this.ministerio = ministerio; }

}
