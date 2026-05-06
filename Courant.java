public class Courant extends Ressource {
    private char dir;
    
    public Courant (String nom, char dir){
        super(nom,1);
        this.dir = dir;
    }

    public Courant (String nom, int dir){
        super(nom,1);
        if (dir==0){
            this.dir='H';
        }
        if (dir==1){
            this.dir='D';
        }
        if (dir==2){
            this.dir='B';
        }
        if (dir==3){
            this.dir='G';
        }
    }

    public char getDir(){
        return dir;
    }

}