package credit_cards;

public class Invalid extends CreditCard implements ICreditCard {

	@Override
	public void create(String cardnumber, String date, String cardholder) {
		// TODO Auto-generated method stub
		this.cardnumber=cardnumber;
		this.cardholder=cardholder;
		this.date=date;
		error="InvalidCardNumber";
		
	}

	

}
