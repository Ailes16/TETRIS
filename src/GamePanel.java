import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GamePanel extends JPanel implements ActionListener {

    // 定数
    final int SCREEN_WIDTH = 500;
    final int SCREEN_HEIGHT = 620;

    // 変数
    GameField gameField;
    Score score;
    static Timer timer;

    // コンストラクタ
    GamePanel() {

        // インスタンス化
        timer = new Timer(1000, this);
        gameField = new GameField();
        score = new Score();
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setLayout(null);

        this.add(score);
        this.add(gameField.newNextPanel);
        this.add(gameField);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        gameField.action();

        repaint();

    }

}
