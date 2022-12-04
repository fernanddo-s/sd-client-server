package br.ufc.livraria.servidor;

import br.ufc.livraria.model.Livro;
import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

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

    public String invoke(Message m) throws IOException {
        Gson gson = new Gson();
        String resposta = "";
        LivrariaEsqueleto e = new LivrariaEsqueleto();
        switch (m.methodId) {
            case "comprarLivro":
                Livro l = gson.fromJson(m.arguments, Livro.class);
                resposta = e.comprarLivro(l);
                break;
            case "renovarEstoque":
                String[] str = m.arguments.split(" ");
                resposta = e.renovarEstoque(Integer.parseInt(str[0]),Integer.parseInt(str[1]));
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
                String[] args = m.arguments.split(" ");
                resposta = e.venderLivro(Integer.parseInt(args[0]),Integer.parseInt(args[1]));
                break;

            default:
                System.out.println("Erro");
                break;
        }
        return resposta;
    }
}
