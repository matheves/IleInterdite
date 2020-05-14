package ile;

import java.util.*;
import java.util.Map.Entry;

public class Grille {

    private HashMap<Integer, Tuile> cases;

    Grille() {
        cases = new HashMap<>();
    }
    
    public HashMap<Integer, Tuile> getCasesDiagonales(Tuile tuile) {
        HashMap<Integer, Tuile> res = new HashMap<>();
        
        Set<Entry<Integer, Tuile>> set = getCases().entrySet();
        Iterator<Entry<Integer, Tuile>> it = set.iterator();
        Tuile t = new Tuile();
        while (it.hasNext()) {
            Entry<Integer, Tuile> val = it.next();
            t = val.getValue();
            if ((t.getPosX() == tuile.getPosX() + 1 || t.getPosX() == tuile.getPosX() - 1) && (t.getPosY() == tuile.getPosY() + 1 || t.getPosY() == tuile.getPosY() - 1) && t.deplacementPossible()) {
                res.put(val.getKey(), t);
            }
        }
        return res;
    }



    public HashMap<Integer, Tuile> getCasesAdjacente(Tuile tuile) {
        HashMap<Integer, Tuile> res = new HashMap<>();
        
        Set<Entry<Integer, Tuile>> set = getCases().entrySet();
        Iterator<Entry<Integer, Tuile>> it = set.iterator();
        Tuile t = new Tuile();
        while (it.hasNext()) {
            Entry<Integer, Tuile> val = it.next();
            t = val.getValue();
            if ((t.deplacementPossible()) && (t.getPosX() == tuile.getPosX() && (t.getPosY() == tuile.getPosY() + 1 || t.getPosY() == tuile.getPosY() - 1)) || (t.getPosY() == tuile.getPosY() && (t.getPosX() == tuile.getPosX() + 1 || t.getPosX() == tuile.getPosX() - 1))) {
                res.put(val.getKey(), t);
            }
        }
        return res;
    }

    public HashMap<Integer, Tuile> getCasesAdjacentePlongeur(Tuile tuile) {
        HashMap<Integer, Tuile> res = new HashMap<>();
        
        Set<Entry<Integer, Tuile>> set = getCases().entrySet();
        Iterator<Entry<Integer, Tuile>> it = set.iterator();
        Tuile t = new Tuile();
        while (it.hasNext()) {
            Entry<Integer, Tuile> val = it.next();
            t = val.getValue();
            if ((t.deplacementPossiblePlongeur()) && (t.getPosX() == tuile.getPosX() && (t.getPosY() == tuile.getPosY() + 1 || t.getPosY() == tuile.getPosY() - 1)) || (t.getPosY() == tuile.getPosY() && (t.getPosX() == tuile.getPosX() + 1 || t.getPosX() == tuile.getPosX() - 1))) {
                res.put(val.getKey(), t);
            }
        }
        return res;
    }

        public HashMap<Integer, Tuile> getCases() {
        return this.cases;
    }
}
