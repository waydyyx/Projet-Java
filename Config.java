public class Config {
    public static final int POISSON_MIN = 5;
    public static final int POISSON_MAX = 20;
    public static final int COURANT_MIN = 1;
    public static final int COURANT_MAX = 10;
    public static final double PROBA_DEPART = 1.0 / 3;

    private Config() {}

    public static void validerTerrain(int nbLignes, int nbColonnes) throws SimulationException {
        if (nbLignes <= 0 || nbColonnes <= 0)
            throw new SimulationException("Les dimensions du terrain doivent être positives.");
    }
}
