package org.example;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Bullshit extends entity {
    int num;
    GamePanel gp;
    public BufferedImage animB;

    boolean isActive = false;


    public Bullshit(Player player, GamePanel gamePanel, int
            num) {
        this.gp = gamePanel;
        this.x = player.getXSpaceShipPosition() + 8;
        this.y = player.getYSpaceShipPosition() - 80;
        this.num = num;
        getBulImage();
        ;
        isActive = true;


    }


    public int getBulletXPosition() {
        return x;
    }

    public int getBulletYPosition() {
        return y;
    }

    public boolean isBulletActive() {
        return isActive;
    }

    public void changeBulletActive() {
        isActive = !isActive;
    }

    public void getBulImage() {//плееризображениее
        try {
            animB = ImageIO.read(getClass().getResourceAsStream("/player/bullshit.png"));


        } catch (IOException e) {
            e.printStackTrace();


        }
    }

    public void bulshit_update() {

        y -= 5;

    }

    public void draw(Graphics2D g2) {


        g2.drawImage(animB, x, y, gp.plTileSize, gp.plTileSize, null);


    }

    public boolean hitEn(Enemy obj) {
        if (obj.y < -10) {
            return false;
        }

        int objX = obj.x;
        int objY = obj.y;
        double objW = gp.scale;
        double objH = gp.scale;

        if (objX - 50 < this.x) {
            if (objY - 50 < this.y) {
                if (objX + objW + 50 > this.x) {
                    if (objY + objH + 50 > this.y) {
                        return true;
                    }
                }
            }
        }

        return false;
    }
    public boolean hitPl(Player obj) {
        if (obj.y < -10) {
            return false;
        }

        int objX = obj.x;
        int objY = obj.y;
        double objW = gp.scale;
        double objH = gp.scale;

        if (objX - 50 < this.x) {
            if (objY - 50 < this.y) {
                if (objX + objW + 50 > this.x) {
                    if (objY + objH + 50 > this.y) {
                        return true;
                    }
                }
            }
        }

        return false;
    }
}
