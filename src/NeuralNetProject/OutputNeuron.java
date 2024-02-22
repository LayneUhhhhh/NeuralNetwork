package NeuralNetProject;

import NeuralNetProject.Nueron;

public class OutputNeuron extends Nueron {

    public OutputNeuron(){
        super();
    }

    public OutputNeuron(Nueron n){
        super(n);
    }

    public boolean getBoolExcitement(){
        if(this.CurrentExcitementLevel >= 1)
            return true;
        else
            return false;
    }

}
