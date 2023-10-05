package object;

import entity.Entity;
import main.Gamepanel;

public class Obj_Shield_Blue extends Entity {
    public  static final String objName = "Blue Shield";
    public Obj_Shield_Blue(Gamepanel gp) {
        super(gp);

        type = type_shield;
        name = objName;
        down1 = setup("/objects/shield_blue", gp.tileSize, gp.tileSize);
        defenseValue = 4;
        description = "[" + name + "]\n A shiny blue shield.";
        price = 25;
    }
}
