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

}