package br.ufc.livraria.server;

import br.ufc.livraria.model.ItemVenda;
import br.ufc.livraria.model.Livro;
import br.ufc.livraria.model.Transacao;
import br.ufc.livraria.model.Venda;

import java.util.ArrayList;

public class Livraria {
    private static Livraria livraria = new Livraria();
    private ArrayList<Venda> vendas = new ArrayList<>();
    private ArrayList<Livro> livros = new ArrayList<>();

    private double saldo = 100000;

    private Livraria() {
    }

    public Livraria(ArrayList<Venda> vendas, ArrayList<Livro> livros, double saldo) {
        this.vendas = vendas;
        this.livros = livros;
        this.saldo = saldo;
    }

    public static Livraria getInstance() {
        return livraria;
    }

    public ArrayList<Venda> getVendas() {
        return vendas;
    }

    public void setVendas(ArrayList<Venda> vendas) {
        this.vendas = vendas;
    }

    public ArrayList<Livro> getLivros() {
        return livros;
    }

    public void setLivros(ArrayList<Livro> livros) {
        this.livros = livros;
    }

    public double getSaldo() {
        return this.saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String comprarLivro(Livro livro) {
        if (livro.getValor() * livro.getQuantidadeEstoque() <= this.getSaldo()) {
            this.livros.add(livro);

            this.setSaldo(this.getSaldo() - (livro.getValor() * livro.getQuantidadeEstoque()));
            return "Livro comprado com sucesso! Seu novo saldo é: " + this.getSaldo();
        }
        return "Saldo insuficiente! Seu saldo é: " + this.getSaldo() + " e voĉe precisa de: " + livro.getValor() * livro.getQuantidadeEstoque();
    }

    public String renovarEstoque(Transacao t) {
        int id = t.getId();
        int qtdCompra = t.getQtd();
        if (id > this.getLivros().size()) {
            return "Livro não encontrado";
        }
        double saldoAtual = this.getSaldo();
        double valorCompra = qtdCompra * this.getLivros().get(id).getValor();
        if (saldoAtual >= valorCompra) {
            this.getLivros().get(id).setQuantidadeEstoque(this.getLivros().get(id).getQuantidadeEstoque() + qtdCompra);
            this.setSaldo(saldoAtual - valorCompra);
            return "Foram adicionados " + qtdCompra + " livro(s) no estoque! Seu novo saldo é: " + this.getSaldo();
        } else {
            return "Saldo insuficiente! Seu saldo é: " + this.getSaldo() + " e voĉe precisa de: " + livros.get(id).getValor() * qtdCompra;
        }
    }

    //deve mostrar a lista de livros em estoque
    public ArrayList<Livro> consultarEstoque() {
        return this.livros;
    }

    public ArrayList<Venda> consultarVendas() {
        return this.vendas;
    }

    ArrayList<Venda> vds = new ArrayList<>();

    public String venderLivro(Transacao t) {
        int id = t.getId();
        int qtdVenda = t.getQtd();
        if (id > this.getLivros().size() - 1) {
            return "Livro não encontrado";
        }
        double saldoAtual = this.getSaldo();
        double valorCompra = qtdVenda * this.getLivros().get(id).getValor();
        int estoqueAtual = this.getLivros().get(id).getQuantidadeEstoque();
        if (estoqueAtual >= qtdVenda) {
            this.getLivros().get(id).setQuantidadeEstoque(estoqueAtual - qtdVenda);
            this.setSaldo(saldoAtual + valorCompra);
            ItemVenda iv = new ItemVenda(livros.get(id).getNome(), qtdVenda);
            Venda v = new Venda(valorCompra, iv);
            vds.add(v);
            this.setVendas(vds);
            return "O livro " + livros.get(id).getNome() + " foi vendido";
        } else {
            return "Temos apenas " + livros.get(id).getQuantidadeEstoque() + " unidade(s) deste livro";
        }
    }
}
