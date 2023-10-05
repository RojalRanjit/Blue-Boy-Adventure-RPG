package main;

import data.Progress;
import entity.NPC_BigRock;
import entity.NPC_Merchant;
import entity.NPC_OldMan;
import monster.*;
import object.*;
import tile_interactive.IT_DestructibleWall;
import tile_interactive.IT_DryTree;
import tile_interactive.IT_MetalPlate;

public class AssetSetter {
    Gamepanel gp;
    public AssetSetter(Gamepanel gp){
        this.gp = gp;
    }

    //Obejct set
    public void setObject(){
        int mapNum = 0;
        int i = 0;

        gp.Obj[mapNum][i] = new Obj_Axe(gp);
        gp.Obj[mapNum][i].WorldX = gp.tileSize*33;
        gp.Obj[mapNum][i].WorldY = gp.tileSize*7;
        i++;

        gp.Obj[mapNum][i] = new Obj_Lantern(gp);
        gp.Obj[mapNum][i].WorldX = gp.tileSize*20;
        gp.Obj[mapNum][i].WorldY = gp.tileSize*7;
        i++;

        gp.Obj[mapNum][i] = new Obj_Tent(gp);
        gp.Obj[mapNum][i].WorldX = gp.tileSize*39;
        gp.Obj[mapNum][i].WorldY = gp.tileSize*42;
        i++;

        gp.Obj[mapNum][i] = new Obj_Door(gp);
        gp.Obj[mapNum][i].WorldX = gp.tileSize*14;
        gp.Obj[mapNum][i].WorldY = gp.tileSize*28;
        i++;

        gp.Obj[mapNum][i] = new Obj_Door(gp);
        gp.Obj[mapNum][i].WorldX = gp.tileSize*12;
        gp.Obj[mapNum][i].WorldY = gp.tileSize*12;
        i++;

//        gp.Obj[mapNum][i] = new Obj_Chest(gp);
//        gp.Obj[mapNum][i].setLoot(new Obj_Key(gp));
//        gp.Obj[mapNum][i].WorldX = gp.tileSize*20;
//        gp.Obj[mapNum][i].WorldY = gp.tileSize*22;
//        i++;

        gp.Obj[mapNum][i] = new Obj_Chest(gp);
        gp.Obj[mapNum][i].setLoot(new Obj_Tent(gp));
        gp.Obj[mapNum][i].WorldX = gp.tileSize*17;
        gp.Obj[mapNum][i].WorldY = gp.tileSize*20;
        i++;

        gp.Obj[mapNum][i] = new Obj_Chest(gp);
        gp.Obj[mapNum][i].setLoot(new Obj_Key(gp));
        gp.Obj[mapNum][i].WorldX = gp.tileSize*30;
        gp.Obj[mapNum][i].WorldY = gp.tileSize*37;
        i++;

        gp.Obj[mapNum][i] = new Obj_Chest(gp);
        gp.Obj[mapNum][i].setLoot(new Obj_Boots(gp));
        gp.Obj[mapNum][i].WorldX = gp.tileSize*30;
        gp.Obj[mapNum][i].WorldY = gp.tileSize*11;
        i++;

        gp.Obj[mapNum][i] = new Obj_Chest(gp);
        gp.Obj[mapNum][i].setLoot(new Obj_Potion_Red(gp));
        gp.Obj[mapNum][i].WorldX = gp.tileSize*30;
        gp.Obj[mapNum][i].WorldY = gp.tileSize*29;
        i++;


        //MAP 2
        mapNum = 2;
        i=0;
        gp.Obj[mapNum][i] = new Obj_Chest(gp);
        gp.Obj[mapNum][i].setLoot(new OBJ_Pickaxe(gp));
        gp.Obj[mapNum][i].WorldX = gp.tileSize*40;
        gp.Obj[mapNum][i].WorldY = gp.tileSize*41;
        i++;

        gp.Obj[mapNum][i] = new Obj_Chest(gp);
        gp.Obj[mapNum][i].setLoot(new Obj_Potion_Red(gp));
        gp.Obj[mapNum][i].WorldX = gp.tileSize*13;
        gp.Obj[mapNum][i].WorldY = gp.tileSize*16;
        i++;

        gp.Obj[mapNum][i] = new Obj_Chest(gp);
        gp.Obj[mapNum][i].setLoot(new Obj_Shield_Blue(gp));
        gp.Obj[mapNum][i].WorldX = gp.tileSize*26;
        gp.Obj[mapNum][i].WorldY = gp.tileSize*34;
        i++;

        gp.Obj[mapNum][i] = new Obj_Chest(gp);
        gp.Obj[mapNum][i].setLoot(new Obj_Boots(gp));
        gp.Obj[mapNum][i].WorldX = gp.tileSize*27;
        gp.Obj[mapNum][i].WorldY = gp.tileSize*15;
        i++;

        gp.Obj[mapNum][i] = new OBJ_Door_Iron(gp);
        gp.Obj[mapNum][i].WorldX = gp.tileSize*18;
        gp.Obj[mapNum][i].WorldY = gp.tileSize*23;
        i++;

        mapNum = 3;
        i = 0;
        gp.Obj[mapNum][i] = new OBJ_Door_Iron(gp);
        gp.Obj[mapNum][i].WorldX = gp.tileSize*25;
        gp.Obj[mapNum][i].WorldY = gp.tileSize*15;
        i++;

        gp.Obj[mapNum][i] = new OBJ_BlueHeart(gp);
        gp.Obj[mapNum][i].WorldX = gp.tileSize*25;
        gp.Obj[mapNum][i].WorldY = gp.tileSize*8;
        i++;

    }
    public void setNPC() {
        int mapNum = 0;
        int i =0;
        //MAP 0
        gp.npc[mapNum][i] = new NPC_OldMan(gp);
        gp.npc[mapNum][i].WorldX = gp.tileSize*21;
        gp.npc[mapNum][i].WorldY = gp.tileSize*21;
        i++;

        //MAP 1
        mapNum = 1;
        i = 0;
        gp.npc[mapNum][i] = new NPC_Merchant(gp);
        gp.npc[mapNum][i].WorldX = gp.tileSize*12;
        gp.npc[mapNum][i].WorldY = gp.tileSize*7;
        i++;

        //MAP 2
        mapNum = 2;
        i = 0;
        gp.npc[mapNum][i] = new NPC_BigRock(gp);
        gp.npc[mapNum][i].WorldX = gp.tileSize*20;
        gp.npc[mapNum][i].WorldY = gp.tileSize*25;
        i++;

        gp.npc[mapNum][i] = new NPC_BigRock(gp);
        gp.npc[mapNum][i].WorldX = gp.tileSize*11;
        gp.npc[mapNum][i].WorldY = gp.tileSize*18;
        i++;

        gp.npc[mapNum][i] = new NPC_BigRock(gp);
        gp.npc[mapNum][i].WorldX = gp.tileSize*23;
        gp.npc[mapNum][i].WorldY = gp.tileSize*14;
        i++;
    }

