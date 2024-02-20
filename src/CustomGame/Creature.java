package CustomGame;
import NeuralNetProject.InputNueron;
import NeuralNetProject.NueralNet;
import NeuralNetProject.Nueron;

import java.util.ArrayList;
import java.util.List;

public class Creature {

    private static final int MoveRightValue = 1;
    private static final int MoveLeftValue = 2;
    private static final int MoveDownValue = 3;
    private static final int MoveUpValue = 4;

    public int x;
    public int y;
    public NueralNet Brain;
    private int score;


    public Creature(int x, int y, NueralNet brain) {
        this.x = x;
        this.y = y;
        Brain = brain;
    }

    

    public void Tick(TileMap t){
        List<InputNueron> ineurons = Brain.getInputNeuronList();
        ineurons.get(0).setNextExcitementLevel(this.x * 0.01);
        ineurons.get(1).setNextExcitementLevel(this.y * 0.01);
        Brain.TickUpdateAllNeurons();
        List<OutputNueron> oneurons = Brain.getOutputNeuronList();

        int tempHighestDirection;
        double tempHighestOutputLevel = 0.0;

        for(int i = 0; i < 4; i++){
            if (oneurons.get(i).getExcitementLevel() > tempHighestOutputLevel){
                tempHighestDirection = i + 1;
            }
        }

        if(!(tempHighestOutputLevel < Nueron.ExcitementThreshold))
            this.move(t, tempHighestDirection);
          
    }

    public void move(TileMap t, int moveValue){
        if (this.x < 100){
            t.getTilesOnMapList().get((this.x * 100) + this.y).containsCreature = false;

            switch(tempHighestDirection){
                case MoveRightValue: this.x++; break;
                case MoveLeftValue: this.x--; break;
                case MoveDownValue: this.y++; break;
                case MoveUpValue: this.y--; break;
            }

            t.getTilesOnMapList().get((this.x * 100) + this.y).containsCreature = true;
        }
    }

}
