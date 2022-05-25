import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Map;

public class Game extends JPanel implements KeyListener {

    final int screenWidth = 1600;
    final int screenHeight = 900;
    final int NANO = 1000000000;

    private int doorCount;
    private int playerProjCounter;
    private int playerProjSpeed = 15;
    private int enemyProjCounter;
    private int enemyProjSpeed = 7;

    private boolean removed;

    Boolean[] isPressed = {false, false, false, false};
    Boolean[] isPressedArrow = {false, false, false, false};

    Map<Character, Integer> keyMap = Map.of('w', 0, 's', 1, 'a', 2, 'd', 3);

    Entity player = new Entity(800,450,75,75, 10, 100);

    ArrayList<Enemy> enemyList = new ArrayList<>();
    ArrayList<Projectile> projList = new ArrayList<>();

    Level backGround = new Level();

    public Game() {
        setPreferredSize(new Dimension(screenWidth, screenHeight));
        backGround.level1();
        spawnLevelOneEnemy();
    }

    public void gameLoop() {

        long currentTime;

        while(true) {

            currentTime = System.nanoTime();

            update();

            repaint();

            while (System.nanoTime()-currentTime <= NANO/60) {

            }
        }
    }

    public void update() {

        executeNextTile(player);
        playerMove();
        for(int i = 0; i < enemyList.size(); i++) {
            executeNextTile(enemyList.get(i));
            enemyList.get(i).enemyMove();
        }

        for(int i = 0; i < projList.size(); i++) {
            removed = false;
            executeNextTile(projList.get(i));
            if(!removed) {
                projList.get(i).projectileMove();
            }
        }

        for(int i = 0; i < projList.size(); i++) {
            projCollision(projList.get(i));
        }

        if(playerProjCounter++ >= 8) {
            spawnPlayerProj();
        }
        
        if(enemyProjCounter++ >= 45) {
            spawnEnemyProj();
        }
    }
    
    @Override
    public void paintComponent (Graphics g) {
        super.paintComponent(g);
        backGround.draw(g);
        player.draw(g);

        for(Enemy enemy : enemyList) {
            enemy.draw(g);
        }

        for (Projectile projectile : projList) {
            projectile.draw(g);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        e.consume();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        char c = e.getKeyChar();
        if (keyMap.containsKey(c)) {
            isPressed[keyMap.get(c)] = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == 76) {
            isPressedArrow[3] = true;
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == 74 ) {
            isPressedArrow[2] = true;
        } else if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == 73 ) {
            isPressedArrow[0] = true;
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == 75 ) {
            isPressedArrow[1] = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        char c = e.getKeyChar();
        if (keyMap.containsKey(c)) {
            isPressed[keyMap.get(c)] = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == 76) {
            isPressedArrow[3] = false;
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == 74 ) {
            isPressedArrow[2] = false;
        } else if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == 73 ) {
            isPressedArrow[0] = false;
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == 75 ) {
            isPressedArrow[1] = false;
        }
    }

    public void playerMove () {
        if (isPressed[0]) {
            player.moveUp();
        }

        if (isPressed[1]) {
            player.moveDown();
        }

        if (isPressed[2]) {
            player.moveLeft();
        }

        if (isPressed[3]) {
            player.moveRight();
        }
    }

    public void spawnPlayerProj () {
        if (isPressedArrow[0]) {
            Projectile proj = new Projectile(player.getXPos() + player.getWidth()/2 - 10, player.getYPos() - 20,
                    20, 20, playerProjSpeed, 0, ((int)(Math.random()*3)), true);
            projList.add(proj);
            playerProjCounter = 0;
        } else if (isPressedArrow[1]) {
            Projectile proj = new Projectile(player.getXPos() + player.getWidth()/2 - 10, player.getYPos()+player.getHeight(),
                    20, 20, playerProjSpeed, 1, ((int)(Math.random()*3)), true);
            projList.add(proj);
            playerProjCounter = 0;
        } else if (isPressedArrow[2]) {
            Projectile proj = new Projectile(player.getXPos() - 20, player.getYPos() + player.getHeight()/2 - 10,
                    20, 20, playerProjSpeed, 2, ((int)(Math.random()*3)), true);
            projList.add(proj);
            playerProjCounter = 0;
        } else if (isPressedArrow[3]) {
            Projectile proj = new Projectile(player.getXPos() + player.getWidth(), player.getYPos() + player.getHeight()/2 - 10,
                    20, 20, playerProjSpeed, 3, ((int)(Math.random()*3)), true);
            projList.add(proj);
            playerProjCounter = 0;
        }
    }

