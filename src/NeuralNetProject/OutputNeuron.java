package NeuralNetProject;

public class OutputNeuron extends Neuron {

    public OutputNeuron(){
        super();
    }

    public OutputNeuron(Neuron n){
        super(n);
    }

    private static final double FULL_OUTPUT_VALUE = 1.0;

    public boolean GetBoolExcitement(){
        if(this.CurrentExcitementLevel >= FULL_OUTPUT_VALUE)
            return true;
        else
            return false;
    }

    public double GetOutputAsPercent(){
        double temp = this.CurrentExcitementLevel / FULL_OUTPUT_VALUE;
        return temp * 100;
    }

}
