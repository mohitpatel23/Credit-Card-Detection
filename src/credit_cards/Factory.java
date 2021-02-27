package credit_cards;

public class Factory {
	public ICreditCard getCard(String cardno){
		if(cardno.length()==16 && cardno.charAt(0)=='5' && (cardno.charAt(1)=='5' || cardno.charAt(1)=='4' || cardno.charAt(1)=='3' || cardno.charAt(1)=='2' || cardno.charAt(1)=='1'))
    	{
    		return new MasterCard();
    	}
    	else if((cardno.length()==16 || cardno.length()==13) && cardno.charAt(0)=='4')
    	{
    		return new Visa();
    	}
    	else if(cardno.length()==15 && cardno.charAt(0)=='3' && (cardno.charAt(1)=='4' || cardno.charAt(1)=='7'))
    	{
    		return new AmericanExpress();
    	}
    	else if(cardno.length()==16 && cardno.charAt(0)=='6' && cardno.charAt(1)=='0' && cardno.charAt(2)=='1' && cardno.charAt(3)=='1')
    	{
    		return new Discover();
    	}
    	else
    	{
    		return new Invalid();
    	}
	}
}
