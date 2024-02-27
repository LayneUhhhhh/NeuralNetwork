package NeuralNetProject;

import java.util.ArrayList;
import java.util.List;
import CustomGame.Game;

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
            else if(n.getClass() == OutputNeuron.class)
                this.NueronList.add(new OutputNeuron(n));
            else
                this.NueronList.add(new Nueron(n));
        }

        for(NeuronConnection n: net.NueronConnectionList){
            NeuronConnection newNeuronConn = new NeuronConnection(n);
            this.NueronConnectionList.add(newNeuronConn);
            newNeuronConn.setNeuronsWithID(this.NueronList);
            //System.out.println("RM endID: " + newNeuronConn.endNeuronID + " startID: " + newNeuronConn.startNeuronID);
        }
        //System.out.print("copy ");
        //checkForDuplicateIDs(true);
    }

    public NueralNet(List<Nueron> Neurons, List<NeuronConnection> NeuronsConnections){
        this.NueronList = Neurons;
        this.NueronConnectionList = NeuronsConnections;
        this.NextNeuronID = 0;
        for (Nueron n: this.NueronList){
            n.SetID(this.NextNeuronID);
            this.NextNeuronID++;
            System.out.println(n.GetID() + " " + n);
        }
        for(NeuronConnection n: this.NueronConnectionList){
            n.UpdateIDs();
            //System.out.println("OG endID: " + n.endNeuronID + " startID: " + n.startNeuronID);
        }
        //System.out.print("create ");
        //checkForDuplicateIDs(true);
    }

    public NueralNet() {
    }

    public void addNeuron(Nueron newNeuron){
        newNeuron.SetID(this.NextNeuronID);
        this.NextNeuronID++;
        NueronList.add(newNeuron);
        //System.out.print("add ");
        //checkForDuplicateIDs(true);
    }

    public void TickUpdateAllNeurons(){
        /////System.out.print("tick ");
        //checkForDuplicateIDs(true);
        //System.out.println("next, connections: " + this.NueronConnectionList.size() + " neurons: " + this.NueronList.size());
        //System.out.println("new creature");
        for(Nueron N: this.NueronList){
            for(NeuronConnection NC: NueronConnectionList){
                
                
                if(NC.GetStartNeuron() == N){
                    N.UpdateExcitement(0.0);
                    NC.SeeStartNeuronAndUpdateIsActive();
                    //if (Game.round > 1000)
                        //NC.print();
                    }

                }

            }


        boolean output = false;
        if (output) {
            for(Nueron N: this.NueronList) {
                if (N.getClass() == OutputNeuron.class)
                    System.out.print("O: " + N.getExcitement() + ", ID: " + N.NeuronInternalNetworkID);
                else if (N.getClass() == InputNueron.class)
                    System.out.print("I: " + N.getExcitement() + ", ID: " + N.NeuronInternalNetworkID);
                else
                    System.out.print("N: " + N.getExcitement() + ", ID: " + N.NeuronInternalNetworkID);
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
        Nueron n = new Nueron();
        NueronList.add(n);
        n.SetID(this.NextNeuronID);
        this.NextNeuronID++;
    }

    public List<Nueron> getNeuronList(){
        return this.NueronList;
    }

    public void addNewNeuralConnection(NeuronConnection nc){
        nc.UpdateIDs();
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

    public boolean checkForDuplicateIDs(boolean print){

        boolean duplicate = false;

        for(Nueron n: this.NueronList){
            for(Nueron m: this.NueronList){
                if (n != m && n.GetID() == m.GetID()){
                    duplicate = true;
                    break;
                }
            }
            if (duplicate){
                break;
            }
        }

        if (print && duplicate){
            System.out.println("duplicate true");
        }
        else if (print && !duplicate){
            System.out.println("duplicate false");
        }
        return duplicate;
    }

}
