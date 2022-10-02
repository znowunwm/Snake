import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Field extends JPanel implements ActionListener {

    private final boolean bot;
    private static Game game;
    private boolean running;
    private boolean canMove;
    private final int pxside = 40;
    private int snakeSize = 3;
    private int appleX;
    private int appleY;
    private char direction = 'R';
    private final List<Integer> x = new ArrayList<>();
    private final List<Integer> y = new ArrayList<>();
    private Timer timer;
    private final Random random = new Random();

    public int getSnakeSize() {
        return snakeSize;
    }

    public Field(int speed, boolean bot, Game game){
        this.bot=bot;
        Field.game =game;
        this.setLayout(null);
        this.setPreferredSize(new Dimension(810,810));
        if(!bot){
            this.setFocusable(true);
            this.addKeyListener(new Keys());
        }
        startGame(speed);
    }

    public void botGame(){
        if (x.get(0)==19 && y.get(0)==19)direction='L';
        else if (x.get(0)==19 && y.get(0)==0)direction='D';
        else if (x.get(0)==0  && y.get(0)%2==1)direction='U';
        else if (x.get(0)==0  && y.get(0)%2==0)direction='R';
        else if (x.get(0)==18  && y.get(0)%2==1 && y.get(0)>0 && y.get(0)<19)direction='L';
        else if (x.get(0)==18  && y.get(0)%2==0 && y.get(0)>0 && y.get(0)<19)direction='U';
    }
    public void startGame(int speed){
        running = true;
        x.add(10);y.add(10);
        x.add(9);y.add(10);
        x.add(8);y.add(10);


        timer = new Timer(speed,this);
        timer.start();
        apple();
    }
    public void apple(){
        int a=random.nextInt(20);
        int b=random.nextInt(20);
        for(int i=0; i<snakeSize; i++){
            if(x.get(i)==a && y.get(i)==b){
                a=random.nextInt(20);
                b=random.nextInt(20);
                i=0;
            }
        }
        appleX = 5 + pxside*a;
        appleY = 5 + pxside*b;
    }
    public void eatApple(){
        x.add(x.get(snakeSize-1));
        y.add(y.get(snakeSize-1));
        apple();
        snakeSize++;
    }
    public void move(){
        if(5+pxside*x.get(0)==appleX && 5+pxside*y.get(0)==appleY)eatApple();

        for(int i=x.size()-1; i>0; i--){
            x.set(i,x.get(i-1));
            y.set(i,y.get(i-1));
        }

        switch (direction) {
            case 'R' -> x.set(0, x.get(0) + 1);
            case 'L' -> x.set(0, x.get(0) - 1);
            case 'U' -> y.set(0, y.get(0) - 1);
            case 'D' -> y.set(0, y.get(0) + 1);
        }
    }
    public void end(){
        running = false;
        timer.stop();
        repaint();
    }
    public void collisions(){
        if(x.get(0)==-1 || y.get(0)==-1)end();
        else if(x.get(0)==20 || y.get(0)==20)end();
        else if (snakeSize == 401)end();
        else {
            for (int i = 1; i < snakeSize; i++) if(x.get(i).equals(x.get(0)) && y.get(i).equals(y.get(0)))end();
        }
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw();
    }
    public void draw(){
        this.removeAll();
        if(running){
            for (int i = 0; i <snakeSize; i++) {
                JLabel part = new JLabel(new ImageIcon("graphics/orange.png"));
                if(i==0){
                    String path = "graphics/head";

                    switch (direction){
                        case 'R':
                            path+="R.png";break;
                        case 'L':
                            path+="L.png";break;
                        case 'U':
                            path+="U.png";break;
                        case 'D':
                            path+="D.png";break;

                    }
                    ImageIcon head = new ImageIcon(path);
                    part.setIcon(head);
                } else if (i==snakeSize-1) {
                    String path = "graphics/tail";

                    if(x.get(i).equals(x.get(i - 1)) && y.get(i) == y.get(i-1)+1)path+="U.png";
                    else if(x.get(i).equals(x.get(i - 1)) && y.get(i) == y.get(i-1)-1)path+="D.png";
                    else if(x.get(i) == x.get(i-1)+1 && y.get(i).equals(y.get(i - 1)))path+="L.png";
                    else path+="R.png";

                    ImageIcon tail = new ImageIcon(path);
                    part.setIcon(tail);
                }
                part.setBounds(5 + pxside * x.get(i), 5 + pxside * y.get(i), pxside, pxside);
                this.add(part);
            }
            JLabel apple = new JLabel(new ImageIcon("graphics/apple.png"));
            apple.setBounds(appleX,appleY,pxside,pxside);
            this.add(apple);
        }
        else {
            if(snakeSize==401){
                JLabel win = new JLabel(new ImageIcon("graphics/win.png"));
                win.setBounds(5,5,800,800);

                JLabel again = new JLabel(new ImageIcon("graphics/again.png"));
                again.setBounds(287,443,237,262);

                win.add(again);
                again.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                this.add(win);
                again.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        super.mouseClicked(e);
                        new Main();
                        game.dispose();
                    }
                });

            }
            else {
                JLabel gameOver = new JLabel(new ImageIcon("graphics/end.png"));
                gameOver.setBounds(5,5,800,800);

                JLabel again = new JLabel(new ImageIcon("graphics/again.png"));
                again.setBounds(287,443,237,262);

                gameOver.add(again);
                again.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                this.add(gameOver);

                again.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        super.mouseClicked(e);
                        new Main();
                        game.dispose();
                    }
                });

            }
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        canMove = true;
        if(bot)botGame();
        move();
        collisions();
        repaint();
    }

    public class Keys extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case 68 -> {
                    if (direction != 'L' && canMove) direction = 'R';
                    canMove = false;
                }
                case 65 -> {
                    if (direction != 'R' && canMove) direction = 'L';
                    canMove = false;
                }
                case 83 -> {
                    if (direction != 'U' && canMove) direction = 'D';
                    canMove = false;
                }
                case 87 -> {
                    if (direction != 'D' && canMove) direction = 'U';
                    canMove = false;
                }
            }
        }
    }
}
