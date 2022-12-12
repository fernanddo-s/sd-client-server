package br.ufc.livraria.server;

public class Message {
    public static int geraId = 0;
    private int type;
    private int id;
    private String obfReference;
    private String methodId;
    private String arguments;

    public Message() {
    }

    public Message(int type, String obfReference, String methodId, String arguments) {
        this.type = type;
        this.id = geraId++;
        this.obfReference = obfReference;
        this.methodId = methodId;
        this.arguments = arguments;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getObfReference() {
        return obfReference;
    }

    public void setObfReference(String obfReference) {
        this.obfReference = obfReference;
    }

    public String getMethodId() {
        return methodId;
    }

    public void setMethodId(String methodId) {
        this.methodId = methodId;
    }

    public String getArguments() {
        return arguments;
    }

    public void setArguments(String arguments) {
        this.arguments = arguments;
    }
}
