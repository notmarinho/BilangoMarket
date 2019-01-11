package com.example.mateus.multiplestables;

public class Usuario {
    private int ID;
    private int status = 1;
    private String nome;
    private String senha;
    private String email;
    private int anuncios_vendidos  = 0;
    private int anuncios_comprados = 0;
    private float total_gasto    = 0;
    private float total_recebido = 0;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAnuncios_vendidos() {
        return anuncios_vendidos;
    }

    public void setAnuncios_vendidos(int anuncios_vendidos) {
        this.anuncios_vendidos = anuncios_vendidos;
    }

    public int getAnuncios_comprados() {
        return anuncios_comprados;
    }

    public void setAnuncios_comprados(int anuncios_comprados) {
        this.anuncios_comprados = anuncios_comprados;
    }

    public float getTotal_gasto() {
        return total_gasto;
    }

    public void setTotal_gasto(float total_gasto) {
        this.total_gasto = total_gasto;
    }

    public float getTotal_recebido() {
        return total_recebido;
    }

    public void setTotal_recebido(float total_recebido) {
        this.total_recebido = total_recebido;
    }
}