    public void setMonster() {
        int mapNum = 0;
        int i = 0;
        gp.monster[mapNum][i] = new MON_GreenSlime(gp);
        gp.monster[mapNum][i].WorldX = gp.tileSize*21;
        gp.monster[mapNum][i].WorldY = gp.tileSize*38;
        i++;

//        gp.monster[mapNum][i] = new MON_GreenSlime(gp);
//        gp.monster[mapNum][i].WorldX = gp.tileSize*13;
//        gp.monster[mapNum][i].WorldY = gp.tileSize*33;
//        i++;


        gp.monster[mapNum][i] = new MON_RedSlime(gp);
        gp.monster[mapNum][i].WorldX = gp.tileSize*38;
        gp.monster[mapNum][i].WorldY = gp.tileSize*7;
        i++;

        gp.monster[mapNum][i] = new MON_RedSlime(gp);
        gp.monster[mapNum][i].WorldX = gp.tileSize*36;
        gp.monster[mapNum][i].WorldY = gp.tileSize*42;
        i++;

        gp.monster[mapNum][i] = new MON_Orc(gp);
        gp.monster[mapNum][i].WorldX = gp.tileSize*12;
        gp.monster[mapNum][i].WorldY = gp.tileSize*33;
        i++;

        mapNum = 2;
        i++;
        gp.monster[mapNum][i] = new MON_Bat(gp);
        gp.monster[mapNum][i].WorldX = gp.tileSize*34;
        gp.monster[mapNum][i].WorldY = gp.tileSize*39;
        i++;
        gp.monster[mapNum][i] = new MON_Bat(gp);
        gp.monster[mapNum][i].WorldX = gp.tileSize*36;
        gp.monster[mapNum][i].WorldY = gp.tileSize*25;
        i++;
        gp.monster[mapNum][i] = new MON_Bat(gp);
        gp.monster[mapNum][i].WorldX = gp.tileSize*39;
        gp.monster[mapNum][i].WorldY = gp.tileSize*26;
        i++;
        gp.monster[mapNum][i] = new MON_Bat(gp);
        gp.monster[mapNum][i].WorldX = gp.tileSize*28;
        gp.monster[mapNum][i].WorldY = gp.tileSize*11;
        i++;
        gp.monster[mapNum][i] = new MON_Bat(gp);
        gp.monster[mapNum][i].WorldX = gp.tileSize*10;
        gp.monster[mapNum][i].WorldY = gp.tileSize*19;
        i++;

        mapNum = 3;
        i++;

        if (Progress.skeletonLordDefeated == false) {
            gp.monster[mapNum][i] = new MON_SkeletonLord(gp);
            gp.monster[mapNum][i].WorldX = gp.tileSize*23;
            gp.monster[mapNum][i].WorldY = gp.tileSize*16;
            i++;
        }
    }

