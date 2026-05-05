public class BateauMarchand extends Bateau{
    private Port pDest;

    public BateauMarchand(String nom, int x, int y, Terrain t, Port pDest){
        super(nom, x, y, t);
        this.pDest = pDest;
        // System.out.println(nom);
    }

    public BateauMarchand(int x, int y, Terrain t){
        this("BM" + (cpt+1), x, y, t, null);
    }


    public void setPDest(Port pDest){
        this.pDest = pDest;
    }


    public String toString(){
        if (pDest == null)
            return ("BateauMarchand" + super.toString() + String.format("[PortDest: %s]", "null"));
        return ("BateauMarchand" + super.toString() + String.format("[PortDest: %s]", pDest.getNom()));
    }

    public void deplaceVersPort(){
        int DistX = x - pDest.getX();
        int DistY = y - pDest.getY();
        if (DistY == 0){
            if (DistX > 0){
                this.deplacer(this.x-1,this.y);
            }
            else{
                this.deplacer(this.x+1,this.y);
            }
        }
        if (DistX == 0){
            if (DistY > 0){
                this.deplacer(this.x,this.y-1);
            }
            else{
                this.deplacer(this.x,this.y+1);
            }
        }
        if (Math.random()<0.5){
            if (DistX > 0){
                this.deplacer(this.x-1,this.y);
            }
            else{
                this.deplacer(this.x+1,this.y);
            }
        }
        else{
            if (DistX > 0){
                this.deplacer(this.x-1,this.y);
            }
            else{
                this.deplacer(this.x+1,this.y);
            }
        }
    }

}