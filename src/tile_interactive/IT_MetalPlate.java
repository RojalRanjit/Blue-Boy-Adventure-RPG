package tile_interactive;

import main.Gamepanel;

public class IT_MetalPlate extends InteractiveTile{
    Gamepanel gp;
    public static final String itName = "Metal Plate";

    public IT_MetalPlate(Gamepanel gp, int col, int row) {
        super(gp, col, row);
        this.gp = gp;

        this.WorldX = gp.tileSize * col;
        this.WorldY = gp.tileSize * row;

        name = itName;
        down1 = setup("/tile_interactive/metalplate", gp.tileSize, gp.tileSize);

        solidArea.x = 0;
        solidArea.y = 0;
        solidArea.width = 0;
        solidArea.height = 0;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }
}
