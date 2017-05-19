import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;



public class SerialTest implements Runnable {
	
	private String reading;
	
	public SerialTest() {
		super();
	}

public static void main(String[] args){
	
}

@Override
public void run() {
	// TODO Auto-generated method stub
	Process p;
	String s = null;
	try {
	p = Runtime.getRuntime().exec("python ./../serialtest.py");
    
    BufferedReader stdInput = new BufferedReader(new 
         InputStreamReader(p.getInputStream()));

    BufferedReader stdError = new BufferedReader(new 
         InputStreamReader(p.getErrorStream()));

    // read the output from the command
    System.out.println("Here is the standard output of the command:\n");
    int count = 0;
    while ((s = stdInput.readLine()) != null) {

    		reading = s;
    		 System.out.println("SO is + " + s);
    		try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				System.out.println("well f");
			}

    	//System.out.println(count);
        
    }
    
    // read any errors from the attempted command
    
    System.out.println("Here is the standard error of the command (if any):\n");
    while ((s = stdError.readLine()) != null) {
        //System.out.println(s);
    }
    
    System.exit(0);
}
catch (IOException e) {
    System.out.println("exception happened - here's what I know: ");
    e.printStackTrace();
    System.exit(-1);
}
}

public String getReading() {
	return reading;
}


}