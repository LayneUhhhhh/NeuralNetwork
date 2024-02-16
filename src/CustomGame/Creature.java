package CustomGame;
import NeuralNetProject.InputNueron;
import NeuralNetProject.NueralNet;

import java.util.ArrayList;
import java.util.List;

public class Creature {

    public int x;
    public int y;

    public Creature(int x, int y, NueralNet brain) {
        this.x = x;
        this.y = y;
        Brain = brain;
    }

    public NueralNet Brain;

    public void Tick(){
        List<InputNueron> ineurons = Brain.getInputNeuronList();
        ineurons.get(0).setNextExcitementLevel(this.x * 0.1);
        ineurons.get(1).setNextExcitementLevel(this.y * 0.1);
    }


}
