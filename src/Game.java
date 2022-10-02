import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class Game extends JFrame {


    private final Field field;
    private final JLabel scoreVaule = new JLabel();

    public Game(int speed, boolean bot){

        //TIMER
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                scoreVaule.setText(Integer.toString(field.getSnakeSize()-3));
            }
        },speed,speed);

        //SCORE
        Color gray = new Color(30, 30, 30);
        JPanel score = new JPanel();
        score.setBackground(gray);
        score.setPreferredSize(new Dimension(810,50));
        Color white = new Color(255, 255, 255);
        JLabel scoreText = new JLabel("Score: ");
        scoreText.setForeground(white);
        scoreVaule.setForeground(white);
        scoreVaule.setFont(new Font("Serif", Font.BOLD, 35));
        scoreText.setFont(new Font("Serif", Font.BOLD, 35));
        score.add(scoreText);
        score.add(scoreVaule);

        //FIELD
        field = new Field(speed,bot,this);
        field.setBorder(BorderFactory.createLineBorder(gray,5));
        Color green = new Color(37, 213, 101);
        field.setBackground(green);


        //WHOLE PANEL
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        panel.setPreferredSize(new Dimension(810,860));
        panel.add(score,BorderLayout.NORTH);
        panel.add(field,BorderLayout.CENTER);

        //FRAME
        this.setTitle("Snake");
        this.add(panel);
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
    }


}
