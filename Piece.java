public class Piece extends Ressource{
    private int rarete;

    public Piece (String nom, int quantite, int rarete){
        super(nom, quantite);
        this.rarete = rarete;
    }

    public Piece(String nom, int quantite){
        this(nom, quantite, (int)(Math.random() * 5) + 1);
    }

}