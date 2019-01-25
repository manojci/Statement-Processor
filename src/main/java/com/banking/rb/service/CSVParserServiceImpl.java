package com.banking.rb.service;

import java.io.BufferedReader;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.banking.rb.constants.AppConstants;
import com.banking.rb.util.StatementProcessorUtility;
import com.banking.rb.vo.Statement;

/**
 * @author Christopher csv parsing service implementation
 */
@Service(value = "csvparser")
public class CSVParserServiceImpl implements FileParserService {

	/**
	 * csv file parsing implementation
	 * 
	 * @param file name of the file
	 * @return {@link List}
	 */
	@Override
	public List<Statement> fileParser(String file) throws Exception {
		BufferedReader br = StatementProcessorUtility.getBufferedReader(file);
		return br.lines().skip(1).map(val -> {
			String[] statementData = val.split(AppConstants.COMMA_SEPARTOR);
			return new Statement(Long.valueOf(statementData[0]), statementData[1], statementData[2],
					StatementProcessorUtility.formatDecimalPlaces(Double.valueOf(statementData[3])),
					StatementProcessorUtility.formatDecimalPlaces(Double.valueOf(statementData[4])),
					StatementProcessorUtility.formatDecimalPlaces(Double.valueOf(statementData[5])));
		}).collect(Collectors.toList());
	}

}
