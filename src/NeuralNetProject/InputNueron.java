package NeuralNetProject;

public class InputNueron extends Nueron {

    private double nextExcitementLevel;

    public boolean ConstantValue;

    public void setNextExcitementLevel(double nextExcitementLevel){
        this.nextExcitementLevel = nextExcitementLevel;
    }

    public InputNueron(){
        super();
        this.nextExcitementLevel = 0;
    }

    public InputNueron(Nueron n){
        super(n);
        InputNueron temp = (InputNueron) n;
        if(temp.ConstantValue){
            this.nextExcitementLevel = 1.0;
            this.ConstantValue = true;
        }
        else
            this.nextExcitementLevel = 0;
            this.ConstantValue = false;
    }

    @Override
    public void UpdateExcitement(double amount){
        CurrentExcitementLevel = nextExcitementLevel;
    }

}
