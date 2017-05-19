import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import gnu.io.CommPortIdentifier; 
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent; 
import gnu.io.SerialPortEventListener; 
import java.util.Enumeration;


public class SerialTest implements SerialPortEventListener {
SerialPort serialPort;
    /** The port we're normally going to use. */
private static final String PORT_NAMES[] = {"COM1", "COM2", "COM3", 
		"COM4", "COM5", "COM6", "COM7", "COM8",
};
private BufferedReader input;
private OutputStream output;
private static final int TIME_OUT = 2000;
private static final int DATA_RATE = 115200;

public void initialize() {
    CommPortIdentifier portId = null;
    Enumeration portEnum = CommPortIdentifier.getPortIdentifiers();

    //First, Find an instance of serial port as set in PORT_NAMES.
    while (portEnum.hasMoreElements()) {
    	System.out.println("has more elemets");
        CommPortIdentifier currPortId = (CommPortIdentifier) portEnum.nextElement();
        System.out.println("a" + currPortId.getName());
        for (String portName : PORT_NAMES) {
            if (currPortId.getName().equals(portName)) {
                portId = currPortId;
                System.out.println("initilalize found a port: " + currPortId.getName());
                break;
            }
        }
    }
    if (portId == null) {
        System.out.println("Could not find COM port.");
        return;
    }

    try {
        serialPort = (SerialPort) portId.open(this.getClass().getName(),
                TIME_OUT);
        serialPort.setSerialPortParams(DATA_RATE,
                SerialPort.DATABITS_8,
                SerialPort.STOPBITS_1,
                SerialPort.PARITY_NONE);

        // open the streams
        input = new BufferedReader(new InputStreamReader(serialPort.getInputStream()));
        output = serialPort.getOutputStream();

        serialPort.addEventListener(this);
        serialPort.notifyOnDataAvailable(true);
    } catch (Exception e) {
        System.err.println(e.toString());
    }
}


public synchronized void close() {
    if (serialPort != null) {
        serialPort.removeEventListener();
        serialPort.close();
        System.out.println("closing");
    }
}

public synchronized void serialEvent(SerialPortEvent oEvent) {
	System.out.println("calling serialEvent");
    if (oEvent.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
        try {
            String inputLine=null;
            if (input.ready()) {
                inputLine = input.readLine();
                            System.out.println(inputLine);
            }

        } catch (Exception e) {
            System.err.println(e.toString());
        }
    }
    // Ignore all the other eventTypes, but you should consider the other ones.
}

//public static void main(String[] args) throws Exception {
//    SerialTest main = new SerialTest();
//    main.initialize();
//    Thread t=new Thread() {
//        public void run() {
//            //the following line will keep this app alive for 1000    seconds,
//            //waiting for events to occur and responding to them    (printing incoming messages to console).
//            try {Thread.sleep(1000000);} catch (InterruptedException    ie) {}
//        }
//    };
//    t.start();
//    System.out.println("Started");
//}

public static void main(String[] args){
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
    while ((s = stdInput.readLine()) != null) {
        System.out.println(s);
        
    }
    
    // read any errors from the attempted command
    System.out.println("Here is the standard error of the command (if any):\n");
    while ((s = stdError.readLine()) != null) {
        System.out.println(s);
    }
    
    System.exit(0);
}
catch (IOException e) {
    System.out.println("exception happened - here's what I know: ");
    e.printStackTrace();
    System.exit(-1);
}
}


}