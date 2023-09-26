package array;

import com.googlecode.lanterna.terminal.Terminal;
import java.io.IOException;
import java.util.Random;

public class ArrayGen{

  private char[][] arr;
  private final int NUMCOLORS = 4;

  public ArrayGen(){
    arr = new char[6][6];
    gen();
  }

  private void gen(){
    // COLORS - red, green. blue, purple
    
    for(int i = 0; i < arr.length; i++){
      for(int a = 0; a < arr[i].length; a++){
        Random ran = new Random();
        int temp = ran.nextInt(NUMCOLORS);
        
        if(temp == 0){ arr[i][a] = 'R'; } 
        else if(temp == 1){ arr[i][a] = 'G'; } 
        else if(temp == 2){ arr[i][a] = 'B'; } 
        else if(temp == 3){ arr[i][a] = 'P'; } 
      }
    }

  }

  public char[][] getArr(){
    return arr;
  }

}
