package credit_cards;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.DecimalFormat;


public class CsvFileReader implements IReadingStrategy {
	
	@Override
	public void read(String filename, String outputfilename)  {
		// TODO Auto-generated method stub
		String line = "";  
		String splitBy = ",";  
		StringBuilder sb = new StringBuilder();
    	sb.append("CardNumber");
    	sb.append(',');
    	sb.append("CardType");
    	sb.append(',');
    	sb.append("Error");
    	sb.append('\n');
		try   
		{  
		//parsing a CSV file into BufferedReader class constructor  
		BufferedReader br = new BufferedReader(new FileReader(filename));  
		br.readLine();
		DecimalFormat formatter=new DecimalFormat("0");
		while ((line = br.readLine()) != null)   //returns a Boolean value  
		{ 
			String[] data = line.split(splitBy);
			String cardnumber=data[0];
			String date=data[1];
			String cardholder=data[2];
			
			
			String cardno;
			

			if(cardnumber.contains("E+"))
			{
				
				cardno=formatter.format(Double.parseDouble(cardnumber));
				
			}
			else 
			{
				cardno=cardnumber;
			}
			
			Factory factory = new Factory();
	        ICreditCard creditcard = factory.getCard(cardno);
	        creditcard.create(cardno,date,cardholder);
	        sb.append(cardno);
        	sb.append(',');
	    	sb.append(creditcard.getClass().getSimpleName());
	    	sb.append(',');
	    	if(creditcard.getClass().getSimpleName().equals("Invalid"))
	    	{
	    		sb.append("InvalidCardNumber");
	    	}
	    	else
	    	{
	    		sb.append("None");
	    	}
	    	sb.append('\n');
	    	  
		}  
		}   
		catch (IOException e)   
		{  
		e.printStackTrace();  
		}  
	              
	    String outputwithextension= outputfilename+".csv";    	
		String str = sb.toString();
		try (PrintWriter writer = new PrintWriter(new File(outputwithextension))) {

	        

	        writer.write(str);

	        System.out.println("Done creating CSV File!");

	      } catch (FileNotFoundException e) {
	        System.out.println(e.getMessage());
	      }
	    
	}

}
