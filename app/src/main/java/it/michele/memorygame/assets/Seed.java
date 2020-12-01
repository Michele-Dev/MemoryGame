package it.michele.memorygame.assets;

public enum Seed {

    CUORI("_cuori"),
    FIORI("_fiori"),
    PICCHE("_picche"),
    QUADRI("_quadri");

    private String seed;
    Seed(String seed){
        this.seed = seed;
    }

    public String getSeed(){
        return seed;
    }
}
