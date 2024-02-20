package NeuralNetProject;

public class NeuronConnection {

    private Nueron startNeuron;
    private Nueron endNeuron;
    private boolean Active;
    private double OutputSignalWhileActive; //between -1.0 and 1.0

    public NeuronConnection(Nueron startNeuron, Nueron endNeuron, double outputSignalWhenActive){
        this.startNeuron = startNeuron;
        this.endNeuron = endNeuron;
        Active = false;
        OutputSignalWhileActive = OutputSignalWhileActive;
        this.startNeuron.addNueronConnection(this);
    }

    public NeuronConnection(Nueron startNeuron, Nueron endNeuron){
        this.startNeuron = startNeuron;
        this.endNeuron = endNeuron;
        Active = false;
        int randomInt = 0;
        this.OutputSignalWhileActive = randomInt;
        this.startNeuron.addNueronConnection(this);
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
        if (endNeuron.getExcitement() >= Nueron.ExcitementThreshold)
            this.Active = true;
        else
            this.Active = false;
    }
}
