package tile_interactive;
import main.Gamepanel;

public class IT_Trunk extends InteractiveTile{
    Gamepanel gp;
//    public static final String itName = "Trunk";

    public IT_Trunk(Gamepanel gp, int col, int row) {
        super(gp, col, row);
        this.gp = gp;

        this.WorldX = gp.tileSize * col;
        this.WorldY = gp.tileSize * row;

//        name = itName;
        down1 = setup("/tile_interactive/trunk", gp.tileSize, gp.tileSize);

        solidArea.x = 0;
        solidArea.y = 0;
        solidArea.width = 0;
        solidArea.height = 0;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }
}
