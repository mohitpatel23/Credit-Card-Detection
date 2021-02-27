package credit_cards;
import java.io.FileNotFoundException;
import java.util.Scanner; 
public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		Scanner myObj = new Scanner(System.in);
		System.out.println("Enter input file name with extension");

	    String inputfilename = myObj.nextLine();  // Read user input
	    System.out.println("Username is: " + inputfilename); 
	    System.out.println("Enter output file name");

	    String outputfilename = myObj.nextLine();  // Read user input
	    System.out.println("Username is: " + outputfilename); 
	    
	    
	    
	    IReadingStrategy strategy;
	    if(inputfilename.charAt(inputfilename.length()-1)=='v')
	    {
	    	strategy=new CsvFileReader();
	    	strategy.read(inputfilename,outputfilename);
	    }
	    else if(inputfilename.charAt(inputfilename.length()-1)=='n')
	    {
	    	strategy=new JsonFileReader();
	    	strategy.read(inputfilename,outputfilename);
	    }
	    else if(inputfilename.charAt(inputfilename.length()-1)=='l')
	    {
	    	strategy=new XmlFileReader();
	    	strategy.read(inputfilename,outputfilename);
	    }
	    else System.out.println("File type support not available");
	    

	}

}
