package main;

import ai.PathFinder;
import data.SaveLoad;
import entity.Entity;
import entity.Player;
import environment.EnvironmentManager;
import org.w3c.dom.ls.LSOutput;
import tile.Map;
import tile.TileManager;
import tile_interactive.InteractiveTile;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class Gamepanel extends JPanel implements Runnable {
    // SCREEN SETTINGS
    final int originalTileSize = 16; // 16x16 tile
    final int scale = 3;

    public final int tileSize = originalTileSize * scale; // 48x48 tile
    public final int maxScreenCol = 20;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol; // 960 pixel
    public final int screenHeight = tileSize * maxScreenRow; // 576 pixels

    // WORLD SETTINGS
    public int maxWorldCol;
    public int maxWorldRow;
//    public final int worldWidth = titleSize * maxWorldCol;
//    public final int worldHeight = titleSize * maxWorldRow;
    public final int maxMap = 10; //max number of maps
    public int currentMap = 0;

    //Full Screen
    int screenWidth2 = screenWidth;
    int screenHeight2 = screenHeight;
    BufferedImage tempScreen;
    Graphics2D g2;
    public boolean fullScreenOn = false;

    //FPS
    int FPS = 60;

    //SYSTEM

    public TileManager tileM = new TileManager(this);
    public KeyHandler keyH = new KeyHandler(this);
    Sound music = new Sound();
    Sound se = new Sound();
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public UI ui = new UI(this);
    public EventHandler eHandler = new EventHandler(this);
    Config config = new Config(this);
    public PathFinder pFinder = new PathFinder(this);
    EnvironmentManager eManager = new EnvironmentManager(this);
    Map map = new Map(this);
    SaveLoad saveLoad = new SaveLoad(this);
    public EntityGenerator eGenerator = new EntityGenerator(this);
    public CutsceneManager csManager = new CutsceneManager(this);
    Thread gameThread;


    //ENTITY AND OBJECT
    public Player player = new Player(this,keyH);
    public Entity Obj[][] = new Entity[maxMap][20];
    public Entity npc[][] = new Entity[maxMap][10];
    public Entity monster[][] = new Entity[maxMap][20];
    public InteractiveTile iTile[][] = new InteractiveTile[maxMap][50];
    public Entity projectile[][] = new Entity[maxMap][20];
//    public ArrayList<Entity> projectileList = new ArrayList<>();
    public ArrayList<Entity> particleList = new ArrayList<>();
    ArrayList<Entity> entityList = new ArrayList<>();

    //GAME STATE
    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogueState = 3;
    public final int characterState = 4;
    public final int optionsState = 5;
    public final int gameOverState = 6;
    public final int transitionState = 7;
    public final int tradeState = 8;
    public final int sleepState = 9;
    public final int mapState = 10;
    public final int cutsceneState = 11;

    //OTHERS
    public boolean bossBattleOn = false;

    //AREA
    public int currentArea;
    public int nextArea;
    public final int outside = 50;
    public final int indoor = 51;
    public final int dungeon = 52;



    public Gamepanel(){
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }
    public void setupGame() {
        aSetter.setObject();
        aSetter.setNPC();
        aSetter.setMonster();
        aSetter.setInteractiveTile();
        eManager.setup();

        gameState = titleState;
        currentArea = outside;

        tempScreen = new BufferedImage(screenWidth, screenHeight, BufferedImage.TYPE_INT_ARGB);
        g2 = (Graphics2D)tempScreen.getGraphics();

        if (fullScreenOn == true) {
            setFullScreen();
        }
    }
    public void resetGame(boolean restart) {

        stopMusic();
        currentArea = outside;
        removeTempEntity();
        bossBattleOn = false;
        player.setDefaultPositions();
        player.restoreStatus();
        player.resetCounter();
        aSetter.setNPC();
        aSetter.setMonster();

        if (restart == true) {
            player.setDefaultValues();
            aSetter.setObject();
            aSetter.setInteractiveTile();
            eManager.lighting.resetDay();
        }
    }
    public void setFullScreen() {
        //Get Local Screen Device
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        gd.setFullScreenWindow(Main.window);

        //Get Full Screen Width & Height
        screenWidth2 = Main.window.getWidth();
        screenHeight2 = Main.window.getHeight();
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        long drawCount = 0;
        while (gameThread!= null){
            currentTime = System.nanoTime();
            delta+= (currentTime -lastTime)/drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if(delta>=1){
                update();
                drawToTempScreen(); //draw everything to the buffered image
                drawToScreen(); //draw the buffered image to the screen
                delta--;
                drawCount++;
            }
            if (timer>=1000000000){
//                System.out.println("FPS"+drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }

    public void update(){

        if (gameState == playState){
            // PLAYER
            player.update();
            // NPC
            for(int i = 0; i < npc[1].length; i++) {
                if(npc[currentMap][i] != null) {
                    npc[currentMap][i].update();
                }
            }

            // MONSTER
            for(int i = 0; i < monster[1].length; i++) {
                if(monster[currentMap][i] != null) {
                    if (monster[currentMap][i].alive == true && monster[currentMap][i].dying == false){
                        monster[currentMap][i].update();
                    }
                    if (monster[currentMap][i].alive == false){
                        monster[currentMap][i].checkDrop();
                        monster[currentMap][i] = null;
                    }
                }
            }

            for(int i = 0; i < projectile[1].length; i++) {
                if(projectile[currentMap][i] != null) {
                    if (projectile[currentMap][i].alive == true){
                        projectile[currentMap][i].update();
                    }
                    if (projectile[currentMap][i].alive == false){
                        projectile[currentMap][i] = null;
                    }
                }
            }

            for(int i = 0; i < particleList.size(); i++) {
                if(particleList.get(i) != null) {
                    if (particleList.get(i).alive == true){
                        particleList.get(i).update();
                    }
                    if (particleList.get(i).alive == false){
                        particleList.remove(i);
                    }
                }
            }

            for (int i = 0; i < iTile[1].length; i++){
                if (iTile[currentMap][i] != null){
                    iTile[currentMap][i].update();
                }
            }
            eManager.update();
        }
        if (gameState == pauseState){
            //nothing
        }
    }

    public void drawToTempScreen() {
        //DEBUG
        long drawStart = 0;
        if (keyH.checkDrawTime == true) {
            drawStart = System.nanoTime();
        }

        //Title screen
        if(gameState == titleState) {
            ui.draw(g2);
        }
        // Map Screen
        else if (gameState == mapState) {
            map.drawFullMapScreen(g2);
        }

        //Others
        else {
            //TILE
            tileM.draw(g2);

            //INTERACTIVE TILE
            for (int i = 0; i < iTile[1].length; i++){
                if (iTile[currentMap][i] != null){
                    iTile[currentMap][i] .draw(g2);
                }
            }

            //Add Entities to list
            entityList.add(player);
            for (int i = 0; i < npc[1].length; i++) {
                if (npc[currentMap][i] != null) {
                    entityList.add(npc[currentMap][i]);
                }
            }

            for (int i = 0; i < Obj[1].length; i++) {
                if (Obj[currentMap][i] != null) {
                    entityList.add(Obj[currentMap][i]);
                }
            }

            for (int i = 0; i < monster[1].length; i++) {
                if (monster[currentMap][i] != null) {
                    entityList.add(monster[currentMap][i]);
                }
            }

            for (int i = 0; i < projectile[1].length; i++) {
                if (projectile[currentMap][i] != null) {
                    entityList.add(projectile[currentMap][i]);
                }
            }

            for (int i = 0; i < particleList.size(); i++) {
                if (particleList.get(i) != null) {
                    entityList.add(particleList.get(i));
                }
            }

            //Sort
            Collections.sort(entityList, new Comparator<Entity>() {
                @Override
                public int compare(Entity e1, Entity e2) {
                    int result = Integer.compare(e1.WorldY, e2.WorldY);
                    return 0;
                }
            });

            //Draw Entities
            for (int i = 0; i < entityList.size(); i++) {
                entityList.get(i).draw(g2);
            }

            //Empty Entity list
            entityList.clear();

            //Environment
            eManager.draw(g2);

            // MINI MAP
            map.drawMiniMap(g2);

            //CUTSCEME
            csManager.draw(g2);

            //UI
            ui.draw(g2);
        }

        //DEBUG
//        if (keyH.checkDrawTime){
//            long drawEnd = System.nanoTime();
//            long passed = drawEnd - drawStart;
//            g2.setColor(Color.white);
//            g2.drawString("Draw Time: " + passed, 10, 400);
//            System.out.println("Draw Time: " + passed);
//        }
        if (keyH.showDebugText == true) {
            long drawEnd = System.nanoTime();
            long passed = drawEnd - drawStart;

            g2.setFont(new Font("Arial", Font.PLAIN, 20));
            g2.setColor(Color.white);
            int x = 10;
            int y = 400;
            int lineHeight = 20;

            g2.drawString("WorldX: " + player.WorldX, x, y); y += lineHeight;
            g2.drawString("WorldY: " + player.WorldY, x, y); y += lineHeight;
            g2.drawString("Col: " + (player.WorldX + player.solidArea.x)/tileSize, x, y); y += lineHeight;
            g2.drawString("Row: " + (player.WorldY + player.solidArea.y)/tileSize, x, y); y += lineHeight;
            g2.drawString("Draw TIme: " + passed, x, y); y += lineHeight;
            g2.drawString("God Mode: " + keyH.godModeOn, x, y);
        }


        // Get the tile num
//        int playerTileNum = tileM.mapTileNum[currentMap][player.getCol()][player.getRow()];
//        g2.drawString("Player is on" + playerTileNum + " tile", x, y);
    }

    public void drawToScreen() {
        Graphics g = getGraphics();
        g.drawImage(tempScreen, 0, 0, screenWidth2, screenHeight2, null);
        g.dispose();
    }

    //Sound parts
    public void playMusic(int i) {
        music.setFile(i);
        music.play();
        music.loop();
    }

    public void stopMusic() {
        music.stop();
    }

    public void playSE(int i) {
        se.setFile(i);
        se.play();
    }
    public void changeArea(){
        if (nextArea != currentArea){
            stopMusic();
            if (nextArea == outside){
                playMusic(0);
            }
            if (nextArea == indoor){
                playMusic(18);
            }
            if (nextArea == dungeon){
                playMusic(19);
            }
            aSetter.setNPC();
        }
        currentArea = nextArea;
        aSetter.setMonster();
    }

    public void removeTempEntity() {
        for (int mapNum = 0; mapNum < maxMap; mapNum++) {
            for (int i =0; i < Obj[1].length; i++) {
                if (Obj[mapNum][i] != null && Obj[mapNum][i].temp == true) {
                    Obj[mapNum][i] = null;
                }
            }
        }
    }
}
