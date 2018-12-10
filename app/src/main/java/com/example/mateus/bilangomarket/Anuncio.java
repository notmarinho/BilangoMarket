package com.example.mateus.bilangomarket;

public class Anuncio {
    private long   ID;
    private String nome;
    private String descrição;
    private Float  preço;

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescrição() {
        return descrição;
    }

    public void setDescrição(String descrição) {
        this.descrição = descrição;
    }

    public Float getPreço() {
        return preço;
    }

    public void setPreço(Float preço) {
        this.preço = preço;
    }
}
