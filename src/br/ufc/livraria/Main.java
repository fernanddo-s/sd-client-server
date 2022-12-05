package br.ufc.livraria;

import br.ufc.livraria.model.Livro;
import br.ufc.livraria.servidor.Livraria;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        int id;
        int qtdCompra;
        int qtdVenda;
        int estoqueAtual;
        double saldoAtual;
        double valorCompra;

        ArrayList<Livro> bd_livros = new ArrayList<>();
        bd_livros.add(new Livro("Harry Potter e a Pedra Filosofal", 50, 7, "aventura", "ilustracoes"));
        bd_livros.add(new Livro("Robison Crusoe", 55, 10, "aventura", "ilustracoes"));
        bd_livros.add(new Livro("Dom Quixote", 48, 12, "aventura", "ilustracoes"));
        bd_livros.add(new Livro("Romeu e Julieta", 29, 2, "drama", "capa dura"));
        bd_livros.add(new Livro("A paciente silenciosa", 35, 5, "drama", "capa dura"));
        bd_livros.add(new Livro("Hamlet", 25, 8, "drama", "capa dura"));
        bd_livros.add(new Livro("Divina Comédia", 25, 9, "comedia", "capa brochura"));
        bd_livros.add(new Livro("Paraíso", 30, 10, "comedia", "capa brochura"));
        bd_livros.add(new Livro("Até o Fim do Mundo", 15, 4, "comedia", "capa brochura"));

        Livraria livraria = Livraria.getInstance();
        livraria.setLivros(bd_livros);

        while (true) {
            menu();
            Scanner scanner = new Scanner(System.in);
            String op = scanner.nextLine();
            switch (op) {
                case "0":
                    return;
                case "1":
                    //Comprar livro(adicionar um novo livro no estoque - com quantidade)
                    System.out.println("Qual o nome do livro?");
                    String nome = scanner.nextLine();
                    System.out.println("Qual o valor do livro?");
                    double valor = Double.parseDouble(scanner.nextLine());
                    System.out.println("Qual a quantidade de livros?");
                    int quantidade = Integer.parseInt(scanner.nextLine());
                    System.out.println("Qual o genero do livro?(Aventura, drama ou comedia)");
                    String genero = scanner.nextLine();
                    String caracteristica = "";
                    if (genero.equalsIgnoreCase("aventura")) {
                        caracteristica = "ilustracoes";
                    } else if (genero.equalsIgnoreCase("drama")) {
                        caracteristica = "capa dura";
                    } else if (genero.equalsIgnoreCase("comedia")) {
                        caracteristica = "capa brochura";
                    }
                    Livro l = new Livro(nome, valor, quantidade, genero, caracteristica);
                    System.out.println(livraria.comprarLivro(l));
                    System.out.println("Aperte Enter para voltar ao menu");
                    scanner.nextLine();
                    break;
                case "2":
                    //Comprar livro(alterar a quantidade de um livro já existente e o saldo)
                    for (int i = 0; i < livraria.consultarEstoque().size(); i++){
                        System.out.println(livraria.consultarEstoque().get(i));
                    }
                    System.out.println("Informe o id o livro que deseja comprar");
                    id = Integer.parseInt(scanner.nextLine());
                    System.out.println("Agora informe quantos livros deseja comprar");
                    qtdCompra = Integer.parseInt(scanner.nextLine());
                    //passar esses parametros pro metodo
                    System.out.println(livraria.renovarEstoque(id, qtdCompra));
                    System.out.println("Aperte Enter para voltar ao menu");
                    scanner.nextLine();
                    break;
                case "3":
                    //Vender livro(alterar a quantidade de um livro já existente e o saldo)
                    boolean vendendo = true;
                    while (vendendo) {
                        for (int i = 0; i < livraria.consultarEstoque().size(); i++){
                            System.out.println(livraria.consultarEstoque().get(i));
                        }
                        System.out.println("Qual o id do livro que deseja vender?");
                        id = Integer.parseInt(scanner.nextLine());
                        System.out.println("Qual a quantidade que deseja vender?");
                        qtdVenda = Integer.parseInt(scanner.nextLine());
                        System.out.println(livraria.venderLivro(id, qtdVenda));
                        System.out.println("deseja vender mais um livro?S/N");
                        if (scanner.nextLine().equals("N"))
                            vendendo = false;
                    }
                    System.out.println("Aperte Enter para voltar ao menu");
                    scanner.nextLine();
                    break;
                case "4":
                    //consultar saldo
                    System.out.println(livraria.getSaldo());
                    System.out.println("Aperte Enter para voltar ao menu");
                    scanner.nextLine();
                    break;
                case "5":
                    //consultar vendas
                    for (int i = 0; i < livraria.consultarVendas().size(); i++){
                        System.out.println(livraria.consultarVendas().get(i));
                    }
                    System.out.println("Aperte Enter para voltar ao menu");
                    scanner.nextLine();
                    break;
                case "6":
                    //consultar estoque de livros
                    for (int i = 0; i < livraria.consultarEstoque().size(); i++){
                        System.out.println(livraria.consultarEstoque().get(i));
                    }
                    System.out.println("Aperte Enter para voltar ao menu");
                    scanner.nextLine();
                    break;
                default:
                    System.out.println("Escolha uma opção válida!Ok?:)");
                    System.out.println("Aperte Enter para voltar ao menu");
                    scanner.nextLine();
            }
        }
    }

    public static void menu() {
        System.out.println("Seja bem-vindo a livraria do Sr Code!\n" +
                "O que quer fazer?\n" +
                "0 - Fechar o sistema\n" +//ok
                "1 - Comprar um livro\n" +//ok
                "2 - Renovar estoque de um livro\n" +//ok -> falta fazer mais de um livro
                "3 - Vender livros\n" +//Falta adicionar a venda de livros diferentes
                "4 - Consultar Saldo\n" +//ok
                "5 - Consultar Vendas\n" +//ok
                "6 - Consultar estoque de livros");//ok
    }
}