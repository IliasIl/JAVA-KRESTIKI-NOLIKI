
package krestikiinol;



import java.util.*;


public class KrestikiINol {

    final static int FIELD_SIZE = 3;

    static char[][] field = new char[FIELD_SIZE][FIELD_SIZE];

    static Scanner scanner = new Scanner(System.in);
    static Random rand = new Random();

    public static void main(String[] args) {

        createField();
        print();

        while(true){

            playerTurn();
            print();
            if(isFieldFull()) {
                System.out.println("Ничья");
                break;
            }
            if(checkWin('X')) {
                System.out.println("Победил человек!");
                break;
            }

            aiTurn();
            print();
            if(isFieldFull()) {
                System.out.println("Ничья");
                break;
            }
            if(checkWin('O')) {
                System.out.println("Победил AI!");
                break;
            }

        }
        
    }

    public static void createField() {
        for (int i = 0; i < FIELD_SIZE; i++) {
            for (int j = 0; j < FIELD_SIZE; j++) {
                field[i][j] = '*';
            }
        }
    }

    public static void print() {
        for (int i = 0; i < FIELD_SIZE; i++) {
            for (int j = 0; j < FIELD_SIZE; j++) {
                System.out.print(field[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static boolean setXO(int x, int y, char xo) {

        if(field[y][x] == '*') {
            field[y][x] = xo;
            return true;
        }

        return false;
    }

    public static void playerTurn() {

        int x;
        int y;

        do {
            System.out.println("Введите координаты: X Y");
            x = scanner.nextInt();
            y = scanner.nextInt();

        }while(!isCellEmpty(x - 1, y - 1));

        setXO(x - 1, y - 1, 'X');
    }

    public static boolean isCellEmpty(int x, int y) {

        if(field[y][x] == '*')
            return true;

        return false;
    }

    public static void aiTurn() {

        int x;
        int y;

        do {
            x = rand.nextInt(FIELD_SIZE);
            y = rand.nextInt(FIELD_SIZE);

        }while(!isCellEmpty(x, y));

        setXO(x, y, 'O');
    }

    public static boolean isFieldFull() {

        for (int i = 0; i < FIELD_SIZE; i++) {
            for (int j = 0; j < FIELD_SIZE; j++) {
                if(field[i][j] == '*')
                    return false;
            }
        }

        return true;
    }

    public static boolean checkWin(char xo) {

        for (int i = 0; i < FIELD_SIZE; i++) {
            if(checkLine(0, i, 1, 0, FIELD_SIZE, xo)) return true;
            if(checkLine(i, 0, 0, 1, FIELD_SIZE, xo)) return true;
        }

        if(checkLine(0, 0, 1, 1, FIELD_SIZE, xo)) return true;
        if(checkLine(0, 2, 1, -1, FIELD_SIZE, xo)) return true;

        return false;
    }

    public static boolean checkLine(int x, int y, int vx, int vy, int l, char xo) {

        for (int i = 0; i < l; i++) {
            if(field[y + vy * i][x + vx * i] != xo) return false;
        }

        return true;
    }



}