package object;

import entity.Entity;
import main.Gamepanel;

public class OBJ_Sword_Normal extends Entity {
    public  static final String objName = "Normal Sword";

    public OBJ_Sword_Normal(Gamepanel gp){
        super(gp);

        type = type_sword;
        name = objName;
        down1 = setup("/objects/sword_normal", gp.tileSize, gp.tileSize);
        attackValue = 2;
        attackArea.width = 36;
        attackArea.height = 36;
        description = "[" + name + "]\nAn old sword.";
        price = 10;
        knockBackPower = 2;
        motion1_duration = 5;
        motion2_duration = 25;
    }
}
