package com.banking.rb.service;

import java.util.List;

import com.banking.rb.vo.Statement;

/**
 * @author Christopher Interface for file parsing
 */
@FunctionalInterface
public interface FileParserService {

	List<Statement> fileParser(String fileName) throws Exception;
}
