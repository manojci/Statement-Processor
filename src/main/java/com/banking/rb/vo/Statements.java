package com.banking.rb.vo;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * @author Christopher List of Statements
 */
@XmlRootElement(name = "records")
@AllArgsConstructor
@NoArgsConstructor
public class Statements {

	private List<Statement> statement;

	public List<Statement> getStatement() {
		return statement;
	}

	@XmlElement(name = "record")
	public void setStatement(List<Statement> statement) {
		this.statement = statement;
	}
}
