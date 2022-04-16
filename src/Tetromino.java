import java.awt.*;

public class Tetromino {

    // 定数
    private final int[][] I = { { 1, 1 }, { 0, 1 }, { 2, 1 }, { 3, 1 } };
    private final int[][] O = { { 1, 1 }, { 1, 0 }, { 2, 0 }, { 2, 1 } };
    private final int[][] T = { { 1, 1 }, { 0, 1 }, { 1, 0 }, { 2, 1 } };
    private final int[][] J = { { 1, 1 }, { 0, 0 }, { 0, 1 }, { 2, 1 } };
    private final int[][] L = { { 1, 1 }, { 0, 1 }, { 2, 0 }, { 2, 1 } };
    private final int[][] S = { { 1, 1 }, { 0, 1 }, { 1, 0 }, { 2, 0 } };
    private final int[][] Z = { { 1, 1 }, { 0, 0 }, { 1, 0 }, { 2, 1 } };

    // フィールド
    int[][] tetromino;
    Color color;
    int turn;

    // コンストラクタ
    Tetromino() {
        this.turn = 0;
    }

    Tetromino(int tetNum) {
        this();
        switch (tetNum) {
            case 1:
                this.tetromino = I;
                this.color = Color.CYAN;
                break;
            case 2:
                this.tetromino = O;
                this.color = Color.YELLOW;
                break;
            case 3:
                this.tetromino = T;
                this.color = Color.MAGENTA;
                break;
            case 4:
                this.tetromino = J;
                this.color = Color.BLUE;
                break;
            case 5:
                this.tetromino = L;
                this.color = Color.ORANGE;
                break;
            case 6:
                this.tetromino = S;
                this.color = Color.GREEN;
                break;
            case 7:
                this.tetromino = Z;
                this.color = Color.RED;
                break;
        }
    }

    // 落下
    public void fallTetromino() {

        for (int i = 0; i < this.tetromino.length; i++) {
            this.tetromino[i][1] += 1;
        }
    }

    // 左移動
    public void leftTetromino() {

        for (int i = 0; i < this.tetromino.length; i++) {
            this.tetromino[i][0] -= 1;
        }
    }

    // 右移動
    public void rightTetromino() {

        for (int i = 0; i < this.tetromino.length; i++) {
            this.tetromino[i][0] += 1;
        }
    }

    // 回転
    public void turnTetromino(int tetNum) {

        int[][] clone = new int[this.tetromino.length][this.tetromino[0].length];
        for (int i = 0; i < this.tetromino.length; i++) {
            for (int j = 0; j < this.tetromino[i].length; j++) {
                clone[i][j] = this.tetromino[i][j] - this.tetromino[0][j];
            }
        }

        int[] clone2 = { this.tetromino[0][0], this.tetromino[0][1] };

        switch (tetNum) {

            // I
            case 1:
                if (this.turn == 0) {
                    for (int i = 0; i < this.tetromino.length; i++) {
                        for (int j = 0; j < this.tetromino[i].length; j++) {
                            if (j == 0) {
                                this.tetromino[i][j] = -clone[i][1] + clone2[j] + 1;
                            } else {
                                this.tetromino[i][j] = clone[i][0] + clone2[j];
                            }
                        }
                    }
                    this.turn = 1;
                } else if (this.turn == 1) {
                    for (int i = 0; i < this.tetromino.length; i++) {
                        for (int j = 0; j < this.tetromino[i].length; j++) {
                            if (j == 0) {
                                this.tetromino[i][j] = -clone[i][1] + clone2[j];
                            } else {
                                this.tetromino[i][j] = clone[i][0] + clone2[j] + 1;
                            }
                        }
                    }
                    this.turn = 2;
                } else if (this.turn == 2) {
                    for (int i = 0; i < this.tetromino.length; i++) {
                        for (int j = 0; j < this.tetromino[i].length; j++) {
                            if (j == 0) {
                                this.tetromino[i][j] = -clone[i][1] + clone2[j] - 1;
                            } else {
                                this.tetromino[i][j] = clone[i][0] + clone2[j];
                            }
                        }
                    }
                    this.turn = 3;
                } else {
                    for (int i = 0; i < this.tetromino.length; i++) {
                        for (int j = 0; j < this.tetromino[i].length; j++) {
                            if (j == 0) {
                                this.tetromino[i][j] = -clone[i][1] + clone2[j];
                            } else {
                                this.tetromino[i][j] = clone[i][0] + clone2[j] - 1;
                            }
                        }
                    }
                    this.turn = 0;
                }
                break;

            // O
            case 2:

                break;

            // T,J,L,S,Z
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
                for (int i = 0; i < this.tetromino.length; i++) {
                    for (int j = 0; j < this.tetromino[i].length; j++) {
                        if (j == 0) {
                            this.tetromino[i][j] = -clone[i][1] + clone2[j];
                        } else {
                            this.tetromino[i][j] = clone[i][0] + clone2[j];
                        }
                    }
                }
                break;
        }
    }
}
