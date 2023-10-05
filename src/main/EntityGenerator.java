package main;

import entity.Entity;
import object.*;

public class EntityGenerator {
    Gamepanel gp;

    public EntityGenerator(Gamepanel gp) {
        this.gp = gp;
    }
    public Entity getObject(String itemName) {
        Entity obj = null;

        switch (itemName) {
            case Obj_Axe.objName: obj = new Obj_Axe(gp); break;
            case Obj_Boots.objName: obj = new Obj_Boots(gp); break;
            case Obj_Chest.objName: obj = new Obj_Chest(gp); break;
            case OBJ_Coin_Bronze.objName: obj = new OBJ_Coin_Bronze(gp); break;
            case Obj_Door.objName: obj = new Obj_Door(gp); break;
            case OBJ_Door_Iron.objName: obj = new OBJ_Door_Iron(gp); break;
            case Obj_Fireball.objName: obj = new Obj_Fireball(gp); break;
            case Obj_Heart.objName: obj = new Obj_Heart(gp); break;
            case Obj_Key.objName: obj = new Obj_Key(gp); break;
            case Obj_Lantern.objName: obj = new Obj_Lantern(gp); break;
            case OBJ_ManaCrystal.objName: obj = new OBJ_ManaCrystal(gp); break;
            case OBJ_Pickaxe.objName: obj = new OBJ_Pickaxe(gp); break;
            case Obj_Potion_Red.objName: obj = new Obj_Potion_Red(gp); break;
            case Obj_Shield_Blue.objName: obj = new Obj_Shield_Blue(gp); break;
            case OBJ_Shield_Wood.objName: obj = new OBJ_Shield_Wood(gp); break;
            case OBJ_Sword_Normal.objName: obj = new OBJ_Sword_Normal(gp); break;
            case Obj_Tent.objName: obj = new Obj_Tent(gp); break;
        }
        return obj;
    }
}
