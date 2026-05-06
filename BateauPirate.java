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

    public void attaquer (){
        if(m[x][y] instanceof BateauMarchand){
            ((BateauMarchand)m[x][y]).couler();
        }
        for (int i=x-1;i<=x+1;i=i+2){
            if(m[i][y+1] instanceof BateauMarchand){
                ((BateauMarchand)m[i][y+1]).couler();
            }
            if(m[i][y-1] instanceof BateauMarchand){
                ((BateauMarchand)m[i][y-1]).couler();
            }
        }
    }
}