package org.example;


import javax.imageio.ImageIO;
import javax.sound.sampled.Line;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class Player extends entity {
    int num = 0;
    GamePanel gp;
    KeyHandler keyH;
    int hp = 3;


    public BufferedImage an1, anR, anL;//анимации


    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;
        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        this.x = 500;
        this.y = 500;
        speed = 5;
        direction = "up";
    }

    public void getPlayerImage() {//плееризображениее
        try {
            an1 = ImageIO.read(getClass().getResourceAsStream("/player/player.png"));
            anR = ImageIO.read(getClass().getResourceAsStream("/player/playerR.png"));
            anL = ImageIO.read(getClass().getResourceAsStream("/player/playerL.png"));

        } catch (IOException e) {
            e.printStackTrace();


        }
    }

    public int getXSpaceShipPosition() {
        return x;
    }

    public int getYSpaceShipPosition() {
        return y;
    }

    public void update() {
        if (keyH.upPressed == true) {
            y -= speed;
            direction = "up";
        } else if (keyH.downPressed == true) {
            y += speed;
            direction = "up";
        } else if (keyH.rightPressed == true) {
            x += speed;
            direction = "right";
        } else if (keyH.leftPressed == true) {
            x -= speed;
            direction = "left";
        }
        if (keyH.leftPressed == false & keyH.rightPressed == false) {
            direction = "up";
        }
        if (keyH.attack == true) {
            set_bullet(gp.bullets);
            keyH.attack = false;


        }


    }

    public void draw(Graphics2D g2) {

        BufferedImage image = null;
        switch (direction) {
            case "up":
                image = an1;
                break;
            case "right":
                image = anR;
                break;
            case "left":
                image = anL;
                break;
        }
        g2.drawImage(image, x, y, gp.plTileSize, gp.plTileSize, null);


    }

    public void set_bullet(ArrayList<Bullshit> bullets) {
        num += 1;
        Bullshit bul = new Bullshit(this, gp, num);
        gp.bullets.add(bul);


    }


}
