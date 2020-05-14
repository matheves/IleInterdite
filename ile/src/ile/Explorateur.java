package ile;

import java.util.*;
import java.util.Map.Entry;

public class Explorateur extends Aventurier {

    public Explorateur(Pion pion, Tuile casePos, PiocheCartes pioche) {
        super(pion, casePos, pioche);
    }

    @Override
    public HashMap<Integer, Tuile> getCasesPossibles(Grille grille) {
        HashMap<Integer, Tuile> casespos = new HashMap();
        casespos = grille.getCasesAdjacente(this.getPosition());

        Set<Map.Entry<Integer, Tuile>> set = grille.getCasesDiagonales(this.getPosition()).entrySet();
        Iterator<Map.Entry<Integer, Tuile>> it = set.iterator();
        while (it.hasNext()) {
            Entry<Integer, Tuile> val = it.next();
            casespos.put(val.getKey(), val.getValue());
        }
        return casespos;
    }
}
