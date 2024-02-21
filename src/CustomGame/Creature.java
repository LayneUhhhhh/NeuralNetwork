package CustomGame;
import NeuralNetProject.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.lang.Integer.valueOf;


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

    public Creature(int x, int y) {
        this.x = x;
        this.y = y;
        Brain = NeuronTestingInterface.generateCreatureRandomStartingNet(40, 400);
    }

    public Creature(Creature c){

        this.x = valueOf(c.x);
        this.y = valueOf(c.y);
        this.Brain = new NueralNet(c.Brain);

    }

    public Creature evolveNewCreatureWithNewConnections(){
        
        NueralNet tempBrain = new NueralNet(Brain);
        Random rand = new Random();
        int tempInt = rand.nextInt(2) + 1;
        for(int i = 0; i < tempInt; i++){
            NeuronTestingInterface.evolveNewConnection(tempBrain);
        }
        return new Creature(this.x, this.y, tempBrain);

    }

    public Creature evolveNewCreatureWithNewNeuron(){

        Random rand = new Random();
        NueralNet tempBrain = new NueralNet(Brain);
        NeuronTestingInterface.evolveNewNeuronWithConnections(tempBrain, rand.nextInt(2), rand.nextInt(2));
        return new Creature(this.x, this.y, tempBrain);
        
    }

    public void Tick(TileMap t){
        List<InputNueron> ineurons = Brain.getInputNeuronList();
        ineurons.get(0).setNextExcitementLevel(this.x * 0.01);
        ineurons.get(1).setNextExcitementLevel(this.y * 0.01);
        ineurons.get(2).setNextExcitementLevel(0.0);
        Brain.TickUpdateAllNeurons();
        List<OutputNeuron> oneurons = Brain.getOutputNeuronList();

        int tempHighestDirection = 1;
        double tempHighestOutputLevel = 0.0;

        for(int i = 0; i < 4; i++){
            if (oneurons.get(i).getExcitement() > tempHighestOutputLevel){
                tempHighestDirection = i + 1;
                tempHighestOutputLevel = oneurons.get(i).getExcitement();
            }
        }

        System.out.print("highestOut: " + tempHighestOutputLevel + " direction: " + tempHighestDirection + "\n");

        if(!(tempHighestOutputLevel < Nueron.ExcitementThreshold))
            if (!this.move(t, tempHighestDirection));
                ineurons.get(2).setNextExcitementLevel(1.0);
          
    }

    public boolean move(TileMap t, int moveValue){

        System.out.print("Move!\n");

        boolean canMove = true;

        if (this.x < 100){
            
            Tile startTile = t.getTilesOnMapList().get((this.x * 100) + this.y);

            switch(moveValue){
                case MoveRightValue: if(x != 99){this.x++;}else{canMove = false;} break;
                case MoveLeftValue: if(x != 0){this.x--;}else{canMove = false;} break;
                case MoveDownValue: if(this.y != 99){this.y++;}else{canMove = false;} break;
                case MoveUpValue: if(this.y != 0){this.y--;}else{canMove = false;} break;
            }

            Tile endTile = t.getTilesOnMapList().get((this.x * 100) + this.y);

            if(endTile.contents != null){
                canMove = false;
                this.x = startTile.x;
                this.y = startTile.y;
            }
            else{
                startTile.contents = null;
                endTile.contents = this;
            }
        }
        return canMove;
    }

    public boolean UpdatePos(TileMap t, int x, int y){

        Tile endTile = t.getTilesOnMapList().get((this.x * 100) + this.y);
        Tile startTile = t.getTilesOnMapList().get((x * 100) + y);
        if(endTile.contents != null){
            return false;
        }
        else {
            startTile.contents = null;
            endTile.contents = this;
            return true;
        }
    }

    public void addCreatureToTile(TileMap t){


    }
}
