package entity;

import main.Gamepanel;
import main.UtilityTool;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

public class Entity {
    Gamepanel gp;


    public BufferedImage up1,up2,down1,down2,left1,left2,right1,right2;
    public BufferedImage attackUp1, attackUp2, attackDown1, attackDown2, attackLeft1, attackLeft2, attackRight1, attackRight2, guardUp, guardDown, guardLeft, guardRight;
    public BufferedImage image, image2, image3;
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public Rectangle attackArea = new Rectangle(0, 0, 0, 0);
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collision = false;
    public String dialogues[][] = new String[20][20];
    public Entity attacker;
    public Entity linkedEntity;
    public boolean temp = false;

    //STATE
    public int WorldX,WorldY;
    public String direction = "down";
    public int spriteNum =  1;
    public int dialogueSet = 0;
    public int dialogueIndex = 0;
    public boolean collisionOn = false;
    public boolean invincible = false;
    public boolean attacking = false;
    public boolean alive = true;
    public boolean dying = false;
    public boolean hpBarOn = false;
    public boolean onPath = false;
    public boolean knockBack = false;
    public String knockBackDirection;
    public boolean guarding = false;
    public boolean transparent = false;
    public boolean offBalance = false;
    public Entity loot;
    public boolean opened = false;
    public boolean inRage = false;
    public boolean sleep = false;
    public boolean drawing = true;

    //COUNTER
    public int spriteCounter = 0;
    public int actionLockCounter = 0;
    public int invincibleCounter = 0;
    public int shotAvailableCounter = 0;
    int dyingCounter = 0;
    public int hpBarCounter = 0;
    int knockBackCounter = 0;
    public int guardCounter = 0;
    int offBalanceCounter = 0;

    //CHARACTER ATTRIBUTES
    public String name;
    public int defaultSpeed;
    public int speed;
    public int maxLife;
    public int life;
    public int maxMana;
    public int mana;
    public int ammo;
    public int level;
    public int strength;
    public int dexterity;
    public int attack;
    public int defense;
    public int exp;
    public int nextLevelExp;
    public int coin;
    public int motion1_duration;
    public int motion2_duration;
    public Entity currentWeapon;
    public Entity currentShield;
    public Entity currentLight;
    public Projectile projectile;
    public boolean boss;

    //ITEM ATTRIBUTES
    public ArrayList<Entity> inventory = new ArrayList<>();
    public final int maxInventorySize = 20;
    public int value;
    public int attackValue;
    public int  defenseValue;
    public String description = "";
    public int useCost;
    public int price;
    public int knockBackPower = 0;
    public boolean stackable = false;
    public int amount = 1;
    public int lightRadius;

    //Type
    public int type; // 0 = player, 1 = npc, 2 = monster
    public final int type_player = 0;
    public final int type_npc = 1;
    public final int type_monster = 2;
    public final int type_sword = 3;
    public final int type_axe = 4;
    public final int type_shield = 5;
    public final int type_consumable = 6;
    public final int type_pickupOnly = 7;
    public final int type_obstacle = 8;
    public final int type_light = 9;
    public final int type_pickaxe = 10;



