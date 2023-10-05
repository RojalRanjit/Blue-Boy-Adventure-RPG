package main;

import javax.swing.*;

public class Main {

    public static JFrame window;
    public static void main(String[] args) {
        window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        new Main().setIcon();

        Gamepanel gamepanel = new Gamepanel();
        window.add(gamepanel);

        gamepanel.config.loadConfig();
        if (gamepanel.fullScreenOn == true) {
//            window.setUndecorated(true);
        }

        window.pack();

        window.setTitle("Blue Boy Adventure");
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        gamepanel.setupGame();
        gamepanel.startGameThread();
    }

    public void setIcon() {
        ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("player/boy_down_1.png"));
        window.setIconImage(icon.getImage());
    }
}

