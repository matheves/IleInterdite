package ile;

import java.util.*;

public abstract class Aventurier {

    private Tuile casePos;
    private ArrayList<Carte> mainCarte;
    private Pion pion;

    /**
     *
     * @param grille
     */
    Aventurier(Pion pion, Tuile casePos, PiocheCartes pioche) {
        this.mainCarte = new ArrayList<>();
        pioche.mainDeDepart(this);
        this.pion = pion;
        this.casePos = casePos;
}

    public HashMap<Integer, Tuile> getCasesPossibles(Grille grille) {
        return grille.getCasesAdjacente(this.getPosition());
    }

    public boolean addCarte(Carte carte) {
        if (mainCarte.size() < 9) {
            mainCarte.add(carte);
            return true;
        }
        else{
        return false;}
}
    
    public Tuile getPosition() {
        return this.getCasePos();
    }

    public boolean assecher(Tuile newT, Grille grille) {
        boolean adj = false;

        HashMap<Integer, Tuile> res = getCasesPossibles(grille);
        ArrayList<Tuile> resAl = new ArrayList<>(res.values());
        for (int i = 0; i < resAl.size(); i++) {
            if (newT == resAl.get(i) || newT == this.casePos) {
                adj = true;
                break;
            }
        }

        if (adj) {
            if (newT.getEtat().getEtat() == "SECHE") {
                System.out.println("La case est déjà sèche.");
                return false;
            } else {
                
                    if (newT.getEtat().getEtat() == "INONDEE") {
                        newT.setEtat(new Etat("SECHE"));
                        System.out.println("La case a été asséchée.");
                        return true;
                    } else {
                        return false;
                    }
                }
        } else {
            System.out.println("La tuile n'est pas adjacente ou sombrée");
            return false;
        }
    }

    public boolean deplacer(Tuile newT, Grille g) {
        HashMap<Integer, Tuile> res = getCasesPossibles(g);

        ArrayList<Tuile> resAl = new ArrayList<>(res.values());
        for (int i = 0; i < resAl.size(); i++) {
            if (newT == resAl.get(i) && newT.deplacementPossible()) {

                ArrayList<Aventurier> newNbJoueursCase = new ArrayList<>();
                newNbJoueursCase = this.getCasePos().getNbJoueursCase();
                newNbJoueursCase.remove(this);
                this.getCasePos().setNbJoueursCase(newNbJoueursCase);

                this.setCasePos(newT);

                newT.getNbJoueursCase().add(this);

                System.out.println("Déplacement effectué.");
                return true;
            }
        }
        System.out.println("Déplacement non effectué.");
        return false;
    }

    public ArrayList<Carte> getMainCarte() {
        return mainCarte;
    }
    
    

    /**
     *
     * @param tuile
     */
    public void ajouterCollecCases(Tuile tuile) {
        // TODO - implement Aventurier.ajouterCollecCases
        throw new UnsupportedOperationException();
    }

    /**
     * @param casePos the casePos to set
     */
    public void setCasePos(Tuile casePos) {
        this.casePos = casePos;
    }

    /**
     * @return the casePos
     */
    public Tuile getCasePos() {
        return casePos;
    }

    public Pion getPion() {
        return pion;
    }
    public void teleportation(Tuile newT, Grille g){
    }
    
    

}