public class TestSimulation{
    public static void main(String args[]) {
        Simulation s = new Simulation(new Terrain(7, 7), 10, 2, 2, 2);
        s.initSimulation();
        for (int i = 0; i < 8; i++){
            s.prochainTour();          
            s.afficheRessource(5);
            s.afficheAgent(5);
            try {
                Thread.sleep(1000);
            } 
            catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        // s.afficheStatistique();
        // System.out.println(s.nbCaseVide());
    }
}