    public Entity(Gamepanel gp) {this.gp = gp;}
    public int getScreenX() {
        int screenX = WorldX - gp.player.WorldX + gp.player.screenX;
        return screenX;
    }
    public int getScreenY() {
        int screenY = WorldY - gp.player.WorldY + gp.player.screenY;
        return screenY;
    }
    public int getLeftX() {
        return WorldX + solidArea.x;
    }
    public int getRightX() {
        return WorldX + solidArea.x + solidArea.width;
    }
    public int getTopY() {
        return WorldY + solidArea.y;
    }
    public int getBottomY() {
        return WorldY + solidArea.y + solidArea.height;
    }
    public int getCol() {
        return (WorldX + solidArea.x)/gp.tileSize;
    }
    public int getRow() {
        return (WorldY + solidArea.y)/gp.tileSize;
    }
    public int getCenterX() {
        int centerX = WorldX + left1.getWidth()/2;
        return centerX;
    }
    public int getCenterY() {
        int centerY = WorldY + up1.getHeight()/2;
        return centerY;
    }
    public int getXdistance(Entity target){
        int xDistance = Math.abs(getCenterX() - target.getCenterX());
        return xDistance;
    }
    public int getYdistance(Entity target){
        int yDistance = Math.abs(getCenterY() - target.getCenterY());
        return yDistance;
    }
    public int getTileDistance(Entity target){
        int tileDistance = Math.abs(getXdistance(target) + getYdistance(target))/gp.tileSize;
        return tileDistance;
    }
    public int getGoalCol(Entity target){
        int goalCol = (gp.player.WorldX + gp.player.solidArea.x)/gp.tileSize;
        return goalCol;
    }
    public int getGoalRow(Entity target){
        int goalRow = (gp.player.WorldY + gp.player.solidArea.y)/gp.tileSize;
        return goalRow;
    }

