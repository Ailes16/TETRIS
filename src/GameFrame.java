import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {

    // 定数
    final String TITLE = "Tetris";

    // 変数
    GamePanel gamePanel;

    // コンストラクタ
    GameFrame() {

        // インスタンス化
        gamePanel = new GamePanel();

        this.setTitle(TITLE);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new FlowLayout());
        this.setResizable(false);

        this.add(gamePanel);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

}
