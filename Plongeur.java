package ile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Plongeur extends Aventurier {
    
    public Plongeur(Pion pion, Tuile casePos) {
        super(pion, casePos);
    }
    
    public HashMap<Integer,Tuile> getCasesPossiblesPlongeur(Grille grille) {
        return grille.getCasesAdjacentePlongeur(this.getPosition());
    }
    
    @Override
    public HashMap<Integer, Tuile> getCasesPossibles(Grille grille){
        
        Tuile tuileL;
        HashMap<Integer,Tuile> tuilesPossibles = this.getCasesPossiblesPlongeur(grille);
        
        ArrayList<Tuile> tuilesTrav = new ArrayList();
        tuilesTrav.add(getPosition());
        
        
        for (int i = 0; i < tuilesTrav.size(); i++) {
            tuileL = tuilesTrav.get(i);
            
            Set<Map.Entry<Integer, Tuile>> set = grille.getCasesAdjacentePlongeur(tuileL).entrySet();
            Iterator<Map.Entry<Integer, Tuile>> it = set.iterator();
            Tuile tuile = new Tuile();
            while(it.hasNext()) {
                Map.Entry<Integer, Tuile> val = it.next();
                tuile = val.getValue();
                if (!tuile.getEtat().getEtat().equals("null") && !tuile.getEtat().getEtat().equals("SOMBREE") && !tuilesPossibles.containsValue(tuile)){
                    tuilesPossibles.put(tuile.getNum(),tuile);
                }
                if (!tuile.getEtat().getEtat().equals("null") && !tuile.getEtat().getEtat().equals("SECHE") && !tuilesTrav.contains(tuile)){
                    tuilesTrav.add(tuile);
                }    
            }
        }
        
        tuilesPossibles.remove(getPosition().getNum());
        return tuilesPossibles;
    
    }
    
}