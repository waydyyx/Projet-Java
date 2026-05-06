public class BateauPirate extends Bateau{

    public BateauPirate(String nom, int x, int y, Terrain t, Agent[][] m){
        super(nom, x, y, t, m);
    }

    public BateauPirate(int x, int y, Terrain t, Agent[][] m){
        this("BP" + (cpt+1), x, y, t, m);
    }

    public String getNom(){
        return (nom);
    }

    public void deplace_rand(){
        if (Math.random()<0.5){
            if (Math.random()<0.5){
                this.deplacer(x+1,y);
            }
            else{
                this.deplacer(x-1,y);
            }
        }
        else{
            if (Math.random()<0.5){
                this.deplacer(x,y+1);
            }
            else{
                this.deplacer(x,y-1);
            }
        }
    }

    public void action(){   
        this.deplace_rand();
        this.attaquer();
    }

    public String toString(){
        return ("BateauPirate" + super.toString());
    }


    private void coulerSiMarchand(int ligne, int col) {
        if (ligne >= 0 && ligne < m.length && col >= 0 && col < m[0].length
                && m[ligne][col] instanceof BateauMarchand)
            ((BateauMarchand) m[ligne][col]).couler();
    }

    public void attaquer() {
        coulerSiMarchand(x - 1, y - 1); // case propre du pirate
        coulerSiMarchand(x,     y - 1); // bas
        coulerSiMarchand(x - 2, y - 1); // haut
        coulerSiMarchand(x - 1, y);     // droite
        coulerSiMarchand(x - 1, y - 2); // gauche
    }
}