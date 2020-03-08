package com.mine.tdddemo;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class AccountTest {

	/*@Test
	public void test() {
		fail("Not yet implemented");
	}*/
	
	@Test
	public void defaultAccountCreationTest() {
		Account account = new Account();
		int idlenght = account.getId().length();
		assertThat(idlenght, equalTo(6));
		boolean status = account.getStatus();
		assertThat(status,equalTo(true));
		Zone accZone = account.getZone();
		assertThat(accZone, equalTo(Zone.ZONE_1));
		double accBalance = account.getBalance();
		assertThat(accBalance,equalTo(0.00));
	}

}
