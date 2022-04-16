public class Field {

    // フィールド
    int[][] field;
    int delay = 1000; // 遅延
    final int SPEED_UP = 25;
    final int MAX_SPEED = 150;

    // コンストラクタ
    Field() {
        this.field = new int[22][10]; // フィールド
    }

    // 落下確認
    public boolean fallJudge(int[][] tetromino) {
        boolean judge;

        if (tetromino[0][1] > this.field.length - 2
                || tetromino[1][1] > this.field.length - 2
                || tetromino[2][1] > this.field.length - 2
                || tetromino[3][1] > this.field.length - 2) {

            judge = false;

        } else if (this.field[tetromino[0][1] + 1][tetromino[0][0]] == 0
                && this.field[tetromino[1][1] + 1][tetromino[1][0]] == 0
                && this.field[tetromino[2][1] + 1][tetromino[2][0]] == 0
                && this.field[tetromino[3][1] + 1][tetromino[3][0]] == 0) {

            judge = true;

        } else {

            judge = false;
        }

        return judge;
    }

    // 左壁確認
    public boolean leftJudge(int[][] tetromino) {
        boolean judge;

        if (tetromino[0][0] < 1
                || tetromino[1][0] < 1
                || tetromino[2][0] < 1
                || tetromino[3][0] < 1) {

            judge = false;

        } else if (this.field[tetromino[0][1]][tetromino[0][0] - 1] == 0
                && this.field[tetromino[1][1]][tetromino[1][0] - 1] == 0
                && this.field[tetromino[2][1]][tetromino[2][0] - 1] == 0
                && this.field[tetromino[3][1]][tetromino[3][0] - 1] == 0) {

            judge = true;

        } else {

            judge = false;
        }

        return judge;
    }

    // 右壁確認
    public boolean rightJudge(int[][] tetromino) {
        boolean judge;

        if (tetromino[0][0] > this.field[0].length - 2
                || tetromino[1][0] > this.field[0].length - 2
                || tetromino[2][0] > this.field[0].length - 2
                || tetromino[3][0] > this.field[0].length - 2) {

            judge = false;

        } else if (this.field[tetromino[0][1]][tetromino[0][0] + 1] == 0
                && this.field[tetromino[1][1]][tetromino[1][0] + 1] == 0
                && this.field[tetromino[2][1]][tetromino[2][0] + 1] == 0
                && this.field[tetromino[3][1]][tetromino[3][0] + 1] == 0) {

            judge = true;

        } else {

            judge = false;
        }

        return judge;
    }

    // 回転確認
    public boolean turnJudge(int tetNum, int[][] tetromino, int turn) {
        boolean judge;

        int[][] judgeTet = new int[4][2];
        for (int i = 0; i < judgeTet.length; i++) {
            for (int j = 0; j < judgeTet[i].length; j++) {
                judgeTet[i][j] = tetromino[i][j];
            }
        }

        int[][] clone = new int[judgeTet.length][judgeTet[0].length];
        for (int i = 0; i < judgeTet.length; i++) {
            for (int j = 0; j < judgeTet[i].length; j++) {
                clone[i][j] = judgeTet[i][j] - judgeTet[0][j];
            }
        }

        int[] clone2 = { judgeTet[0][0], judgeTet[0][1] };

        switch (tetNum) {

            // I
            case 1:
                if (turn == 0) {
                    for (int i = 0; i < judgeTet.length; i++) {
                        for (int j = 0; j < judgeTet[i].length; j++) {
                            if (j == 0) {
                                judgeTet[i][j] = -clone[i][1] + clone2[j] + 1;
                            } else {
                                judgeTet[i][j] = clone[i][0] + clone2[j];
                            }
                        }
                    }
                } else if (turn == 1) {
                    for (int i = 0; i < judgeTet.length; i++) {
                        for (int j = 0; j < judgeTet[i].length; j++) {
                            if (j == 0) {
                                judgeTet[i][j] = -clone[i][1] + clone2[j];
                            } else {
                                judgeTet[i][j] = clone[i][0] + clone2[j] + 1;
                            }
                        }
                    }
                } else if (turn == 2) {
                    for (int i = 0; i < judgeTet.length; i++) {
                        for (int j = 0; j < judgeTet[i].length; j++) {
                            if (j == 0) {
                                judgeTet[i][j] = -clone[i][1] + clone2[j] - 1;
                            } else {
                                judgeTet[i][j] = clone[i][0] + clone2[j];
                            }
                        }
                    }
                } else {
                    for (int i = 0; i < judgeTet.length; i++) {
                        for (int j = 0; j < judgeTet[i].length; j++) {
                            if (j == 0) {
                                judgeTet[i][j] = -clone[i][1] + clone2[j];
                            } else {
                                judgeTet[i][j] = clone[i][0] + clone2[j] - 1;
                            }
                        }
                    }
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
                for (int i = 0; i < judgeTet.length; i++) {
                    for (int j = 0; j < judgeTet[i].length; j++) {
                        if (j == 0) {
                            judgeTet[i][j] = -clone[i][1] + clone2[j];
                        } else {
                            judgeTet[i][j] = clone[i][0] + clone2[j];
                        }
                    }
                }
                break;
        }

        if (judgeTet[0][0] < 0
                || judgeTet[1][0] < 0
                || judgeTet[2][0] < 0
                || judgeTet[3][0] < 0
                || judgeTet[0][0] > this.field[0].length - 1
                || judgeTet[1][0] > this.field[0].length - 1
                || judgeTet[2][0] > this.field[0].length - 1
                || judgeTet[3][0] > this.field[0].length - 1
                || judgeTet[0][1] > this.field.length - 1
                || judgeTet[1][1] > this.field.length - 1
                || judgeTet[2][1] > this.field.length - 1
                || judgeTet[3][1] > this.field.length - 1) {

            judge = false;

        } else if (this.field[judgeTet[0][1]][judgeTet[0][0]] == 0
                && this.field[judgeTet[1][1]][judgeTet[1][0]] == 0
                && this.field[judgeTet[2][1]][judgeTet[2][0]] == 0
                && this.field[judgeTet[3][1]][judgeTet[3][0]] == 0) {

            judge = true;

        } else {

            judge = false;

        }

        return judge;
    }

    // 横列削除
    public void deletRow() {
        for (int i = this.field.length - 1; i >= 2; i--) {
            if (this.field[i][0] != 0
                    && this.field[i][1] != 0
                    && this.field[i][2] != 0
                    && this.field[i][3] != 0
                    && this.field[i][4] != 0
                    && this.field[i][5] != 0
                    && this.field[i][6] != 0
                    && this.field[i][7] != 0
                    && this.field[i][8] != 0
                    && this.field[i][9] != 0) {

                for (int j = 0; j < this.field[i].length; j++) {

                    this.field[i][j] = 0;
                }
                if (this.delay >= MAX_SPEED) {

                    this.delay -= SPEED_UP;
                }
                Score.score += 50;
            }
        }
    }

    // 列削除後の落下
    public void fallRow() {
        for (int i = this.field.length - 1; i >= 2; i--) {
            if (this.field[i][0] == 0
                    && this.field[i][1] == 0
                    && this.field[i][2] == 0
                    && this.field[i][3] == 0
                    && this.field[i][4] == 0
                    && this.field[i][5] == 0
                    && this.field[i][6] == 0
                    && this.field[i][7] == 0
                    && this.field[i][8] == 0
                    && this.field[i][9] == 0) {

                for (int j = i; j >= 2; j--) {
                    for (int k = 0; k < this.field[j].length; k++) {

                        this.field[j][k] = this.field[j - 1][k];
                    }
                }
            }
        }
    }

    // ゲームオーバー判定
    public boolean gameOverJudge() {
        boolean judge;

        if (this.field[1][0] != 0
                || this.field[1][1] != 0
                || this.field[1][2] != 0
                || this.field[1][3] != 0
                || this.field[1][4] != 0
                || this.field[1][5] != 0
                || this.field[1][6] != 0
                || this.field[1][7] != 0
                || this.field[1][8] != 0
                || this.field[1][9] != 0) {

            judge = true;

        } else {

            judge = false;
        }

        return judge;
    }
}
