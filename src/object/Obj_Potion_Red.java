package object;

import entity.Entity;
import main.Gamepanel;

public class Obj_Potion_Red extends Entity {
    public  static final String objName = "Red Potion";

    Gamepanel gp;
    public Obj_Potion_Red(Gamepanel gp) {
        super(gp);

        this.gp = gp;

        type = type_consumable;
        name = objName;
        value = 5;
        down1 = setup("/objects/potion_red", gp.tileSize, gp.tileSize);
        description = "[Red Potion]\n Heals your life by" + value + ".";
        price = 25;
        stackable = true;
        setDialouge();
    }

    public void setDialouge() {
        dialogues[0][0] = "You drink the " + name + "!\n" + "Your life has been recovered by " + value + ".";
    }

    public boolean use(Entity entity) {
        startDialogue(this,0);
        entity.life += value;
        gp.playSE(2);
        return true;
    }
}