    public void resetCounter() {
        spriteCounter = 0;
        actionLockCounter = 0;
        invincibleCounter = 0;
        shotAvailableCounter = 0;
        dyingCounter = 0;
        hpBarCounter = 0;
        knockBackCounter = 0;
        guardCounter = 0;
        offBalanceCounter = 0;
    }
    public void setLoot(Entity loot) {}
    public void setAction() {}
    public void move(String direction){}
    public void damageReaction(){}
    public void speak() {}
    public void facePLayer() {

        switch (gp.player.direction) {
            case "up":
                direction = "down";
                break;
            case "down":
                direction = "up";
                break;
            case "left":
                direction = "right";
                break;
            case "right":
                direction = "left";
                break;
        }
    }
    public void startDialogue(Entity entity, int setNum) {
        gp.gameState = gp.dialogueState;
        gp.ui.npc = entity;
        dialogueSet = setNum;
    }
    public void interact() {}
    public boolean use(Entity entity) { return false; }
    public void checkDrop() {}
    public void dropItem(Entity droppedItem) {
        for (int i = 0; i < gp.Obj[1].length; i++) {
            if(gp.Obj[gp.currentMap][i] == null) {
                gp.Obj[gp.currentMap][i] = droppedItem;
                gp.Obj[gp.currentMap][i].WorldX = WorldX; // the dead monster's WorldX
                gp.Obj[gp.currentMap][i].WorldY = WorldY;
                break;
            }
        }
    }
    public Color getParticleColor() {
        Color color = null;
        return color;
    }
    public int getParticleSize() {
        int size = 0; //^ pixels
        return size;
    }
    public int getParticleSpeed() {
        int speed = 0;
        return speed;
    }
    public int getParticleMaxLife() {
        int maxLife = 0;
        return maxLife;
    }
    public void generateParticle(Entity generator, Entity target) {

        Color color = generator.getParticleColor();
        int size = generator.getParticleSize();
        int speed = generator.getParticleSpeed();
        int maxLife = generator.getParticleMaxLife();

        Particle p1 = new Particle(gp, target, color, size, speed, maxLife, -2, -1);
        Particle p2 = new Particle(gp, target, color, size, speed, maxLife, 2, -1);
        Particle p3 = new Particle(gp, target, color, size, speed, maxLife, -2, 1);
        Particle p4 = new Particle(gp, target, color, size, speed, maxLife, 2, 1);
        gp.particleList.add(p1);
        gp.particleList.add(p2);
        gp.particleList.add(p3);
        gp.particleList.add(p4);
    }
    public void checkCollision(){
        //Collision Checker
        collisionOn = false;
        gp.cChecker.CheckTile(this);
        gp.cChecker.checkObject(this, false);
        gp.cChecker.checkEntity(this, gp.npc);
        gp.cChecker.checkEntity(this, gp.monster);
        gp.cChecker.checkEntity(this, gp.iTile);
        boolean contactPlayer = gp.cChecker.checkPlayer(this);

        if (this.type == type_monster && contactPlayer == true) {
            damagePlayer(attack);
        }
    }
    public void update() {
        if (sleep == false) {
            if (knockBack == true) {

                checkCollision();
                if (collisionOn == true) {
                    knockBackCounter = 0;
                    knockBack = false;
                    speed = defaultSpeed;
                }
                else if (collisionOn == false) {
                    switch (knockBackDirection) {
                        case "up": WorldY -= speed; break;
                        case "down": WorldY += speed; break;
                        case "left": WorldX -= speed; break;
                        case "right": WorldX += speed; break;
                    }

                }
                knockBackCounter++;
                if (knockBackCounter == 10) {
                    knockBackCounter = 0;
                    knockBack = false;
                    speed = defaultSpeed;
                }
            }
            else if (attacking == true) {
                attacking();
            }
            else {
                setAction();
                checkCollision();

                //if collision is false, player can move
                if(collisionOn == false){
                    switch (direction){
                        case "up": WorldY -= speed; break;
                        case "down": WorldY += speed; break;
                        case "left": WorldX -= speed; break;
                        case "right": WorldX += speed; break;
                    }
                }
                spriteCounter++;
                if(spriteCounter > 24){
                    if(spriteNum == 1){
                        spriteNum = 2;
                    } else if (spriteNum == 2) {
                        spriteNum = 1;
                    }
                    spriteCounter = 0;
                }
            }
            if(invincible == true) {
                invincibleCounter++;
                if(invincibleCounter > 40) {
                    invincible = false;
                    invincibleCounter = 0;
                }
            }
            if (shotAvailableCounter < 30) {
                shotAvailableCounter++;
            }
            if (offBalance == true){
                offBalanceCounter++;
                if (offBalanceCounter > 60){
                    offBalance =false;
                    offBalanceCounter = 0;
                }
            }
        }
    }
    public void checkAttackOrNot(int rate, int straight, int horizontal){
        boolean targetInRange = false;
        int xDis = getXdistance(gp.player);
        int yDis = getYdistance(gp.player);

        switch (direction){
            case "up":
                if (gp.player.getCenterY() < getCenterY() && yDis < straight && xDis < horizontal){
                    targetInRange = true;
                }
                break;
            case "down":
                if (gp.player.getCenterY() > getCenterY() && yDis < straight && xDis < horizontal){
                    targetInRange = true;
                }
                break;
            case "left":
                if (gp.player.getCenterX() < getCenterX() && xDis < straight && yDis < horizontal){
                    targetInRange = true;
                }
                break;
            case "right":
                if (gp.player.getCenterX() > getCenterX() && xDis < straight && yDis < horizontal){
                    targetInRange = true;
                }
                break;
        }
        if (targetInRange == true){
            //Check if it initiates an attack
            int i = new Random().nextInt(rate);
            if (i == 0){
                attacking = true;
                spriteNum = 1;
                spriteCounter = 0;
                shotAvailableCounter = 0;

            }
        }
    }
    public void checkShootOrNot(int rate, int shotInterval){
        int i = new Random().nextInt(rate);
        if(i == 0 && projectile.alive == false && shotAvailableCounter == shotInterval) {
            projectile.set(WorldX, WorldY, direction, true, this);

            // CHECK VACANCY
            for (int ii = 0; ii < gp.projectile[1].length; ii++) {
                if (gp.projectile[gp.currentMap][ii] == null) {
                    gp.projectile[gp.currentMap][ii] = projectile;
                    break;
                }
            }
            shotAvailableCounter = 0;
        }
    }
    public void checkStartChasingOrNot(Entity target, int distance, int rate){
        if (getTileDistance(target) < distance){
            int i = new Random().nextInt(rate);
            if (i == 0){
                onPath = true;
            }
        }
    }
    public void checkStopChasingOrNot(Entity target, int distance, int rate){
        if (getTileDistance(target) > distance){
            int i = new Random().nextInt(rate);
            if (i == 0){
                onPath = false;
            }
        }
    }
    public void getRandomDirection(int interval){
        actionLockCounter ++;

        if(actionLockCounter > interval) {
            Random random = new Random();
            int i = random.nextInt(100)+1; // pick up a number from 1 to 100

            if(i <= 25) {direction = "up";}
            if(i > 25 && i <= 50) {direction = "down";}
            if(i > 50 && i <= 75) {direction = "left";}
            if(i > 75 && i <= 100) {direction = "right";}
            actionLockCounter = 0;
        }
    }
    public void moveTowardPlayer(int interval) {
        actionLockCounter ++;

        if(actionLockCounter > interval) {
            if (getXdistance(gp.player) > getYdistance(gp.player)) {
                if (gp.player.getCenterX() < getCenterX()) {
                    direction = "left";
                }
                else {
                    direction = "right";
                }
            } else if (getXdistance(gp.player) < getYdistance(gp.player)) {
                if (gp.player.getCenterY() < getCenterY()) {
                    direction = "up";
                }
                else {
                    direction = "down";
                }
            }
            actionLockCounter = 0;
        }

    }
    public String getOppositeDirection(String direction){
        String oppositeDirection = "";

        switch (direction){
            case "up": oppositeDirection = "down"; break;
            case "down": oppositeDirection = "up"; break;
            case "left": oppositeDirection = "right"; break;
            case "right": oppositeDirection = "left"; break;
        }
        return oppositeDirection;
    }
    public void attacking(){
        spriteCounter++;
        if(spriteCounter <= motion1_duration){
            spriteNum = 1;
        }
        if (spriteCounter > motion1_duration && spriteCounter <= motion2_duration){
            spriteNum = 2;
            //Save the current player position
            int currentWorldX = WorldX;
            int currentWorldY = WorldY;
            int solidAreaWidth = solidArea.width;
            int solidAreaHeight = solidArea.height;

            //Adjust player's worldX/Y for the attacking
            switch (direction){
                case "up": WorldY -= attackArea.height; break;
                case "down": WorldY += attackArea.height; break;
                case "left": WorldX -= attackArea.width; break;
                case "right": WorldX += attackArea.width; break;
            }

            //attackArea becomes solidArea
            solidArea.width = attackArea.width;
            solidArea.height = attackArea.height;

            if (type == type_monster){
                if (gp.cChecker.checkPlayer(this) == true){
                    damagePlayer(attack);
                }
            }
            else{//Player
                //check monster collision with the updated worldX/Y and solidArea
                int mosterIndex = gp.cChecker.checkEntity(this, gp.monster);
                gp.player.damageMonster(mosterIndex, this, attack, currentWeapon.knockBackPower);

                int iTileIndex = gp.cChecker.checkEntity(this, gp.iTile);
                gp.player.damageInteractiveTile(iTileIndex);

                int projectileIndex = gp.cChecker.checkEntity(this, gp.projectile);
                gp.player.damageProjectile(projectileIndex);
            }

            //After checking collision, restore the original data
            WorldX = currentWorldX;
            WorldY = currentWorldY;
            solidArea.width = solidAreaWidth;
            solidArea.height = solidAreaHeight;
        }

        if (spriteCounter > motion2_duration){
            spriteNum = 1;
            spriteCounter = 0;
            attacking = false;
        }
    }
    public void damagePlayer(int attack) {
        if (gp.player.invincible == false) {
            int damage = attack - gp.player.defense;

            //Get opposite direction of attacker
            String canGuardDirection = getOppositeDirection(direction);
            if (gp.player.guarding == true && gp.player.direction.equals(canGuardDirection)){
                //Parry
                if (gp.player.guardCounter < 30){
                    damage = 0;
                    gp.playSE(16);
                    setKnockBack(this, gp.player, knockBackPower);
                    offBalance = true;
                    spriteCounter =- 60;
                }
                else {
                    //Normal Guard
                    damage /= 3;
                    gp.playSE(15);
                }
            }
            else {
                //Not guarding
                gp.playSE(6);
                if (damage < 1){
                    damage = 1;
                }
            }
            if (damage != 0){
                gp.player.transparent = true;
                setKnockBack(gp.player, this, knockBackPower);
            }
            gp.player.life -= damage;
            gp.player.invincible = true;
        }
    }
    public void setKnockBack(Entity target,Entity attacker, int knockBackPower) {
        this.attacker = attacker;
        target.knockBackDirection = attacker.direction;
        target.speed += knockBackPower;
        target.knockBack = true;
    }
    public boolean inCamera() {
        boolean inCamera = false;

        if (WorldX + gp.tileSize*5 > gp.player.WorldX - gp.player.screenX &&
                WorldX - gp.tileSize < gp.player.WorldX + gp.player.screenX &&
                WorldY + gp.tileSize*5 > gp.player.WorldY - gp.player.screenY &&
                WorldY - gp.tileSize < gp.player.WorldY + gp.player.screenY) {
            inCamera = true;
        }
        return inCamera;

    }
    public void draw(Graphics2D g2) {

        BufferedImage image = null;

        if (inCamera() == true) {

            int tempScreenX = getScreenX();
            int tempScreenY = getScreenY();

            switch (direction){
                case "up":
                    if (attacking == false){
                        if (spriteNum == 1){image = up1;}
                        if (spriteNum == 2){image = up2;}
                    }
                    if (attacking == true){
                        tempScreenY = getScreenY() - up1.getHeight();
                        if (spriteNum == 1){image = attackUp1;}
                        if (spriteNum == 2){image = attackUp2;}
                    }
                    break;
                case "down":
                    if (attacking == false){
                        if (spriteNum == 1){image = down1;}
                        if (spriteNum ==2){image = down2;}
                    }
                    if (attacking == true){
                        if (spriteNum == 1){image = attackDown1;}
                        if (spriteNum == 2){image = attackDown2;}
                    }

                    break;
                case "left":
                    if (attacking == false){
                        if (spriteNum == 1){image = left1;}
                        if (spriteNum == 2){image = left2;}
                    }
                    if (attacking == true){
                        tempScreenX = getScreenX() - left1.getWidth();
                        if (spriteNum == 1){image = attackLeft1;}
                        if (spriteNum == 2){image = attackLeft2;}
                    }

                    break;
                case "right":
                    if (attacking == false){
                        if (spriteNum == 1){image = right1;}
                        if (spriteNum == 2){image = right2;}
                    }
                    if (attacking == true){
                        if (spriteNum == 1){image = attackRight1;}
                        if (spriteNum == 2){image = attackRight2;}
                    }

                    break;
            }

            if(invincible == true) {
                hpBarOn = true;
                hpBarCounter = 0;
                changeAlpha(g2, 0.4f);
            }
            if (dying == true){
                dyingAnimation(g2);
            }

            g2.drawImage(image, tempScreenX, tempScreenY, null);
            changeAlpha(g2, 1f);

        }
    }
    public void dyingAnimation(Graphics2D g2) {
        dyingCounter++;
        int i = 5;

        if (dyingCounter <= i){changeAlpha(g2, 0f);}
        if (dyingCounter > i && dyingCounter <= i*2){changeAlpha(g2, 1f);}
        if (dyingCounter > i*2 && dyingCounter <= i*3){changeAlpha(g2, 0f);}
        if (dyingCounter > i*3 && dyingCounter <= i*4){changeAlpha(g2, 1f);}
        if (dyingCounter > i*4 && dyingCounter <= i*5){changeAlpha(g2, 0f);}
        if (dyingCounter > i*5 && dyingCounter <= i*6){changeAlpha(g2, 1f);}
        if (dyingCounter > i*6 && dyingCounter <= i*7){changeAlpha(g2, 0f);}
        if (dyingCounter > i*7 && dyingCounter <= i*8){changeAlpha(g2, 1f);}
        if (dyingCounter > i*8){
            alive = false;
        }
    }
    public void changeAlpha(Graphics2D g2, float alphaValue){
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,alphaValue));
    }
    public BufferedImage setup(String imagePath, int width, int height){
        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;

        try {
            image = ImageIO.read(getClass().getResourceAsStream( imagePath +".png"));
            image = uTool.scaleImage(image, width, height);
        } catch (Exception e) {
//            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return image;
    }
    public void searchPath(int goalCol, int goalRow){
        int startCol = (WorldX + solidArea.x)/gp.tileSize;
        int startRow = (WorldY + solidArea.y)/gp.tileSize;

        gp.pFinder.setNode(startCol, startRow, goalCol, goalRow, this);
        if (gp.pFinder.search() == true){
            //Next WorldX & WorldY
            int nextX = gp.pFinder.pathList.get(0).col * gp.tileSize;
            int nextY = gp.pFinder.pathList.get(0).row * gp.tileSize;
            //Entity's solidArea position
            int enLeftX = WorldX + solidArea.x;
            int enRightX = WorldX + solidArea.x + solidArea.width;
            int enTopY = WorldY + solidArea.y;
            int enBottomY = WorldY + solidArea.y + solidArea.height;

            if (enTopY > nextY && enLeftX >= nextX && enRightX < nextX + gp.tileSize){
                direction = "up";
            }
            else if (enTopY < nextY && enLeftX <= nextX && enRightX < nextX + gp.tileSize){
                direction = "down";
            }
            else if (enTopY >= nextY && enBottomY < nextY + gp.tileSize) {
                //left or right
                if (enLeftX > nextX){
                    direction = "left";
                }
                if (enLeftX < nextX){
                    direction = "right";
                }
            }
            else if (enTopY > nextY && enLeftX > nextX) {
                //up or left
                direction = "up";
                checkCollision();
                if (collision == true){
                    direction = "left";
                }
            }
            else if (enTopY > nextY && enLeftX < nextX) {
                //up or right
                direction = "up";
                checkCollision();
                if (collision == true){
                    direction = "right";
                }
            }
            else if (enTopY < nextY && enLeftX > nextX){
                //down or left
                direction = "down";
                checkCollision();
                if (collision == true){
                    direction = "left";
                }
            }
            else if (enTopY < nextY && enLeftX < nextX){
                //down or right
                direction = "down";
                checkCollision();
                if (collision == true){
                    direction = "right";
                }
            }
//            //If reaches goal, stop search
//            int nextCol = gp.pFinder.pathList.get(0).col;
//            int nextRow = gp.pFinder.pathList.get(0).row;
//            if (nextCol == goalCol && nextRow == goalRow){
//                onPath = false;
//            }
        }
    }
    public int getDetected(Entity user, Entity target[][], String targetName) {
        int index = 999;

        // Check the surrounding object
        int nextWorldX = user.getLeftX();
        int nextWorldY = user.getTopY();

        switch (user.direction) {
            case "up": nextWorldY = user.getTopY()-gp.player.speed; break;
            case "down": nextWorldY = user.getBottomY()+gp.player.speed; break;
            case "left": nextWorldX = user.getLeftX()-gp.player.speed; break;
            case "right": nextWorldX = user.getRightX()+gp.player.speed; break;
        }
        int col = nextWorldX/gp.tileSize;
        int row =  nextWorldY/gp.tileSize;

        for (int i = 0; i < target[1].length; i++) {
            if(target[gp.currentMap][i] != null) {
                if(target[gp.currentMap][i].getCol() == col &&
                        target[gp.currentMap][i].getRow() == row &&
                        target[gp.currentMap][i].name.equals(targetName)) {

                    index = i;
                    break;
                }
            }
        }
        return index;
    }
}
