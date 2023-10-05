package object;

import entity.Entity;
import main.Gamepanel;

public class OBJ_Shield_Wood extends Entity {
    public  static final String objName = "Wood Shield";
    public OBJ_Shield_Wood(Gamepanel gp) {
        super(gp);

        type = type_shield;
        name = objName;
        down1 = setup("/objects/shield_wood", gp.tileSize, gp.tileSize);
        defenseValue = 2;
        description = "[" + name + "]\nmade by wood.";
        price = 10;
    }
}
