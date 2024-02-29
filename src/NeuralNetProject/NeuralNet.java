package NeuralNetProject;

import CustomGame.Creature;
import CustomGame.Game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static java.lang.Integer.valueOf;

public class NeuralNet {

    private List<Neuron> neuronList;
    private int nextNeuronID;
    private List<NeuronConnection> neuronConnectionList;

    public static int temp = 0;


    // create new NeuralNet from existing NeuralNet
    //copies all data and creates new neuron objects
    public NeuralNet(NeuralNet net) {

        //create new lists for Neurons and NeuronConnections
        this.neuronList = new ArrayList<>();
        this.neuronConnectionList = new ArrayList<>();

        this.nextNeuronID = valueOf(net.nextNeuronID);

        //create new copies of the neurons
        for(Neuron n: net.neuronList){
            if(n.getClass() == InputNeuron.class)
                this.neuronList.add(new InputNeuron(n));
            else if(n.getClass() == OutputNeuron.class)
                this.neuronList.add(new OutputNeuron(n));
            else
                this.neuronList.add(new Neuron(n));
        }

        //create new copies of NeuronConnections and set their Neurons using Neuron ID's
        for(NeuronConnection n: net.neuronConnectionList){
            NeuronConnection nConnection = new NeuronConnection(n);
            this.neuronConnectionList.add(nConnection);
            nConnection.SetNeuronsWithID(this.neuronList);
        }
    }

    //create new NeuralNet using a Neuron list and a NeuronConnection list
    public NeuralNet(List<Neuron> Neurons, List<NeuronConnection> NeuronsConnections){

        //copy lists to class members
        this.neuronList = Neurons;
        this.neuronConnectionList = NeuronsConnections;

        this.nextNeuronID = 0;

        //set each Neuron's ID for this network
        for (Neuron n: this.neuronList){
            n.SetID(this.nextNeuronID);
            this.nextNeuronID++;
        }

        //update Neuron ID's in the NeuronConnection using their saved Neuron members
        for(NeuronConnection n: this.neuronConnectionList){
            n.SetNeuronIDs();
        }

    }

    //adds a new Neuron to the list and sets its ID
    public void addNeuron(Neuron newNeuron){
        newNeuron.SetID(this.nextNeuronID);
        this.nextNeuronID++;
        neuronList.add(newNeuron);
    }


    //pulses and updates the brain one time each call
    //goes in order of start Neuron ID
    public void TickUpdateAllNeurons(){

        //sort NeuronConnections by their start Neuron's ID in ascending order
        Collections.sort(this.neuronConnectionList, new Comparator<NeuronConnection>() {
            @Override
            public int compare(NeuronConnection obj1, NeuronConnection obj2) {
                return Integer.compare(obj1.startNeuronID, obj2.startNeuronID);
            }
        });


        //loop through each NeuronConnection and update them
        for(NeuronConnection NC: neuronConnectionList){
            NC.SeeStartNeuronAndUpdateIsActive();
        }



        boolean output = true;
        if (output && temp != Game.round) {
            temp = Game.round;
            System.out.println("Neurons: " + this.neuronList.size() + " NeuronConnections: " + this.neuronConnectionList.size());
            for(Neuron N: this.neuronList) {
                if (N.getClass() == OutputNeuron.class)
                    System.out.print("O: " + N.getExcitement() + ", ID: " + N.NeuronID);
                else if (N.getClass() == InputNeuron.class)
                    System.out.print("I: " + N.getExcitement() + ", ID: " + N.NeuronID);
                else
                    System.out.print("N: " + N.getExcitement() + ", ID: " + N.NeuronID);
                System.out.print("\n");
            }
        }


    }


    public List<NeuronConnection> GetConnectionList(){
        return this.neuronConnectionList;
    }

    public List<InputNeuron> getInputNeuronList() {
        List<InputNeuron> templist = new ArrayList<>();

        for (Neuron N : neuronList) {
            if (N.getClass() == InputNeuron.class) {
                templist.add((InputNeuron) N);
            }
        }
        return templist;
    }

    public List<OutputNeuron> getOutputNeuronList() {
        List<OutputNeuron> templist = new ArrayList<>();

        for (Neuron N : neuronList) {
            if (N.getClass() == OutputNeuron.class) {
                templist.add((OutputNeuron) N);
            }
        }
        return templist;
    }


    public List<Neuron> getNeuronList(){
        return this.neuronList;
    }

    public void addNewNeuralConnection(NeuronConnection nc){
        nc.SetNeuronIDs();
        this.neuronConnectionList.add(nc);
    }


    //deactivate NeuronConnections and set each Neurons excitement to 0
    public void ResetNetActivity(){

        //deactivate NeuronConnections
        for(NeuronConnection nc: this.neuronConnectionList){
            nc.SetDeactive();
        }

        //set Neuron's excitement to 0
        for(Neuron n: this.neuronList){
            n.ResetExcitement();
        }

    }

    //for testing: will be deleted
    public boolean checkForDuplicateIDs(boolean print){

        boolean duplicate = false;

        for(Neuron n: this.neuronList){
            for(Neuron m: this.neuronList){
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
