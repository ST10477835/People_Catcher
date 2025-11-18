/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package box_catcher;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.*;

/**
 *
 * @author abule
 */
public class Box_catcher {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(()->{
            new GameFrame();
        });
    }
    
}
class GameFrame extends JFrame{
    public GamePanel panel;
    public JLabel label;
    public static int score = 0;
    public GameFrame(){
        setSize(new Dimension(500,500));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setTitle("GameCatcher");
        
        panel = new GamePanel();
        
        add(panel);
        
        addKeyListener(new KeyAdapter(){
            @Override
            public void keyPressed(KeyEvent e){
                int keyCode = e.getKeyCode();
                if(keyCode == KeyEvent.VK_D){
                    panel.player.moving = true;
                    panel.player.direction = true;
                }else if(keyCode == KeyEvent.VK_A){
                    panel.player.moving = true;
                    panel.player.direction = false;
                }
            }
            public void keyReleased(KeyEvent e){
                panel.player.moving = false;
            } 
        });
        
        new Thread(()->{
            
            while(panel.running){
                for(Box box: panel.box_list){
                    Rectangle boxRect = new Rectangle(box.X, box.Y, box.WIDTH, box.HEIGHT);
                    Rectangle paddleRect = new Rectangle(panel.player.X, panel.player.Y, Player.WIDTH, Player.HEIGHT);
                    
                    box.Y+=Box.SPEED;
                    
                    if(paddleRect.intersects(boxRect)){
                        box.Y = 0;
                        score++;
                        if(score>=10){
                            Box.SPEED++;
                        }
                    }
                    if(box.Y+50>=getHeight()){
                        box.Y = 0;
                        panel.health = health_tracker(panel.health);
                        if(TF_checker(panel.health)){
                            JOptionPane.showMessageDialog(null,"Game Over!");
                            System.exit(0);
                        }
                    }
                }
                
                if(panel.player.moving){
                    if(panel.player.direction){
                        if(panel.player.X<getWidth()-Player.WIDTH)panel.player.X+=Player.SPEED;
                    }else{
                        if(panel.player.X>0)panel.player.X-=Player.SPEED;
                    }    
                }
                
                try{Thread.sleep(10);}catch(Exception e){}
                panel.repaint();
            }
            
        }).start();
        
        
        setVisible(true);
    }
    public boolean[] health_tracker(boolean[] arr){
        for(int i = 0; i < arr.length; i++){
            if(arr[i]==true){
                arr[i]=false;
                return arr;
            }
        }
        return arr;
    }
    public boolean TF_checker(boolean[] arr){
        boolean standard = arr[0];
        for(boolean val: arr){
            if(val!=standard){
                return false;
            }
        }
        return true;
    }
}
class Box{
    public int X, Y;
    //possible X's = 50, 150, 250, 350, and 450
    public static int WIDTH = 100;
    public static int HEIGHT = 100;
    public static int SPEED = 1;
    
    public Box(int X, int Y){
        this.X = X;
        this.Y = Y;
    }
}
class Player{
    public int X, Y;
    public static int WIDTH = 50;
    public static int HEIGHT = 50;
    public static int SPEED = 4;
    
    public volatile boolean moving = false;
    public volatile boolean direction = true;
    
    public Player(int X, int Y){
        this.X = X;
        this.Y = Y;
    }
}
class GamePanel extends JComponent{
    private Image backgroundImage;
    
    public ArrayList<Box> box_list = new ArrayList<>();
    public boolean[] health = {true, true, true};
    
    Image health_full = new ImageIcon("HeartFull.png").getImage();
    Image health_empty = new ImageIcon("HeartEmpty.png").getImage();
    Image king = new ImageIcon("king.png").getImage().getScaledInstance(Player.WIDTH, Player.HEIGHT, Image.SCALE_SMOOTH);
    
    Image box1 = new ImageIcon("box1.png").getImage().getScaledInstance(Box.WIDTH, Box.HEIGHT, Image.SCALE_SMOOTH);
    Image box2 = new ImageIcon("box2.png").getImage().getScaledInstance(Box.WIDTH, Box.HEIGHT, Image.SCALE_SMOOTH);
    Image box3 = new ImageIcon("box3.png").getImage().getScaledInstance(Box.WIDTH, Box.HEIGHT, Image.SCALE_SMOOTH);
    public volatile boolean running = true;
    
    Player player = new Player(200, 410);
    
    public GamePanel(){
        try {
            backgroundImage = new ImageIcon("background.jpg").getImage();
        } catch (Exception e) {
            e.printStackTrace();
        }
        box_list.add(new Box(50, 0));
        box_list.add(new Box(150, 0));
        box_list.add(new Box(250, 0));
    }
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
         if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
        g.drawString("Score: "+GameFrame.score, 10, 20);
        
        for(Box box: box_list){
            switch (box.X) {
                case 50 -> g.drawImage(box1, box.X, box.Y, this);
                case 150 -> g.drawImage(box2, box.X, box.Y, this);
                default -> g.drawImage(box3, box.X, box.Y, this);
            }
        }
        int pos = 400;
        for(boolean hp: health){
            if(hp){
                g.drawImage(health_full, pos, 10, 30, 30, this);
            }else{
                g.drawImage(health_empty, pos, 10, 30, 30, this);
            }
            pos+=25;
        }
        g.drawImage(king, player.X, player.Y, this);
    }
}