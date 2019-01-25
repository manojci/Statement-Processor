package com.banking.rb.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

import com.banking.rb.constants.AppConstants;
import com.banking.rb.vo.Statement;

/**
 * @author Christopher Statement Processor Utility
 */
public class StatementProcessorUtility {

	/**
	 * Get Buffered Reader for the csv file
	 */
	public static BufferedReader getBufferedReader(String fileName) {
		InputStream inputFS = StatementProcessorUtility.class.getClassLoader().getResourceAsStream(fileName);
		return new BufferedReader(new InputStreamReader(inputFS));
	}

	/**
	 * Format decimal places for the amount field
	 */
	public static Double formatDecimalPlaces(Double value) {
		return Double.valueOf(String.format(AppConstants.FLOAT_TWO_PLACES, value));
	}

	/**
	 * Validate the amount field and filter the results
	 */
	public static List<Statement> validate(List<Statement> statments) {
		return statments.stream().filter(statement -> {
			Double sum = StatementProcessorUtility
					.formatDecimalPlaces(Double.sum(statement.getStartBalance(), statement.getMutation()));
			return Double.compare(sum, statement.getEndBalance()) != 0;
		}).map(statement -> {
			statement.setDescription(AppConstants.DESCRIPTION_MESSAGE);
			return statement;
		}).collect(Collectors.collectingAndThen(
				Collectors.toCollection(() -> new TreeSet<>(Comparator.comparingLong(Statement::getReferenceNumber))),
				ArrayList::new));
	}
}
