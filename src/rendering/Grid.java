package rendering;

import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.TextColor;
import java.io.IOException;
import array.ArrayGen;
import array.Logic;
import array.Selected;

import java.io.*;
import java.util.*;

public class Grid{
  
  private static final char VLINE = '│';
  private static final char VLINE1 = '║';

  private static final char TRLINE = '┐';
  private static final char TRLINE1 = '╗';
  
  private static final char BRLINE = '┘';
  private static final char BRLINE1 = '╝';

  private static final char BLLINE = '└';
  private static final char BLLINE1 = '╚';

  private static final char TLLINE = '┌';
  private static final char TLLINE1 = '╔';

  private static final char HLINE = '─';
  private static final char HLINE1 = '═';

  private static final char[] charArr1 = {VLINE1, TRLINE1, BRLINE1, BLLINE1, TLLINE1, HLINE1};
  private static final char[] charArr = {VLINE, TRLINE, BRLINE, BLLINE, TLLINE, HLINE};

  private static Terminal t;
  private static Selected sel;
  private static char[][] temp;
  private static int count = 0;

  public static void init(Terminal term, int w, int h) throws IOException{
    t = term;
    
    addChar(0,0, charArr[4]);
    addChar(0, h, charArr[3]);
    addChar(w, 0, charArr[1]);
    addChar(w, h, charArr[2]);
    
    for(int i = 1; i < h; i++){
      addChar(0, i, charArr[0]);
      addChar(w, i, charArr[0]);
    }

    for(int i = 1; i < w; i++){
      addChar(i, 0, charArr[5]);
      addChar(i, h, charArr[5]);
    }
    
    ArrayGen ag = new ArrayGen();
    temp = ag.getArr();
    int no = Logic.check(temp, false);

    renderArr(temp);
    sel = new Selected();
    renderSelected(sel.getSelected(), temp);
    renderScore(count);
  }

  private static void renderChar(char render) throws IOException{
    for(int x = 0; x < 6; x++){
      for(int y = 0; y < 6; y++){
        addChar((x*4)+1, (y*2)+1, render);
        addChar((x*4)+2, (y*2)+1, render);
        addChar((x*4)+3, (y*2)+1, render);
      }
    }
  }

  public static void renderArr(char[][] arr) throws IOException{
    for(int x = 0; x < 6; x++){
      for(int y = 0; y < 6; y++){
        t.setForegroundColor(TextColor.ANSI.DEFAULT);

        if(arr[x][y] == 'R'){ t.setBackgroundColor(TextColor.ANSI.RED); }
        else if(arr[x][y] == 'G'){ t.setBackgroundColor(TextColor.ANSI.GREEN); }
        else if(arr[x][y] == 'B'){ t.setBackgroundColor(TextColor.ANSI.BLUE); }
        else if(arr[x][y] == 'P'){ t.setBackgroundColor(TextColor.ANSI.MAGENTA); }

        addChar((x*4)+1, (y*2)+1, arr[x][y]);
        addChar((x*4)+2, (y*2)+1, arr[x][y]);
        addChar((x*4)+3, (y*2)+1, arr[x][y]);
      }
    }
  }
  private static void addChar(int x, int y, char a) throws IOException{
    t.setCursorPosition(x,y);
    t.putCharacter(a);
    t.flush();
  }

  public static void renderSelected(boolean[][] sel, char[][] arr) throws IOException{ 
    for(int x = 0; x < 6; x++){
      for(int y = 0; y < 6; y++){
        if(sel[x][y] == true){ 
          t.setBackgroundColor(TextColor.ANSI.BLACK);

          if(arr[x][y] == 'R'){ t.setForegroundColor(TextColor.ANSI.RED); }
          else if(arr[x][y] == 'G'){ t.setForegroundColor(TextColor.ANSI.GREEN); }
          else if(arr[x][y] == 'B'){ t.setForegroundColor(TextColor.ANSI.BLUE); }
          else if(arr[x][y] == 'P'){ t.setForegroundColor(TextColor.ANSI.MAGENTA); }

          addChar((x*4)+1, (y*2)+1, arr[x][y]);
          addChar((x*4)+2, (y*2)+1, arr[x][y]);
          addChar((x*4)+3, (y*2)+1, arr[x][y]);
        }
      }
    }
  }

