package com.banking.rb.vo;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

import com.banking.rb.util.StatementProcessorUtility;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * @author Christopher Statement
 */
@AllArgsConstructor
@NoArgsConstructor
public class Statement {

	private long referenceNumber;

	private String accountNumber;

	private String description;
	private Double startBalance;
	private Double mutation;
	private Double endBalance;

	@Override
	public String toString() {
		return "Statement [referenceNumber=" + referenceNumber + ", accountNumber=" + accountNumber + ", description="
				+ description + ", startBalance=" + startBalance + ", mutatuion=" + mutation + ", endBalance="
				+ endBalance + "]";
	}

	public Double getStartBalance() {
		return StatementProcessorUtility.formatDecimalPlaces(startBalance);
	}

	public Double getMutation() {
		return StatementProcessorUtility.formatDecimalPlaces(mutation);
	}

	public Double getEndBalance() {
		return StatementProcessorUtility.formatDecimalPlaces(endBalance);
	}

	public long getReferenceNumber() {
		return referenceNumber;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public String getDescription() {
		return description;
	}

	@XmlAttribute(name = "reference")
	public void setReferenceNumber(long referenceNumber) {
		this.referenceNumber = referenceNumber;
	}

	@XmlElement(name = "accountNumber")
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	@XmlElement(name = "description")
	public void setDescription(String description) {
		this.description = description;
	}

	@XmlElement(name = "startBalance")
	public void setStartBalance(Double startBalance) {
		this.startBalance = startBalance;
	}

	@XmlElement(name = "mutation")
	public void setMutation(Double mutation) {
		this.mutation = mutation;
	}

	@XmlElement(name = "endBalance")
	public void setEndBalance(Double endBalance) {
		this.endBalance = endBalance;
	}

}
