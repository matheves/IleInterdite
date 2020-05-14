package ile;

import java.util.*;

public class CarteTresor extends Carte {
    private Tresor tresor;

    public CarteTresor(Tresor tresor) {
        this.tresor = tresor;
    }
    
    public Tresor getTresor() {
        return tresor;
    }
    
}