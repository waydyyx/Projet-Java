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


    private void coulerSiMarchand(int x, int y) {
        if (x >= 0 && x < m.length && y >= 0 && y < m[0].length && m[x][y] instanceof BateauMarchand)
            ((BateauMarchand) m[x][y]).couler(); // pas x - 1 et y - 1 car en appelant la fonction il ya deja les decalages
    }

    public void attaquer() {
        coulerSiMarchand(x - 1, y - 1); // case propre du pirate
        coulerSiMarchand(x,     y - 1); // bas
        coulerSiMarchand(x - 2, y - 1); // haut
        coulerSiMarchand(x - 1, y);     // droite
        coulerSiMarchand(x - 1, y - 2); // gauche
    }
}