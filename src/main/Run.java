package main;

import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;

import java.io.IOException;
import java.io.*;
import java.util.*;
import java.awt.event.*;
import java.lang.Thread;

import rendering.Grid;
import array.*;

public class Run implements Runnable{
  
  private static Terminal term;

  public void run(){
    KeyStroke key = null;
    try{

      term = new DefaultTerminalFactory().createTerminal();
      term.enterPrivateMode();
      term.setCursorVisible(false);

      Grid.init(term, 24, 12);

      while(true) {
        key = term.pollInput();
        if(key != null){
          if(key.getKeyType() == KeyType.Escape){break;}
          if(key.getCharacter() == 'q'){break;}
          if(key.getCharacter() == 'r'){
            reload();
          }

          if(key.getCharacter() == 'w'){ 
            if(Grid.getSelected().mode()){ Grid.getSelected().moveSelected('n'); update();}
            if(!(Grid.getSelected().mode())){ Grid.shift('n'); Grid.getSelected().select(); update(); }
          }
          if(key.getCharacter() == 'a'){ 
            if(Grid.getSelected().mode()){ Grid.getSelected().moveSelected('w'); update();}
            if(!(Grid.getSelected().mode())){ Grid.shift('w'); Grid.getSelected().select(); update(); }
          }
          if(key.getCharacter() == 's'){ 
            if(Grid.getSelected().mode()){ Grid.getSelected().moveSelected('s'); update();}
            if(!(Grid.getSelected().mode())){ Grid.shift('s'); Grid.getSelected().select(); update(); }
          }
          if(key.getCharacter() == 'd'){ 
            if(Grid.getSelected().mode()){ Grid.getSelected().moveSelected('e'); update();}
            if(!(Grid.getSelected().mode())){ Grid.shift('e'); Grid.getSelected().select(); update(); }
          }
          if(key.getCharacter() == ' '){ Grid.getSelected().select(); update();}
        }
        Thread.sleep(1);
      } 

      term.exitPrivateMode();
    
    }catch(Exception e){
      try{
        
        System.out.println(e);
        Thread.sleep(10000);

      }catch(Exception ee){}
    }
  }

  public void reload(){ 
    try{
      ArrayGen ag = new ArrayGen();
      char[][] temp = ag.getArr();
      int m = Logic.check(temp, true);
      Grid.renderArr(temp);
    }catch(Exception e){
      System.out.println(e);
      try{
        Thread.sleep(1000);
      }catch(Exception ee){}
    }
  }

  public void update(){ 
    try{
      Grid.render();

      while(Logic.hasToMove(Grid.getTemp())){ 

        Logic.checkStep(Grid.getTemp());
        Thread.sleep(100);
      }

    }catch(Exception e){
      System.out.println(e);
      try{Thread.sleep(1000);}catch(Exception ee){}
    }
  }

  public void sleep(int i){ 
    try{
      Thread.sleep(i);
    }catch(Exception e){}
  }

}
