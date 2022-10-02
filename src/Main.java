import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Main extends JFrame{

    private final JLabel ch1 = new JLabel(new ImageIcon("graphics/unchecked.png"));
    private final JLabel ch2 = new JLabel(new ImageIcon("graphics/checked.png"));
    private final JLabel ch3 = new JLabel(new ImageIcon("graphics/unchecked.png"));
    private int speed = 100;



    public Main(){

        //BUTTONS
        JLabel play = new JLabel(new ImageIcon("graphics/play.png"));
        play.setBounds(255,105,300,100);
        JLabel bot = new JLabel(new ImageIcon("graphics/run.png"));
        bot.setBounds(255,255,300,100);
        play.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        bot.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        JLabel level = new JLabel(new ImageIcon("graphics/level.png"));
        level.setBounds(260,405,300,100);

        ch1.setBounds(255,510,56,56);
        ch2.setBounds(255,610,56,56);
        ch3.setBounds(255,710,56,56);

        ch1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        ch2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        ch3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));


        // easy - 200
        // medium - 100
        // hard - 50
        JLabel easy = new JLabel(new ImageIcon("graphics/easy.png"));
        easy.setBounds(315,495,200,100);
        JLabel medium = new JLabel(new ImageIcon("graphics/medium.png"));
        medium.setBounds(315,595,250,100);
        JLabel hard = new JLabel(new ImageIcon("graphics/hard.png"));
        hard.setBounds(315,695,200,100);

        ch1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(speed!=200){
                    speed=200;
                    ch1.setIcon(new ImageIcon("graphics/checked.png"));
                    ch2.setIcon(new ImageIcon("graphics/unchecked.png"));
                    ch3.setIcon(new ImageIcon("graphics/unchecked.png"));
                }
            }
        });

        ch2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(speed!=100){
                    speed=100;
                    ch1.setIcon(new ImageIcon("graphics/unchecked.png"));
                    ch2.setIcon(new ImageIcon("graphics/checked.png"));
                    ch3.setIcon(new ImageIcon("graphics/unchecked.png"));
                }
            }
        });

        ch3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(speed!=50){
                    speed=50;
                    ch1.setIcon(new ImageIcon("graphics/unchecked.png"));
                    ch2.setIcon(new ImageIcon("graphics/unchecked.png"));
                    ch3.setIcon(new ImageIcon("graphics/checked.png"));
                }
            }
        });

        //PANEL
        JPanel panel = new JPanel();
        panel.add(play);
        panel.add(bot);
        panel.add(level);
        panel.add(ch1);
        panel.add(ch2);
        panel.add(ch3);
        panel.add(easy);
        panel.add(medium);
        panel.add(hard);
        panel.setBorder(BorderFactory.createLineBorder(new Color(30,30,30),5));
        panel.setBackground(new Color(0,162,232));
        panel.setLayout(null);
        panel.setPreferredSize(new Dimension(800,800));


        //FRAME
        this.add(panel);
        this.setTitle("Snake");
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);


        play.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                new Game(speed,false);
                Main.super.dispose();
            }
        });

        bot.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                new Game(5,true);
                Main.super.dispose();
            }
        });

    }

    public static void main(String[] args) {
        new Main();
    }
}