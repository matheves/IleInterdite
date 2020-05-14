package ile;

import java.util.*;

/**
 *
 * @author arnautom
 */
public class PiocheInondation {

    private ArrayList<Carte> p = new ArrayList<>();

    PiocheInondation(Grille g) {
        HashMap<Integer, Tuile> m = g.getCases();
        ArrayList<Tuile> tuileAl = new ArrayList<>(m.values());

        for (int i = 0; i < tuileAl.size(); i++) {
            CarteInondation carte = new CarteInondation(tuileAl.get(i));
            p.add(carte);
        }
        
        Collections.shuffle(p);
    }

    public void remplir(Defausse d) {
        for (int i = 0; i < d.getD().size(); i++) {
            p.add(d.getD().get(i));
        }
        
        d.getD().clear();
        Collections.shuffle(p);
    }

}