package NeuralNetProject;

import java.util.ArrayList;
import java.util.List;
public class NueralNet {

    private List<Nueron> NueronList = new ArrayList<>();
    private List<NeuronConnection> NueronConnectionList = new ArrayList<>();

    public NueralNet(NueralNet net) {
        this.NueronList = net.NueronList;
        this.NueronConnectionList = net.NueronConnectionList;
    }

    public NueralNet(List<Nueron> Neurons, List<NeuronConnection> NeuronsConnections){
        this.NueronList = Neurons;
        this.NueronConnectionList = NeuronsConnections;
    }

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

    public void evolveNewRandomNueron(){
        NueronList.add(new Nueron());
    }

    public List<Nueron> getNeuronList(){
        return this.NueronList;
    }

    public void addNewNeuralConnection(NeuronConnection nc){
        this.NueronConnectionList.add(nc);
    }

}
