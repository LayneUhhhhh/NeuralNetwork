package NeuralNetProject;

import java.util.ArrayList;
import java.util.Random;
import java.util.List;

public interface NeuronTestingInterface {

    public static final int CONNECTIONS_PER_NEURON = 2;

    public static NeuralNet generateCreatureRandomStartingNet(int neuronRangeMin, int neuronRangeMax){
        List<Neuron> tempNeuronList = new ArrayList<>();
        tempNeuronList.add(new InputNeuron(false, 0.0));
        tempNeuronList.add(new InputNeuron(false, 0.0));
        tempNeuronList.add(new InputNeuron(false, 0.0));
        InputNeuron alwaysActive = new InputNeuron(true, 2.0);
        tempNeuronList.add(alwaysActive);
        tempNeuronList.add(new InputNeuron(false, 0.0));
        tempNeuronList.add(new OutputNeuron());
        tempNeuronList.add(new OutputNeuron());
        tempNeuronList.add(new OutputNeuron());
        tempNeuronList.add(new OutputNeuron());

        Random rand = new Random();

        int randomInt = rand.nextInt(neuronRangeMax - neuronRangeMin + 1) + neuronRangeMin;
        List<NeuronConnection> tempConnectionList = new ArrayList<>();

        for(int i = 0; i < randomInt; i++){
            tempNeuronList.add(new Neuron());
        }

        List<Neuron> possibleEndConnection = new ArrayList<>();
        for (Neuron n: tempNeuronList){
            if(n.getClass() != InputNeuron.class)
                possibleEndConnection.add(n);
        }

        List<Neuron> possibleStartConnection = new ArrayList<>();
        for (Neuron n: tempNeuronList){
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

        NeuralNet net = new NeuralNet(tempNeuronList, tempConnectionList);
        return net;

    }

    public static void evolveNewConnection(NeuralNet net){

        List<Neuron> tempNeuronList = net.getNeuronList();

        List<Neuron> possibleEndConnection = new ArrayList<>();
        for (Neuron n: tempNeuronList){
            if(n.getClass() != InputNeuron.class)
                possibleEndConnection.add(n);
        }

        List<Neuron> possibleStartConnection = new ArrayList<>();
        for (Neuron n: tempNeuronList){
            if(n.getClass() != OutputNeuron.class)
                possibleStartConnection.add(n);
        }

        Random rand = new Random();
        NeuronConnection n = new NeuronConnection(possibleStartConnection.get(possibleStartConnection.size() - 1), possibleEndConnection.get(rand.nextInt(possibleEndConnection.size() - 1)));
        n.SetNeuronIDs();
        net.addNewNeuralConnection(n);

    }

    static Neuron evolveNewNeuron(NeuralNet net){
        Neuron n = new Neuron();
        net.addNeuron(n);
        return n;
    }

    static void RemoveConnections(NeuralNet net, int amount){

        List<NeuronConnection> connectionList = net.GetConnectionList();

        if(connectionList.size() < 15){
            return;
        }

        Random rand = new Random();
        for(int i = 0; i < amount; i++) {
            int deleteNum = rand.nextInt(connectionList.size());
            connectionList.remove(deleteNum);
        }
    }

    static void RemoveNeuron(NeuralNet net){
        List<Neuron> nList = net.getNeuronList();
        List<Neuron> tempList = new ArrayList<>();

        if(nList.size() < 11){
            return;
        }

        for(Neuron N: nList){
            if(N.getClass() != InputNeuron.class && N.getClass() != OutputNeuron.class){
                tempList.add(N);
            }
        }
        Random rand = new Random();
        int getNum = rand.nextInt(tempList.size());
        Neuron N = tempList.get(getNum);
        nList.remove(N);
        System.out.println(N.getClass());
        List<NeuronConnection> connList = net.GetConnectionList();
        List<NeuronConnection> tempConnList = new ArrayList<>(connList);
        for(NeuronConnection NC: tempConnList){
            if (NC.GetStartNeuron() == N || NC.GetEndNeuron() == N){
                connList.remove(NC);
            }
        }

    }

    static void evolveNewNeuronWithConnections(NeuralNet net, int amountOfStartConnections, int amountOfEndConnections){

        Neuron N = evolveNewNeuron(net);
        Random rand = new Random();
        List<Neuron> tempNeuronList = net.getNeuronList();

        List<Neuron> possibleEndConnection = new ArrayList<>();
        for (Neuron n: tempNeuronList){
            if(n.getClass() != InputNeuron.class)
                possibleEndConnection.add(n);
        }

        List<Neuron> possibleStartConnection = new ArrayList<>();
        for (Neuron n: tempNeuronList){
            if(n.getClass() != OutputNeuron.class)
                possibleStartConnection.add(n);
        }

        for(int i = 0; i < amountOfStartConnections; i++){
            NeuronConnection nc = new NeuronConnection(N, possibleEndConnection.get(rand.nextInt(possibleEndConnection.size())));
            nc.SetNeuronIDs();
            net.addNewNeuralConnection(nc);
        }

        for(int i = 0; i < amountOfEndConnections; i++){
            NeuronConnection nc = new NeuronConnection(possibleStartConnection.get(rand.nextInt(possibleStartConnection.size())), N);
            nc.SetNeuronIDs();
            net.addNewNeuralConnection(nc);
        }

    }


}

