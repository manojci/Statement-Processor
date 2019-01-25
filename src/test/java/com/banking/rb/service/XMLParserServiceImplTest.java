package com.banking.rb.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.InjectMocks;

import com.banking.rb.BaseTest;
import com.banking.rb.vo.Statement;

/**
 * @author Christopher Test for XML parser service implementation
 */
public class XMLParserServiceImplTest extends BaseTest {

	@InjectMocks
	XMLParserServiceImpl xmlParserServiceImpl;

	/**
	 * Test method for file parser Happy path
	 */
	@Test
	public void testFileParser() throws Exception {
		List<Statement> result = xmlParserServiceImpl.fileParser("records.xml");
		Assert.assertNotNull(result);
	}
}
