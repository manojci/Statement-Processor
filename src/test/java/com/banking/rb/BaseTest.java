package com.banking.rb;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.banking.rb.util.StatementProcessorUtility;

/**
 * @author Christopher Base class for Test files
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(StatementProcessorUtility.class)
public class BaseTest {

	/**
	 * Method to initialize before run of every method
	 */
	@Before
	public void init() {
		PowerMockito.mockStatic(StatementProcessorUtility.class);
	}
	
	@Test
	public void test() {
		
	}
}
