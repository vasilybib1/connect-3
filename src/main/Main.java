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
import main.Run;

public class Main{

  private static Terminal term;
  private static Run r;

  public static void main(String[] args) throws IOException{
    r = new Run();
    r.run();
  }

  public static Run getR(){ 
    return r;
  }

    
}
