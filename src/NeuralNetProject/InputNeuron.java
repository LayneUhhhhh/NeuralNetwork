package NeuralNetProject;

public class InputNeuron extends Neuron {

    public boolean constantOutputSignal;
    private double temp;

    public InputNeuron(boolean constantOutput, double startingOutput){
        super();
        this.CurrentExcitementLevel = startingOutput;
        this.constantOutputSignal = constantOutput;
        if(constantOutput)
            this.temp = startingOutput;
    }

    public InputNeuron(boolean constantOutput, double startingOutput, int id){
        super(id);
        this.CurrentExcitementLevel = startingOutput;
        this.constantOutputSignal = constantOutput;
        if(constantOutput)
            this.temp = startingOutput;
    }

    public InputNeuron(Neuron n){
        super(n);
        InputNeuron temp = (InputNeuron) n;
        if(temp.constantOutputSignal){
            this.CurrentExcitementLevel = temp.temp;
            this.constantOutputSignal = true;
            this.temp = temp.temp;
        }
        else
            this.CurrentExcitementLevel = 0.0;
            this.constantOutputSignal = false;
    }

    @Override
    public void UpdateExcitement(double amount){
        if(!constantOutputSignal)
            CurrentExcitementLevel = amount;
    }

    @Override
    public void ResetExcitement(){
        if(!constantOutputSignal)
            CurrentExcitementLevel = 0.0;
    }

}
