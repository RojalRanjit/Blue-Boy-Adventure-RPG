package object;

import entity.Entity;
import main.Gamepanel;

public class Obj_Door extends Entity {
    Gamepanel gp;
    public  static final String objName = "Door";

    public Obj_Door(Gamepanel gp){
        super(gp);
        this.gp = gp;
        type = type_obstacle;
        name = "Door";
        down1 = setup("/objects/door", gp.tileSize, gp.tileSize);
        collision = true;

        solidArea.x = 0;
        solidArea.y = 16;
        solidArea.width = 48;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        setDialogue();
    }
    public void  setDialogue() {
        dialogues[0][0] = "You need a key to open this";
    }
    public void interact() {
        startDialogue(this,0);
    }
}