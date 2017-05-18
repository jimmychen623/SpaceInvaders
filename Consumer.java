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
         System.out.printf("in %s run method%n", name);
         while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (print) {
               String output = String.format("From %s: %s%n", name, line);
               System.out.printf(output);
            }
         }
         if (scanner != null) {
            scanner.close();
         }
      }
   }

   private static final String PRODUCER_PATH = "C:/Users/Pete/Documents/Fubar/Java/producer.jar";

   public static void main(String[] args) {
      List<String> command = new ArrayList<>();
      command.add("./UV4.exe ");
      command.add("-b ");
      command.add("../../Users/meghan/Desktop/SpaceInvaders/Lab4.uvprojx");

      try {
         ProcessBuilder pBuilder = new ProcessBuilder(command);
         Process process = pBuilder.start(); 
         StreamGobbler errorGobbler = new StreamGobbler("Error Gobbler", process.getErrorStream(), true);
         StreamGobbler inputGobbler = new StreamGobbler("Input Gobbler", process.getInputStream(), true);

         new Thread(errorGobbler).start();
         new Thread(inputGobbler).start();
         process.waitFor();
      } catch (IOException | InterruptedException e) {
         e.printStackTrace();
      }
   }
}