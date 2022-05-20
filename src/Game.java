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
    Boolean[] isPressed = {false, false, false, false};
    Boolean[] isPressedArrow = {false, false, false, false};
    Map<Character, Integer> keyMap = Map.of('w', 0, 's', 1, 'a', 2, 'd', 3);

    Entity player = new Entity(800,450,75,75, 10);

    ArrayList<Entity> entList = new ArrayList<Entity>();

    Level backGround = new Level();

    public Game() {
        setPreferredSize(new Dimension(screenWidth, screenHeight));
        backGround.level1();
    }

    public void gameLoop() {

        entList.add(player);

        int counter = 0;
        
        int projCounter = 0;

        long currentTime;

        while(true) {

            currentTime = System.nanoTime();

            update(projCounter);

            repaint();

            while (System.nanoTime()-currentTime <= NANO/60) {

            }
            
            if (projCounter++ > 30) {
                projCounter = 0;
            }
            
//            if(counter++ >= 60) {
//                System.out.println(isPressedArrow[0]);
//                counter = 0;
//            }

        }
    }

    public void update(int proj) {
        for(int i = 0; i < entList.size(); i++) {
            executeNextTile(entList.get(i));
            if (entList.get(i).getClass() != Projectile.class) {
                playerMove();
            } else if (entList.get(i).getClass() == Projectile.class) {
                //projective move
            } // else for enemy
        }
        
        if(proj == 30) {
            //spawn projectile
        }
        
    }
    @Override
    public void paintComponent (Graphics g) {
        super.paintComponent(g);
        backGround.draw(g);
        player.draw(g);
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
        if (e.getKeyCode() == KeyEvent.VK_RIGHT ) {
            isPressedArrow[3] = true;
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT ) {
            isPressedArrow[2] = true;
        } else if (e.getKeyCode() == KeyEvent.VK_UP ) {
            isPressedArrow[0] = true;
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN ) {
            isPressedArrow[1] = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        char c = e.getKeyChar();
        if (keyMap.containsKey(c)) {
            isPressed[keyMap.get(c)] = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT ) {
            isPressedArrow[3] = false;
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT ) {
            isPressedArrow[2] = false;
        } else if (e.getKeyCode() == KeyEvent.VK_UP ) {
            isPressedArrow[0] = false;
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN ) {
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
    
//    public void spawnProj () {
//        if (isPressed[0]) {
//            ent.moveUp();
//        }
//
//        if (isPressed[1]) {
//            ent.moveDown();
//        }
//
//        if (isPressed[2]) {
//            ent.moveLeft();
//        }
//
//        if (isPressed[3]) {
//            ent.moveRight();
//        }
//    }


    public void executeNextTile(Entity ent) {
        int x = ent.getXpos();
        int y = ent.getYpos();
        int dx = 0;
        int dy = 0;
        if(isPressed[2]) dx -= ent.getSpeed();
        if(isPressed[3]) dx += ent.getSpeed() + ent.getWidth();
        if(isPressed[0]) dy -= ent.getSpeed();
        if(isPressed[1]) dy += ent.getSpeed() + ent.getHeight();

        if(isPressed[2] || isPressed[3]) {
            Tile horizontalTile = backGround.getTiles()[y/100][(x+dx)/100];
            Tile horizontalTileTwo = backGround.getTiles()[(y+ent.getHeight()-1)/100][(x+dx)/100];
            if((horizontalTile.getClass() == Wall.class || horizontalTileTwo.getClass() == Wall.class) && isPressed[2]) {
                ent.setXpos(horizontalTile.getXpos()+100+ent.getSpeed());
            }
            if((horizontalTile.getClass() == Wall.class || horizontalTileTwo.getClass() == Wall.class) && isPressed[3]) {
                ent.setXpos(horizontalTile.getXpos() - ent.getWidth() - ent.getSpeed());
            }

        }

        if(isPressed[0] || isPressed[1]) {
            Tile verticalTile = backGround.getTiles()[(y + dy)/100][x/100];
            Tile verticalTileTwo = backGround.getTiles()[(y+dy)/100][(x+ent.getWidth()-1)/100];
            if((verticalTile.getClass() == Wall.class || verticalTileTwo.getClass() == Wall.class) && isPressed[0]) {
                ent.setYpos(verticalTile.getYpos()+100+ent.getSpeed());
            }
            if((verticalTile.getClass() == Wall.class || verticalTileTwo.getClass() == Wall.class) && isPressed[1]) {
                ent.setYpos(verticalTile.getYpos() - ent.getHeight() - ent.getSpeed());
            }
        }
    }
}





