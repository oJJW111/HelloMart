package com.hellomart.util;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileUtils {
	
	public boolean fileUpload(MultipartFile upload, String path) throws IllegalStateException, IOException{
		if(upload == null) { return false; }
		
		String originFileName = upload.getOriginalFilename();
		
		File file = new File(path + originFileName);
		File file2 = rename(file);
		upload.transferTo(file2);
		
		return true;
	}
	
	public File rename(File f) {             	//File f는 원본 파일
	    if (createNewFile(f)) return f;        	//생성된 f가 중복되지 않으면 리턴
	     
	    String name = f.getName();
	    String body = null;
	    String ext = null;
	 
	    int dot = name.lastIndexOf(".");
	    if (dot != -1) {                             //확장자가 있을때
	      body = name.substring(0, dot);
	      ext = name.substring(dot);
	    } else {                                     //확장자가 없을때
	      body = name;
	      ext = "";
	    }
	 
	    int count = 0;
	    //중복된 파일이 있을때
	    //파일이름뒤에 a숫자.확장자 이렇게 들어가게 되는데 숫자는 999999까지 된다.
	    while (!createNewFile(f) && count < 999999) { 
	      count++;
	      String newName = body + count + ext;
	      f = new File(f.getParent(), newName);
	    }
	    return f;
	}
 
	private boolean createNewFile(File f) {
	    try {
	      return f.createNewFile();                    //존재하는 파일이 아니면
	    }catch (IOException ignored) {
	      return false;
	    }
	}
	
}