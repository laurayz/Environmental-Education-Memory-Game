	//Name: Laura Zhang Date: 3/25/21
   import java.awt.*;
   import java.awt.event.*;
   import javax.swing.*;
   import java.util.*;
   public class MemoryGame extends JPanel
   {
      private JButton[][] board;
      private String[] vocab;
      private ImageIcon[] picture;
      private JLabel label;
      private JLabel matches;
      private JButton reset, exit;
      private int[] index, vocabIndex, iconIndex;
      private int count;
      private int match;
      private int index1, index2, type1, type2, myRow1, myCol1, myRow2, myCol2;
      private boolean isAMatch;
      public MemoryGame()
      {
         vocab = new String[]{"Eutrophication", "Runoff", "Air Pollution", "Climate Change", "Deforestation", "Litter"};
         picture = new ImageIcon[]{new ImageIcon("eutrophication.jpg"), new ImageIcon("runoff.jpeg"), new ImageIcon("air pollution.jpeg"), new ImageIcon("climate change.jpeg"), new ImageIcon("deforestation.jpg"), new ImageIcon("litter.jpg")};
         count = 0;
         match = 0;
         isAMatch = false;
         
         setLayout(new BorderLayout());
      
         JPanel north = new JPanel();
         north.setLayout(new FlowLayout());
         north.setBackground(new Color(255, 237, 255));
         add(north, BorderLayout.NORTH);
         label = new JLabel("Welcome to Memory Game!");
         label.setFont(new Font("Serif", Font.BOLD, 50));
         north.add(label);
         matches = new JLabel("Number of matches: 0");
         matches.setFont(new Font("Serif", Font.BOLD, 50));
         north.add(matches);
                  
         JPanel center = new JPanel();
         center.setLayout(new GridLayout(3,4));
         add(center, BorderLayout.CENTER);
         board = new JButton[4][4];
         for(int r = 0; r < 3; r++)
            for(int c = 0; c < 4; c++)
            {
               board[r][c] = new JButton();
               board[r][c].addActionListener(new Handler1(r, c));
               board[r][c].setFont(new Font("Serif", Font.BOLD, 30));
               board[r][c].setBackground(new Color(230, 234, 255));
               center.add(board[r][c]);
            }
         
         JPanel south = new JPanel();
         south.setLayout(new FlowLayout());
         south.setBackground(new Color(227, 246, 255));
         add(south, BorderLayout.SOUTH);
         reset = new JButton("Reset");
         reset.addActionListener(new Handler2());
         reset.setFont(new Font("Serif", Font.BOLD, 30));
         reset.setBackground(new Color(201, 243, 255));
         south.add(reset);
         exit = new JButton("Exit");
         exit.addActionListener(new Handler3());
         exit.setFont(new Font("Serif", Font.BOLD, 30));
         exit.setBackground(new Color(201, 243, 255));
         south.add(exit);

         shuffleCards();
      }
      private void shuffleCards()
      {
         index = new int[12];
         for (int i = 0; i < 12; i++)
            index[i] = i;
         scrambleInt(index);
         
         vocabIndex = new int[6];
         iconIndex = new int[6];
         for (int i = 0; i < 6; i++)
            vocabIndex[i] = index[i];
         for (int i = 6; i < 12; i++)
            iconIndex[i - 6] = index[i];
      }
      public void scrambleInt(int[] array)
      {
         for (int i = 0; i < 50; i++)
            swapInt(array, (int)(Math.random() * array.length), (int)(Math.random() * array.length));
      }
      public void swapInt(int[] array, int a, int b)
      {
         	int temp = array[b];
            array[b] = array[a];
            array[a] = temp;
      }
      private class Handler1 implements ActionListener
      {
         private int myRow, myCol, x;
         public Handler1(int r, int c)
         {
            myRow = r;
            myCol = c;
            x = r*4 + c;
         }
         public void actionPerformed(ActionEvent e)
         {  
            if(count%2 == 0 && isAMatch)
            {
               board[myRow2][myCol2].setEnabled(false);
               board[myRow1][myCol1].setEnabled(false);
            }
            if(count%2 == 0 && !isAMatch)
            {
               board[myRow2][myCol2].setText(null);
               board[myRow2][myCol2].setIcon(null);
               board[myRow1][myCol1].setText(null);
               board[myRow1][myCol1].setIcon(null);
            }
            count ++;
            for(int i = 0; i < 6; i++)
            {
               if(vocabIndex[i] == x)
               {
                  board[myRow][myCol].setText(vocab[i]);
                  if(count%2 == 1)
                  {
                     index1 = i;
                     type1 = 0;
                     myRow1 = myRow;
                     myCol1 = myCol;
                   }
                   else if(count%2 == 0)
                   {
                     index2 = i;
                     type2 = 0;
                   }
                }
               else if(iconIndex[i] == x)
               {
                  board[myRow][myCol].setIcon(picture[i]);
                  if(count%2 == 1)
                  {
                     index1 = i;
                     type1 = 1;
                     myRow1 = myRow;
                     myCol1 = myCol;
                  }
                  else if(count%2 == 0)
                  {
                     index2 = i;
                     type2 = 1;
                  }
               }
            }
            myRow2 = myRow;
            myCol2 = myCol;
            if (count%2 == 0 && type1 != type2 && index1 == index2)
            {
               match++;
               isAMatch = true;
               label.setText("Match!");
            }
            else if (count%2 == 0)
            {
               isAMatch = false;
               label.setText("Not a match!");
            }
            matches.setText("Number of matches: " + match);
            if(match == 6)
            {
               label.setText("Congratulations!");
               board[myRow2][myCol2].setEnabled(false);
               board[myRow1][myCol1].setEnabled(false);
            }
         }
      }
      private class Handler2 implements ActionListener
      {
         public void actionPerformed(ActionEvent e)
         {
            count = 0;
            match = 0;
            isAMatch = false;
            shuffleCards();
            for(int r = 0; r < 3; r++)
               for(int c = 0; c < 4; c++)
               {
                  board[r][c].setText(null);
                  board[r][c].setIcon(null);
                  board[r][c].setEnabled(true);
               }
            label.setText("Welcome to Memory Game!");
            matches.setText("Number of matches: 0");
         }
      }
      private class Handler3 implements ActionListener
      {
         public void actionPerformed(ActionEvent e)
         {
            System.exit(0);
         }
      }
   }