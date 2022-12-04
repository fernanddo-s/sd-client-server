//package br.ufc.livraria.client;
//
//import java.io.BufferedReader;
//import java.io.DataOutputStream;
//import java.io.IOException;
//import java.net.*;
//
//public class UDPClient {
//
//    DatagramSocket socket = null;
//    BufferedReader in;
//    DataOutputStream out;
//    private InetAddress address;
//    private byte[] buffer;
//    private boolean running = true;
//
//    public UDPClient() throws IOException {
//        try {
//            this.socket = new DatagramSocket();
//            this.address = InetAddress.getByName("localhost");
//        }catch (SocketException e){
//            e.printStackTrace();
//        }catch (UnknownHostException e){
//            e.printStackTrace();
//        }catch (IOException e){
//            e.printStackTrace();
//        }
//
//        Thread t = new Thread(){
//            @Override
//            public void run() {
//                while (running){
//                    DatagramPacket p = new DatagramPacket(buffer, buffer.length);
//                    try {
//                        socket.receive(p);
//                        String str = new String(p.getData(),0,p.getLength());
//                        System.out.println("Mensage Server: " + str);
//                        running = false;
//                    }catch (IOException e){
//                        e.printStackTrace();
//                    }
//                }
//            }
//        };
//        t.start();
//    }
//
//    public void sendMenssage(String menssage){
//        this.buffer = menssage.getBytes();
//        DatagramPacket packet = new DatagramPacket(this.buffer, this.buffer.length, address, 9880);
//        try {
//            //enviando pacote para o servidor
//            this.socket.send(packet);
//        }catch (IOException e){
//            e.printStackTrace();
//        }
//    }
//
//
////    public static void main(String args[]) throws Exception {
////        System.out.println("-------------------Cliente-------------------");
////        try {
////            String servidorHostName = new String("127.0.0.1"); //especificando ip do servidor atravÃ©s de uma string
////            if (args.length > 0)
////                servidorHostName = args[0];
////
////            DatagramSocket clienteSocket = new DatagramSocket(); //Instancia de ClienteSocket
////            BufferedReader user_buff = new BufferedReader(new InputStreamReader(System.in)); //buffer que contÃ©m mensagem
////            InetAddress IPAddress = InetAddress.getByName(servidorHostName);  //endereÃ§o ip do servidor destino
////
////            System.out.println("Preparando para comunicaÃ§Ã£o com Servidor de IP " + IPAddress + ") via porta UDP 9880");
////
////            byte[] envioDado = new byte[1024]; //comprimento da mensagem
////            byte[] recebimentoDado = new byte[1024]; //comprimento da mensagem
////
////            System.out.print("Escreva uma mensagem: ");  //cliente escreve mensagem que deseja enviar para o servidor
////            String msg = user_buff.readLine(); //mensagem Ã© lida e atribuida para - msg -
////            envioDado = msg.getBytes();
////            /* ------------------------------------------- */
////
////            /* ----- Envio de Mensagem -------------------- */
////            System.out.println("Enviando mensagem de " + envioDado.length + " bytes para o servidor");
////            DatagramPacket envioPacote = new DatagramPacket(envioDado, envioDado.length, IPAddress, 9880); //envio de mensagem para o servidor
////            clienteSocket.send(envioPacote);
////            /* ------------------------------------------- */
////            clienteSocket.close();
////        } catch (UnknownHostException ex) {
////            System.err.println(ex);
////        } catch (IOException ex) {
////            System.err.println(ex);
////        }
////    }
//}
