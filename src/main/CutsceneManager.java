package main;

import entity.PlayerDummy;
import monster.MON_SkeletonLord;
import object.OBJ_BlueHeart;
import object.OBJ_Door_Iron;

import java.awt.*;

public class CutsceneManager {
    Gamepanel gp;
    Graphics2D g2;
    public int sceneNum;
    public int scenePhase;
    int counter = 0;
    float alpha = 0f;
    int y;
    String endCredit;

    //Scene NUmber
    public final int NA = 0;
    public final int skeletonLord = 1;
    public final int ending = 2;

    public CutsceneManager(Gamepanel gp) {
        this.gp = gp;

        endCredit = "Program/Music/Art\n"
                + "Amrit & Rojal"
                + "\n\n\n\n\n\n\n\n\n\n\n\n"
                + "Editor\n"
                + "Rojan Pant"
                + "\n\n\n\n\n\n\n\n\n\n\n\n"
                + "Artist\n"
                + "kist"
                + "\n\n\n\n\n\n\n\n\n\n\n\n"
                + "Special thanks to Roshan sir"
                + "\n\n\n\n\n\n\n\n\n\n\n\n"
                + "Special Thanks\n"
                + "GeeksforGeeks\n"
                + "W3School\n"
                + "Javapoints\n\n\n\n\n"
                + "Thank you for Playing!";
    }

    public void draw(Graphics2D g2) {
        this.g2 = g2;

        switch (sceneNum) {
            case skeletonLord: scene_skeletonLord(); break;
            case ending: scene_ending(); break;
        }
    }
    public void scene_skeletonLord() {
        if (scenePhase == 0) {
            gp.bossBattleOn = true;

            //shut the iron door
            for (int i = 0; i < gp.Obj[1].length; i++) {
                if (gp.Obj[gp.currentMap][i] == null) {
                    gp.Obj[gp.currentMap][i] = new OBJ_Door_Iron(gp);
                    gp.Obj[gp.currentMap][i].WorldX = gp.tileSize * 25;
                    gp.Obj[gp.currentMap][i].WorldY = gp.tileSize * 28;
                    gp.Obj[gp.currentMap][i].temp = true;
                    gp.playSE(21);
                    break;
                }
            }
            // Search a vacant slot for Dummy
            for (int i = 0; i < gp.npc[1].length; i++) {
                if (gp.npc[gp.currentMap][i] == null) {
                    gp.npc[gp.currentMap][i] = new PlayerDummy(gp);
                    gp.npc[gp.currentMap][i].WorldX = gp.player.WorldX;
                    gp.npc[gp.currentMap][i].WorldY = gp.player.WorldY;
                    gp.npc[gp.currentMap][i].direction = gp.player.direction;
                    break;
                }
            }
            gp.player.drawing = false;

            scenePhase++;
        }
        if (scenePhase == 1) {
            gp.player.WorldY -= 2;

            if (gp.player.WorldY < gp.tileSize * 16) {
                scenePhase++;
            }
        }

        if (scenePhase == 2) {
            //Search the BOSS
            for (int i = 0; i < gp.monster[1].length; i++) {
                if (gp.monster[gp.currentMap][i] != null && gp.monster[gp.currentMap][i].name.equals(MON_SkeletonLord.monName)) {
                    gp.monster[gp.currentMap][i].sleep = false;
                    gp.ui.npc = gp.monster[gp.currentMap][i];
                    scenePhase++;
                    break;
                }
            }
        }

        if (scenePhase == 3) {
            //BOSS speaks
            gp.ui.drawDialogueScreen();
        }

        if (scenePhase == 4) {
            //Return to the player

            //Search thr Dummy
            for (int i = 0; i < gp.npc[1].length; i++) {
                if (gp.npc[gp.currentMap][i] != null && gp.npc[gp.currentMap][i].name.equals(PlayerDummy.npcName)) {
                    //Restore the player position
                    gp.player.WorldX = gp.npc[gp.currentMap][i].WorldX;
                    gp.player.WorldY = gp.npc[gp.currentMap][i].WorldY;

                    // Delete the Dummy
                    gp.npc[gp.currentMap][i] = null;
                    break;

                }
            }

            //Start drawing the Dummy
            gp.player.drawing = true;

            //Reset
            sceneNum = NA;
            scenePhase = 0;
            gp.gameState = gp.playState;

            //Change the music
            gp.stopMusic();
            gp.playMusic(22);
        }
    }
    public void scene_ending() {
        if (scenePhase == 0) {
            gp.stopMusic();
            gp.ui.npc = new OBJ_BlueHeart(gp);
            scenePhase++;
        }
        if (scenePhase == 1) {
            //Display dialogues
            gp.ui.drawDialogueScreen();
        }
        if (scenePhase == 2) {
            //Play the fanfare
            gp.playSE(4);
            scenePhase++;
        }
        if (scenePhase == 3) {
            //wait until the sound effect ends
            if (counterReached(300) == true) {
                scenePhase++;
            }
        }
        if (scenePhase == 4) {
            //The screen gets darker
            alpha += 0.005f;
            if (alpha > 1f) {
                alpha = 1f;
            }
            drawBlackBackground(alpha);

            if (alpha == 1f) {
                alpha = 0;
                scenePhase++;
            }
        }
        if (scenePhase == 5) {
            drawBlackBackground(1f);
            alpha += 0.005f;
            if (alpha > 1f) {
                alpha = 1f;
            }
            String text = "After the fierce battle with the Skeleton Lord,\n"
                    + "the Blue Boy finally found the legendary treasure.\n"
                    + "But this is not the end of his journey.\n"
                    + "The Blue Boy's adventure has just begun.";
            drawString(alpha, 38f, 200, text, 70);

            if (counterReached(600) ==  true) {
                gp.playMusic(0);
                scenePhase++;
            }
        }
        if (scenePhase == 6) {
            drawBlackBackground(1f);
            drawString(1f, 120f, gp.screenHeight/2, "Blue Boy Adventure", 40);

            if (counterReached(480) ==  true) {
                scenePhase++;
            }
        }

        if (scenePhase == 7) {
            drawBlackBackground(1f);

            y = gp.screenHeight/2;
            drawString(1f, 38f, y, endCredit,40);

            if (counterReached(480) ==  true) {
                scenePhase++;
            }
        }
        if (scenePhase == 8) {
            drawBlackBackground(1f);

            //scrolling the credit
            y--;
            drawString(1f, 38f, y, endCredit, 40);
        }
    }
    public boolean counterReached(int target) {
        boolean counterReached = false;

        counter++;
        if (counter > target) {
            counterReached = true;
            counter = 0;
        }
        return counterReached;
    }
    public void drawBlackBackground(float alpha) {
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        g2.setColor(Color.black);
        g2.fillRect(0,0, gp.screenWidth, gp.screenHeight);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
    }
    public void drawString(float alpha, float fontSize, int y, String text, int lineHeight) {
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(fontSize));

        for (String line: text.split("\n")) {
            int x = gp.ui.getXforCenteredText(line);
            g2.drawString(line, x, y);
            y += lineHeight;
        }
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
    }
}