package NeuralNetProject;

import java.util.ArrayList;
import java.util.List;
public class NueralNet {

    private List<Nueron> NueronList = new ArrayList<>();

    public NueralNet() {
    }

    public void addNueron(Nueron newNeuron){
        NueronList.add(newNeuron);
    }

    public void TickUpdateAllNeurons(){
        for(Nueron N: NueronList){
            N.updateExcitement();
        }
    }

    public List<InputNueron> getInputNeuronList() {
        List<InputNueron> templist = new ArrayList<>();

        for (Nueron N : NueronList) {
            if (N.getClass() == InputNueron.class) {
                templist.add((InputNueron) N);
            }
        }
        return templist;
    }

    public List<OutputNeuron> getOutputNeuronList() {
        List<OutputNeuron> templist = new ArrayList<>();

        for (Nueron N : NueronList) {
            if (N.getClass() == OutputNeuron.class) {
                templist.add((OutputNeuron) N);
            }
        }
        return templist;
    }

}
