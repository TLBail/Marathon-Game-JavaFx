package model;

import java.io.Serializable;

public class Record implements Serializable , Comparable {

    private static final int DISTANCEDEFAULT = 42195;


    private String nom;
    private int score;

    public int distanceRestante;

    public long timePassed;

    public boolean haveFinish;



    public Record(String nom){
        this.nom = nom;
        haveFinish = false;
        timePassed = 0;
        distanceRestante = DISTANCEDEFAULT;
    }

    public String getNom() {
        return nom;
    }

    @Override
    public int compareTo(Object o) {
        if(o instanceof Record){
            return score - ((Record) o).score;
        }
        return 0;
    }
}
