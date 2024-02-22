package NeuralNetProject;

import NeuralNetProject.NeuronConnection;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.valueOf;

public class Nueron {

    public static final double ExcitementThreshold = 1.0;
    protected double CurrentExcitementLevel;
    protected int NeuronInternalNetworkID;


    protected List<NeuronConnection> OutputConnections;


    public Nueron(Nueron n){
        this.CurrentExcitementLevel = 0.0;
        this.NeuronInternalNetworkID = valueOf(n.NeuronInternalNetworkID);
    }

    public Nueron(){
        this.CurrentExcitementLevel = 0.0;
        this.NeuronInternalNetworkID = -1;
    }

    public Nueron(int id){
        this.CurrentExcitementLevel = 0.0;
        this.NeuronInternalNetworkID = id;
    }

    public double getExcitement(){
        return CurrentExcitementLevel;
    }

    public int GetID(){
        return this.NeuronInternalNetworkID;
    }

    public void SetID(int id){
        this.NeuronInternalNetworkID = id;
    }

    public void UpdateExcitement(double amount){
        this.CurrentExcitementLevel += amount;
    }

    public void SetBackToZeroExcitement(){
        this.CurrentExcitementLevel = 0.0;
    }
}
