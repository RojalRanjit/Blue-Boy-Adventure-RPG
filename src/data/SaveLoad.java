package data;

import entity.Entity;
import main.Gamepanel;
import object.*;

import java.io.*;

public class SaveLoad {
    Gamepanel gp;
    public SaveLoad(Gamepanel gp) {
        this.gp = gp;
    }

    public void save() {

        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("save.dat")));

            DataStorage ds = new DataStorage();

            // Player stats
            ds.level = gp.player.level;
            ds.maxLife = gp.player.maxLife;
            ds.life = gp.player.life;
            ds.maxMana = gp.player.maxMana;
            ds.mana = gp.player.mana;
            ds.strength = gp.player.strength;
            ds.dexterity = gp.player.dexterity;
            ds.exp = gp.player.exp;
            ds.nextLevelExp = gp.player.nextLevelExp;
            ds.coin = gp.player.coin;

            // Player Inventory
            for (int i = 0; i < gp.player.inventory.size(); i++) {
                ds.itemNames.add(gp.player.inventory.get(i).name);
                ds.itemAmounts.add(gp.player.inventory.get(i).amount);
            }
            // Player Equipment
            ds.currentWeaponSlot = gp.player.getCurrentWeaponSlot();
            ds.currentShieldSlot = gp.player.getCurrentShieldSlot();

            // Objects On Map
            ds.mapObjectNames = new String[gp.maxMap][gp.Obj[1].length];
            ds.mapObjectWorldX = new int[gp.maxMap][gp.Obj[1].length];
            ds.mapObjectWorldY = new int[gp.maxMap][gp.Obj[1].length];
            ds.mapObjectLootNames = new String[gp.maxMap][gp.Obj[1].length];
            ds.mapObjectOpened = new boolean[gp.maxMap][gp.Obj[1].length];

            for (int mapNum = 0; mapNum < gp.maxMap; mapNum++) {
                for (int i = 0; i < gp.Obj[1].length; i++) {
                    if (gp.Obj[mapNum][i] == null) {
                        ds.mapObjectNames[mapNum][i] = "NA";
                    }
                    else {
                        ds.mapObjectNames[mapNum][i] = gp.Obj[mapNum][i].name;
                        ds.mapObjectWorldX[mapNum][i] = gp.Obj[mapNum][i].WorldX;
                        ds.mapObjectWorldY[mapNum][i] = gp.Obj[mapNum][i].WorldY;
                        if (gp.Obj[mapNum][i].loot != null) {
                            ds.mapObjectLootNames[mapNum][i] = gp.Obj[mapNum][i].loot.name;
                        }
                        ds.mapObjectOpened[mapNum][i] = gp.Obj[mapNum][i].opened;
                    }
                }
            }

            // Write the DataStorage object
            oos.writeObject(ds);
        }
        catch (Exception e) {
            System.out.println("Save Exception");
        }
    }
    public void load() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("save.dat")));

            // Read the DataStorage object
            DataStorage ds = (DataStorage)ois.readObject();

            // Player stats
            gp.player.level = ds.level;
            gp.player.maxLife = ds.maxLife;
            gp.player.life = ds.life;
            gp.player.maxMana = ds.maxMana;
            gp.player.mana = ds.mana;
            gp.player.strength = ds.strength;
            gp.player.dexterity = ds.dexterity;
            gp.player.exp = ds.exp;
            gp.player.nextLevelExp = ds.nextLevelExp;
            gp.player.coin = ds.coin;

            // Player Inventory
            gp.player.inventory.clear();
            for (int i = 0; i < ds.itemNames.size(); i++) {
                gp.player.inventory.add(gp.eGenerator.getObject(ds.itemNames.get(i)));
                gp.player.inventory.get(i).amount = ds.itemAmounts.get(i);
            }
            // Player Equipment
            gp.player.currentWeapon = gp.player.inventory.get(ds.currentWeaponSlot);
            gp.player.currentShield = gp.player.inventory.get(ds.currentShieldSlot);
            gp.player.getAttack();
            gp.player.getDefense();
            gp.player.getAttackImage();

            // Object On Map
            for (int mapNum = 0; mapNum < gp.maxMap; mapNum++) {
                for (int i = 0; i < gp.Obj[1].length; i++) {
                    if (ds.mapObjectNames[mapNum][i].equals("NA")) {
                        gp.Obj[mapNum][i] = null;
                    }
                    else {
                        gp.Obj[mapNum][i] = gp.eGenerator.getObject(ds.mapObjectNames[mapNum][i]);
                        gp.Obj[mapNum][i].WorldX = ds.mapObjectWorldX[mapNum][i];
                        gp.Obj[mapNum][i].WorldY = ds.mapObjectWorldY[mapNum][i];
                        if (ds.mapObjectLootNames[mapNum][i] != null) {
                            gp.Obj[mapNum][i].setLoot(gp.eGenerator.getObject(ds.mapObjectLootNames[mapNum][i]));
                        }
                        gp.Obj[mapNum][i].opened = ds.mapObjectOpened[mapNum][i];
                        if (gp.Obj[mapNum][i].opened == true) {
                            gp.Obj[mapNum][i].down1 = gp.Obj[mapNum][i].image2;
                        }
                    }
                }
            }
        }
        catch (Exception e) {
            System.out.println("Load Exception");
        }

    }
}
