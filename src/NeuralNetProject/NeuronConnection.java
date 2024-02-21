package NeuralNetProject;

import java.util.Random;

public class NeuronConnection {

    private Nueron startNeuron;
    private Nueron endNeuron;
    private boolean Active;
    private double OutputSignalWhileActive; //between -1.0 and 1.0

    public NeuronConnection(Nueron startNeuron, Nueron endNeuron, double outputSignalWhenActive){
        this.startNeuron = startNeuron;
        this.endNeuron = endNeuron;
        Active = false;
        this.OutputSignalWhileActive = outputSignalWhenActive;
        this.startNeuron.addNueronConnection(this);
    }

    public NeuronConnection(Nueron startNeuron, Nueron endNeuron){
        this.startNeuron = startNeuron;
        this.endNeuron = endNeuron;
        Active = false;
        Random rand = new Random();
        double randomValue = rand.nextDouble() * 2000 - 1000;
        randomValue /= 1000;
        randomValue = Math.round(randomValue * 1000.0) / 1000.0;
        this.OutputSignalWhileActive = randomValue + 0.7;
        System.out.print("connectionval: " + randomValue + "\n");
        this.startNeuron.addNueronConnection(this);
    }

    public NeuronConnection(NeuronConnection n){

    }

    public boolean getActiveStatus() {
        checkIfStartNueronIsActive();
        return this.Active;
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
}
