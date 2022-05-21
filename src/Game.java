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

    private int projCounter;
    private boolean removed;
    Boolean[] isPressed = {false, false, false, false};
    Boolean[] isPressedArrow = {false, false, false, false};
    Map<Character, Integer> keyMap = Map.of('w', 0, 's', 1, 'a', 2, 'd', 3);

    Entity player = new Entity(800,450,75,75, 10);

    ArrayList<Entity> entList = new ArrayList<>();
    ArrayList<Projectile> projList = new ArrayList<>();

    Level backGround = new Level();

    public Game() {
        setPreferredSize(new Dimension(screenWidth, screenHeight));
        backGround.level1();
    }

    public void gameLoop() {

        entList.add(player);

//        int counter = 0;

        long currentTime;

        while(true) {

            currentTime = System.nanoTime();

            update();

            repaint();

            while (System.nanoTime()-currentTime <= NANO/60) {

            }
            
//            if(counter++ >= 60) {
//                System.out.println(isPressedArrow[0]);
//                counter = 0;
//            }
        }
    }

    public void update() {
        for(int i = 0; i < entList.size(); i++) {
            executeNextTile(entList.get(i));
            if (entList.get(i).getClass() != Projectile.class) {
                playerMove();
            } else if (entList.get(i).getClass() == Projectile.class) {
                //other Entities
            }
        }

        for(int i = 0; i < projList.size(); i++) {
            removed = false;
            executeNextTile(projList.get(i));
            if(!removed) {
                projList.get(i).projectileMove();
            }
        }
        
        if(projCounter++ >= 8) {
            spawnPlayerProj();
        }
    }
    
    @Override
    public void paintComponent (Graphics g) {
        super.paintComponent(g);
        backGround.draw(g);
        for (Entity entity : entList) {
            entity.draw(g);
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
                    20, 20, 15, 0, ((int)(Math.random()*3)));
            projList.add(proj);
            projCounter = 0;
        } else if (isPressedArrow[1]) {
            Projectile proj = new Projectile(player.getXPos() + player.getWidth()/2 - 10, player.getYPos()+player.getHeight(),
                    20, 20, 15, 1, ((int)(Math.random()*3)));
            projList.add(proj);
            projCounter = 0;
        } else if (isPressedArrow[2]) {
            Projectile proj = new Projectile(player.getXPos() - 20, player.getYPos() + player.getHeight()/2 - 10,
                    20, 20, 15, 2, ((int)(Math.random()*3)));
            projList.add(proj);
            projCounter = 0;
        } else if (isPressedArrow[3]) {
            Projectile proj = new Projectile(player.getXPos() + player.getWidth(), player.getYPos() + player.getHeight()/2 - 10,
                    20, 20, 15, 3, ((int)(Math.random()*3)));
            projList.add(proj);
            projCounter = 0;
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
                if ((horizontalTile.getClass() == Wall.class || horizontalTileTwo.getClass() == Wall.class)) {
                    projList.remove(ent);
                    System.out.println("horizontal entity deleted");
                    removed = true;
                }
            }
            
            if(dy != 0) {
                Tile verticalTile = backGround.getTiles()[(projY + dy) / 100][projX / 100];
                Tile verticalTileTwo = backGround.getTiles()[(projY + dy) / 100][(projX + ent.getWidth()) / 100];
                if ((verticalTile.getClass() == Wall.class || verticalTileTwo.getClass() == Wall.class)) {
                    projList.remove(ent);
                    System.out.println("vertical entity deleted");
                    removed = true;
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
                }
                if ((horizontalTile.getClass() == Wall.class || horizontalTileTwo.getClass() == Wall.class) && isPressed[3]) {
                    ent.setXPos(horizontalTile.getXPos() - ent.getWidth() - ent.getSpeed());
                }
            }

            if (isPressed[0] || isPressed[1]) {
                Tile verticalTile = backGround.getTiles()[(y + dy) / 100][x / 100];
                Tile verticalTileTwo = backGround.getTiles()[(y + dy) / 100][(x + ent.getWidth() - 1) / 100];
                if ((verticalTile.getClass() == Wall.class || verticalTileTwo.getClass() == Wall.class) && isPressed[0]) {
                    ent.setYPos(verticalTile.getYPos() + 100 + ent.getSpeed());
                }
                if ((verticalTile.getClass() == Wall.class || verticalTileTwo.getClass() == Wall.class) && isPressed[1]) {
                    ent.setYPos(verticalTile.getYPos() - ent.getHeight() - ent.getSpeed());
                }
            }
        }
    }
}





