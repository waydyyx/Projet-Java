public class Poisson extends Ressource implements Toxic{

    public Poisson (String nom, int quantite){
        super(nom, quantite);
    }

    public Poisson(String nom){
        this(nom, (int)(Math.random() * 20) + 5);
    }

    public Poisson(){
        this("Poisson");
    }

    public void evoluer(){
        super.setQuantite(super.getQuantite() + super.getQuantite() / 3);
    }

    public void poissonPecher(int nb){
        super.setQuantite(super.getQuantite() - nb);
        
    }

    public int getNbPoisson(){
        return super.getQuantite();
    }

}