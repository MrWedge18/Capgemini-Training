
public class Client {

	public static void main(String[] args) {
		ICICIBank bank = new ICICIBank();
		
		try {
			System.out.println(bank.createAccount(101, 3000));
			System.out.println(bank.createAccount(102, 3000));
		}catch (Exception e) {
			System.out.println("Insufficient balance to open account, must be greater than 500");
		}
		
		try
		{
			System.out.println("Balance = "+bank.withdrawAmount(101, 2000));
		}catch(InvalidAccountNumberException i)
		{
			System.out.println("Invalid account number ");
		}
		catch(InsufficientBalanceException ibe){
			System.out.println("insufficient balance");
		}

	}

}
