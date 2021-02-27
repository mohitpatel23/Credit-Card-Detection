package credit_cards;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonFileReader implements IReadingStrategy{

	@Override
	public void read(String filename,String outputfilename) {
		JSONParser jsonParser = new JSONParser();
        
        try (FileReader reader = new FileReader(filename))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);
 
            JSONArray employeeList = (JSONArray) obj;
            //System.out.println(employeeList);
            Iterator iterator = employeeList.iterator();
            JSONArray data = new JSONArray();
            while (iterator.hasNext()) {
               //System.out.println(iterator.next());
               JSONObject jsonObject = (JSONObject)iterator.next();
               Long cardnumber = (Long) jsonObject.get("CardNumber");
               String date=(String)jsonObject.get("ExpirationDate");
               String cardholder=(String)jsonObject.get("NameOfCardholder");
               String cardno=String.valueOf(cardnumber);
               Factory factory = new Factory();
   	           ICreditCard creditcard = factory.getCard(cardno);
   	           creditcard.create(cardno,date,cardholder);
   	           
   	           
   	           JSONObject dataobject = new JSONObject(); 
   	           dataobject.put("CardNumber", cardnumber);
   	           dataobject.put("CardType", creditcard.getClass().getSimpleName());
   	        if(creditcard.getClass().getSimpleName().equals("Invalid"))
	    	{
   	        	dataobject.put("Error", "InvalidCardNumber");
   	        	
	    	}
	    	else
	    	{
	    		dataobject.put("Error", "None");
	    	}
   	        	data.add(dataobject);
            
            
            } 
            
            
            
            String outputwithextension=outputfilename+".json";
            try (FileWriter file = new FileWriter(outputwithextension)) {
           	 
                file.write(data.toJSONString());
                file.flush();
                System.out.println("Done creating JSON File");
     
            } catch (IOException e) {
                e.printStackTrace();
            }
            
            
 
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
        
        
        
        
        
        
        
	}


}
