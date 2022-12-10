package br.ufc.livraria.servidor;

import br.ufc.livraria.client.UDPClient;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPServer {
    public static int PORT = 7898;

    public static void main(String[] args) throws IOException {
        DatagramSocket servidorSocket = null;
        try {
            servidorSocket = new DatagramSocket(UDPClient.PORT);
            System.out.println("Servidor criado na porta: "+UDPClient.PORT);
        }catch (Exception e){
            System.out.println("Erro: "+e.getMessage());
        }

        byte[] dadosRecebidos = new byte[1024];
        byte[] dadosEnviados = new byte[1024];

        while (true){
            DatagramPacket packet = new DatagramPacket(dadosRecebidos, dadosRecebidos.length);
            servidorSocket.receive(packet);
            String mensagem = new String(packet.getData(),0, dadosRecebidos.length);

            LivrariaDespachante d = new LivrariaDespachante();

            dadosEnviados = d.invoke(mensagem).getBytes();

            DatagramPacket pacoteEnviado = new DatagramPacket(dadosEnviados, dadosEnviados.length, packet.getAddress(), packet.getPort());
            servidorSocket.send(pacoteEnviado);
            System.out.println("Mensagem enviada de volta");
            dadosEnviados = new byte[1024];
            dadosRecebidos = new byte[1024];
        }
    }
}