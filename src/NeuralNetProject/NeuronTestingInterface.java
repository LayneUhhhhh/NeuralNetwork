package NeuralNetProject;

import java.util.ArrayList;
import java.util.Random;
import java.util.List;

public interface NeuronTestingInterface {

    public static final int CONNECTIONS_PER_NEURON = 8;

    public static NueralNet generateCreatureRandomStartingNet(int neuronRangeMin, int neuronRangeMax){
        List<Nueron> tempNeuronList = new ArrayList<>();
        tempNeuronList.add(new InputNueron());
        tempNeuronList.add(new InputNueron());
        tempNeuronList.add(new InputNueron());
        InputNueron alwaysActive = new InputNueron();
        alwaysActive.setNextExcitementLevel(1);
        tempNeuronList.add(alwaysActive);
        tempNeuronList.add(new OutputNeuron());
        tempNeuronList.add(new OutputNeuron());
        tempNeuronList.add(new OutputNeuron());
        tempNeuronList.add(new OutputNeuron());

        Random rand = new Random();

        int randomInt = rand.nextInt(neuronRangeMax - neuronRangeMin + 1) + neuronRangeMin;
        List<NeuronConnection> tempConnectionList = new ArrayList<>();

        for(int i = 0; i < randomInt; i++){
            tempNeuronList.add(new Nueron());
        }

        List<Nueron> possibleEndConnection = new ArrayList<>();
        for (Nueron n: tempNeuronList){
            if(n.getClass() != InputNueron.class)
                possibleEndConnection.add(n);
        }

        List<Nueron> possibleStartConnection = new ArrayList<>();
        for (Nueron n: tempNeuronList){
            if(n.getClass() != OutputNeuron.class)
                possibleStartConnection.add(n);
        }

        int randomIntEndConnectionNeuron = 0;
        int randomIntStartConnectionNeuron = 0;

        randomInt = randomInt * CONNECTIONS_PER_NEURON;
        for(int i = 0; i < randomInt; i++){
            randomIntEndConnectionNeuron = rand.nextInt(possibleEndConnection.size());
            randomIntStartConnectionNeuron = rand.nextInt(possibleStartConnection.size());
            System.out.print("here\n");
            NeuronConnection n = new NeuronConnection(possibleStartConnection.get(randomIntStartConnectionNeuron), possibleEndConnection.get(randomIntEndConnectionNeuron));
            tempConnectionList.add(n);
        }

        NueralNet net = new NueralNet(tempNeuronList, tempConnectionList);
        return net;

    }

    public static void evolveNewConnection(NueralNet net){

        List<Nueron> tempNeuronList = net.getNeuronList();

        List<Nueron> possibleEndConnection = new ArrayList<>();
        for (Nueron n: tempNeuronList){
            if(n.getClass() != InputNueron.class)
                possibleEndConnection.add(n);
        }

        List<Nueron> possibleStartConnection = new ArrayList<>();
        for (Nueron n: tempNeuronList){
            if(n.getClass() != OutputNeuron.class)
                possibleStartConnection.add(n);
        }

        Random rand = new Random();
        NeuronConnection n = new NeuronConnection(new Nueron(possibleStartConnection.get(possibleStartConnection.size() - 1)), new Nueron(possibleEndConnection.get(rand.nextInt(possibleEndConnection.size() - 1))));
        net.addNewNeuralConnection(n);

    }

    static Nueron evolveNewNeuron(NueralNet net){
        Nueron n = new Nueron();
        net.addNeuron(n);
        return n;
    }

    static void RemoveConnections(NueralNet net, int amount){
        List<NeuronConnection> connectionList = net.GetConnectionList();
        Random rand = new Random();
        for(int i = 0; i < amount; i++) {
            int deleteNum = rand.nextInt(connectionList.size());
            connectionList.remove(deleteNum);
        }
    }

    static void RemoveNeuron(NueralNet net){
        List<Nueron> nList = net.getNeuronList();
        List<Nueron> tempList = new ArrayList<>();
        for(Nueron N: nList){
            if(N.getClass() != InputNueron.class && N.getClass() != OutputNeuron.class){
                tempList.add(N);
            }
        }
        Random rand = new Random();
        int getNum = rand.nextInt(tempList.size());
        Nueron N = tempList.get(getNum);
        nList.remove(N);
        System.out.println(N.getClass());
        List<NeuronConnection> connList = net.GetConnectionList();
        for(NeuronConnection NC: connList){
            if (NC.GetStartNeuron() == N || NC.GetEndNeuron() == N){
                connList.remove(NC);
            }
        }

    }

    static void evolveNewNeuronWithConnections(NueralNet net, int amountOfStartConnections, int amountOfEndConnections){

        if(net.getNeuronList().size() > 24){
            return;
        }

        Nueron N = evolveNewNeuron(net);
        Random rand = new Random();
        List<Nueron> tempNeuronList = net.getNeuronList();

        List<Nueron> possibleEndConnection = new ArrayList<>();
        for (Nueron n: tempNeuronList){
            if(n.getClass() != InputNueron.class)
                possibleEndConnection.add(n);
        }

        List<Nueron> possibleStartConnection = new ArrayList<>();
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

