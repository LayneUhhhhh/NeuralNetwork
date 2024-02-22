package NeuralNetProject;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.valueOf;

public class NueralNet {

    private List<Nueron> NueronList;
    private int NextNeuronID;
    private List<NeuronConnection> NueronConnectionList;

    public NueralNet(NueralNet net) {

        this.NueronList = new ArrayList<>();
        this.NueronConnectionList = new ArrayList<>();

        this.NextNeuronID = valueOf(net.NextNeuronID);

        for(Nueron n: net.NueronList){
            if(n.getClass() == InputNueron.class)
                this.NueronList.add(new InputNueron(n));
            if(n.getClass() == OutputNeuron.class)
                this.NueronList.add(new OutputNeuron(n));
            else
                this.NueronList.add(new Nueron(n));
        }

        for(NeuronConnection n: net.NueronConnectionList){
            NeuronConnection newNeuronConn = new NeuronConnection(n);
            this.NueronConnectionList.add(newNeuronConn);
            newNeuronConn.setNeuronsWithID(this.NueronList);
        }
    }

    public NueralNet(List<Nueron> Neurons, List<NeuronConnection> NeuronsConnections){
        this.NueronList = Neurons;
        this.NueronConnectionList = NeuronsConnections;
        this.NextNeuronID = 0;
        for (Nueron n: this.NueronList){
            n.SetID(this.NextNeuronID);
            this.NextNeuronID++;
        }
    }

    public NueralNet() {
    }

    public void addNeuron(Nueron newNeuron){
        newNeuron.SetID(this.NextNeuronID);
        this.NextNeuronID++;
        NueronList.add(newNeuron);
    }

    public void TickUpdateAllNeurons(){
        System.out.println("next, connections: " + this.NueronConnectionList.size() + " neurons: " + this.NueronList.size());
        for(NeuronConnection NC: NueronConnectionList){
            for(Nueron N: this.NueronList){
                N.UpdateExcitement(0.0);
                if(NC.GetStartNeuron() == N){
                    NC.SeeStartNeuronAndUpdateIsActive();


                    }

                }

            }


        boolean output = false;
        if (output) {
            for(Nueron N: this.NueronList) {
                if (N.getClass() == OutputNeuron.class)
                    System.out.print("O: " + N.getExcitement());
                else if (N.getClass() == InputNueron.class)
                    System.out.print("I: " + N.getExcitement());
                else
                    System.out.print("N: " + N.getExcitement());
                System.out.print("\n");
            }
        }
    }


    public List<NeuronConnection> GetConnectionList(){
        return this.NueronConnectionList;
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

    public void ResetNetActivity(){
        for(NeuronConnection nc: this.NueronConnectionList){
            nc.SetDeactive();
        }
        for(Nueron n: this.NueronList){
            n.SetBackToZeroExcitement();
        }
    }

}
