package NeuralNetProject;

import java.util.List;
import java.util.Random;

import static java.lang.Integer.valueOf;

public class NeuronConnection {

    private Nueron startNeuron;
    private Nueron endNeuron;
    public int startNeuronID;
    public int endNeuronID;
    private double ActivityThreshold;
    private boolean Active;
    private double OutputSignalWhileActive; //between -1.0 and 1.0

    public NeuronConnection(Nueron startNeuron, Nueron endNeuron, double outputSignalWhenActive){
        Random rand = new Random();
        this.startNeuron = startNeuron;
        this.endNeuron = endNeuron;
        this.endNeuronID = endNeuron.GetID();
        this.startNeuronID = startNeuron.GetID();
        Active = false;
        this.OutputSignalWhileActive = outputSignalWhenActive;
        this.ActivityThreshold = rand.nextDouble();
    }

    public NeuronConnection(Nueron startNeuron, Nueron endNeuron){
        this.startNeuron = startNeuron;
        this.endNeuron = endNeuron;
        this.endNeuronID = endNeuron.GetID();
        this.startNeuronID = startNeuron.GetID();
        Active = false;
        Random rand = new Random();
        double randomValue = rand.nextDouble() * 2000 - 1000;
        randomValue /= 1000;
        randomValue = Math.round(randomValue * 1000.0) / 1000.0;
        this.OutputSignalWhileActive = randomValue;
        System.out.print("connectionval: " + randomValue + "\n");
        this.ActivityThreshold = rand.nextDouble();
    }

    public NeuronConnection(NeuronConnection n){
        this.endNeuronID = valueOf(n.endNeuronID);
        this.startNeuronID = valueOf(n.startNeuronID);
        this.Active = false;
        this.OutputSignalWhileActive = Double.valueOf(n.OutputSignalWhileActive);
        this.ActivityThreshold = Double.valueOf(n.ActivityThreshold);
    }

    public boolean SeeStartNeuronAndUpdateIsActive() {

        if(startNeuron == null)
            return false;

        if(this.startNeuron.getExcitement() >= this.ActivityThreshold)
            this.SetActive();
        else
            this.SetDeactive();

        return true;
    }

    public void SetActive(){

        if(this.Active == false){
            if (this.OutputSignalWhileActive > 0)
                endNeuron.UpdateExcitement(this.OutputSignalWhileActive + startNeuron.CurrentExcitementLevel);
            else
                endNeuron.UpdateExcitement(this.OutputSignalWhileActive - startNeuron.CurrentExcitementLevel);
            this.Active = true;
        }

    }

    public void SetDeactive(){

        if(this.Active == true){
            endNeuron.UpdateExcitement(0 - this.OutputSignalWhileActive);
            this.Active = false;
        }
    }

    public double getCurrentConnectionOutputSignal(){
        checkIfStartNueronIsActive();
        if (this.Active == true)
            return this.OutputSignalWhileActive;
        else
            return 0.0;
    }

    private void checkIfStartNueronIsActive() {
        if (startNeuron.getExcitement() >= Nueron.ExcitementThreshold)
            this.Active = true;
        else
            this.Active = false;
    }

    public void setNeuronsWithID(List<Nueron> neuronList){
        boolean foundEnd = false;
        boolean foundStart = false;
        for(Nueron n: neuronList){
            if (n.GetID() == this.startNeuronID){
                foundStart = true;
                this.startNeuron = n;
            }
            if (n.GetID() == this.endNeuronID){
                foundEnd = true;
                this.endNeuron = n;
            }
            if(foundStart && foundEnd)
                return;
        }
        System.out.println("bad; " + endNeuronID);
        for (Nueron N: neuronList){
            System.out.println(N.GetID());
        }

    }

    public Nueron GetStartNeuron(){
        return this.startNeuron;
    }
    public Nueron GetEndNeuron(){
        return this.endNeuron;
    }

    public void print(){

        String a;
        if (this.Active == true)
            a = "true";
        else 
            a = "false";
        System.out.println("threshhold: " + this.ActivityThreshold + " output when active: " + this.OutputSignalWhileActive + " start nueron excitement: " + startNeuron.CurrentExcitementLevel + " start neuron ID: " + startNeuronID + " active: " + a + " end nueron excitement: " + endNeuron.CurrentExcitementLevel + " end neuron ID: " + endNeuronID + " endNuron: " + endNeuron);
    }

    public void UpdateIDs(){
        this.endNeuronID = this.endNeuron.NeuronInternalNetworkID;
        this.startNeuronID = this.startNeuron.NeuronInternalNetworkID;
    }

}
