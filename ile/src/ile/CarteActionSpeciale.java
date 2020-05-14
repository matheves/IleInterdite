package ile;

public class CarteActionSpeciale extends Carte {
    private TypeAcSpe type;

    public CarteActionSpeciale(TypeAcSpe type) {
        this.type = type;
    }

    @Override
    public TypeAcSpe getType() {
        return type;
    }
    
}