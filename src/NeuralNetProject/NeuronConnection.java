package NeuralNetProject;

import java.util.List;
import java.util.Random;

import static java.lang.Integer.valueOf;

public class NeuronConnection {

    //members
    private Neuron startNeuron;
    private Neuron endNeuron;
    public int startNeuronID;
    public int endNeuronID;
    private double activityThreshold;
    private boolean active;
    private double outputSignalWhileActive; //between -1.0 and 1.0

    //constants
    private static final double MAX_RANDOM_OUTPUT_SIGNAL = 2.0;
    private static final double MIN_RANDOM_OUTPUT_SIGNAL = -2.0;
    private static final double MAX_RANDOM_ACTIVITY_THRESHOLD = 1.0;
    private static final double MIN_RANDOM_ACTIVITY_THRESHOLD = 0.0;


    //create new NeuronConnection with all values set
    public NeuronConnection(Neuron startNeuron, Neuron endNeuron, double outputSignalWhenActive, double threshold){
        this.startNeuron = startNeuron;
        this.endNeuron = endNeuron;
        this.endNeuronID = endNeuron.GetID();
        this.startNeuronID = startNeuron.GetID();
        this.active = false;
        this.outputSignalWhileActive = outputSignalWhenActive;
        this.activityThreshold = threshold;
    }


    //create new NeuronConnection with random outputSignalWhileActive and activityThreshold
    public NeuronConnection(Neuron startNeuron, Neuron endNeuron){


        this.startNeuron = startNeuron;
        this.endNeuron = endNeuron;
        this.endNeuronID = endNeuron.GetID();
        this.startNeuronID = startNeuron.GetID();
        this.active = false;

        //generate random output signal between MIN_RANDOM_OUTPUT_SIGNAL and MAX_RANDOM_OUTPUT_SIGNAL
        //multiply by 1000 to generate random value to at least 4 digits then divide by 1000 to get the original decimal place
        Random rand = new Random();
        double multiplyRandomFunctionBy = (MAX_RANDOM_OUTPUT_SIGNAL + Math.abs(MIN_RANDOM_OUTPUT_SIGNAL)) * 1000;
        double subtractRandomFunctionBy = MIN_RANDOM_OUTPUT_SIGNAL * 1000;
        double outputSignal = (rand.nextDouble() * multiplyRandomFunctionBy) + subtractRandomFunctionBy;
        //System.out.println("outputSignal: " + outputSignal + " subtractRandomFunctionBy: " + subtractRandomFunctionBy);
        outputSignal /= 1000;
        this.outputSignalWhileActive = outputSignal;

        //generate random activity threshold between MIN_RANDOM_ACTIVITY_THRESHOLD and MAX_RANDOM_ACTIVITY_THRESHOLD
        multiplyRandomFunctionBy = (MAX_RANDOM_ACTIVITY_THRESHOLD + Math.abs(MIN_RANDOM_ACTIVITY_THRESHOLD)) * 1000;
        subtractRandomFunctionBy = MIN_RANDOM_ACTIVITY_THRESHOLD * 1000;
        double threshold = (rand.nextDouble() * multiplyRandomFunctionBy) + subtractRandomFunctionBy;
        threshold /= 1000;
        this.activityThreshold = threshold;

    }

    //creates a copy of a NeuronConnection
    //DOES NOT COPY NEURONS OR MAKE NEW ONES. This only copies the Neuron IDs. Neurons will have to be set using SetNeuronsWithID
    public NeuronConnection(NeuronConnection n){
        this.endNeuronID = valueOf(n.endNeuronID);
        this.startNeuronID = valueOf(n.startNeuronID);
        this.active = false;
        this.outputSignalWhileActive = Double.valueOf(n.outputSignalWhileActive);
        this.activityThreshold = Double.valueOf(n.activityThreshold);
    }

    public boolean SeeStartNeuronAndUpdateIsActive() {

        if(startNeuron == null)
            return false;

        //System.out.println("startNeuron: " + this.startNeuron.getExcitement() + " threshold: " + this.activityThreshold);

        if(this.startNeuron.getExcitement() >= this.activityThreshold)
            this.SetActive();
        else
            this.SetDeactive();

        return true;
    }


    //checks if Neuron is already active and if not then it adds its output to the end Neuron
    //if this NeuronConnection is already active then this has no effect
    public void SetActive(){

        if(!this.active){
            endNeuron.UpdateExcitement(this.outputSignalWhileActive);
            //System.out.println("setActive: " + this.outputSignalWhileActive + " endNeuron: " + this.endNeuron.CurrentExcitementLevel);
            this.active = true;
        }

    }

    //checks if Neuron is active and if so then it subtracts its output from the end Neuron
    //if the NeuronConnection is already unactive then this has no effect
    public void SetDeactive(){

        if(this.active){
            endNeuron.UpdateExcitement(0.0 - this.outputSignalWhileActive);
            this.active = false;
        }
    }

    //returns current output to end Neuron
    public double getCurrentConnectionOutputSignal(){

        if (this.active)
            return this.outputSignalWhileActive;
        else
            return 0.0;

    }

    //active getter. Does not check start neuron to update this.active
    private boolean GetActive() {
        return this.active;
    }

    //startNeuron and endNeuron are set using Neurons in the parameter list using startNeuronID and endNeuronID
    //returns true if both Neurons were found and set otherwise, returns false
    public boolean SetNeuronsWithID(List<Neuron> neuronList){

        boolean endNeuronIsFound = false;
        boolean startNeuronIsFound = false;

        for(Neuron n: neuronList){
            if (n.GetID() == this.startNeuronID){
                startNeuronIsFound = true;
                this.startNeuron = n;
            }
            if (n.GetID() == this.endNeuronID){
                endNeuronIsFound = true;
                this.endNeuron = n;
            }
            if(startNeuronIsFound && endNeuronIsFound)
                return true;
        }

        return false;

    }

    //sets startNeuronID and endNeuronID using the ID values in the startNeuron and endNeuron Neuron objects
    public void SetNeuronIDs(){
        this.endNeuronID = this.endNeuron.NeuronID;
        this.startNeuronID = this.startNeuron.NeuronID;
    }

    //startNeuronGetter
    public Neuron GetStartNeuron(){
        return this.startNeuron;
    }

    //endNeuronGetter
    public Neuron GetEndNeuron(){
        return this.endNeuron;
    }

    //for test: will be deleted
    public void print(){

        String a;
        if (this.active == true)
            a = "true";
        else
            a = "false";

        System.out.println("threshhold: " + this.activityThreshold + " output when active: " + this.outputSignalWhileActive + " start nueron excitement: " + startNeuron.CurrentExcitementLevel + " start neuron ID: " + startNeuronID + " active: " + a + " end nueron excitement: " + endNeuron.CurrentExcitementLevel + " end neuron ID: " + endNeuronID + " endNuron: " + endNeuron);

    }

}
