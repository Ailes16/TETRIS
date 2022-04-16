import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class GameField extends JPanel {

    // 定数
    static final int FIELD_WIDTH = 300;
    static final int FIELD_HEIGHT = 600;
    static final int UNIT_SIZE = 30;

    // 変数
    int tetNum; // 落下中のブロックの種類
    Tetromino tet;
    Field newField;
    NextPanel newNextPanel;
    int startPosition = 3; // ブロックのスタート位置 0~6
    boolean gameJudge;

    // コンストラクタ
    GameField() {

        this.setBounds(20, 10, FIELD_WIDTH, FIELD_HEIGHT);
        this.setBackground(Color.BLACK);
        this.addKeyListener(new MyKeyAdapter());
        this.setFocusable(true);

        start();
    }

    // ゲームスタート
    public void start() {
        newField = new Field();
        newNextPanel = new NextPanel();
        GamePanel.timer.start();
        gameJudge = true;
        firstTetromino(); // (仮)
    }

    // 最初のブロック
    public void firstTetromino() {
        tetNum = newNextPanel.getNum();
        // tetNum = 1;
        tet = new Tetromino(tetNum); // 1~7
        for (int i = 0; i < tet.tetromino.length; i++) {
            tet.tetromino[i][0] += startPosition;
        }
    }

    // 新しいブロック
    public void newTetromino() {
        newNextPanel.createNum();
        tetNum = newNextPanel.getNum();
        // tetNum = 1;
        tet = new Tetromino(tetNum); // 1~7
        for (int i = 0; i < tet.tetromino.length; i++) {
            tet.tetromino[i][0] += startPosition;
        }
    }

    // ペイントコンポーネント
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    // draw
    public void draw(Graphics g) {

        // tetromino
        g.setColor(tet.color);
        for (int i = 0; i < tet.tetromino.length; i++) {
            g.fillRect(tet.tetromino[i][0] * UNIT_SIZE, (tet.tetromino[i][1] - 2) * UNIT_SIZE, UNIT_SIZE,
                    UNIT_SIZE);
        }

        // 停止したブロック
        for (int y = 0; y < newField.field.length; y++) {
            for (int x = 0; x < newField.field[y].length; x++) {
                if (newField.field[y][x] == 1) {
                    g.setColor(Color.CYAN);
                    g.fillRect(x * UNIT_SIZE, (y - 2) * UNIT_SIZE, UNIT_SIZE, UNIT_SIZE);
                } else if (newField.field[y][x] == 2) {
                    g.setColor(Color.YELLOW);
                    g.fillRect(x * UNIT_SIZE, (y - 2) * UNIT_SIZE, UNIT_SIZE, UNIT_SIZE);
                } else if (newField.field[y][x] == 3) {
                    g.setColor(Color.MAGENTA);
                    g.fillRect(x * UNIT_SIZE, (y - 2) * UNIT_SIZE, UNIT_SIZE, UNIT_SIZE);
                } else if (newField.field[y][x] == 4) {
                    g.setColor(Color.BLUE);
                    g.fillRect(x * UNIT_SIZE, (y - 2) * UNIT_SIZE, UNIT_SIZE, UNIT_SIZE);
                } else if (newField.field[y][x] == 5) {
                    g.setColor(Color.ORANGE);
                    g.fillRect(x * UNIT_SIZE, (y - 2) * UNIT_SIZE, UNIT_SIZE, UNIT_SIZE);
                } else if (newField.field[y][x] == 6) {
                    g.setColor(Color.GREEN);
                    g.fillRect(x * UNIT_SIZE, (y - 2) * UNIT_SIZE, UNIT_SIZE, UNIT_SIZE);
                } else if (newField.field[y][x] == 7) {
                    g.setColor(Color.RED);
                    g.fillRect(x * UNIT_SIZE, (y - 2) * UNIT_SIZE, UNIT_SIZE, UNIT_SIZE);
                }
            }
        }

        // 格子
        for (int i = 0; i < FIELD_HEIGHT / UNIT_SIZE; i++) {
            g.setColor(Color.GRAY);
            g.drawLine(i * UNIT_SIZE, 0, i * UNIT_SIZE, FIELD_HEIGHT);
            g.drawLine(0, i * UNIT_SIZE, FIELD_WIDTH, i * UNIT_SIZE);
        }

        if (!gameJudge) {
            gameOver(g);
        }
    }

    // game over
    public void gameOver(Graphics g) {
        // Game Over Text
        g.setColor(Color.RED);
        g.setFont(new Font("Ink Free", Font.BOLD, 30));
        FontMetrics metrics1 = getFontMetrics(g.getFont());
        g.drawString("Game Over", (FIELD_WIDTH - metrics1.stringWidth("Game Over")) / 2, FIELD_HEIGHT / 3);

    }

    public void action() {

        if (newField.fallJudge(tet.tetromino)) {

            tet.fallTetromino();

        } else {

            for (int i = 0; i < tet.tetromino.length; i++) {
                newField.field[tet.tetromino[i][1]][tet.tetromino[i][0]] = tetNum;
            }
            newField.deletRow();

            for (int i = 0; i < 4; i++) {
                newField.fallRow();
            }

            if (newField.gameOverJudge()) {

                GamePanel.timer.stop();
                gameJudge = false;
            }

            GamePanel.timer.setDelay(newField.delay);

            newTetromino();
        }
    }

    public class MyKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    if (newField.leftJudge(tet.tetromino)) {

                        tet.leftTetromino();

                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if (newField.rightJudge(tet.tetromino)) {

                        tet.rightTetromino();

                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if (newField.fallJudge(tet.tetromino)) {

                        tet.fallTetromino();

                    }
                    break;
                case KeyEvent.VK_SPACE:
                    if (newField.turnJudge(tetNum, tet.tetromino, tet.turn)) {

                        tet.turnTetromino(tetNum);
                    }
                    break;
                case KeyEvent.VK_ENTER:
            }
            repaint();
        }
    }

}
