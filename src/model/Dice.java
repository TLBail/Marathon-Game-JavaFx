package model;

public class Dice {

    private int value;

    public int getIndex() {
        return value;
    }

    public int getValue() {
        return value + 1;
    }




    public void  lancerLeDe(){
         value = (int) (Math.random() * 6);
    }



}
