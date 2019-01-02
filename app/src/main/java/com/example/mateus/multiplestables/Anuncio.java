package com.example.mateus.multiplestables;

public class Anuncio {
    private int ID;
    private int donoID;
    private String nome;
    private float preço;
    private String descricao;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getDonoID() {
        return donoID;
    }

    public void setDonoID(int donoID) {
        this.donoID = donoID;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getPreço() {
        return preço;
    }

    public void setPreço(float preço) {
        this.preço = preço;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
