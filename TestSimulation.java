public class TestSimulation{
    public static void main(String args[]){
        Simulation s = new Simulation(new Terrain(7, 7), 2, 2, 2, 2);
        s.initSimulation();
        s.afficheRessource(5);
        s.afficheAgent(5);
        System.out.println(s.nbCaseVide());
    }
}