package CustomGame;
import NeuralNetProject.*;

import java.util.List;
import java.util.Random;

import static java.lang.Integer.valueOf;


public class Creature {

    private static final int MoveRightValue = 1;
    private static final int MoveLeftValue = 2;
    private static final int MoveDownValue = 3;
    private static final int MoveUpValue = 4;
    public static int allMoves;
    public int moves;
    public int x;
    public int y;
    public NeuralNet Brain;
    private int score;


    public Creature(int x, int y, NeuralNet brain) {
        this.x = x;
        this.y = y;
        Brain = brain;
        this.moves = 0;
    }

    public Creature(int x, int y) {
        this.x = x;
        this.y = y;
        Brain = NeuronTestingInterface.generateCreatureRandomStartingNet(5, 10);
        this.moves = 0;
    }

    public Creature(Creature c, TileMap t){

        Random rand = new Random();
        int randx = rand.nextInt(100);
        int randy = rand.nextInt(100);
        Tile endTile = t.getTilesOnMapList().get((randx * 100) + randy);

        while (true){
            if(endTile.contents == null){
                endTile.contents = this;
                break;
            }
            else{
                randy = rand.nextInt(100);
                randx = rand.nextInt(100);
                endTile = t.getTilesOnMapList().get((randx * 100) + randy);
            }
        }
        this.x = randx;
        this.y = randy;
        this.moves = 0;
        this.Brain = new NeuralNet(c.Brain);
        //System.out.print("endXY Con: " + this.x + "-" + this.y + "\n");

    }

    public Creature evolveNewCreatureWithNewConnections(TileMap t){
        
        NeuralNet tempBrain = new NeuralNet(Brain);
        Random rand = new Random();
        int tempInt = rand.nextInt(2) + 1;
        for(int i = 0; i < tempInt; i++){
            NeuronTestingInterface.evolveNewConnection(tempBrain);
        }
        Creature c = new Creature(this.x, this.y, tempBrain);
        return new Creature(c, t);

    }

    public Creature evolveNewCreatureWithRemovedConnection(TileMap t){

        NeuralNet tempBrain = new NeuralNet(Brain);
        Random rand = new Random();
        int tempInt = rand.nextInt(2) + 1;
        for(int i = 0; i < tempInt; i++){
            NeuronTestingInterface.RemoveConnections(tempBrain, rand.nextInt(2));
        }
        Creature c = new Creature(this.x, this.y, tempBrain);
        return new Creature(c, t);

    }
    public Creature evolveNewCreatureWithRemovedNeuron(TileMap t){

        NeuralNet tempBrain = new NeuralNet(Brain);
        Random rand = new Random();
        int tempInt = rand.nextInt(2) + 1;
        for(int i = 0; i < tempInt; i++){
            NeuronTestingInterface.RemoveNeuron(tempBrain);
        }
        Creature c = new Creature(this.x, this.y, tempBrain);
        return new Creature(c, t);

    }

    public Creature evolveNewCreatureWithNewNeuron(TileMap t){

        Random rand = new Random();
        NeuralNet tempBrain = new NeuralNet(Brain);
        NeuronTestingInterface.evolveNewNeuronWithConnections(tempBrain, rand.nextInt(2), rand.nextInt(2));
        Creature c = new Creature(this.x, this.y, tempBrain);
        return new Creature(c, t);
        
    }

    public void Tick(TileMap t){
        List<InputNeuron> ineurons = Brain.getInputNeuronList();
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

        //System.out.print("highestOut: " + tempHighestOutputLevel + " direction: " + tempHighestDirection + "\n");

        if(!(tempHighestOutputLevel < 1.0))
            if (!this.move(t, tempHighestDirection));
                ineurons.get(2).setNextExcitementLevel(1.0);
          
    }

    public boolean move(TileMap t, int moveValue){

        //System.out.print("Move!\n");

        boolean canMove = true;

            int startX = valueOf(this.x);
            int startY = valueOf(this.y);
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
                this.x = startX;
                this.y = startY;
            }
            else{
                t.getTilesOnMapList().get((startX * 100) + startY).contents = null;
                endTile.contents = this;
            }
        //System.out.print("start tile: " + startTile.contents + " end tile: " + endTile.contents + " this: " + this + "\n");
        //System.out.print("endXY Move: " + this.x + "-" + this.y + "\n");
        if(canMove)
            this.allMoves++;
        return canMove;
    }

    public boolean UpdatePos(TileMap t, int x, int y){

        Tile startTile = t.getTilesOnMapList().get((this.x * 100) + this.y);
        Tile endTile = t.getTilesOnMapList().get((x * 100) + y);
        if(!(endTile.contents == null)){
            //System.out.print("endXY UpdatePos: " + this.x + "-" + this.y + "\n");
            return false;
        }
        else {
            startTile.contents = null;
            endTile.contents = this;
            this.x = x;
            this.y = y;
            //System.out.print("endXY UpdatePos: " + x + "-" + y + "\n");
            return true;
        }
    }

    public void addCreatureToTile(TileMap t){

    }

    public void RemoveFromMap(TileMap T){
        for(Tile t: T.getTilesOnMapList()){
            if(t.contents == this)
                t.contents = null;

        }
    }
}