    public void spawnEnemyProj () {
        for (int i = 0; i<enemyList.size(); i++) {
            Projectile proj = new Projectile(enemyList.get(i).getXPos() + enemyList.get(i).getWidth()/2 - 10, enemyList.get(i).getYPos() - 20,
                    20, 20, enemyProjSpeed, 0, ((int)(Math.random()*3)), false);
            projList.add(proj);
            Projectile proj1 = new Projectile(enemyList.get(i).getXPos() + enemyList.get(i).getWidth()/2 - 10,
                    enemyList.get(i).getYPos()+enemyList.get(i).getHeight(), 20, 20, enemyProjSpeed, 1,
                    ((int)(Math.random()*3)), false);
            projList.add(proj1);
            Projectile proj2 = new Projectile(enemyList.get(i).getXPos() - 20, enemyList.get(i).getYPos() + enemyList.get(i).getHeight()/2 - 10,
                    20, 20, enemyProjSpeed, 2, ((int)(Math.random()*3)), false);
            projList.add(proj2);
            Projectile proj3 = new Projectile(enemyList.get(i).getXPos() + enemyList.get(i).getWidth(),
                    enemyList.get(i).getYPos() + enemyList.get(i).getHeight()/2 - 10, 20, 20, enemyProjSpeed,
                    3, ((int)(Math.random()*3)), false);
            projList.add(proj3);
            enemyProjCounter = 0;
        }
    }

