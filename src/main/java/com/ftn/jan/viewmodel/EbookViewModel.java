package com.ftn.jan.viewmodel;

import org.springframework.web.multipart.MultipartFile;

import com.ftn.jan.model.Ebook;

public class EbookViewModel extends Ebook {

	private MultipartFile file;

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}
	
	
	
}
