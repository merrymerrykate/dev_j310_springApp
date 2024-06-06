package com.example.dev_j310_springapp.common.dto;

public enum ClientType {
    INDIVIDUAL("Физическое лицо"),
    LEGAL("Юридическое лицо");

    private String type;

    ClientType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }


    public static ClientType getClientType(String type){
        if(type == null) return null;
        for(ClientType typ : ClientType.values()){
           if(typ.type.equals(type))
               return typ;
        }
        return null;
    }
}
