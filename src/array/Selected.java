package array;

public class Selected{

  boolean[][] selected;
  boolean sele = false;;
  boolean mode = true;

  public Selected(){ 
    selected = new boolean[6][6];

    for(int i = 0; i < selected.length; i++){
      for(int a = 0; a < selected[i].length; a++){
        selected[i][a] = false;
      }
    }

    selected[2][2] = true;
  }

  public void moveSelected(char dir){ 

    if(dir == 'n'){ 
      if(getSelectedAxis()[1] != 0){ 
        int[] temp = getSelectedAxis();
        selected[temp[0]][temp[1]] = false;
        selected[temp[0]][temp[1]-1] = true;
      }
    }else if(dir == 'e'){ 
      if(getSelectedAxis()[0] != selected[0].length-1){ 
        int[] temp = getSelectedAxis();
        selected[temp[0]][temp[1]] = false;
        selected[temp[0]+1][temp[1]] = true;
      }
    }else if(dir == 's'){ 
      if(getSelectedAxis()[1] != selected.length-1){ 
        int[] temp = getSelectedAxis();
        selected[temp[0]][temp[1]] = false;
        selected[temp[0]][temp[1]+1] = true;
      }
    }else if(dir == 'w'){ 
      if(getSelectedAxis()[0] != 0){ 
        int[] temp = getSelectedAxis();
        selected[temp[0]][temp[1]] = false;
        selected[temp[0]-1][temp[1]] = true;
      }
    }
  }

  public boolean[][] getSelected(){ 
    return selected;
  }

  public int[] getSelectedAxis(){ 
    for(int i = 0; i < selected.length; i++){
      for(int a = 0; a < selected[i].length; a++){
        if(selected[i][a] == true){ 
          int[] bruh = new int[2];
          bruh[0] = i;
          bruh[1] = a;
          return bruh;
        }
      }
    }

    int[] bruh = new int[2];
    bruh[0] = -1;
    bruh[1] = -1;

    return bruh;
  }

  public void select(){ 
    if(sele){ sele = false; }
    else{ sele = true; }

    if(mode){ mode = false; }
    else{ mode = true; }
  }

  public boolean mode(){ 
    return mode;
  }

  public boolean getSele(){ 
    return sele;
  }

}