  public static void renderSelect(boolean[][] sel, char[][] arr, boolean sele) throws IOException{ 
    for(int x = 0; x < 6; x++){
      for(int y = 0; y < 6; y++){
        if((sel[x][y] == true)&&(sele)){ 
          t.setBackgroundColor(TextColor.ANSI.YELLOW);
          t.setForegroundColor(TextColor.ANSI.BLACK);

          //if(arr[x][y] == 'R'){ t.setForegroundColor(TextColor.ANSI.RED); }
          //else if(arr[x][y] == 'G'){ t.setForegroundColor(TextColor.ANSI.GREEN); }
          //else if(arr[x][y] == 'B'){ t.setForegroundColor(TextColor.ANSI.BLUE); }
          //else if(arr[x][y] == 'P'){ t.setForegroundColor(TextColor.ANSI.MAGENTA); }
        
          addChar((x*4)+1, (y*2)+1, arr[x][y]);
          addChar((x*4)+2, (y*2)+1, arr[x][y]);
          addChar((x*4)+3, (y*2)+1, arr[x][y]);
        }
      }
    }
  }

  public static Selected getSelected(){ 
    return sel;
  }

  public static void renderScore(int s) throws IOException{ 
    t.setBackgroundColor(TextColor.ANSI.DEFAULT);
    t.setForegroundColor(TextColor.ANSI.DEFAULT);
    String b = "Score";
    int bb = 0;
    for(int i = 0; i < b.length(); i++){
      addChar(i, 13, b.charAt(i));
      bb = i;
    }
    String bbb = " "+s;
    for(int i = 0; i < bbb.length(); i++){
      addChar(i+bb+1, 13, bbb.charAt(i));
    }
  }

  public static void render() throws IOException{ 
    renderArr(temp);
    renderSelected(sel.getSelected(), temp);
    renderSelect(sel.getSelected(), temp, sel.getSele());
    renderScore(count);
  }

  public static void shift(char dir){ 
    if(dir == 'n'){ 
      if(sel.getSelectedAxis()[1] != 0){ 
        int[] t = sel.getSelectedAxis();

        char or = temp[t[0]][t[1]];
        char ro = temp[t[0]][t[1]-1];
        
        temp[t[0]][t[1]] = ro;
        temp[t[0]][t[1]-1] = or;
        
        count += Logic.check(temp, true);
      }
    }else if(dir == 'e'){ 
      if(sel.getSelectedAxis()[0] != sel.getSelected()[0].length-1){ 
        int[] t = sel.getSelectedAxis();

        char or = temp[t[0]][t[1]];
        char ro = temp[t[0]+1][t[1]];

        temp[t[0]][t[1]] = ro;
        temp[t[0]+1][t[1]] = or;

        count += Logic.check(temp, true);
      }
    }else if(dir == 's'){ 
      if(sel.getSelectedAxis()[1] != sel.getSelected().length-1){ 
        int[] t = sel.getSelectedAxis();

        char or = temp[t[0]][t[1]];
        char ro = temp[t[0]][t[1]+1];

        temp[t[0]][t[1]] = ro;
        temp[t[0]][t[1]+1] = or;

        count += Logic.check(temp, true);
      }
    }else if(dir == 'w'){ 
      if(sel.getSelectedAxis()[0] != 0){ 
        int[] t = sel.getSelectedAxis();

        char or = temp[t[0]][t[1]];
        char ro = temp[t[0]-1][t[1]];

        temp[t[0]][t[1]] = ro;
        temp[t[0]-1][t[1]] = or;

        count += Logic.check(temp, true);
      }
    }
    try{render();}catch(Exception e){}
  } 

  public static char[][] getTemp(){ 
    return temp;
  }

}
