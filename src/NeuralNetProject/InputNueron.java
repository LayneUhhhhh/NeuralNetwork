package NeuralNetProject;

public class InputNueron extends Nueron {

    private double nextExcitementLevel;
    public void setNextExcitementLevel(double nextExcitementLevel){
        this.nextExcitementLevel = nextExcitementLevel;
    }

    public InputNueron(){
        super();
        this.nextExcitementLevel = 0;
    }

    public InputNueron(Nueron n){
        super(n);
        this.nextExcitementLevel = 0;
    }

    @Override
    public void UpdateExcitement(double amount){
        CurrentExcitementLevel = nextExcitementLevel;
    }

}