    public void executeNextTile(Entity ent) {
        int x = ent.getXPos();
        int y = ent.getYPos();
        int dx = 0;
        int dy = 0;

        if(ent.getClass() == Projectile.class) {
            int projX = (int) ((Projectile) ent).getProjX();
            int projY = (int) ((Projectile) ent).getProjY();

            switch (((Projectile) ent).getDirection()) {
                case 0 -> dy -= ent.getSpeed();
                case 1 -> dy += ent.getSpeed() + ent.getHeight();
                case 2 -> dx -= ent.getSpeed();
                case 3 -> dx += ent.getSpeed() + ent.getWidth();
            }

            if(dx != 0) {
                Tile horizontalTile = backGround.getTiles()[projY / 100][(projX + dx) / 100];
                Tile horizontalTileTwo = backGround.getTiles()[(projY + ent.getHeight()) / 100][(projX + dx) / 100];
                if ((horizontalTile.getClass() == Wall.class || horizontalTileTwo.getClass() == Wall.class) ||
                        (horizontalTile.getClass() == Door.class || horizontalTileTwo.getClass()== Door.class)) {
                    projList.remove(ent);
                    removed = true;
                }
            }

            if(dy != 0) {
                Tile verticalTile = backGround.getTiles()[(projY + dy) / 100][projX / 100];
                Tile verticalTileTwo = backGround.getTiles()[(projY + dy) / 100][(projX + ent.getWidth()) / 100];
                if ((verticalTile.getClass() == Wall.class || verticalTileTwo.getClass() == Wall.class)||
                        (verticalTile.getClass() == Door.class || verticalTileTwo.getClass()== Door.class)) {
                    projList.remove(ent);
                    removed = true;
                }
            }

        } else if(ent.getClass() == Enemy.class){
            int enX =  ent.getXPos();
            int enY =  ent.getYPos();
            if(((Enemy) ent).getBehavior()==1) {
                switch (((Enemy) ent).getDirection()) {
                    case 0 -> dy -= ent.getSpeed();
                    case 1 -> dy += ent.getSpeed() + ent.getHeight();
                    case 2 -> dx -= ent.getSpeed();
                    case 3 -> dx += ent.getSpeed() + ent.getWidth();
                }
            } else {
                switch (((Enemy) ent).getDirection()) {
                    case 0 -> {
                        dx += ent.getSpeed() + ent.getWidth();
                        dy -= ent.getSpeed();
                    }
                    case 1 -> {
                        dx -= ent.getSpeed();
                        dy -= ent.getSpeed();
                    }
                    case 2 -> {
                        dx -= ent.getSpeed();
                        dy += ent.getSpeed() + ent.getHeight();
                    }
                    case 3 -> {
                        dx += ent.getSpeed() + ent.getWidth();
                        dy += ent.getSpeed() + ent.getHeight();
                    }
                }
            }

            if(dx != 0) {
                Tile horizontalTile = backGround.getTiles()[enY / 100][(enX + dx) / 100];
                Tile horizontalTileTwo = backGround.getTiles()[(enY + ent.getHeight()) / 100][(enX + dx) / 100];
                if ((horizontalTile.getClass() == Wall.class || horizontalTileTwo.getClass() == Wall.class)||
                        (horizontalTile.getClass() == Door.class || horizontalTileTwo.getClass()== Door.class)) {
                    if(((Enemy) ent).getBehavior() == 1) {
                        switch (((Enemy) ent).getDirection()) {
                            case 2 -> ((Enemy) ent).setDirection(3);
                            case 3 -> ((Enemy) ent).setDirection(2);
                        }
                    } else {
                        switch ((((Enemy) ent).getDirection())) {
                            case 0 -> ((Enemy) ent).setDirection(1);
                            case 1 -> ((Enemy) ent).setDirection(0);
                            case 2 -> ((Enemy) ent).setDirection(3);
                            case 3 -> ((Enemy) ent).setDirection(2);
                        }
                    }
                }
            }

            if(dy != 0) {
                Tile verticalTile = backGround.getTiles()[(enY + dy) / 100][enX / 100];
                Tile verticalTileTwo = backGround.getTiles()[(enY + dy) / 100][(enX + ent.getWidth()) / 100];
                if ((verticalTile.getClass() == Wall.class || verticalTileTwo.getClass() == Wall.class)||
                        (verticalTile.getClass() == Door.class || verticalTileTwo.getClass()== Door.class)) {
                    if(((Enemy) ent).getBehavior() == 1) {
                        switch (((Enemy) ent).getDirection()) {
                            case 0 -> ((Enemy) ent).setDirection(1);
                            case 1 -> ((Enemy) ent).setDirection(0);
                        }
                    } else {
                        switch ((((Enemy) ent).getDirection())) {
                            case 0 -> ((Enemy) ent).setDirection(3);
                            case 1 -> ((Enemy) ent).setDirection(2);
                            case 2 -> ((Enemy) ent).setDirection(1);
                            case 3 -> ((Enemy) ent).setDirection(0);
                        }
                    }
                }
            }

        } else {

            if(isPressed[2]) dx -= ent.getSpeed();
            if(isPressed[3]) dx += ent.getSpeed() + ent.getWidth();
            if(isPressed[0]) dy -= ent.getSpeed();
            if(isPressed[1]) dy += ent.getSpeed() + ent.getHeight();

            if (isPressed[2] || isPressed[3]) {
                Tile horizontalTile = backGround.getTiles()[y / 100][(x + dx) / 100];
                Tile horizontalTileTwo = backGround.getTiles()[(y + ent.getHeight() - 1) / 100][(x + dx) / 100];
                if ((horizontalTile.getClass() == Wall.class || horizontalTileTwo.getClass() == Wall.class) && isPressed[2]) {
                    ent.setXPos(horizontalTile.getXPos() + 100 + ent.getSpeed());
                } else if ((horizontalTile.getClass()==Door.class || horizontalTileTwo.getClass()==Door.class) && isPressed[2]){
                    if(enemyList.size() == 0) {
                        if(doorCount == 0) {
                            if (doorCount == 0) {
                                backGround.level2();
                                spawnLevelTwoEnemy();
                                ent.setYPos(725);
                                doorCount++;
                            } else if (doorCount == 1) {
                                backGround.level3();
                                spawnLevelThreeEnemy();
                                ent.setYPos(725);
                                doorCount++;
                            } else if (doorCount == 2) {
                                backGround.level4();
                                spawnLevelThreeEnemy();
                                ent.setYPos(725);
                                doorCount++;
                            } else if (doorCount == 3) {
                                backGround.level1();
                                spawnLevelOneEnemy();
                                System.out.println("You win - game restarting");
                                ent.setYPos(725);
                                doorCount = 0;
                            }
                        }
                    } else {
                        ent.setXPos(horizontalTile.getXPos() + 100 + ent.getSpeed());
                    }
                }

                if ((horizontalTile.getClass() == Wall.class || horizontalTileTwo.getClass() == Wall.class) && isPressed[3]) {
                    ent.setXPos(horizontalTile.getXPos() - ent.getWidth() - ent.getSpeed());
                } else if ((horizontalTile.getClass()==Door.class || horizontalTileTwo.getClass()==Door.class) && isPressed[3]){
                    if(enemyList.size() == 0) {
                        if (doorCount == 0) {
                            backGround.level2();
                            spawnLevelTwoEnemy();
                            ent.setYPos(725);
                            doorCount++;
                        } else if (doorCount == 1) {
                            backGround.level3();
                            spawnLevelThreeEnemy();
                            ent.setYPos(725);
                            doorCount++;
                        } else if (doorCount == 2) {
                            backGround.level4();
                            spawnLevelThreeEnemy();
                            ent.setYPos(725);
                            doorCount++;
                        } else if (doorCount == 3) {
                            backGround.level1();
                            spawnLevelOneEnemy();
                            System.out.println("You win - game restarting");
                            ent.setYPos(725);
                            doorCount = 0;
                        }
                    } else {
                        ent.setXPos(horizontalTile.getXPos() - ent.getWidth() - ent.getSpeed());
                    }
                }
            }

            if (isPressed[0] || isPressed[1]) {
                Tile verticalTile = backGround.getTiles()[(y + dy) / 100][x / 100];
                Tile verticalTileTwo = backGround.getTiles()[(y + dy) / 100][(x + ent.getWidth() - 1) / 100];
                if ((verticalTile.getClass() == Wall.class || verticalTileTwo.getClass() == Wall.class) && isPressed[0]) {
                    ent.setYPos(verticalTile.getYPos() + 100 + ent.getSpeed());
                } else if ((verticalTile.getClass()==Door.class || verticalTileTwo.getClass()==Door.class) && isPressed[0]){
                    if(enemyList.size() == 0) {
                        if (doorCount == 0) {
                            backGround.level2();
                            spawnLevelTwoEnemy();
                            ent.setYPos(725);
                            doorCount++;
                        } else if (doorCount == 1) {
                            backGround.level3();
                            spawnLevelThreeEnemy();
                            ent.setYPos(725);
                            doorCount++;
                        } else if (doorCount == 2) {
                            backGround.level4();
                            spawnLevelThreeEnemy();
                            ent.setYPos(725);
                            doorCount++;
                        } else if (doorCount == 3) {
                            backGround.level1();
                            spawnLevelOneEnemy();
                            System.out.println("You win - game restarting");
                            ent.setYPos(725);
                            doorCount = 0;
                        }
                    } else {
                        ent.setYPos(verticalTile.getYPos() + 100 + ent.getSpeed());
                    }
                }
                if ((verticalTile.getClass() == Wall.class || verticalTileTwo.getClass() == Wall.class) && isPressed[1]) {
                    ent.setYPos(verticalTile.getYPos() - ent.getHeight() - ent.getSpeed());
                } else if ((verticalTile.getClass()==Door.class || verticalTileTwo.getClass()==Door.class) && isPressed[1]){
                    if(enemyList.size() == 0) {
                        if (doorCount == 0) {
                            backGround.level2();
                            spawnLevelTwoEnemy();
                            ent.setYPos(725);
                            doorCount++;
                        } else if (doorCount == 1) {
                            backGround.level3();
                            spawnLevelThreeEnemy();
                            ent.setYPos(725);
                            doorCount++;
                        } else if (doorCount == 2) {
                            backGround.level4();
                            spawnLevelThreeEnemy();
                            ent.setYPos(725);
                            doorCount++;
                        } else if (doorCount == 3) {
                            backGround.level1();
                            spawnLevelOneEnemy();
                            System.out.println("You win - game restarting");
                            ent.setYPos(725);
                            doorCount = 0;
                        }
                    } else {
                        ent.setYPos(verticalTile.getYPos() - ent.getHeight() - ent.getSpeed());
                    }
                }
            }
        }
    }

