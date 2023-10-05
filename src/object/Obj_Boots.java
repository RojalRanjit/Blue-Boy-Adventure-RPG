package object;

import entity.Entity;
import main.Gamepanel;

public class Obj_Boots extends Entity {
//    public  static final String objName = "Boots";
//    Gamepanel gp;
//    //boots functions
//    public Obj_Boots(Gamepanel gp) {
//        super(gp);
//        name = objName;
//        down1 = setup("objects/boots", gp.tileSize, gp.tileSize);
//    }
public  static final String objName = "Fast Boots";

    Gamepanel gp;
    public Obj_Boots(Gamepanel gp) {
        super(gp);

        this.gp = gp;

        type = type_consumable;
        name = objName;
        value = 3;
        down1 = setup("/objects/boots", gp.tileSize, gp.tileSize);
        description = "[Fast Boots]\n Run like the wind.";
        price = 25;
        stackable = false;
        setDialouge();
    }

    public void setDialouge() {
        dialogues[0][0] = "You wear the " + name + "!\n" + "Your speed has been increased " + value + ".";
    }

    public boolean use(Entity entity) {
        startDialogue(this,0);
        entity.speed += value;
        gp.playSE(2);
        return true;
    }
}