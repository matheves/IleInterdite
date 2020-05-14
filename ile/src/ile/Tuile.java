package ile;

import java.util.*;

public class Tuile {

    private int num;
    private String nom;
    private ArrayList<Aventurier> nbJoueursCase;
    private int posX;
    private int posY;
    private Etat etat;

    public Tuile(Etat etat, int posX, int posY) {
        nbJoueursCase = new ArrayList<>();
        this.etat = etat;
        this.posX = posX - 1;
        this.posY = posY - 1;
    }

    public Tuile(int num, String nom, int posX, int posY, Etat etat) {
        this.num = num;
        this.etat = etat;
        this.nom = nom;
        this.posX = posX - 1;
        this.posY = posY - 1;
        nbJoueursCase = new ArrayList<>();
    }

    public Tuile() {
    }

    public boolean deplacementPossible() {
        return !((this.posX == 0 && this.posY == 0) || (this.posX == 1 && this.posY == 0)
                || (this.posX == 0 && this.posY == 1) || (this.posX == 4 && this.posY == 0)
                || (this.posX == 5 && this.posY == 0) || (this.posX == 5 && this.posY == 1)
                || (this.posX == 0 && this.posY == 4) || (this.posX == 0 && this.posY == 5)
                || (this.posX == 1 && this.posY == 5) || (this.posX == 4 && this.posY == 5)
                || (this.posX == 5 && this.posY == 4) || (this.posX == 5 && this.posY == 5)
                || this.etat.getEtat().equals("SOMBREE"));
    }

    public boolean deplacementPossiblePlongeur() {
        return !((this.posX == 0 && this.posY == 0) || (this.posX == 1 && this.posY == 0)
                || (this.posX == 0 && this.posY == 1) || (this.posX == 4 && this.posY == 0)
                || (this.posX == 5 && this.posY == 0) || (this.posX == 5 && this.posY == 1)
                || (this.posX == 0 && this.posY == 4) || (this.posX == 0 && this.posY == 5)
                || (this.posX == 1 && this.posY == 5) || (this.posX == 4 && this.posY == 5)
                || (this.posX == 5 && this.posY == 4) || (this.posX == 5 && this.posY == 5));
    }

    // <editor-fold defaultstate="collapsed" desc="Getters&Setters">
    /* Getters and Setters */
    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public ArrayList<Aventurier> getNbJoueursCase() {
        return nbJoueursCase;
    }

    public void setNbJoueursCase(ArrayList<Aventurier> nbJoueursCase) {
        this.nbJoueursCase = nbJoueursCase;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }
    
    public void setEtat(Etat etat) {
        this.etat = etat;
    }

    public Etat getEtat() {
        return this.etat;
    }

// </editor-fold>
    
}
