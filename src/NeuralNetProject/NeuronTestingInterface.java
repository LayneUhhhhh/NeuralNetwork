package NeuralNetProject;

import java.util.Random;
import java.util.List;

public interface NeuronTestingInterface {


    public static NueralNet generateCreatureRandomStartingNet(int neuronRangeMin, int neuronRangeMax){
        List<Nueron> tempNeuronList = null;
        tempNeuronList.add(new InputNueron());
        tempNeuronList.add(new InputNueron());
        tempNeuronList.add(new OutputNeuron());
        tempNeuronList.add(new OutputNeuron());
        tempNeuronList.add(new OutputNeuron());
        tempNeuronList.add(new OutputNeuron());

        Random rand = new Random();

        int randomInt = rand.nextInt(neuronRangeMax - neuronRangeMin + 1) + neuronRangeMin;
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

        int randomIntEndConnectionNeuron = 0;
        int randomIntStartConnectionNeuron = 0;

        for(int i = 0; i < randomInt; i++){
            randomIntEndConnectionNeuron = rand.nextInt(possibleEndConnection.size());
            randomIntStartConnectionNeuron = rand.nextInt(possibleStartConnection.size());
            NeuronConnection n = new NeuronConnection(possibleStartConnection.get(randomIntStartConnectionNeuron), possibleEndConnection.get(randomIntEndConnectionNeuron));
            tempConnectionList.add(n);
        }

        NueralNet net = new NueralNet(tempNeuronList, tempConnectionList);
        return net;

    }

    public static void evolveNewConnection(NueralNet net){

        List<Nueron> tempNueronList = net.getNeuronList();

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

        Random rand = new Random();
        NeuronConnection n = new NeuronConnection(possibleStartConnection.get(possibleStartConnection.size()), possibleEndConnection.get(rand.nextInt(possibleEndConnection.size())));
        net.addNewNeuralConnection(n);

    }

    public static Nueron evolveNewNeuron(NueralNet net){
        Nueron n = new Nueron();
        net.addNueron(n);
        return n;
    }

    public static void evolveNewNeuronWithConnections(NueralNet net, int amountOfStartConnections, int amountOfEndConnections){
        N = evolveNewNeuron(net);

        List<Nueron> tempNueronList = net.getNeuronList();

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

        for(int i = 0; i < amountOfStartConnections; i++){
            NeuronConnection nc = new NeuronConnection(N, possibleEndConnection.get(rand.nextInt(possibleEndConnection.size())));
            net.addNewNeuralConnection(nc);
        }

        for(int i = 0; i < amountOfEndConnections; i++){
            NeuronConnection nc = new NeuronConnection(possibleStartConnection.get(rand.nextInt(possibleStartConnection.size())), N);
            net.addNewNeuralConnection(nc);
        }

    }



}

