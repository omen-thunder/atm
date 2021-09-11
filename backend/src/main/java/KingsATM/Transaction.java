package KingsATM;

import java.time.LocalDateTime;

public class Transaction {
	private long transactionNumber;
	private int clientNumber;
	private int accountNumber;
	private int cardNumber;
	private LocalDateTime datetime;
	private long amount;
	private String status;
	private String comment;

	public Transaction(long transactionNumber, int clientNumber, int accountNumber, int cardNumber,
			LocalDateTime datetime, long amount, String status, String comment) {
		this.transactionNumber = transactionNumber;
		this.clientNumber = clientNumber;
		this.accountNumber = accountNumber;
		this.cardNumber = cardNumber;
		this.datetime = datetime;
		this.amount = amount;
		this.status = status;
		this.comment = comment;
	}

	public int getClientNumber() {
		return clientNumber;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public int getCardNumber() {
		return cardNumber;
	}

	public LocalDateTime getDateTime() {
		return datetime;
	}

	public long getAmount() {
		return amount;
	}

	public String getStatus() {
		return status;
	}

	public String getComment() {
		return comment;
	}
}
