package br.ufc.livraria.servidor;

public class Message {
    public static int geraId = 0;
    int type;
    int id;
    String obfReference;
    String methodId;
    String arguments;

    public Message() {
    }

    public Message(int type, String obfReference, String methodId, String arguments) {
        this.type = type;
        this.id = geraId++;
        this.obfReference = obfReference;
        this.methodId = methodId;
        this.arguments = arguments;
    }

    public String getObjectReference() {
        return this.obfReference;
    }

    public String getMethodId() {
        return this.methodId;
    }
}
