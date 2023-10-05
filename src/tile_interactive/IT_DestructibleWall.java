package tile_interactive;

import entity.Entity;
import main.Gamepanel;
import object.OBJ_Coin_Bronze;
import object.OBJ_ManaCrystal;
import object.Obj_Heart;

import java.awt.*;
import java.util.Random;

public class IT_DestructibleWall extends InteractiveTile{
    Gamepanel gp;
//    public static final String itName = "Destructible Wall";

    public IT_DestructibleWall(Gamepanel gp, int col, int row) {
        super(gp, col, row);
        this.gp = gp;

        this.WorldX = gp.tileSize * col;
        this.WorldY = gp.tileSize * row;

//        name = itName;
        down1 = setup("/tile_interactive/destructiblewall", gp.tileSize, gp.tileSize);
        destructible = true;
        life = 2;
    }
    public boolean isCorrectItem(Entity entity){
        boolean isCorrectItem = false;

        if (entity.currentWeapon.type == type_pickaxe){
            isCorrectItem = true;
        }
        return isCorrectItem;
    }

    public void playSE() {
        gp.playSE(20);
    }

    public InteractiveTile getDestroyedForm() {
        InteractiveTile tile = null;
        return tile;
    }

    public Color getParticleColor() {
        Color color = new Color(65, 65, 65);
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
    public void checkDrop() {
        // CAST A DIE
        int i = new Random().nextInt(100)+1;

        // SET THE MONSTER DROP
        if(i < 50) {
            dropItem(new OBJ_Coin_Bronze(gp));
        }
        if(i >= 75 && i < 100) {
            dropItem(new OBJ_ManaCrystal(gp));
        }
    }
}
