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
        if (m[x - 1][y - 1] instanceof BateauMarchand)
            ((BateauMarchand)m[x - 1][y - 1]).couler();

        if(x < m.length  && x >= 0 && m[x][y - 1] instanceof BateauMarchand)
            ((BateauMarchand)m[x][y - 1]).couler();

        if(x < m.length  && x >= 0 && m[x - 2][y - 1] instanceof BateauMarchand)
            ((BateauMarchand)m[x - 2][y - 1]).couler();

        if(y < m[x].length  && y >= 0 && m[x - 1][y] instanceof BateauMarchand)
            ((BateauMarchand)m[x - 1][y]).couler();

        if(y < m[x].length  && y >= 0 && m[x - 1][y - 2] instanceof BateauMarchand)
            ((BateauMarchand)m[x - 1][y - 2]).couler();
    }
}