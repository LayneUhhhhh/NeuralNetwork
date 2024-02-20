package NeuralNetProject;

import NeuralNetProject.Nueron;

public class OutputNeuron extends Nueron {

    public boolean getBoolExcitement(){
        if(this.CurrentExcitementLevel >= 1)
            return true;
        else
            return false;
    }

}
