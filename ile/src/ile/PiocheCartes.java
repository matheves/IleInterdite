package ile;

import java.util.*;

/**
 *
 * @author arnautom
 */
public class PiocheCartes {

    private ArrayList<Carte> m = new ArrayList();

    PiocheCartes() {
        for (int i = 0; i < 5; i++) {
            CarteTresor ct = new CarteTresor(Tresor.PIERRE);
            m.add(ct);
        }
        for (int i = 0; i < 5; i++) {
            CarteTresor ct = new CarteTresor(Tresor.ZEPHYR);
            m.add(ct);
        }
        for (int i = 0; i < 5; i++) {
            CarteTresor ct = new CarteTresor(Tresor.CRISTAL);
            m.add(ct);
        }
        for (int i = 0; i < 5; i++) {
            CarteTresor ct = new CarteTresor(Tresor.CALICE);
            m.add(ct);
        }
        for (int i = 0; i < 2; i++) {
            CarteActionSpeciale cas = new CarteActionSpeciale(TypeAcSpe.MONTEEDESEAUX);
        }
        for (int i = 0; i < 2; i++) {
            CarteActionSpeciale cas = new CarteActionSpeciale(TypeAcSpe.SACDESABLE);
        }
        for (int i = 0; i < 3; i++) {
            CarteActionSpeciale cas = new CarteActionSpeciale(TypeAcSpe.HELICOPTERE);
        }
        Collections.shuffle(m);
    }

    public void piocher(Aventurier aventurier) {
        aventurier.addCarte(m.get(0));
        m.remove(0);
        aventurier.addCarte(m.get(0));
        m.remove(0);
        // 3 actions
        // Tirer 2 cartes
        // Tirer cartes inondations = niveau d'eau
    }

    public void remplir(Defausse d) {
        for (int i = 0; i < d.getD().size(); i++) {
            m.add(d.getD().get(i));
        }
        d.getD().clear();
        Collections.shuffle(m);
    }

}