public class Tools {
    public static int[][] zeroMatrix(int row, int col) {

        int subrow;
        int subcol;
        int[][] zero = new int[row][col];

        for (subrow = 0; subrow < row; subrow++) {
            for (subcol = 0; subcol < col; subcol++) {
                zero[subrow][subcol] = 0;
            }
        }
        return zero;
    }
    

    public static void PrintMap(int[][] Map) {
        final String ANSI_BLUE = "\u001B[34m";
        final String ANSI_RESET = "\u001B[0m";
        final String ANSI_GRAY = "\u001B[1m";
        int row = Map.length;
        int col = Map[0].length;
        int subrow;
        int subcol;
        System.out.println("   "+"A"+" "+"B"+" "+"C"+" "+"D"+" "+"E"+" "+"F"+" "+"G"+" "+"H"+" "+"I"+" "+"J");
        for(subrow=0; subrow<row; subrow++) {
            if(subrow<9) {
                System.out.print(subrow + 1 + "  ");
            }
            else System.out.print(subrow + 1 + " ");
            for (subcol = 0; subcol < col; subcol++) {
                if(Map[subrow][subcol]==0) {
                    System.out.print(ANSI_BLUE + Map[subrow][subcol] + " "+ ANSI_RESET);
                }
                else if(Map[subrow][subcol]==1){
                    System.out.print(ANSI_GRAY + Map[subrow][subcol] + " "+ ANSI_RESET);
                }
            }
            System.out.println();
        }
    }
    public static void PrintBattleMap(int[][] Map) {
        final String ANSI_BLUE = "\u001B[34m";
        final String ANSI_RESET = "\u001B[0m";
        final String ANSI_GRAY = "\u001B[1m";
        final String ANSI_WHITE = "\u001B[37m";
        final String ANSI_RED = "\u001B[31m";
        int row = Map.length;
        int col = Map[0].length;
        int subrow;
        int subcol;
        System.out.println("   "+"A"+" "+"B"+" "+"C"+" "+"D"+" "+"E"+" "+"F"+" "+"G"+" "+"H"+" "+"I"+" "+"J");
        for(subrow=0; subrow<row; subrow++) {
            if(subrow<9) {
                System.out.print(subrow + 1 + "  ");
            }
            else System.out.print(subrow + 1 + " ");
            for (subcol = 0; subcol < col; subcol++) {
                if(Map[subrow][subcol]==0) {
                    System.out.print(ANSI_BLUE + Map[subrow][subcol] + " "+ ANSI_RESET);
                }
                else if(Map[subrow][subcol]==1){
                    int placeholder=Map[subrow][subcol]-1;
                    System.out.print(ANSI_BLUE + placeholder + " "+ ANSI_RESET);
                }
                else if(Map[subrow][subcol]==-1){
                    int placeholder=Map[subrow][subcol]+1;
                    System.out.print(ANSI_WHITE + "  "+ ANSI_RESET);
                }
                else if(Map[subrow][subcol]==2){
                    System.out.print(ANSI_RED + Map[subrow][subcol] + " "+ ANSI_RESET);
                }
            }
            System.out.println();
        }
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
}