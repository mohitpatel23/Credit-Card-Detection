package credit_cards;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.parsers.DocumentBuilder;  
import org.w3c.dom.Document;  
import org.w3c.dom.NodeList;  
import org.w3c.dom.Node;  
import org.w3c.dom.Element;  
import java.io.File;  

public class XmlFileReader implements IReadingStrategy{

	@Override
	public void read(String filename, String outputfilename) {
		// TODO Auto-generated method stub
		try   
		{  
		//creating a constructor of file class and parsing an XML file  
		File file = new File(filename);  
		//an instance of factory that gives a document builder  
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();  
		//an instance of builder to parse the specified xml file  
		DocumentBuilder db = dbf.newDocumentBuilder();  
		Document doc = db.parse(file);  
		doc.getDocumentElement().normalize();  
		
		
		
		//for writing
		DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
		 
        DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();

        Document document = documentBuilder.newDocument();
        
        
        Element root = document.createElement("root");
        document.appendChild(root);
		
		NodeList nodeList = doc.getElementsByTagName("row");  
		 
		for (int itr = 0; itr < nodeList.getLength(); itr++)   
		{  
		Node node = nodeList.item(itr);  
		 
		if (node.getNodeType() == Node.ELEMENT_NODE)   
		{  
				Element eElement = (Element) node;  
				
				String cardno=eElement.getElementsByTagName("CardNumber").item(0).getTextContent();
				String date=eElement.getElementsByTagName("ExpirationDate").item(0).getTextContent();
				String cardholder=eElement.getElementsByTagName("NameOfCardholder").item(0).getTextContent();
				
				
				Factory factory = new Factory();
		        ICreditCard creditcard = factory.getCard(cardno);
		        creditcard.create(cardno,date,cardholder);
		        
		        
		        Element employee = document.createElement("row");
		        
		        root.appendChild(employee);
		        
		        Element cardnumber = document.createElement("CardNumber");
		        cardnumber.appendChild(document.createTextNode(cardno));
		        employee.appendChild(cardnumber);
		
		        // lastname element
		        Element cardtype = document.createElement("CardType");
		        cardtype.appendChild(document.createTextNode(creditcard.getClass().getSimpleName()));
		        employee.appendChild(cardtype);
		
		        // email element
		        Element error = document.createElement("Error");
		        if(creditcard.getClass().getSimpleName().equals("Invalid"))
		    	{
		        	error.appendChild(document.createTextNode("InvalidCardNumber"));
		    	}
		    	else
		    	{
		    		error.appendChild(document.createTextNode("None"));
		    	}
		        
		        employee.appendChild(error);      
        	
		}  
		}
		
		
		String outputwithextension= outputfilename+".xml";    	
		
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource domSource = new DOMSource(document);
        StreamResult streamResult = new StreamResult(new File(outputwithextension));
        transformer.transform(domSource, streamResult);

        System.out.println("Done creating XML File");
		}   
		catch (Exception e)   
		{  
		e.printStackTrace();  
		}  
		
		
		
	}

}
