package tile_interactive;

import entity.Entity;
import main.Gamepanel;

import java.awt.*;

public class IT_DryTree extends InteractiveTile{
    Gamepanel gp;
//    public static final String itName = "Dry Tree";

    public IT_DryTree(Gamepanel gp, int col, int row) {
        super(gp, col, row);
        this.gp = gp;

        this.WorldX = gp.tileSize * col;
        this.WorldY = gp.tileSize * row;

//        name = itName;
        down1 = setup("/tile_interactive/drytree", gp.tileSize, gp.tileSize);
        destructible = true;
        life = 1;
    }
    public boolean isCorrectItem(Entity entity){
        boolean isCorrectItem = false;

        if (entity.currentWeapon.type == type_axe){
            isCorrectItem = true;
        }
        return isCorrectItem;
    }

    public void playSE() {
        gp.playSE(11);
    }

    public InteractiveTile getDestroyedForm() {
        InteractiveTile tile = new IT_Trunk(gp, WorldX/gp.tileSize, WorldY/gp.tileSize);
        return tile;
    }

    public Color getParticleColor() {
        Color color = new Color(65, 50, 30);
        return color;
    }

    public int getParticleSize() {
        int size = 6; //^ pixels
        return size;
    }

    public int getParticleSpeed() {
        int speed = 1;
        return speed;
    }

    public int getParticleMaxLife() {
        int maxLife = 20;
        return maxLife;
    }
}
