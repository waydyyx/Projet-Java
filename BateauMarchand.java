import java.util.ArrayList;

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

    public int getValeurPiece(){
        int somme = nbPiece;
        for (int i = 0; i < tabPiece.size(); i++){
            somme += tabPiece.get(i).getValeurPiece();
        }
        return somme;
    }

    public void setPDest(Port pDest){
        this.pDest = pDest;
    }

    public void deplaceVersPort(){
        int DistX = x - pDest.getX();
        int DistY = y - pDest.getY();

        System.out.println(String.format("pDest x : %d, y: %d", pDest.getX(), pDest.getY()));
        System.out.println(String.format("x : %d, y: %d", x, y));
        if (DistX == 0 && DistY == 0)
        {
            if (LOG == true)
                System.out.println(this + " est arrive a destination");
            return;
        }
        if (DistY == 0){
            if (DistX > 0)
                this.deplacer(this.x-1,this.y);
            else
                this.deplacer(this.x+1,this.y);
            return ;
        }
        if (DistX == 0){
            if (DistY > 0)
                this.deplacer(this.x,this.y-1);
            else
                this.deplacer(this.x,this.y+1);
            return ;
        }
        if (Math.random() < 0.5){
            if (DistX > 0)
                this.deplacer(this.x-1,this.y);
            else
                this.deplacer(this.x+1,this.y);
        }
        else{
            if (DistY > 0)
                this.deplacer(this.x,this.y-1);
            else
                this.deplacer(this.x,this.y+1);
        }
    }

    public void action(){
        deplaceVersPort();
        getItem();
    }

    public String toString(){
        if (pDest == null)
            return ("BateauMarchand" + super.toString() + String.format("[PortDest: %s]", "null"));
        return ("BateauMarchand" + super.toString() + String.format("[PortDest: %s]", pDest.getNom()));
    }




}