package com.banking.rb.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.banking.rb.constants.AppConstants;
import com.banking.rb.service.FileParserService;
import com.banking.rb.util.StatementProcessorUtility;
import com.banking.rb.vo.ErrorResponse;
import com.banking.rb.vo.FileType;
import com.banking.rb.vo.RequestObject;
import com.banking.rb.vo.Statement;

/**
 * @author Christopher Statement Processor Controller
 */
@RestController
public class StatementProcessorController {

	static Logger logger = Logger.getLogger(StatementProcessorController.class);

	@Autowired
	@Qualifier(value = "csvparser")
	FileParserService csvToStatementService;

	@Autowired
	@Qualifier(value = "xmlparser")
	FileParserService xmlToStatementService;

	/**
	 * Process file based on the file type sent in request body
	 * 
	 * @param requestObject request object
	 * @return {@link ResponseEntity}
	 */
	@RequestMapping(path = "/v1/get-statement", method = RequestMethod.POST)
	public ResponseEntity<Object> processFile(@RequestBody RequestObject requestObject) throws Exception {
		List<Statement> result = null;
		if (!(requestObject.getFileType().equals(FileType.xml.toString()))
				&& !(requestObject.getFileType().equals(FileType.csv.toString()))) {
			ErrorResponse er = new ErrorResponse();
			er.setErrDescription(requestObject.getFile());
			er.setStatusCode(HttpStatus.BAD_REQUEST.value());
			logger.error(AppConstants.FILE_TYPE_NOT_SUPPORTED);
			return new ResponseEntity<Object>(er, HttpStatus.BAD_REQUEST);
		} else {
			try {
				logger.info("Processing started for " + requestObject.getFileType() + " file");
				if (requestObject.getFileType().equals(FileType.csv.toString())) {
					result = StatementProcessorUtility
							.validate(csvToStatementService.fileParser(requestObject.getFile()));
				} else if (requestObject.getFileType().equals(FileType.xml.toString())) {
					result = StatementProcessorUtility
							.validate(xmlToStatementService.fileParser(requestObject.getFile()));
				}
			} catch (Exception e) {
				logger.error("Processing Failed for " + requestObject.getFileType() + " file");
				ErrorResponse er = new ErrorResponse();
				er.setErrDescription(e.getMessage());
				er.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
				return new ResponseEntity<Object>(er, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			logger.info("Processing completed for " + requestObject.getFileType() + " file");
			return new ResponseEntity<Object>(result, HttpStatus.OK);
		}
	}
}
