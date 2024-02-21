package NeuralNetProject;

import NeuralNetProject.NeuronConnection;

import java.util.ArrayList;
import java.util.List;

public class Nueron {

    public static final double ExcitementThreshold = 1.0;
    protected double CurrentExcitementLevel;
    protected List<NeuronConnection> InputConnections = new ArrayList<>();

    public Nueron(){
    }

    public Nueron(Nueron n){
        this.InputConnections = n.InputConnections;
    }

    public Nueron(List<NeuronConnection> inputConnections){
        this.InputConnections = inputConnections;
    }

    public double getExcitement(){
        return CurrentExcitementLevel;
    }

    public void updateExcitement(){
        CurrentExcitementLevel = 0;
        for (NeuronConnection N: InputConnections){
            //System.out.print("updated, added: " + N.getCurrentConnectionOutputSignal() + " total: " + CurrentExcitementLevel + N.getCurrentConnectionOutputSignal() + "\n");
            CurrentExcitementLevel += N.getCurrentConnectionOutputSignal();
        }
    }

    public void addNueronConnection(NeuronConnection newNeuronConnection){
        InputConnections.add(newNeuronConnection);
    }

}