    public void projCollision(Projectile proj) {
        if (!(proj.getIsPlayerProj())) {
            if ((proj.getProjY() >= (player.getYPos()) && proj.getProjY() <= (player.getYPos() + 75)
                    && (proj.getProjX() + proj.getWidth()) >= player.getXPos()) && (proj.getProjX()) <= player.getXPos() + 75) {
                projList.remove(proj);
                removed = true;
                player.setHealth(player.getHealth()-1);
                checkEntHealth(player);
            } else if ((proj.getProjX() >= (player.getXPos()) && proj.getProjX() <= (player.getXPos() + 75)
                    && (proj.getProjY() + proj.getHeight()) >= player.getYPos()) && proj.getProjY() <= player.getYPos() + 75) {
                projList.remove(proj);
                removed = true;
                player.setHealth(player.getHealth()-1);
                checkEntHealth(player);
            }
        }

        if (proj.getIsPlayerProj()) {
            for (int i = enemyList.size()-1; i >= 0; i--) {
                if ((proj.getProjY() >= (enemyList.get(i).getYPos()) && proj.getProjY() <= (enemyList.get(i).getYPos() + enemyList.get(i).getHeight())
                        && (proj.getProjX() + proj.getWidth()) >= enemyList.get(i).getXPos()) && (proj.getProjX()) <= enemyList.get(i).getXPos() + enemyList.get(i).getWidth()) {
                    projList.remove(proj);
                    removed = true;
                    enemyList.get(i).setHealth(enemyList.get(i).getHealth() - 1);
                    checkEntHealth(enemyList.get(i));
                } else if ((proj.getProjX() >= (enemyList.get(i).getXPos()) && proj.getProjX() <= (enemyList.get(i).getXPos() + enemyList.get(i).getWidth())
                        && (proj.getProjY() + proj.getHeight()) >= enemyList.get(i).getYPos()) && proj.getProjY() <= enemyList.get(i).getYPos() + enemyList.get(i).getHeight()) {
                    projList.remove(proj);
                    removed = true;
                    enemyList.get(i).setHealth(enemyList.get(i).getHealth() - 1);
                    checkEntHealth(enemyList.get(i));
                }
            }
        }
    }

