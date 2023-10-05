package object;

import entity.Entity;
import main.Gamepanel;

public class OBJ_BlueHeart extends Entity {
    Gamepanel gp;
    public static final String objName = "Blue Heart";

    public OBJ_BlueHeart(Gamepanel gp) {
        super(gp);

        this.gp = gp;

        type = type_pickupOnly;
        name = objName;
        down1 = setup("/objects/blueheart",gp.tileSize,gp.tileSize);

        setDiaglogues();
    }

    public void setDiaglogues() {
        dialogues[0][0] = "You pick up a beautiful blue Gem.";
        dialogues[0][1] = "You find the BLue Heart, the legendary treasure!";
    }

    public boolean use(Entity entity) {
        gp.gameState = gp.cutsceneState;
        gp.csManager.sceneNum = gp.csManager.ending;
        return true;
    }
}
