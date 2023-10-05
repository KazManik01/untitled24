package org.example;

import org.apache.commons.lang3.time.StopWatch;

import javax.swing.*;
import javax.swing.plaf.BorderUIResource;
import java.awt.*;
import java.security.spec.ECField;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Timer;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class GamePanel extends JPanel implements Runnable {
    int sec = 3;
    final int originalTitleSize = 20;
    final int scale = 3;
    final int plTileSize = 100;

    final public int tileSize = originalTitleSize * scale; //48*48
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol; //
    final int screenHeight = tileSize * maxScreenRow;//
    int FPS = 45;
    public boolean setenemyper = true;


    Thread gameThread; // нужен для игрового цикла
    KeyHandler keyH = new KeyHandler();
    public Player player = new Player(this, keyH);

    public ArrayList<Bullshit> bullets = new ArrayList<>();
    public ArrayList<Enemy> enemys = new ArrayList<>();


    int enemyX = 100;
    int enemyY = 100;
    int quality = 6;


    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.gray);
        this.setDoubleBuffered(true);//ну тоже ОПТИМИЗАЦИЯ
        this.addKeyListener(keyH);//добавляем наш клавищо слыхатель
        this.setFocusable(true);//фокусирует нашу ГЭЙмпанель на клавишах
    }

    public void startGameThread() { //запускаем трид
        gameThread = new Thread(this);
        gameThread.start();


    }


    @Override
    public void run() {
        double drawInterval = 1000000000 / FPS; //нано секунды делёные на фпс для получения частоты обновления (60 кадров в секунду)
        double nextDrawTime = System.nanoTime() + drawInterval;
        setEnemy();
        Timer timer = new Timer();

        while (gameThread != null) { //цикл пока трид не отключен
            // обновление позиции и отрисовка


            update();


            repaint();

            double remainigTime = nextDrawTime - System.nanoTime();//кол-во времени до следующей отрисовки
            remainigTime = remainigTime / 1000000;//тайм только с миллисекундами,перевод из нано в милли

            if (remainigTime < 0) {
                remainigTime = 0;
            }
            try {
                Thread.sleep((long) remainigTime);

                nextDrawTime += drawInterval;


            } catch (InterruptedException e) {

                throw new RuntimeException(e);
            }
        }

    }


    public void update() {

//        if (){
//            for (Enemy enemy: enemys){
//                enemy.move_met();
//            }
//        }

        player.update();


        for (Bullshit bullshit : bullets) {
            bullshit.bulshit_update();
        }
        for (Enemy enemy : enemys) {
            enemy.move_met();
        }

        delete_bullshit();


    }

    public void setEnemy() {
        if (setenemyper == true) {
            for (int i = 1; i < 9; i++) {
                Enemy enemy = new Enemy(this, enemyX * i, enemyY);
                enemys.add(enemy);
                setenemyper = false;
            }

        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);//супер указывает на то,что g наследуется от совего род класса(вроде)
        Graphics2D g2 = (Graphics2D) g;//графикс 2д более крутой


        player.draw(g2);
        for (Enemy enemy : enemys) {
            enemy.draw(g2);
        }
        for (Bullshit bullshit : bullets) {
            bullshit.draw(g2);
        }


        g2.dispose();//надо чтобы было БОЛЕЕ ОПТИМИЗИРОВАННО


    }

    public void delete_bullshit() {
        for (int i = 0; i < bullets.size(); i++) {
            if (bullets.get(i).getBulletYPosition() <= -50) {
                bullets.remove(i);
                i--;
            }
        }
        for (int r = 0; r < enemys.size(); r++) {
            Enemy en = enemys.get(r);
            for (int i = 0; i < bullets.size(); i++) {
                Bullshit b = bullets.get(i);
                if (b.hitEn(en)) {
                    bullets.remove(i);
                    enemys.get(r).hp -= 1;
                    if (enemys.get(r).hp == 0) {
                        enemys.remove(r);
                    }
                    i--;
                }
            }
        }
        for (int i = 0; i < bullets.size(); i++) {
            Bullshit b = bullets.get(i);
            if (b.hitPl(player)) {
                bullets.remove(i);
                player.hp--;
                if (player.hp == 0){
                    gameThread.stop();
                }

                }
            }




    }
}

