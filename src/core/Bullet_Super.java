package core;

import main.GameUI;

import java.io.IOException;
import javax.imageio.ImageIO;


public class Bullet_Super extends Bullet {
    public Bullet_Super(GameUI gameUI, int x, int y,Entity obj) throws IOException {
        super(gameUI, x, y, obj);
    }

    @Override
    public void defaultSetting() {
        this.width = 70; 
        this.height = 70; 
        this.speed = 4; 
        this.AP = 2;
    }

    @Override
    public void getImage() throws IOException {
           bufferedImage = ImageIO.read(getClass().getResourceAsStream("/bullet/SuperBulletCS3343.png"));
    }
}