import java.io.*;
import java.util.Scanner;

import javax.swing.SwingConstants;
public class DiskTest implements Runnable{

	File file;
	PrintWriter wr;
	private double startTime, endTime, difference;
	//in terms of bytes
	private static double data=1024*1024; //1 mebibyte
	Scanner sc=new Scanner(System.in);
	char twoBytes=0x0000; //char is 2 bytes
	double numBytes=0;
	boolean play=true;
	public void run() {
		while(play) {
		getNumber();
		while(numBytes<=0) {
			System.out.println("Number must be greater than 0!!!");
			getNumber();
			
		}
		try {
			file=new File("Temp");
		wr=new PrintWriter(file);
		System.out.println("Test is starting...");
		
		startTime=System.currentTimeMillis();
		for(int i =0; i< (data/2)*numBytes; i++) {
			wr.print(twoBytes);
		}
		endTime=System.currentTimeMillis();
		difference=(endTime-startTime)/1000; //diff in seconds
		double speed=(data/(1024*1024))/difference*numBytes;
		System.out.println("It took "+ difference+" seconds to write "+(data/(1024*1024))*numBytes+" mebibytes");
		System.out.println("Your Hard Disk speed is "+ speed +" MB/sec");
		file.delete();
		wr.close();
		
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		}
		
		
	}
	
	public void getNumber() {
		System.out.print("Please enter the number of mebibytes to test with: ");
		numBytes=sc.nextDouble();
	}
	
	public static void main(String[] args) {
		new Thread(new DiskTest()).start();
	}
	
	
}
