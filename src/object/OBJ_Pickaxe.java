package object;

import entity.Entity;
import main.Gamepanel;

public class OBJ_Pickaxe extends Entity {

    public  static final String objName = "Pickaxe";
    public OBJ_Pickaxe(Gamepanel gp) {
        super(gp);

        type = type_pickaxe;
        name = objName;
        down1 = setup("/objects/pickaxe", gp.tileSize, gp.tileSize);
        attackValue = 2;
        attackArea.width = 30;
        attackArea.height = 30;
        description = "[Pickaxe]\n You can dig it!!";
        price = 30;
        knockBackPower = 10;
        motion1_duration = 10;
        motion2_duration = 20;
    }
}
