package com.ftn.jan.viewmodel;

import org.springframework.web.multipart.MultipartFile;

import com.ftn.jan.model.Ebook;

public class NewEbookViewModel {

	private Ebook ebook;
	private MultipartFile file;
	public Ebook getEbook() {
		return ebook;
	}
	public void setEbook(Ebook ebook) {
		this.ebook = ebook;
	}
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	
	
	
}
