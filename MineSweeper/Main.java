package MineSweeper;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter Level (int) 1. Easy    2. Medium    3. Hard  => ");
        int level = in.nextInt();
        int n = 0;
        switch (level) {
            case 1: {
                n = 8;
                break;
            }
            case 2: {
                n = 12;
                break;
            }
            case 3: {
                n = 16;
                break;
            }
        }
        MineSweeper game = new MineSweeper(n);
    }

}
