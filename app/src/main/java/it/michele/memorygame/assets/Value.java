package it.michele.memorygame.assets;

public enum Value {

    ASSO("asso"),
    DUE("due"),
    TRE("tre"),
    QUATTRO("quattro"),
    CINQUE("cinque"),
    SEI("sei"),
    SETTE("sette"),
    OTTO("otto"),
    NOVE("nove"),
    DIECI("dieci"),
    J("j"),
    Q("q"),
    K("k");

    private String value;
    Value(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
