package CustomGame;
import NeuralNetProject.*;

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

    public Creature(int x, int y) {
        this.x = x;
        this.y = y;
        Brain = NeuronTestingInterface.generateCreatureRandomStartingNet();
    }

    public Creature(Creature c){

        this.x = new Int(c.x);
        this.y = new Int(c.y);
        this.Brain = new NueralNet(c.Brain);

    }

    public Creature evolveNewCreatureWithNewConnections(Creature c){
        
        tempBrain = new NueralNet(Brain);
        Random rand = new Random();
        int tempInt = rand.randInt(2) + 1;
        for(int i = 0; i < tempInt; i++){
            NeuronTestingInterface.evolveNewConnection(tempBrain);
        }
        return new Creature(this.x, this.y, tempBrain);

    }

    public Creature evolveNewCreatureWithNewNeuron(Creature c){

        tempBrain = new NueralNet(Brain);
        NeuronTestingInterface.evolveNewNeuronWithConnections(tempBrain);
        return new Creature(this.x, this.y, tempBrain);
        
    }

    public void Tick(TileMap t){
        List<InputNueron> ineurons = Brain.getInputNeuronList();
        ineurons.get(0).setNextExcitementLevel(this.x * 0.01);
        ineurons.get(1).setNextExcitementLevel(this.y * 0.01);
        Brain.TickUpdateAllNeurons();
        List<OutputNeuron> oneurons = Brain.getOutputNeuronList();

        int tempHighestDirection = 1;
        double tempHighestOutputLevel = 0.0;

        for(int i = 0; i < 4; i++){
            if (oneurons.get(i).getExcitement() > tempHighestOutputLevel){
                tempHighestDirection = i + 1;
            }
        }

        if(!(tempHighestOutputLevel < Nueron.ExcitementThreshold))
            this.move(t, tempHighestDirection);
          
    }

    public boolean move(TileMap t, int moveValue){

        boolean cantMove = false;

        if (this.x < 100){
            
            startTile = t.getTilesOnMapList().get((this.x * 100) + this.y);

            switch(moveValue){
                case MoveRightValue: if(x != 100){this.x++;}else{cantMove = true;} break;
                case MoveLeftValue: if(x != 1){this.x--;}else{cantMove = true;} break;
                case MoveDownValue: if(this.y != 100){this.y++;}else{cantMove = true;} break;
                case MoveUpValue: if(this.y != 1){this.y--;}else{cantMove = true;} break;
            }

            endTile = t.getTilesOnMapList().get((this.x * 100) + this.y);

            if(endTile.contents != null)
                
        }
    }

    public UpdatePos(int x, int y){
        t.getTilesOnMapList().get((this.x * 100) + this.y).contents = null;
        
        t.getTilesOnMapList().get((this.x * 100) + this.y).contents = this;
    }

}
