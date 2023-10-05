package object;

import entity.Entity;
import main.Gamepanel;

public class Obj_Axe extends Entity {

    public  static final String objName = "Woodcutter's Axe";
    public Obj_Axe(Gamepanel gp) {
        super(gp);

        type = type_axe;
        name = objName;
        down1 = setup("/objects/axe", gp.tileSize, gp.tileSize);
        attackValue = 2;
        attackArea.width = 30;
        attackArea.height = 30;
        description = "[Woodcutter's Axe]\n A bit rusty but still \n can cut some trees.";
        price = 10;
        knockBackPower = 10;
        motion1_duration = 20;
        motion2_duration = 40;
    }
}
