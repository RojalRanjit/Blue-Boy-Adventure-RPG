package tile_interactive;

import entity.Entity;
import main.Gamepanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class InteractiveTile extends Entity {

    Gamepanel gp;
    public boolean destructible = false;

    public InteractiveTile(Gamepanel gp, int col, int row) {
        super(gp);
        this.gp =gp;
    }

    public boolean isCorrectItem(Entity entity){
        boolean isCorrectItem = false;
        return isCorrectItem;
    }

    public void playSE() {    }

    public InteractiveTile getDestroyedForm() {
        InteractiveTile tile = null;
        return tile;
    }

    public void update(){
        if (invincible == true) {
            invincibleCounter++;
            if (invincibleCounter > 20) {
                invincible = false;
                invincibleCounter = 0;
            }
        }
    }

    public void draw(Graphics2D g2) {

        int screenX = WorldX - gp.player.WorldX + gp.player.screenX;
        int screenY = WorldY - gp.player.WorldY + gp.player.screenY;

        if (WorldX + gp.tileSize > gp.player.WorldX - gp.player.screenX &&
                WorldX - gp.tileSize < gp.player.WorldX + gp.player.screenX &&
                WorldY + gp.tileSize > gp.player.WorldY - gp.player.screenY &&
                WorldY - gp.tileSize < gp.player.WorldY + gp.player.screenY) {

            g2.drawImage(down1, screenX, screenY, null);

        }
    }
}
