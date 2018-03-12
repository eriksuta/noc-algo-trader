package com.capco.noc.algo;

import com.capco.noc.algo.engine.AlgoTradeService;
import com.capco.noc.algo.repository.AccountRepository;
import com.capco.noc.algo.schema.Tick;
import com.capco.noc.algo.schema.Ticker;
import com.capco.noc.algo.schema.Account;
import com.capco.noc.algo.schema.Portfolio;
import com.capco.noc.algo.util.FormatterUtil;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

@SpringBootApplication
public class AlgoTraderApplication {

	//Constants
	public static final String ACCOUNT_ID = UUID.randomUUID().toString();
	private static final String USER_NAME = "johndoe";
	private static final String PASSWORD = "12345";
	public static final Double INITIAL_CASH_BALANCE = 250000.00;

	private AccountRepository accountRepository;

	public static void main(String[] args) {
		SpringApplication.run(AlgoTraderApplication.class, args);
    }

	/*
	* Initialise embedded H2 database with some initial data
	* */
	@Bean
	CommandLineRunner loadData(AlgoTradeService algoTradeService, AccountRepository accountRepository) {
		this.accountRepository = accountRepository;

		return (args) -> {
			//Init the account
			initAccount();
		};
	}

	private void initAccount(){
		//Initialize the dummy account
		Account account = new Account();
		account.setId(ACCOUNT_ID);
		account.setUsername(USER_NAME);
		account.setPassword(PASSWORD);

		//Initialize the portfolio
		Portfolio portfolio = new Portfolio();
		account.setPortfolio(portfolio);
		portfolio.setCurrency("$");
		portfolio.setCashBalance(INITIAL_CASH_BALANCE);

		accountRepository.add(account);
	}

	/*==================================================*/
	/* Helper Methods                                   */
	/*==================================================*/
	private static void printPortfolio(Portfolio portfolio){
		AtomicReference<Double> nlv = new AtomicReference<>(portfolio.getCashBalance());

		System.out.println("========== Portfolio: ==========");
		System.out.println("Cash balance: " + FormatterUtil.formatDouble(portfolio.getCashBalance()));
		System.out.println("Products in portfolio:");
		portfolio.getProducts().forEach(product -> {
				System.out.println(
						product.getTicker().getSymbol() + ": " + product.getPosition() +
						" at " + product.getUnitValue() + " per share. Total: " +
						FormatterUtil.formatDouble(product.getValue())
				);

				nlv.updateAndGet(v -> ((v + product.getValue())));
		});
		System.out.println("Net Liquidation Value: " + FormatterUtil.formatDouble(nlv.get()));
	}
}
