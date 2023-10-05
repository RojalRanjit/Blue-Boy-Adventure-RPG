package main;

import java.io.*;

public class Config {

    Gamepanel gp;

    public Config(Gamepanel gp) {
        this.gp = gp;
    }

    public void saveConfig() {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("config.txt"));

            //Full screen
            if(gp.fullScreenOn == true) {
                bufferedWriter.write("On");
            }
            if (gp.fullScreenOn == false) {
                bufferedWriter.write("Off");
            }
            bufferedWriter.newLine();

            // Music Volume
            bufferedWriter.write(String.valueOf(gp.music.volumeScale));
            bufferedWriter.newLine();

            //SE volume
            bufferedWriter.write(String.valueOf(gp.se.volumeScale));
            bufferedWriter.newLine();

            bufferedWriter.close();

        } catch (IOException e) {
            //TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void loadConfig() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("config.txt"));
            String string = bufferedReader.readLine();

            //Full Screen
            if (string.equals("On")) {
                gp.fullScreenOn = true;
            }
            if (string.equals("Off")) {
                gp.fullScreenOn = false;
            }

            //Music Volume
            string = bufferedReader.readLine();
            gp.music.volumeScale = Integer.parseInt(string);

            //SE Volume
            string = bufferedReader.readLine();
            gp.se.volumeScale = Integer.parseInt(string);

            bufferedReader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
