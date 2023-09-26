# Connect 3 

![Image](example.png| width=200)

## Run

```
git clone https://github.com/vasilybib1/connect-3
cd connect-3/src
javac -cp .:../external/lanterna.jar ./main/Main.java
java -cp .:../external/lanterna.jar main/Main
```

## Descritpion

A terminal connect 3 game (similar to Candy Crush). The terminal rendering was done using a library called lanterna. Only the core of the game is finished and requires more optimizing and fixing. (add animations to visualise how many combos you get, saving the high score, more points for getting more than 3 in a row, prevent user from moving a block if there is no combo)

## How to Play

To play the game use `w a s d` to move around the grid and use `space` to select a block that you wish to move. After input a `w a s d` input to move the block in the chosen direction. Press `q` to quit.
