package ile;

public class CarteInondation extends Carte {
    Tuile tuile;

    public CarteInondation(Tuile tuile) {
        this.tuile = tuile;
    }

    public Tuile getTuile() {
        return tuile;
    }
    
}