package com.banking.rb.vo;

import org.springframework.util.StringUtils;

import com.banking.rb.constants.AppConstants;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Christopher Request Object
 */
@Getter
@Setter
public class RequestObject {

	private String fileName;
	private String fileType;

	public String getFile() {
		if (!StringUtils.isEmpty(getFileName()) && !StringUtils.isEmpty(getFileType())) {
			if (FileType.xml.toString().equals(getFileType()) || FileType.csv.toString().equals(getFileType())) {
				return getFileName() + AppConstants.DOT_SEPARTOR + getFileType();
			}
			return getFileType() + AppConstants.SPACE_SEPARTOR
					+ AppConstants.FILE_TYPE_NOT_SUPPORTED;
		}
		return AppConstants.INVALID_DETAILS;
	}
}
