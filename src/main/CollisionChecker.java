package main;

import entity.Entity;

public class CollisionChecker {
    Gamepanel gp;
    public CollisionChecker(Gamepanel gp){
        this.gp = gp;
    }

    public void CheckTile(Entity entity){
        int entityLeftWorldX = entity.WorldX + entity.solidArea.x;
        int entityRightWorldX = entity.WorldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.WorldY + entity.solidArea.y;
        int entityBottomWorldY = entity.WorldY + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftWorldX/gp.tileSize;
        int entityRightCol = entityRightWorldX/gp.tileSize;
        int entityTopRow = entityTopWorldY/gp.tileSize;
        int entityBottomRow = entityBottomWorldY/gp.tileSize;

        int tileNum1, tileNum2;
        //Use a temporal direction when it's being knockbacked
        String direction = entity.direction;
        if (entity.knockBack == true){
            direction = entity.knockBackDirection;
        }
        switch (direction){
            case "up":
                entityTopRow = (entityTopWorldY - entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityTopRow];

                if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                    entity.collisionOn = true;
                }
                break;
            case "down":
                entityBottomRow = (entityBottomWorldY + entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityBottomRow];
                tileNum2 = gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityBottomRow];

                if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                    entity.collisionOn = true;
                }
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityBottomRow];

                if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                    entity.collisionOn = true;
                }
                break;
            case "right":
                entityRightCol = (entityRightWorldX - entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityBottomRow];

                if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                    entity.collisionOn = true;
                }
                break;


        }
    }
    public int checkObject(Entity entity, boolean player) {
        int index = 999;

        //Use a temporal direction when it's being knockbacked
        String direction = entity.direction;
        if (entity.knockBack == true){
            direction = entity.knockBackDirection;
        }

        for (int i = 0; i < gp.Obj[1].length; i++){
            if (gp.Obj[gp.currentMap][i] != null) {
                // Get entity's solid area position
                entity.solidArea.x = entity.WorldX + entity.solidArea.x;
                entity.solidArea.y = entity.WorldY + entity.solidArea.y;

                // Get the object's solid area position
                gp.Obj[gp.currentMap][i].solidArea.x = gp.Obj[gp.currentMap][i].WorldX + gp.Obj[gp.currentMap][i].solidArea.x;
                gp.Obj[gp.currentMap][i].solidArea.y = gp.Obj[gp.currentMap][i].WorldY + gp.Obj[gp.currentMap][i].solidArea.y;
                switch (direction)
                {
                    case "up": entity.solidArea.y -= entity.speed; break;
                    case "down": entity.solidArea.y += entity.speed; break;
                    case "left": entity.solidArea.x -= entity.speed; break;
                    case "right": entity.solidArea.x += entity.speed; break;
                }

                if (entity.solidArea.intersects(gp.Obj[gp.currentMap][i].solidArea)){
                    if (gp.Obj[gp.currentMap][i].collision == true){
                        entity.collisionOn = true;
                    }
                    if (player == true){
                        index = i;
                    }
                }

                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                gp.Obj[gp.currentMap][i].solidArea.x = gp.Obj[gp.currentMap][i].solidAreaDefaultX;
                gp.Obj[gp.currentMap][i].solidArea.y = gp.Obj[gp.currentMap][i].solidAreaDefaultY;
            }
        }
        return index;
    }
    // NPC OR MONSTER
    public int checkEntity(Entity entity, Entity[][] target) {
        int index = 999;
        //Use a temporal direction when it's being knockbacked
        String direction = entity.direction;
        if (entity.knockBack == true){
            direction = entity.knockBackDirection;
        }

        for (int i = 0; i < target[1].length; i++){
            if (target[gp.currentMap][i] != null) {
                // Get entity's solid area position
                entity.solidArea.x = entity.WorldX + entity.solidArea.x;
                entity.solidArea.y = entity.WorldY + entity.solidArea.y;

                // Get the object's solid area position
                target[gp.currentMap][i].solidArea.x = target[gp.currentMap][i].WorldX + target[gp.currentMap][i].solidArea.x;
                target[gp.currentMap][i].solidArea.y = target[gp.currentMap][i].WorldY + target[gp.currentMap][i].solidArea.y;
                switch (direction)
                {
                    case "up": entity.solidArea.y -= entity.speed; break;
                    case "down": entity.solidArea.y += entity.speed; break;
                    case "left": entity.solidArea.x -= entity.speed; break;
                    case "right": entity.solidArea.x += entity.speed; break;
                }

                if (entity.solidArea.intersects(target[gp.currentMap][i].solidArea)){
                    if(target[gp.currentMap][i] != entity) {
                        entity.collisionOn = true;
                        index = i;
                    }
                }

                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                target[gp.currentMap][i].solidArea.x = target[gp.currentMap][i].solidAreaDefaultX;
                target[gp.currentMap][i].solidArea.y = target[gp.currentMap][i].solidAreaDefaultY;
            }
        }
        return index;
    }
    public boolean checkPlayer(Entity entity) {

        boolean contactPlayer = false;

        // Get entity's solid area position
        entity.solidArea.x = entity.WorldX + entity.solidArea.x;
        entity.solidArea.y = entity.WorldY + entity.solidArea.y;

        // Get the object's solid area position
        gp.player.solidArea.x = gp.player.WorldX + gp.player.solidArea.x;
        gp.player.solidArea.y = gp.player.WorldY + gp.player.solidArea.y;
        switch (entity.direction) {
            case "up":
                entity.solidArea.y -= entity.speed;
                break;
            case "down":
                entity.solidArea.y += entity.speed;
                break;
            case "left":
                entity.solidArea.x -= entity.speed;
                break;
            case "right":
                entity.solidArea.x += entity.speed;
                break;
        }

        if (entity.solidArea.intersects(gp.player.solidArea)) {
            entity.collisionOn = true;
            contactPlayer = true;
        }

        entity.solidArea.x = entity.solidAreaDefaultX;
        entity.solidArea.y = entity.solidAreaDefaultY;
        gp.player.solidArea.x = gp.player.solidAreaDefaultX;
        gp.player.solidArea.y = gp.player.solidAreaDefaultY;

        return contactPlayer;
    }
}
