import java.util.*;

public class Game_1 {
    public static int[][] PlayerMap = Tools.zeroMatrix(10, 10);
    public static int[][] ComputerMap = Tools.zeroMatrix(10, 10);

    public static int[][] pShips = {{0,0,5,0,0},{0,0,4,0,0},{0,0,3,0,0},{0,0,3,0,0},{0,0,2,0,0}};
    public static int pShipRow =0;

    public static int[][] cShips = {{0,0,5,0,0},{0,0,4,0,0},{0,0,3,0,0},{0,0,3,0,0},{0,0,2,0,0}};
    public static int cShipRow =0;


    public static void main(String[] args) {
        placePlayerShips();
        Tools.PrintMap(PlayerMap);
        placeCShips();
        //Tools.PrintMap(ComputerMap);
        int playerShips   = 5;
        int computerShips = 5;
        while(playerShips>0 && computerShips>0)
        {
            playerTurn();
            checkShips(ComputerMap,cShips,"You", "their");
            computerTurn();
            checkShips(PlayerMap,pShips,"They", "your");
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
            System.out.println("You won!");
        else
            System.out.println("How?");

    }

    public static void checkShips(int[][] Map, int[][] Ships, String subject, String object) {
        for(int i=0; i<5; i++) {
            if (Ships[i][4] == 1) {

            }
            else{
                if(Ships[i][3]==0) {
                    int sum=0;
                    for (int j = 0; j<Ships[i][2];j++){
                        sum += Map[Ships[i][0]][j + Ships[i][1]];
                    }
                    if(sum==2*Ships[i][2]){
                        String name;
                        switch (i+1) {
                            case 1:
                                name = "Carrier";
                                break;
                            case 2:
                                name = "Battleship";
                                break;
                            case 3:
                                name = "Cruiser";
                                break;
                            case 4:
                                name = "Submarine";
                                break;
                            case 5:
                                name = "Destroyer";
                                break;
                            default: name = "???";
                        }
                        System.out.println(subject + " sunk " + object + " " +name+"!");
                        Ships[i][4]=1;
                    }
                }
                else{
                    int sum=0;
                    for (int j = 0; j<Ships[i][2];j++){
                        sum += Map[j + Ships[i][0]][Ships[i][1]];
                    }
                    if(sum==2*Ships[i][2]){
                        //    System.out.println("You've entered deadship loop");
                        String name;
                        switch (i+1) {
                            case 1:
                                name = "Carrier";
                                break;
                            case 2:
                                name = "Battleship";
                                break;
                            case 3:
                                name = "Cruiser";
                                break;
                            case 4:
                                name = "Submarine";
                                break;
                            case 5:
                                name = "Destroyer";
                                break;
                            default: name = "???";
                        }
                        System.out.println(subject + " sunk " + object + " " +name+"!");
                        Ships[i][4]=1;
                    }
                }

            }
        }
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
    public static void placeShip(int length, String info) {       int sum = 0;
        int row =(int)(Math.random() * 10)+1;
        int col =(int)(Math.random() * 10)+1;
        int z =(int)(Math.random() *2 ) ;
        if (z == 0) {
            if(col+length-1 >10 ||col < 1 ||row<1 ||row>10) {
                placeShip(length, info);
            }
            else {
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
                    placeShip(length, info);
                }
            }

        }
        else {
            if(col >10 ||col < 1 ||row<1 ||row+length-1>10) {
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
                    placeShip(length, info);
                }
            }
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
            }
            else {
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

        }
        else {
            if(col >10 ||col < 1 ||row<1 ||row+length-1>10) {
                placeCShip(length, info);
            }
            else {
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
        int row =(int)(Math.random() * 10)+1;
        int col =(int)(Math.random() * 10)+1;
        if(ComputerMap[row-1][col-1]!=0 && ComputerMap[row-1][col-1]!=1 ){
            playerTurn();
        }
        else{
            if(ComputerMap[row-1][col-1]==1){
                System.out.println("Hit!");
                ComputerMap[row-1][col-1]=2;
            }
            else{
                System.out.println("Miss!");
                ComputerMap[row-1][col-1]=-1;
            }
        }

    }
    public static void computerTurn(){
        int row =(int)(Math.random() * 10)+1;
        int col =(int)(Math.random() * 10)+1;
        if(PlayerMap[row-1][col-1]!=0 && PlayerMap[row-1][col-1]!=1 ){
            computerTurn();
        }
        else{
            if(PlayerMap[row-1][col-1]==1){
                System.out.println("Hit!");
                PlayerMap[row-1][col-1]=2;
            }
            else{
                System.out.println("Miss!");
                PlayerMap[row-1][col-1]=-1;
            }
        }
    }
}

