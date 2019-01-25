package com.banking.rb.controller;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;

import com.banking.rb.BaseTest;
import com.banking.rb.service.CSVParserServiceImpl;
import com.banking.rb.service.FileParserService;
import com.banking.rb.service.XMLParserServiceImpl;
import com.banking.rb.util.StatementProcessorUtility;
import com.banking.rb.vo.ErrorResponse;
import com.banking.rb.vo.RequestObject;
import com.banking.rb.vo.Statement;

/**
 * @author Christopher Test for Statement Processor Controller
 */
public class StatementProcessorControllerTest extends BaseTest {

	FileParserService csvToStatementService = Mockito.mock(CSVParserServiceImpl.class);
	FileParserService xmlToStatementService = Mockito.mock(XMLParserServiceImpl.class);
	@InjectMocks
	StatementProcessorController statementProcessorController;

	/**
	 * Test method for process file - csv check for different file type
	 * 
	 * @throws Exception
	 */
	@Test
	public void testProcessFile() throws Exception {
		RequestObject ro = new RequestObject();
		ro.setFileName("reports");
		ro.setFileType("csvw");
		ResponseEntity<Object> response = statementProcessorController.processFile(ro);
		Assert.assertEquals(400, ((ErrorResponse) response.getBody()).getStatusCode());
	}

	/**
	 * Test method for process file - csv happy path for csv file
	 * 
	 * @throws Exception
	 */
	@Test
	public void testProcessFileCase2() throws Exception {
		List<Statement> list = new ArrayList<>();
		list.add(new Statement());
		RequestObject ro = new RequestObject();
		ro.setFileName("records");
		ro.setFileType("csv");
		when(csvToStatementService.fileParser(ro.getFile())).thenReturn(list);
		when(StatementProcessorUtility.validate(list)).thenReturn(list);
		ResponseEntity<Object> response = statementProcessorController.processFile(ro);
		Assert.assertEquals(200, response.getStatusCodeValue());
		@SuppressWarnings("unchecked")
		List<Statement> data = (List<Statement>) response.getBody();
		Assert.assertEquals(1, data.size());
	}

	/**
	 * Test method for process file - csv Throws null pointer exception when file is
	 * not found in classpath location
	 * 
	 * @throws Exception
	 */
	@Test
	public void testProcessFileCase3() throws Exception {
		RequestObject ro = new RequestObject();
		ro.setFileName("report");
		ro.setFileType("csv");
		when(csvToStatementService.fileParser(ro.getFile())).thenThrow(NullPointerException.class);
		ResponseEntity<Object> response = statementProcessorController.processFile(ro);
		Assert.assertEquals(500, ((ErrorResponse) response.getBody()).getStatusCode());
	}

	/**
	 * Test method for process file - csv Throws Number format exception when amount
	 * field is mapped as string
	 * 
	 * @throws Exception
	 */
	@Test
	public void testProcessFileCase4() throws Exception {
		RequestObject ro = new RequestObject();
		ro.setFileName("test");
		ro.setFileType("csv");
		when(csvToStatementService.fileParser(ro.getFile())).thenThrow(NumberFormatException.class);
		ResponseEntity<Object> response = statementProcessorController.processFile(ro);
		Assert.assertEquals(500, ((ErrorResponse) response.getBody()).getStatusCode());
	}

	/**
	 * Test method for process file - xml happy path for xml file
	 * 
	 * @throws Exception
	 */
	@Test
	public void testProcessFileCase5() throws Exception {
		List<Statement> list = new ArrayList<>();
		list.add(new Statement());
		RequestObject ro = new RequestObject();
		ro.setFileName("records");
		ro.setFileType("xml");
		when(xmlToStatementService.fileParser(ro.getFile())).thenReturn(list);
		when(StatementProcessorUtility.validate(list)).thenReturn(list);
		ResponseEntity<Object> response = statementProcessorController.processFile(ro);
		Assert.assertEquals(200, response.getStatusCodeValue());
		@SuppressWarnings("unchecked")
		List<Statement> data = (List<Statement>) response.getBody();
		Assert.assertEquals(1, data.size());
	}

	/**
	 * Test method for process file - xml Throws null pointer exception when file is
	 * not found in classpath location
	 * 
	 * @throws Exception
	 */
	@Test
	public void testProcessFileCase6() throws Exception {
		RequestObject ro = new RequestObject();
		ro.setFileName("report");
		ro.setFileType("xml");
		when(xmlToStatementService.fileParser(ro.getFile())).thenThrow(NullPointerException.class);
		ResponseEntity<Object> response = statementProcessorController.processFile(ro);
		Assert.assertEquals(500, ((ErrorResponse) response.getBody()).getStatusCode());
	}

	/**
	 * Test method for process file - xml Throws Number format exception when amount
	 * field is mapped as string
	 * 
	 * @throws Exception
	 */
	@Test
	public void testProcessFileCase7() throws Exception {
		RequestObject ro = new RequestObject();
		ro.setFileName("test");
		ro.setFileType("xml");
		when(xmlToStatementService.fileParser(ro.getFile())).thenThrow(NumberFormatException.class);
		ResponseEntity<Object> response = statementProcessorController.processFile(ro);
		Assert.assertEquals(500, ((ErrorResponse) response.getBody()).getStatusCode());
	}

	/**
	 * Test method for process file - xml verify err text is thrown when endBalance
	 * has discrepancy
	 * 
	 * @throws Exception
	 */
	@Test
	public void testProcessFileCase8() throws Exception {
		List<Statement> list = new ArrayList<>();
		list.add(new Statement());
		List<Statement> list2 = new ArrayList<>();
		Statement statement = new Statement(123, "Test", "err text", 10.00, -5.00, 4.23);
		list2.add(statement);
		RequestObject ro = new RequestObject();
		ro.setFileName("records");
		ro.setFileType("xml");
		when(xmlToStatementService.fileParser(ro.getFile())).thenReturn(list);
		when(StatementProcessorUtility.validate(list)).thenReturn(list2);
		ResponseEntity<Object> response = statementProcessorController.processFile(ro);
		Assert.assertEquals(200, response.getStatusCodeValue());
		@SuppressWarnings("unchecked")
		List<Statement> data = (List<Statement>) response.getBody();
		Assert.assertEquals(1, data.size());
		Assert.assertEquals("err text", data.get(0).getDescription());
	}

	/**
	 * Test method for process file - csv verify err text is thrown when endBalance
	 * has discrepancy
	 * 
	 * @throws Exception
	 */
	@Test
	public void testProcessFileCase9() throws Exception {
		List<Statement> list = new ArrayList<>();
		list.add(new Statement());
		List<Statement> list2 = new ArrayList<>();
		Statement statement = new Statement(123, "Test", "err text", 10.00, -5.00, 4.23);
		list2.add(statement);
		RequestObject ro = new RequestObject();
		ro.setFileName("records");
		ro.setFileType("csv");
		when(csvToStatementService.fileParser(ro.getFile())).thenReturn(list);
		when(StatementProcessorUtility.validate(list)).thenReturn(list2);
		ResponseEntity<Object> response = statementProcessorController.processFile(ro);
		Assert.assertEquals(200, response.getStatusCodeValue());
		@SuppressWarnings("unchecked")
		List<Statement> data = (List<Statement>) response.getBody();
		Assert.assertEquals(1, data.size());
		Assert.assertEquals("err text", data.get(0).getDescription());
	}
}
