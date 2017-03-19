package com.ftn.jan.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.impl.Log4JLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ftn.jan.model.Ebook;
import com.ftn.jan.service.EbookService;
import com.ftn.jan.util.EbookPDFHandler;
import com.ftn.jan.util.FileSystemStorageService;
import com.ftn.jan.util.StorageService;
import com.ftn.jan.viewmodel.EbookViewModel;

import javassist.NotFoundException;

@RestController
@RequestMapping("ebook")
public class EbookController {

	@Autowired
	private StorageService storageService;

	@Autowired
	private EbookService ebookService;

	private static Log4JLogger logger = new Log4JLogger(EbookController.class.getName());
	private static final int BUFFER_SIZE = 4096;
	
	
	@RequestMapping(path = "/insert", method = RequestMethod.POST,consumes = {"multipart/form-data"})
	public void insert(@RequestPart("ebook") EbookViewModel ebook,@RequestPart("file") MultipartFile file) {
		
		ebook.setEbookId(System.currentTimeMillis());
		storageService.store(file,ebook);
		

	}
	
	@RequestMapping(path = "/edit", method = RequestMethod.POST,consumes = {"multipart/form-data"})
	public void edit(@RequestPart("ebook") EbookViewModel ebook,@RequestPart(name="file",required=false) MultipartFile file) throws NotFoundException {

		if(file==null){
			Ebook ebookToUpdate = ebookService.findByEbookId(ebook.getEbookId());
			ebookService.updateBook(ebookToUpdate, ebook);
		}else{
			ebookService.delete(ebook.getEbookId());
			storageService.store(file,ebook);
		}
	}
	
	
	@RequestMapping(path = "/upload", method = RequestMethod.POST)
	public Ebook upload(@RequestPart(name="ebookId",required=false) Long ebookId, @RequestPart("file") MultipartFile file,
			RedirectAttributes redirectAttributes) {
		logger.info(ebookId);
		Ebook ebook = EbookPDFHandler.createEbookFromPDF(FileSystemStorageService.convert(file),ebookId);
		System.out.println(ebook);
		return ebook;

	}
	
//	@GetMapping(value = "/download/{filename}")
//	public void download(HttpServletResponse response, @PathVariable("filename") String filename){
//		
//		File file = ebookService.downloadFile(filename);
//		logger.info(filename);
//		
//		response.setContentType("application/pdf");
//		//response.setHeader("Content-Disposition", String.format("inline; filename=\"" + file.getName() +"\""));
//		response.setHeader("Content-Disposition", String.format("attachment; filename=\"%s\"", file.getName()));
//	        
//		
//		try {
//			InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
//			FileCopyUtils.copy(inputStream, response.getOutputStream());
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		
//	} 
	
	@GetMapping(value = "/download/{filename}")
    public void doDownload(HttpServletRequest request,
            HttpServletResponse response,@PathVariable("filename") String filename) throws IOException {
 
		
		
        // get absolute path of the application
        ServletContext context = request.getServletContext();
//        String appPath = context.getRealPath("");
        //System.out.println("appPath = " + appPath);
        if(!filename.endsWith(".pdf")){
        	filename+=".pdf";
        }
        String filePath = ResourceBundle.getBundle("index").getString("docs") + File.separator + filename;
		// construct the complete absolute path of the file
        //String fullPath = appPath + filePath ;      
        File downloadFile = new File(filePath);
        FileInputStream inputStream = new FileInputStream(downloadFile);
         
        // get MIME type of the file
        String mimeType = context.getMimeType(filePath);
        if (mimeType == null) {
            // set to binary type if MIME mapping not found
            mimeType = "application/octet-stream";
        }
        System.out.println("MIME type: " + mimeType);
 
        // set content attributes for the response
        response.setContentType(mimeType);
        response.setContentLength((int) downloadFile.length());
 
        // set headers for the response
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"",
                downloadFile.getName());
        response.setHeader(headerKey, headerValue);
 
        // get output stream of the response
        OutputStream outStream = response.getOutputStream();
 
        byte[] buffer = new byte[BUFFER_SIZE];
        int bytesRead = -1;
 
        // write bytes read from the input stream into the output stream
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, bytesRead);
        }
 
        inputStream.close();
        outStream.close();
 
    }
	
	
	@DeleteMapping(value="delete/{ebookId}")
	public void delete(@PathVariable("ebookId") Long ebookId) throws NotFoundException{
		ebookService.delete(ebookId);
	};
	
	
	
	@RequestMapping(path = "/list", method = RequestMethod.GET)
	//@PreAuthorize("hasRole('ROLE_ADMIN')")
	public List<Ebook> list() {

		 return ebookService.findAll();
		
	}

	

}
