package ile;

import ile.Aventurier;
import ile.Etat;
import java.io.File;
import java.util.*;

public class Tuile {
    
        private int num;
        private String nom;
	private ArrayList<Aventurier> nbJoueursCase;
	private int posX;
	private int posY;
	private Etat etat;
        
        public Tuile(Etat etat, int posX,int posY){
            nbJoueursCase = new ArrayList<>();
            this.etat = etat;
            this.posX = posX-1;
            this.posY = posY-1;
        }

        
        public Tuile(int num,String nom,int posX, int posY,Etat etat) {
            this.num = num;
            this.etat = etat;
            this.nom = nom;
            this.posX = posX -1;
            this.posY = posY-1;
            nbJoueursCase = new ArrayList<>();
        }
        
        public Tuile(){
            
        }

	public Etat getEtat() {
            return this.etat;
	}

    // <editor-fold defaultstate="collapsed" desc="Getters&Setters">
    /* Getters and Setters */
    /**
     * @return the num
     */
    public int getNum() {
        return num;
    }

    /**
     * @param num the num to set
     */
    public void setNum(int num) {
        this.num = num;
    }

    /**
     * @return the nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * @param nom the nom to set
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * @return the nbJoueursCase
     */
    public ArrayList<Aventurier> getNbJoueursCase() {
        return nbJoueursCase;
    }

    /**
     * @param nbJoueursCase the nbJoueursCase to set
     */
    public void setNbJoueursCase(ArrayList<Aventurier> nbJoueursCase) {
        this.nbJoueursCase = nbJoueursCase;
    }

    /**
     * @return the posX
     */
    public int getPosX() {
        return posX;
    }

    /**
     * @return the posY
     */
    public int getPosY() {
        return posY;
    }

    /**
     * @param posY the posY to set
     */

    /**
     * @param etat the etat to set
     */
    public void setEtat(Etat etat) {
        this.etat = etat;
    }
    
    

// </editor-fold>

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
    
}