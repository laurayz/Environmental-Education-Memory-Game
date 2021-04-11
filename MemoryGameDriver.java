   import javax.swing.JFrame;
   public class MemoryGameDriver
   {
      public static void main(String[] args)
      {
         JFrame frame = new JFrame("Memory Game!");
         frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
         frame.setUndecorated(true);
         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         frame.setContentPane(new MemoryGame());
         frame.setVisible(true);
      }
   }