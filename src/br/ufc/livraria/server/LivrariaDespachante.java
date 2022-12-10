package br.ufc.livraria.server;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.*;

public class LivrariaDespachante {
//    public byte[] selecionaEqueleto(Message request) {
//
//        Class<?> objRef = null;
//        Method method = null;
//        byte[] resposta = null;
//        try {
//            objRef = Class.forName("addressBook."
//                    + request.getObjectReference() + "Esqueleto");
//            String methodName = request.getMethodId();
//            System.out.println("Executando: " + methodName);
//            method = objRef.getMethod(methodName, ByteString.class);
//            resposta = (byte[]) (method.invoke(objRef.newInstance(),
//                    request.getArguments()));
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (SecurityException e) {
//            e.printStackTrace();
//        } catch (NoSuchMethodException e) {
//            e.printStackTrace();
//        } catch (IllegalArgumentException e) {
//            e.printStackTrace();
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();
//        } catch (InstantiationException e) {
//            e.printStackTrace();
//        }
//        return resposta;
//    }

    public String invoke(String str) throws IOException {
//        GsonBuilder gsonBuilder = new GsonBuilder().setLenient();
//        Message m = gsonBuilder.create().fromJson(str, Message.class);
        Reader reader = new StringReader(str);
        JsonReader r = new JsonReader(reader);
        Gson gson = new Gson();
        Message m = gson.fromJson(r, Message.class);
        String resposta = "";
        LivrariaEsqueleto e = new LivrariaEsqueleto();
        switch (m.methodId) {
            case "comprarLivro":
                resposta = e.comprarLivro(m.arguments);
                break;
            case "renovarEstoque":
                resposta = e.renovarEstoque(m.arguments);
                break;
            case "consultarEstoque":
                resposta = e.consultarEstoque();
                break;
            case "consultarVendas":
                resposta = e.consultarVendas();
                break;
            case "consultarSaldo":
                resposta = e.consultarSaldo();
                break;
            case "venderLivro":
//                String[] args = m.arguments.split(" ");
                resposta = e.venderLivro(m.arguments);
                break;

            default:
                System.out.println("Erro");
                break;
        }
        return resposta;
    }
}
