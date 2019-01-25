package com.banking.rb.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.springframework.stereotype.Service;

import com.banking.rb.vo.Statement;
import com.banking.rb.vo.Statements;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

/**
 * @author Christopher xml parsing service implementation
 */
@Service(value = "xmlparser")
public class XMLParserServiceImpl implements FileParserService {

	/**
	 * xml file parsing implementation
	 * @param fileName name of the file
	 * @return {@link List}
	 */
	@Override
	public List<Statement> fileParser(String fileName)
			throws JAXBException, JsonParseException, JsonMappingException, IOException {
		File file = new File(
				XMLParserServiceImpl.class.getClassLoader().getResource(fileName).getPath());
		JAXBContext jaxbContext = JAXBContext.newInstance(Statements.class);

		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		Statements statements = (Statements) jaxbUnmarshaller.unmarshal(file);
		return statements.getStatement();
	}

}
