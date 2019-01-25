package com.banking.rb.service;

import static org.mockito.Mockito.when;

import java.io.BufferedReader;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;

import com.banking.rb.BaseTest;
import com.banking.rb.util.StatementProcessorUtility;
import com.banking.rb.vo.Statement;

/**
 * @author Christopher Test for CSV parser service implementation
 */
public class CSVParserServiceImplTest extends BaseTest {

	BufferedReader br = Mockito.mock(BufferedReader.class);
	@InjectMocks
	CSVParserServiceImpl csvParserServiceImpl;

	/**
	 * Test method for file parser Happy path
	 */
	@Test
	public void testFileParser() throws Exception {
		when(StatementProcessorUtility.getBufferedReader("records.csv")).thenReturn(br);
		List<Statement> result = csvParserServiceImpl.fileParser("records.csv");
		Assert.assertNotNull(result);
	}

	/**
	 * Test method for file parser Throws null pointer exception when file is not
	 * found
	 */
	@Test(expected = NullPointerException.class)
	public void testFileParserCase2() throws Exception {
		when(StatementProcessorUtility.getBufferedReader("records.csv")).thenThrow(NullPointerException.class);
		csvParserServiceImpl.fileParser("records.csv");
	}
}
