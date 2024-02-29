package NeuralNetProject;

import java.util.List;

import static java.lang.Integer.valueOf;

public class Neuron {
    
    protected double CurrentExcitementLevel;
    protected int NeuronID;

    protected List<NeuronConnection> OutputConnections;


    public Neuron(Neuron n){
        this.CurrentExcitementLevel = 0.0;
        this.NeuronID = valueOf(n.NeuronID);
    }

    public Neuron(){
        this.CurrentExcitementLevel = 0.0;
        this.NeuronID = -1;
    }

    public Neuron(int id){
        this.CurrentExcitementLevel = 0.0;
        this.NeuronID = id;
    }

    public double getExcitement(){
        return CurrentExcitementLevel;
    }

    public int GetID(){
        return this.NeuronID;
    }

    public void SetID(int id){
        this.NeuronID = id;
    }

    public void UpdateExcitement(double amount){
        this.CurrentExcitementLevel += amount;
    }

    public void ResetExcitement(){
        this.CurrentExcitementLevel = 0.0;
    }
}
