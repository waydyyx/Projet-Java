public class TestBateau{
    public static void main(String args[]){
        Terrain t = new Terrain(7, 7);
        Port p1 = new Port("P1", 0, 0, t);
        Port p2 = new Port("P2", 7, 7, t);
        BateauMarchand bm = new BateauMarchand("BM1", 0, 0, t, null);
        BateauPirate bp = new BateauPirate("BP1", 6, 6, t);
        System.out.println(bm);
        System.out.println(bp);
        p1.arriveBateau(new BateauMarchand(1, 2, t));    
        p1.arriveBateau(new BateauMarchand(1, 3, t));    
        p1.arriveBateau(new BateauMarchand(1, 4, t));
        p1.afficheBateaux();
        System.out.println("-------------");
        p1.departBateau(p2);
        p1.afficheBateaux();

    }
}