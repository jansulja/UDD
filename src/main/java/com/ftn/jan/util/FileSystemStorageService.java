package com.ftn.jan.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import com.ftn.jan.ddm.indexer.IndexManager;
import com.ftn.jan.viewmodel.EbookViewModel;

@Service
public class FileSystemStorageService implements StorageService {

    private final Path rootLocation;

    @Autowired
    public FileSystemStorageService(StorageProperties properties) {
        this.rootLocation = Paths.get(properties.getLocation());
    }

    @Override
    public void store(MultipartFile file) {
        try {
            if (file.isEmpty()) {
                throw new StorageException("Failed to store empty file " + file.getOriginalFilename());
            }
            
          
            
            if(Files.notExists(this.rootLocation.resolve(file.getOriginalFilename()))){
            	//Files.createDirectory(this.rootLocation.resolve(file.getOriginalFilename()));
            	File temp = new File(ResourceBundle.getBundle("index").getString("docs"));
            	boolean succ = temp.mkdirs();
            }
            
            Files.copy(file.getInputStream(), this.rootLocation.resolve(file.getOriginalFilename()));
            IndexManager.getIndexer().index(convert(file));
        } catch (IOException e) {
            throw new StorageException("Failed to store file " + file.getOriginalFilename(), e);
        }
    }

    @Override
    public Stream<Path> loadAll() {
        try {
            return Files.walk(this.rootLocation, 1)
                    .filter(path -> !path.equals(this.rootLocation))
                    .map(path -> this.rootLocation.relativize(path));
        } catch (IOException e) {
            throw new StorageException("Failed to read stored files", e);
        }

    }

    @Override
    public Path load(String filename) {
        return rootLocation.resolve(filename);
    }

    @Override
    public Resource loadAsResource(String filename) {
        try {
            Path file = load(filename);
            Resource resource = new UrlResource(file.toUri());
            if(resource.exists() || resource.isReadable()) {
                return resource;
            }
            else {
                throw new StorageFileNotFoundException("Could not read file: " + filename);

            }
        } catch (MalformedURLException e) {
            throw new StorageFileNotFoundException("Could not read file: " + filename, e);
        }
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(rootLocation.toFile());
    }

    @Override
    public void init() {
        try {
            Files.createDirectory(rootLocation);
        } catch (IOException e) {
            throw new StorageException("Could not initialize storage", e);
        }
    }

	
    private File multipartToFile(MultipartFile multipart) throws IllegalStateException, IOException 
    {
        File convFile = new File( multipart.getOriginalFilename());
        multipart.transferTo(convFile);
        return convFile;
    }
    
    public static File convert(MultipartFile file)
    {    
        File convFile = new File(file.getOriginalFilename());
        try {
			convFile.createNewFile();
			FileOutputStream fos = new FileOutputStream(convFile); 
	        fos.write(file.getBytes());
	        fos.close(); 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
        
        return convFile;
    }

	@Override
	public void store(MultipartFile file, EbookViewModel ebook) {
		// TODO Auto-generated method stub
	       try {
	            if (file.isEmpty()) {
	                throw new StorageException("Failed to store empty file " + file.getOriginalFilename());
	            }
	            
	            String filename = addTimestampToFilename(file.getOriginalFilename());
	            ebook.setFilename(filename);
	            if(Files.notExists(this.rootLocation.resolve(filename))){
	            	//Files.createDirectory(this.rootLocation.resolve(file.getOriginalFilename()));
	            	File temp = new File(ResourceBundle.getBundle("index").getString("docs"));
	            	boolean succ = temp.mkdirs();
	            }
	            
	            Files.copy(file.getInputStream(), this.rootLocation.resolve(filename));
	            IndexManager.getIndexer().index(convert(file),ebook);
	        } catch (IOException e) {
	            throw new StorageException("Failed to store file " + file.getOriginalFilename(), e);
	        }
	}
	
	private String addTimestampToFilename(String filename){
		
		String[] parts= filename.split("\\.");
		SimpleDateFormat sdf = new SimpleDateFormat("dd_MM_yyyy_HH_mm_ss");
		return parts[0] + "_" + sdf.format(new Date()) + "." + parts[1] ;
		
		
	}
    
}
