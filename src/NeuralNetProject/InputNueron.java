package NeuralNetProject;

public class InputNueron extends Nueron {

    private double nextExcitementLevel;
    public void setNextExcitementLevel(double nextExcitementLevel){
        this.nextExcitementLevel = nextExcitementLevel;
    }

    @Override
    public void updateExcitement(){
        CurrentExcitementLevel = nextExcitementLevel;
    }

}
