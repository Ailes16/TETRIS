import java.awt.*;
import javax.swing.*;

public class NextPanel extends JPanel {

    private int nextNum; // 次のテトリミノナンバー
    private int num; // テトリミノナンバー

    static Tetromino nextTet;

    // コンストラクタ
    NextPanel() {

        this.setBounds(GameField.FIELD_WIDTH + 50, (GameField.UNIT_SIZE * 2) + 10, GameField.UNIT_SIZE * 4,
                GameField.UNIT_SIZE * 4);
        this.setBackground(Color.BLACK);
        newTest();
    }

    public void newTest() {
        nextNum = 1 + (int) (Math.random() * 7.0);
        nextTet = new Tetromino(nextNum); // 1~7
        num = nextNum;
        // System.out.println(nextNum);

    }

    // ペイントコンポーネント
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    // draw
    public void draw(Graphics g) {

        if (nextTet != null) {

            // 次のブロック
            g.setColor(nextTet.color);
            for (int i = 0; i < nextTet.tetromino.length; i++) {
                g.fillRect(nextTet.tetromino[i][0] * GameField.UNIT_SIZE,
                        (nextTet.tetromino[i][1] + 1) * GameField.UNIT_SIZE, GameField.UNIT_SIZE,
                        GameField.UNIT_SIZE);
            }
        }

        // g.fillRect(x * GameField.UNIT_SIZE, y * GameField.UNIT_SIZE,
        // GameField.UNIT_SIZE, GameField.UNIT_SIZE);

        // 格子
        for (int i = 0; i < 4; i++) {
            g.setColor(Color.GRAY);
            g.drawLine(i * GameField.UNIT_SIZE, 0, i * GameField.UNIT_SIZE, GameField.UNIT_SIZE * 4);
            g.drawLine(0, i * GameField.UNIT_SIZE, GameField.UNIT_SIZE * 4, i * GameField.UNIT_SIZE);
        }
    }

    // 次のテトリミノを生成
    public void createNum() {

        num = nextNum;
        nextNum = 1 + (int) (Math.random() * 7.0);
        nextTet = new Tetromino(nextNum); // 1~7

        // System.out.println(nextNum);

    }

    // getter
    public int getNum() {

        return num;
    }

}
