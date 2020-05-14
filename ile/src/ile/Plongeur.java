package ile;

import java.util.*;

public class Plongeur extends Aventurier {

    public Plongeur(Pion pion, Tuile casePos, PiocheCartes pioche) {
        super(pion, casePos, pioche);
    }

    public HashMap<Integer, Tuile> getCasesPossiblesPlongeur(Grille grille) {
        return grille.getCasesAdjacentePlongeur(this.getPosition());
    }

    @Override
    public HashMap<Integer, Tuile> getCasesPossibles(Grille grille) {
        Tuile tuileL;
        HashMap<Integer, Tuile> tuilesPossibles = this.getCasesPossiblesPlongeur(grille);
        ArrayList<Tuile> tuilesTrav = new ArrayList();
        tuilesTrav.add(getPosition());

        for (int i = 0; i < tuilesTrav.size(); i++) {
            tuileL = tuilesTrav.get(i);
            Set<Map.Entry<Integer, Tuile>> set = grille.getCasesAdjacentePlongeur(tuileL).entrySet();
            Iterator<Map.Entry<Integer, Tuile>> it = set.iterator();
            Tuile tuile = new Tuile();

            while (it.hasNext()) {
                Map.Entry<Integer, Tuile> val = it.next();
                tuile = val.getValue();

                if (!tuile.getEtat().getEtat().equals("null") && !tuile.getEtat().getEtat().equals("SOMBREE") && !tuilesPossibles.containsValue(tuile)) {
                    tuilesPossibles.put(tuile.getNum(), tuile);
                }

                if (!tuile.getEtat().getEtat().equals("null") && !tuile.getEtat().getEtat().equals("SECHE") && !tuilesTrav.contains(tuile)) {
                    tuilesTrav.add(tuile);
                }
            }
        }
        tuilesPossibles.remove(getPosition().getNum());
        return tuilesPossibles;
    }

    @Override
    public boolean assecher(Tuile newT, Grille grille) {
        boolean adj = false;

        HashMap<Integer, Tuile> res = grille.getCasesAdjacente(this.getCasePos());
        ArrayList<Tuile> resAl = new ArrayList<>(res.values());

        for (int i = 0; i < resAl.size(); i++) {
            if (newT == resAl.get(i) || newT == this.getCasePos()) {
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

}