    public void setInteractiveTile(){

        int mapNum = 0;
        int i = 0;

        gp.iTile[mapNum][i] = new IT_DryTree(gp, 27, 12); i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 28, 12); i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 29, 12); i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 30, 12); i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 31, 12); i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 32, 12); i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 33, 12); i++;

        gp.iTile[mapNum][i] = new IT_DryTree(gp, 31, 21); i++;

        gp.iTile[mapNum][i] = new IT_DryTree(gp, 30, 30); i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 30, 31); i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 29, 31); i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 28, 31); i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 27, 31); i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 27, 30); i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 27, 29); i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 27, 28); i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 27, 27); i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 26, 27); i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 25, 27); i++;


        gp.iTile[mapNum][i] = new IT_DryTree(gp, 30, 38); i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 30, 39); i++;


        gp.iTile[mapNum][i] = new IT_DryTree(gp, 10, 40); i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 10, 41); i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 11, 41); i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 12, 41); i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 13, 41); i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 13, 40); i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 14, 40); i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 15, 40); i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 16, 40); i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 17, 40); i++;


        mapNum = 2;
        i = 0;
        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp, 18, 30); i++;
        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp, 17, 31); i++;
        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp, 17, 32); i++;
        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp, 17, 34); i++;
        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp, 18, 34); i++;
        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp, 18, 33); i++;
        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp, 10, 22); i++;
        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp, 10, 24); i++;
        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp, 38, 18); i++;
        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp, 38, 19); i++;
        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp, 38, 20); i++;
        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp, 38, 21); i++;
        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp, 18, 13); i++;
        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp, 18, 14); i++;
        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp, 22, 28); i++;
        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp, 30, 28); i++;
        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp, 32, 28); i++;

        gp.iTile[mapNum][i] = new IT_MetalPlate(gp, 20, 22); i++;
        gp.iTile[mapNum][i] = new IT_MetalPlate(gp, 8, 17); i++;
        gp.iTile[mapNum][i] = new IT_MetalPlate(gp, 39, 31); i++;

    }
}