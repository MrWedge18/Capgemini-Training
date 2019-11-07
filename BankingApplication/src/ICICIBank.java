import java.util.LinkedList;

public class ICICIBank {
	
	
	LinkedList<Account> accounts=new LinkedList<>();
	
	
	public String createAccount(int accountNumber,int amount) throws InsufficientOpeningBalanceException
	{
		if (amount < 500)
			throw new InsufficientOpeningBalanceException();
		
		Account account = new Account(accountNumber,amount);
		
		accounts.add(account);
		
		return "account created successfully";
	}
	
	private Account searchAccount(int accountNumber)throws InvalidAccountNumberException
	{
		
		for(Account account : accounts)
		{
			if(account.getAccountNumber()==accountNumber)
			{
				return account;
			}
		}
		throw new InvalidAccountNumberException();
		
	}
	
	public int withdrawAmount(int accountNumber,int amount) throws InvalidAccountNumberException, InsufficientBalanceException
	{
		Account account=searchAccount(accountNumber);
		
		if((account.getAmount()-amount)>=0)
		{
			account.setAmount(account.getAmount()-amount);
			return account.getAmount();
		}
		
		throw new InsufficientBalanceException();
	}
	
	public int depositAmount(int accountNumber, int amount) throws InvalidAccountNumberException {
		Account account = searchAccount(accountNumber);
		if (amount <= 0) {
			System.out.println("Must deposit positive amount");
			return account.getAmount();
		}
		
		account.setAmount(account.getAmount() + amount);
		return account.getAmount();
	}
	
	public int[] fundTransfer(int fromAccountNumber, int toAccountNumber, int amount) throws InvalidAccountNumberException, InsufficientBalanceException{
		Account fromAccount = searchAccount(fromAccountNumber);
		Account toAccount = searchAccount(toAccountNumber);
		
		withdrawAmount(fromAccountNumber, amount);
		depositAmount(toAccountNumber, amount);
		
		int[] ret = new int[2];
		ret[0] = fromAccount.getAmount();
		ret[1] = toAccount.getAmount();
		
		return ret;
		
	}

}
