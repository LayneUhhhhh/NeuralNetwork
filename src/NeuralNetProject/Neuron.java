package NeuralNetProject;

import java.util.List;

import static java.lang.Integer.valueOf;

public class Neuron {
    
    protected double CurrentExcitementLevel;
    protected int NeuralNetworkNeuronID;


    protected List<NeuronConnection> OutputConnections;


    public Neuron(Neuron n){
        this.CurrentExcitementLevel = 0.0;
        this.NeuralNetworkNeuronID = valueOf(n.NeuralNetworkNeuronID);
    }

    public Neuron(){
        this.CurrentExcitementLevel = 0.0;
        this.NeuralNetworkNeuronID = -1;
    }

    public Neuron(int id){
        this.CurrentExcitementLevel = 0.0;
        this.NeuralNetworkNeuronID = id;
    }

    public double getExcitement(){
        return CurrentExcitementLevel;
    }

    public int GetID(){
        return this.NeuralNetworkNeuronID;
    }

    public void SetID(int id){
        this.NeuralNetworkNeuronID = id;
    }

    public void UpdateExcitement(double amount){
        this.CurrentExcitementLevel += amount;
    }

    public void ResetExcitement(){
        this.CurrentExcitementLevel = 0.0;
    }
}
