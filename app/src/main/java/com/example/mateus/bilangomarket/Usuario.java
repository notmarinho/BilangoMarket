package com.example.mateus.bilangomarket;

public class Usuario {
    private String nome;
    private String email;
    private String senha;
    private int ativo = 1;
    private long ID;


    public int getID() {
        return (int) ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public int getAtivo() {
        return ativo;
    }

    public void setAtivo(int ativo) {
        this.ativo = ativo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
