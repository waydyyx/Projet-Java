public class BateauPirate extends Bateau{

    public BateauPirate(String nom, int x, int y, Terrain t){
        super(nom, x, y, t);
    }

    public BateauPirate(int x, int y, Terrain t){
        this("BP"+(cpt+1), x, y, t);
    }

    public String toString(){
        return ("BateauPirate" + super.toString());
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
}