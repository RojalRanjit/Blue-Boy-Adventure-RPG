package object;

import entity.Entity;
import main.Gamepanel;

public class Obj_Lantern extends Entity {
    public  static final String objName = "Lantern";

    public Obj_Lantern(Gamepanel gp) {
        super(gp);

        type = type_light;
        name = objName;
        down1 = setup("/objects/lantern", gp.tileSize, gp.tileSize);
        description = "[Lantern]\n Illuminates your \n surrounding.";
        price = 200;
        lightRadius = 350;
    }
}
