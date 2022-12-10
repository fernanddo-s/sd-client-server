package br.ufc.livraria.client;

import br.ufc.livraria.model.Livro;

import java.io.IOException;
import java.util.Scanner;

public class LivrariaCliente {
    Proxy proxy;

    public LivrariaCliente() throws IOException {
        proxy = new Proxy();
    }

    public int selecionaOperacao() throws IOException {
        Scanner scanner = new Scanner(System.in);
        int operacao = 0;
        String opt = null;
        do {
            opt = scanner.nextLine();
        } while (opt.equals("\n") || opt.equals("") || opt.isEmpty());
        operacao = Integer.parseInt(opt);

        switch (operacao) {
            case 1:
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
                Livro l = new Livro(nome, valor, quantidade, genero,caracteristica);
                System.out.println(proxy.comprarLivro(l));
                System.out.println("Aperte Enter para voltar ao menu");
                scanner.nextLine();
                break;
            case 2:
                //Comprar livro(alterar a quantidade de um livro já existente e o saldo)
                for (int i = 0; i < proxy.consultarEstoque().size(); i++){
                    System.out.println(proxy.consultarEstoque().get(i));
                }
                System.out.println("Informe o id o livro que deseja comprar");
                int id = Integer.parseInt(scanner.nextLine());
                System.out.println("Agora informe quantos livros deseja comprar");
                int qtdCompra = Integer.parseInt(scanner.nextLine());
                //passar esses parametros pro metodo
                System.out.println(proxy.renovarEstoque(id, qtdCompra));
                System.out.println("Aperte Enter para voltar ao menu");
                scanner.nextLine();
                break;
            case 3:
                boolean vendendo = true;
                while (vendendo) {
                    for (int i = 0; i < proxy.consultarEstoque().size(); i++){
                        System.out.println(proxy.consultarEstoque().get(i));
                    }
                    System.out.println("Qual o id do livro que deseja vender?");
                    id = Integer.parseInt(scanner.nextLine());
                    System.out.println("Qual a quantidade que deseja vender?");
                    int qtdVenda = Integer.parseInt(scanner.nextLine());
                    System.out.println(proxy.venderLivro(id, qtdVenda));
                    System.out.println("deseja vender mais um livro?S/N");
                    if (scanner.nextLine().equals("N"))
                        vendendo = false;
                }
                System.out.println("Aperte Enter para voltar ao menu");
                scanner.nextLine();
                break;
            case 4:
                System.out.println(proxy.consultarSaldo());
                System.out.println("Aperte Enter para voltar ao menu");
                scanner.nextLine();
                break;
            case 5:
                for (int i = 0; i < proxy.consultarVendas().size(); i++){
                    System.out.println(proxy.consultarVendas().get(i));
                }
                System.out.println("Aperte Enter para voltar ao menu");
                scanner.nextLine();
                break;
            case 6:
                for (int i = 0; i < proxy.consultarEstoque().size(); i++){
                    System.out.println(proxy.consultarEstoque().get(i));
                }
                System.out.println("Aperte Enter para voltar ao menu");
                scanner.nextLine();
                break;
            case 0:
                proxy.finaliza();
                break;
            case 9:
                proxy.tetse("String:mensagem");
                break;
            default:
                System.out.println("Operação invalida, tente outra.");
                break;
        }
        return operacao;
    }

    public static void printMenu() {
        System.out.println("Seja bem-vindo a livraria do Sr Code!\n" +
                "O que quer fazer?\n" +
                "0 - Fechar o sistema\n" +
                "1 - Comprar um livro\n" +
                "2 - Renovar estoque de um livro\n" +
                "3 - Vender livros\n" +
                "4 - Consultar Saldo\n" +
                "5 - Consultar Vendas\n" +
                "6 - Consultar estoque de livros");
    }

    public static void main(String[] args) throws IOException {

        LivrariaCliente livrariaCliente = new LivrariaCliente();
        int operacao = -1;
        do {
            livrariaCliente.printMenu();
            try {
                operacao = livrariaCliente.selecionaOperacao();
            } catch (IOException ex) {
                System.out.println("Escolha uma das operações pelo número");
            }
        } while (operacao != 0);
    }
}