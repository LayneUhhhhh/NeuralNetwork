package NeuralNetProject;

import java.util.List;

public interface NeuronTestingInterface {

    public static NueralNet generateCreatureRandomStartingNet(){
        List<Nueron> tempNeuronList = null;
        tempNeuronList.add(new InputNueron());
        tempNeuronList.add(new InputNueron());
        tempNeuronList.add(new OutputNeuron());
        tempNeuronList.add(new OutputNeuron());
        tempNeuronList.add(new OutputNeuron());
        tempNeuronList.add(new OutputNeuron());

        int randomInt = 0;
        List<NeuronConnection> tempConnectionList = null;

        for(int i = 0; i < randomInt; i++){
            tempNeuronList.add(new Nueron());
        }

        List<Nueron> possibleEndConnection = null;
        for (Nueron n: tempNeuronList){
            if(n.getClass() != InputNueron.class)
                possibleEndConnection.add(n);
        }

        List<Nueron> possibleStartConnection = null;
        for (Nueron n: tempNeuronList){
            if(n.getClass() != OutputNeuron.class)
                possibleStartConnection.add(n);
        }

        randomInt = 0;
        int randomIntEndConnectionNeuron = 0;
        int randomIntStartConnectionNeuron = 0;

        for(int i = 0; i < randomInt; i++){
            randomIntEndConnectionNeuron = 0;
            randomIntStartConnectionNeuron = 0;
            NeuronConnection n = new NeuronConnection(possibleStartConnection.get(randomIntStartConnectionNeuron), possibleEndConnection.get(randomIntEndConnectionNeuron));
            tempConnectionList.add(n);
        }

        NueralNet net = new NueralNet(tempNeuronList, tempConnectionList);
        return net;

    }

    public static void evolveNewConnection(NueralNet net){


    }

    public static void evolveNewNeuron(NueralNet net){


    }

    public static void evolveNewNeuronWithConnections(NueralNet net){


    }

}

