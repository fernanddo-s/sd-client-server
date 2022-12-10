package br.ufc.livraria.model;

public class Transacao {
    int id;
    int qtd;

    public Transacao() {
    }

    public Transacao(int id, int qtd) {
        this.id = id;
        this.qtd = qtd;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQtd() {
        return qtd;
    }

    public void setQtd(int qtd) {
        this.qtd = qtd;
    }
}
