package array;

import java.io.*;
import java.util.*;
import main.Main;

public class Logic{

  private static final boolean DEBUG = false;
  private static int[][] rowIndex = new int[6][6];
  private static int[][] columnIndex = new int[6][6];
  private static char[][] temp = new char[6][6];

  public static int check(char[][] arr, boolean count){
    int score = 0;
    for(int i = 0; i < 6; i++){for(int a = 0; a < 6; a++){temp[i][a] = arr[i][a];}}

    if(DEBUG){
      //Row Indexes left to right
      System.out.println();
      for(int r = 0; r < 6; r++){
        for(int c = 0; c < 6; c++){
          System.out.print(checkRow(arr[r], c) + " ");
        }
        System.out.println();
      }
      
      //Column Indexes top to bottom
      System.out.println();
      for(int r = 0; r < 6; r++){
        for(int c = 0; c < 6; c++){
          System.out.print(checkColumn(arr, c, r) + " ");
        }
        System.out.println();
      }
    }

    find(arr);

    while(hasToMove(temp)){
      find(arr);
      score += getScore(temp);
      move(temp);
      asign(arr,temp);
      find(arr);
      Main.getR().sleep(10);
    }

    return score;
  }

  public static int checkStep(char[][] arr){ 
    int score = 0;

    find(arr);
    score += getScore(temp);
    move(temp);
    asign(arr,temp);
    find(arr);


    return score;
  }

  private static void asign(char[][] arr, char[][] temp){
    for(int i = 0; i < arr.length; i++){
      for(int a = 0; a < arr[i].length; a++){
        arr[i][a] = temp[i][a];
      }
    }
  }

  private static void find(char[][] arr){
    for(int r = 0; r < 6; r++){
      for(int c = 0; c < 6; c++){
        rowIndex[r][c] = checkRow(arr[r], c);
        columnIndex[r][c] = checkColumn(arr, c, r);
      }
    }
    
    for(int r = 0; r < 6; r++){
      for(int c = 0; c < 6; c++){
        if(rowIndex[r][c] >= c+2){
          for(int i = c; i <= rowIndex[r][c]; i++){temp[r][i] = '/';}
        }
        if(columnIndex[r][c] >= r+2){
          for(int i = r; i <= columnIndex[r][c]; i++){temp[i][c] = '/';}
        }
      }
    }
    
  }



  private static void move(char[][] arr){
    for(int c = 0; c < 6; c++){
      moveColumn(c, arr);
    }
  }
  
  private static void moveColumn(int c, char[][] arr){
    for(int i = 5; i > -1; i--){
      if(arr[i][c] == '/' && !((i-1) < 0)){
        arr[i][c] = arr[i-1][c];
        arr[i-1][c] = '/';
      }else if(arr[i][c] == '/' && i == 0){
        Random ran = new Random();
        int temp = ran.nextInt(4);
        
        if(temp == 0){ arr[i][c] = 'R'; } 
        else if(temp == 1){ arr[i][c] = 'G'; } 
        else if(temp == 2){ arr[i][c] = 'B'; } 
        else if(temp == 3){ arr[i][c] = 'P'; } 
      }
    }
  }

  public static boolean hasToMove(char[][] arr){
    for(int i = 0; i < arr.length; i++){
      for(int a = 0; a < arr[i].length; a++){
        if(arr[i][a] == '/'){
          return true;
        }
      }
    }
    return false;
  }


  private static int checkRow(char[] arr, int ind){
    if((ind+1 != 6) && arr[ind] == arr[ind+1]){
      return checkRow(arr, ind+1);
    }
    return ind;
  }
  
  private static int checkColumn(char[][] arr, int c, int ind){
    if((ind+1 != 6) && arr[ind][c] == arr[ind+1][c]){
      return checkColumn(arr, c, ind+1);
    }
    return ind;
  }

  public static int getScore(char[][] arr){ 
    int count = 0;
    for(int i = 0; i < arr.length; i++){
      for(int a = 0; a < arr[i].length; a++){
        if(arr[i][a] == '/'){ 
          count++;
        }
      }
    }
    return count;
  }

}
