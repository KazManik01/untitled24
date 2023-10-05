package org.example;

import org.apache.commons.lang3.time.StopWatch;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;


public class Enemy extends entity {
    int num = 0;

    int hp = 3;
    GamePanel gp;
    BufferedImage aniUFO;

    public boolean ded = false;

    int steps = 4;
    boolean sideofthemovement = true;


    public Enemy(GamePanel gp, int enemyX, int enemyY) {
        this.gp = gp;
        this.x = enemyX;
        this.y = enemyY;


        setDefaultValues();
        getEnemyImage();
    }
    public void enemy_move(){



    }

    public void setDefaultValues() {
        speed = 3;

    }

    public void getEnemyImage() {//плееризображениее
        try {
            aniUFO = ImageIO.read(getClass().getResourceAsStream("/player/UFO.png"));

        } catch (IOException e) {
            e.printStackTrace();


        }
    }


    public void draw(Graphics2D g2) {
        BufferedImage image = aniUFO;
        g2.drawImage(image, x, y, gp.plTileSize, gp.plTileSize, null);

    }

    public void move_met() {
        if (sideofthemovement == true) {
            for (int i = 0; i <= 20; i++) {
                x += 1;


            }
            sideofthemovement = false;


        } else {
            for (int i = 0; i <= 20; i++) {
                x -= 1;
                sideofthemovement = true;

            }

        }


    }

    public int getx() {
        return x;
    }
    public int gety() {
        return y;
    }



}



