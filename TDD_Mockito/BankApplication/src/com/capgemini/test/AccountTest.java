package com.capgemini.test;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.capgemini.exceptions.InsufficientBalanceException;
import com.capgemini.exceptions.InsufficientInitialAmountException;
import com.capgemini.exceptions.InvalidAccountNumberException;
import com.capgemini.model.Account;
import com.capgemini.repository.AccountRepository;
import com.capgemini.service.AccountService;
import com.capgemini.service.AccountServiceImpl;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
public class AccountTest {

	AccountService accountService;
	
	@Mock
	AccountRepository accountRepository;
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		accountService = new AccountServiceImpl(accountRepository);
	}

	/*
	 * create account
	 * 1.when the amount is less than 500 then system should throw exception
	 * 2.when the valid info is passed account should be created successfully
	 */
	
	@Test(expected=com.capgemini.exceptions.InsufficientInitialAmountException.class)
	public void whenTheAmountIsLessThan500SystemShouldThrowException() throws InsufficientInitialAmountException
	{
		accountService.createAccount(101, 400);
	}
	
	@Test
	public void whenTheValidInfoIsPassedAccountShouldBeCreatedSuccessfully() throws InsufficientInitialAmountException
	{
		Account account =new Account();
		account.setAccountNumber(101);
		account.setAmount(5000);
		when(accountRepository.save(account)).thenReturn(true);
		assertEquals(account, accountService.createAccount(101, 5000));
	}
	
	@Test(expected=com.capgemini.exceptions.InsufficientBalanceException.class)
	public void whenAmountWithdrawnIsMoreThanBalanceThrowException() throws InsufficientBalanceException, InvalidAccountNumberException
	{
		Account account = new Account();
		account.setAccountNumber(101);
		account.setAmount(3000);
		when(accountRepository.searchAccount(101)).thenReturn(account);
		accountService.withdrawAmount(101, 5000);
	}
	
	@Test(expected=com.capgemini.exceptions.InvalidAccountNumberException.class)
	public void whenWithdrawingFromInvalidAccountThrowException() throws InsufficientBalanceException, InvalidAccountNumberException
	{
		Account account = new Account();
		account.setAccountNumber(102);
		account.setAmount(5000);
		when(accountRepository.searchAccount(101)).thenThrow(new InvalidAccountNumberException());
		accountService.withdrawAmount(101, 2000);
	}
	
	@Test
	public void whenTheValidInfoIsPassedWithdrawAmountShouldReturnNewAccountBalance() throws InsufficientBalanceException, InvalidAccountNumberException
	{
		Account account = new Account();
		account.setAccountNumber(101);
		account.setAmount(5000);
		when(accountRepository.searchAccount(101)).thenReturn(account);
		assertEquals(4000, accountService.withdrawAmount(101, 1000));
	}
	
	@Test(expected=com.capgemini.exceptions.InvalidAccountNumberException.class)
	public void whenDepositingIntoInvalidAccountThrowException() throws InvalidAccountNumberException
	{
		Account account = new Account();
		account.setAccountNumber(102);
		account.setAmount(5000);
		when(accountRepository.searchAccount(101)).thenThrow(new InvalidAccountNumberException());
		accountService.depositAmount(101, 2000);
	}
	
	@Test
	public void whenTheValidInfoIsPassedDepositAmountShouldReturnNewAccountBalance() throws InvalidAccountNumberException
	{
		Account account = new Account();
		account.setAccountNumber(101);
		account.setAmount(5000);
		when(accountRepository.searchAccount(101)).thenReturn(account);
		assertEquals(6000, accountService.depositAmount(101, 1000));
	}

}
