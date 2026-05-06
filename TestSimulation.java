public class TestSimulation{
    public static void main(String args[]) {
        Simulation s = new Simulation(new Terrain(10, 10), 0, 0, 2, 2);
        s.initSimulation();
        for (int i = 0; i < 8; i++){
            s.prochainTour();          
            // s.afficheRessource(4);
            s.afficheAgent(4);
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