# Battleship
Battleship game
COMP250 – Battleship Assignment – Albert Reyes – 4/25/2024
*changes made:
1. Changed hit/miss to player hit/miss and computer hit/miss 
2.  In placeShip and playerTurn Method, change it so the column letters can be entered instead of numbers. Also changed it so the horizontal/vertical placement can be entered as v and h. Both using try/catch. in depth: The String charAt() method returns the character at the specified index in a string. 
In depth Notes:
•	String columnLetter = input.nextLine().toUpperCase(); - //to input column letter no matter if lowercase 
•	char columnChar = columnLetter.charAt(0); - //returns first character which is at 0 in index of inputted string 
•	if (columnChar >= ‘A’ && col <=’J’) and col = columnChar - ‘A’ + 1; - 
o	PART 1: //int value of ASCII code of inputted *uppercase column character (ex.B = ASCII code 66; all the way up to J=74) - int value of ASCII 65 (uppercase A) aka ‘A’ + 1 which then results in a numeric value (from char a to j = from int 1 to 10) 
o	PART 2: //examples: if colChar is ‘A’, then col = ‘A’ - ‘A’ + 1 results in col = 1; = 65 – 65 + 1 = 0 
	//if colChar is ‘B’, then col = ‘B’ - ‘A’ + 1 results in col = 2; = 66-65 + 1 = 2
o	PART 3: I did the same thing to vert./hori. placement to get first character of input string and then “converted” ASCII code of character to integer cause I wanted the rest of the code to remain unchanged. I initially was gonna go with parseInt but that was a whole mess so i went with characters cause all I wanted was to convert entered letters into integers, have the input scanned to integer but returned as a letter not a string or integer.
3. Just added Battleship title card after placing ships
4. Simple Counter for every player turns (after game) 
5. Total time/game duration
6. In computerTurn method, added Breadth First Search, Binary Tree and in order traversal to make CPU more player-like using playermap array. Essentially, once the CPU gets a hit on a player’s ship’s part, it looks for adjacent cells next to hit so it is more likely to knock down a player’s ships. I found after I did this part, the games took double the time in minutes from 3 ½ minutes to about 7 minutes. Of course, the player turns also doubled from 30-40 to 80-90.

