package ile;

import java.util.*;

/**
 *
 * @author arnautom
 */
public class Defausse {

    private ArrayList<Carte> d;

    Defausse() {
        d = new ArrayList();
    }

    public void addCarte(Carte carte) {
        d.add(carte);
    }

    public ArrayList<Carte> getD() {
        return d;
    }

}