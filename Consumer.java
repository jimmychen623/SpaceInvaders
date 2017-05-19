import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Consumer {

   static class StreamGobbler implements Runnable {
      private String name;
      private boolean print;
      private Scanner scanner;

      public StreamGobbler(String name, InputStream inputStream, boolean print) {
         this.name = name;
         this.print = print;

         scanner = new Scanner(inputStream);
      }

      @Override
      public void run() {
    	  System.out.println("RUNNING");
         System.out.printf("in %s run method%n", name);
         while (scanner.hasNextLine()) {

             System.out.println("scanner has next line");
            String line = scanner.nextLine();
            System.out.print("line \n");
            if (print) {
               String output = String.format("From %s: %s%n", name, line);
               System.out.printf(output);
            }
         }
         if (scanner != null) {
        	 System.out.println("closed " + name);
        	 try {
				Thread.sleep(6000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
         }
         else {
        	 System.out.println(scanner.nextLine());
         }
      }
   }

   private static final String PRODUCER_PATH = "C:/Users/Pete/Documents/Fubar/Java/producer.jar";

   public static void main(String[] args) {
      List<String> command = new ArrayList<>();
      //command.add(, "-b",  "C/Users/meghan/Desktop/SpaceInvaders/C/Lab4.uvprojx");

      try {
         ProcessBuilder pBuilder = new ProcessBuilder("../../../../Keil_v5/UV4/UV4.exe", "-d", "../SpaceInvaders/C/Lab4.uvprojx", "-x");
         Process process = pBuilder.start(); 
         StreamGobbler errorGobbler = new StreamGobbler("Error Gobbler", process.getErrorStream(), true);
         StreamGobbler inputGobbler = new StreamGobbler("Input Gobbler", process.getInputStream(), true);
         Thread.sleep(8000);
         new Thread(errorGobbler).start();
         new Thread(inputGobbler).start();
         process.waitFor();
      } catch (IOException | InterruptedException e) {
         e.printStackTrace();
      }
   }
}