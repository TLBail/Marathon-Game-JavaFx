package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Tirage {


    //attribut
    private List<Dice> dices;

    private Record joueur1;
    private Record joueur2;


    private Timer timer;
    private TimerTask timerTask;

    private long timeStart;
    private Record actualJoueur;


    //getter setter
    public long getTimePassed(){
        return System.currentTimeMillis() - timeStart + actualJoueur.timePassed;
    }

    public int getActualRemainingDistance() {
        return actualJoueur.distanceRestante;
    }

    public List<Dice> getDices() {
        return dices;
    }

    public Record getJoueur1() {
        return joueur1;
    }

    public Record getJoueur2() {
        return joueur2;
    }

    public Record getActualJoueur() {
        return actualJoueur;
    }

    //constructeur
    public Tirage(){
        dices = new ArrayList<>();
        joueur1 = new Record("joueur1");
        joueur2 = new Record("joueur2");
        actualJoueur = joueur1;

    }


    //méthode public
    public void startTimer(){
        timeStart = System.currentTimeMillis();
    }

    public void  lancerLesDe(){
        for (Dice de: dices    ) {
            de.lancerLeDe();
        }
    }

    public void  avancerUnJoueur(int distance){
        actualJoueur.distanceRestante -= distance;
        if(actualJoueur.distanceRestante <= 0){
            actualJoueur.haveFinish = true;
        }


    }

    public void resetGame(){
        dices.clear();

        for (int i = 0; i <= getNbdiceToDisplay() && i < 4; i++) {
            dices.add(new Dice());
        }
        lancerLesDe();
    }


    public int getNbdiceToDisplay(){
        return ((Integer)actualJoueur.distanceRestante).toString().length();
    }


    public void passerAuJoueurSuivant(){
        if(joueur1.haveFinish || joueur2.haveFinish){
            if(joueur1.haveFinish && joueur2.haveFinish){
                fin();
            }else  if(joueur1.haveFinish){
                actualJoueur = joueur2;
            }else{
                actualJoueur = joueur1;
            }
            System.out.println("un des joueur a terminé");
        }else{

            actualJoueur.timePassed += System.currentTimeMillis() - timeStart;
            timeStart = System.currentTimeMillis();
            if(actualJoueur == joueur1){
                actualJoueur = joueur2;
            }else{
                actualJoueur = joueur1;
            }
        }

    }


    private void fin(){

        Enregistreur enregistreur = new Enregistreur();

        ArrayList<Record> records = new ArrayList<>() {{
            add(joueur1);
            add(joueur2);
        }};
        enregistreur.enregistrerScore(records);

        Main.getInstance().controllerGameView.afficherScore();

    }
}