    public void checkEntHealth(Entity ent) {
        if (ent.getClass() == Enemy.class) {
            if(ent.getHealth() == 0) {
                enemyList.remove(ent);
            }
        }

        if (player.getHealth() == 0) {
            System.out.println("You died - game restarting");
            for (int i = enemyList.size()-1; i >= 0; i--) {
                enemyList.remove(i);
            }
            for (int i = projList.size()-1; i >= 0; i--) {
                projList.remove(i);
            }
            player.setHealth(5);
            backGround.level1();
            spawnLevelOneEnemy();
        }
    }

    public void spawnLevelOneEnemy() {
        player.setXPos(300);
        player.setYPos(400);
        Enemy en = new Enemy(900,400,75,75, 6, 5, 2, 0);
        enemyList.add(en);
        doorCount = 0;
    }

    public void spawnLevelTwoEnemy(){
        Enemy en = new Enemy(500, 800, 75, 75, 10, 10, 3, 2);
        enemyList.add(en);
        Enemy en2 = new Enemy(700, 200, 75, 75, 5, 10, 2, 2);
        enemyList.add(en2);
        Enemy en3 = new Enemy(200, 600, 75, 75, 5, 10, 3, 2);
        enemyList.add(en3);
        Enemy en4 = new Enemy(500, 300, 75, 75, 5, 10, 5, 2);
        enemyList.add(en4);
    }

    public void spawnLevelThreeEnemy() {

    }

    public void spawnLevelFourEnemy() {

    }
}





