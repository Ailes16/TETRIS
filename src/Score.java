import java.awt.*;
import javax.swing.*;

public class Score extends JPanel {

    // 定数
    final int SCORE_WIDTH = 100;
    final int SCORE_HEIGHT = 50;

    // 変数
    static int score = 0;

    // コンストラクタ
    Score() {
        this.setLayout(null);
        this.setBounds(GameField.FIELD_WIDTH + 50, (GameField.UNIT_SIZE * 10) + 10, SCORE_WIDTH, SCORE_HEIGHT);
        this.setBackground(Color.BLACK);

    }

    // ペイントコンポーネント
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    // draw
    public void draw(Graphics g) {
        // score Text
        g.setColor(Color.PINK);
        g.setFont(new Font("Ink Free", Font.BOLD, 30));
        FontMetrics metrics1 = getFontMetrics(g.getFont());
        g.drawString(String.valueOf(score), (SCORE_WIDTH - metrics1.stringWidth(String.valueOf(score))) / 2,
                35);

    }
}
