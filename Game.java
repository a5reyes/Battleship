import java.util.*;

public class Game { //below are global variables; in main, are local variables
    public static int[][] PlayerMap = Tools.zeroMatrix(10, 10);
    public static int[][] ComputerMap = Tools.zeroMatrix(10, 10);
    public static int[][] pShips = {{0,0,5,0,0},{0,0,4,0,0},{0,0,3,0,0},{0,0,3,0,0},{0,0,2,0,0}};
    public static int pShipRow =0;
    public static int[][] cShips = {{0,0,5,0,0},{0,0,4,0,0},{0,0,3,0,0},{0,0,3,0,0},{0,0,2,0,0}};
    public static int cShipRow =0;
    public static long startTime;
    public static Node root;
    
    
    public static void main(String[] args) {
        long startTime = System.nanoTime();
        SimpleCounter counter = new SimpleCounter();
        placePlayerShips();
        Tools.PrintMap(PlayerMap);
        placeCShips();
        //Tools.PrintMap(ComputerMap);
        int playerShips   = 5;
        int computerShips = 5;
        while(playerShips>0 && computerShips>0){    
            playerTurn();
            Tools.checkShips(ComputerMap,cShips,"You", "their");
            computerTurn();
            Tools.checkShips(PlayerMap,pShips,"They", "your");
            playerShips=0;
            for(int i=0; i<10; i++) {
                for (int j = 0; j < 10; j++) {
                    if (PlayerMap[i][j] == 1)
                        playerShips++;
                }
            }
            computerShips=0;
            for(int i=0; i<10; i++) {
                for (int j = 0; j < 10; j++) {
                    if (ComputerMap[i][j] == 1)
                        computerShips++;
                }
            }
        }
        if(computerShips>playerShips)
            System.out.println("Sorry, you lost!");
        else if(computerShips<playerShips)
            System.out.println("\u001B{1mYou won!");
            System.out.println("Player Turns: " + counter.getCount());
            long endTime = System.nanoTime(); 
            long duration = endTime - startTime;
            double minutes = (double)duration/60_000_000_000.0; 
            System.out.println("Game Duration:"+ minutes); 
    }
    public static void placePlayerShips() {
        Tools.PrintMap(PlayerMap);
        placeShip(5, "Carrier");
        Tools.PrintMap(PlayerMap);
        placeShip(4, "Battleship");
        Tools.PrintMap(PlayerMap);
        placeShip(3, "Cruiser");
        Tools.PrintMap(PlayerMap);
        placeShip(3, "Submarine");
        Tools.PrintMap(PlayerMap);
        placeShip(2, "Destroyer");
    }
    public static void placeCShips() {
        placeCShip(5, "Carrier");
        placeCShip(4, "Battleship");
        placeCShip(3, "Cruiser");
        placeCShip(3, "Submarine");
        placeCShip(2, "Destroyer");
    }
    public static void placeShip(int length, String info) {
        int sum = 0;
        int col = 0;
        int z = 0;
        Scanner input = new Scanner(System.in);
        System.out.print("Choose a Row for your " + info + ": ");
        int row = input.nextInt();
        input.nextLine();
        System.out.print("Choose a Column for your " + info + ": ");
        try{
            String columnLetter = input.nextLine().toUpperCase();
            char columnChar = columnLetter.charAt(0); 
        if (columnChar >= 'A' && col <='J'){
            col = columnChar - 'A' + 1; 
            System.out.print("Vertical or Horizontal Placement: ");
            try{
                String placementLetter = input.nextLine().toUpperCase();
                char placementChar = placementLetter.charAt(0);
                if (placementChar >= 'H' && z <= 'V'){
                    z = placementChar - 'H' + 0;
                    if (z == 0) {
                    if(col+length-1 >10 ||col < 1 ||row<1 ||row>10) {
                        System.out.println("You can't place ships outside the " + 10 + " by " + 10 + " grid");
                        Tools.PrintMap(PlayerMap);
                        placeShip(length, info);
                    } else {
                        for (int i = col - 1; i <= col + length - 2; i++) {
                            sum = sum + PlayerMap[row-1][i];
                        }
                        if ((row >= 1 && row < 11) && (col >= 1 && col + length - 1 < 11) && (sum == 0)) {
                            for (int i = col - 1; i <= col + length - 2; i++)
                                PlayerMap[row - 1][i] = 1;
                            pShips[pShipRow][0]=row-1;
                            pShips[pShipRow][1]=col-1;
                            pShips[pShipRow][3]=z;
                            pShipRow++;
                        } else if ((row > 0 && row < 11) && (col >= 0 && col < 11) && sum >0) {
                            System.out.println("You can't place two ships on the same location");
                            Tools.PrintMap(PlayerMap);
                            placeShip(length, info);
                        }
                    }
                } else {
                    if(col >10 ||col < 1 ||row<1 ||row+length-1>10) {
                        System.out.println("You can't place ships outside the " + 10 + " by " + 10 + " grid");
                        Tools.PrintMap(PlayerMap);
                        placeShip(length, info);
                    }
                    else {
                        for (int i = row - 1; i <= row + length - 2; i++) {
                            sum = sum + PlayerMap[i][col-1];
                        }
                        if ((row+length - 1 >= 1 && row < 11) && (col >= 1 && col  < 11) && (sum == 0)) {
                            for (int i = row - 1; i <= row + length - 2; i++)
                                PlayerMap[i][col-1] = 1;
                            pShips[pShipRow][0]=row-1;
                            pShips[pShipRow][1]=col-1;
                            pShips[pShipRow][3]=z;
                            pShipRow++;
                        } else if ((row > 0 && row < 11) && (col >= 0 && col < 11) && sum >= 1) {
                            System.out.println("You can't place two ships on the same location");
                            Tools.PrintMap(PlayerMap);
                            placeShip(length, info);
                        }
                    }
                }    
                }
            } catch (InputMismatchException e){
                System.out.println("Try again");
                Tools.PrintMap(PlayerMap);
                placeShip(length, info);
                return;
            }
        } else {
            System.out.println("Column must be from 1 to 10.");
            Tools.PrintMap(PlayerMap);
            placeShip(length, info);
            return;
        }
        } catch (InputMismatchException e){
            System.out.println("Column must be from 1 to 10.");
            Tools.PrintMap(PlayerMap);
            placeShip(length, info);
            return;
        }
    }
    public static void placeCShip(int length, String info) {
        int sum = 0;
        int row =(int)(Math.random() * 10)+1;
        int col =(int)(Math.random() * 10)+1;
        int z =(int)(Math.random() *2 ) ;
        if (z == 0) {
            if(col+length-1 >10 ||col < 1 ||row<1 ||row>10) {
                placeCShip(length, info);
            } else {
                for (int i = col - 1; i <= col + length - 2; i++) {
                    sum = sum + ComputerMap[row-1][i];
                }
                if ((row >= 1 && row < 11) && (col >= 1 && col + length - 1 < 11) && (sum == 0)) {
                    for (int i = col - 1; i <= col + length - 2; i++)
                        ComputerMap[row - 1][i] = 1;
                    cShips[cShipRow][0]=row-1;
                    cShips[cShipRow][1]=col-1;
                    cShips[cShipRow][3]=z;
                    cShipRow++;
                } else if ((row > 0 && row < 11) && (col >= 0 && col < 11) && sum >0) {
                    placeCShip(length, info);
                }
            }
        } else {
            if(col >10 ||col < 1 ||row<1 ||row+length-1>10) {
                placeCShip(length, info);
            } else {
                for (int i = row - 1; i <= row + length - 2; i++) {
                    sum = sum + ComputerMap[i][col-1];
                }
                if ((row+length - 1 >= 1 && row < 11) && (col >= 1 && col  < 11) && (sum == 0)) {
                    for (int i = row - 1; i <= row + length - 2; i++)
                        ComputerMap[i][col-1] = 1;
                    cShips[cShipRow][0]=row-1;
                    cShips[cShipRow][1]=col-1;
                    cShips[cShipRow][3]=z;
                    cShipRow++;
                } else if ((row > 0 && row < 11) && (col >= 0 && col < 11) && sum >= 1) {
                    placeCShip(length, info);
                }
            }
        }
    }
    public static void playerTurn() {
        System.out.println("\n   B A T T L E S H I P     \n");
        Tools.PrintBattleMap(ComputerMap);
        int col= 0;
        Scanner input = new Scanner(System.in);
        SimpleCounter.increment();
        System.out.print("Choose a row for your attack:");
        int row = input.nextInt();
        input.nextLine();
        System.out.print("Choose a column for your attack:");
        try {
            String colLetter = input.nextLine().toUpperCase();
            char colChar = colLetter.charAt(0);
        if (colChar >= 'A' && col <= 'J'){
            col = colChar - 'A' + 1;
        }
        } catch (InputMismatchException e) {
            System.out.println("Try again");
            playerTurn();
        } if(row<1||row>10||col<1||col>10) {
            System.out.println("Out of bounds");
            playerTurn();
        } else if(ComputerMap[row-1][col-1]!=0 && ComputerMap[row-1][col-1]!=1 ){
            System.out.println("You already shot here");
            playerTurn();
        } else{
            if(ComputerMap[row-1][col-1]==1){
                System.out.println("Player Hit!");
                ComputerMap[row-1][col-1]=2;    
            } else{
                System.out.println("Player Miss!");
                ComputerMap[row-1][col-1]=-1;
            }
        }
    }
    public static void computerTurn(){
        BinaryTree tree = new BinaryTree();
        int row =(int)(Math.random() * 10)+1;
        int col =(int)(Math.random() * 10)+1;
        if(PlayerMap[row-1][col-1]!=0 && PlayerMap[row-1][col-1]!=1 ){
            computerTurn();
        } else{
            if(PlayerMap[row-1][col-1]==1){
                System.out.println("Computer Hit!");
                PlayerMap[row-1][col-1]=2;
                inorder(tree.root);
                PlayerMap[row-1][col-1] += hitAdjacent(row - 1, col - 1);
            } else{
                System.out.println("Computer Miss!");
                PlayerMap[row-1][col-1]=-1;
            }
        }
    }
    private static int hitAdjacent(int row, int col){
        int count2 = 0;
        if (row > 0 && PlayerMap[row - 1][col] == 1){
            PlayerMap[row-1][col] = 2;
            count2++;
        }
        if (row < 9 && PlayerMap[row + 1][col] == 1){
            PlayerMap[row+1][col] = 2;
            count2++;
        }
        if (col > 0 && PlayerMap[row][col-1] == 1){
            PlayerMap[row-1][col] = 2;
            count2++;
        }
        if (col < 9 && PlayerMap[row][col+1] == 1){
            PlayerMap[row+1][col] = 2;
            count2++;
        }
        return count2;
    }
    private static void inorder(Node root) {
        if (root == null)
            return;
        inorder(root.left);
        System.out.print(root.data + " ");
        inorder(root.right);
    }
}
class BinaryTree {
    Node root;
    BinaryTree() {
        root = null;
    }
}
class Node{
    int data;
    Node left, right;
    public Node(int item){
        data = item;
        left = right = null;
    }
}