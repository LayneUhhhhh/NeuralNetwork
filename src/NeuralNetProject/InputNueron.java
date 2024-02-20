package NeuralNetProject;

public class InputNueron extends Nueron {

    private double nextExcitementLevel;
    public void setNextExcitementLevel(double nextExcitementLevel){
        this.nextExcitementLevel = nextExcitementLevel;
    }

    public InputNueron(){
        this.nextExcitementLevel = 0;
    }

    @Override
    public void updateExcitement(){
        CurrentExcitementLevel = nextExcitementLevel;
    }

}
