package ile;

import java.util.ArrayList;
import java.util.HashMap;

public class Pilote extends Aventurier {

    public Pilote(Pion pion, Tuile casePos, PiocheCartes pioche) {
        super(pion, casePos, pioche);
    }

    public void teleportation(Tuile newT, Grille g) {
        Tuile t = this.getCasePos();

        HashMap<Integer, Tuile> res = g.getCases();

        ArrayList<Tuile> resAl = new ArrayList<>(res.values());
        resAl.size();

        for (int i = 0; i < resAl.size(); i++) {

            if (resAl.get(i).deplacementPossible() != true) {
                resAl.remove(i);
            }
        }

        for (int i = 0; i < resAl.size(); i++) {
            if (t == resAl.get(i)) {

                ArrayList<Aventurier> newNbJoueursCase = new ArrayList<>();
                newNbJoueursCase = this.getCasePos().getNbJoueursCase();
                newNbJoueursCase.remove(this);
                this.getCasePos().setNbJoueursCase(newNbJoueursCase);

                this.setCasePos(newT);

                newT.getNbJoueursCase().add(this);

                System.out.println("teleportation effectué.");
            }
        }

        System.out.println("teleportation non effectué.");

    }

}
