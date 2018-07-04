package com.hellomart.util;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Component
public class Upload {
	
	public boolean fileUpload(MultipartHttpServletRequest mRequest){
		boolean isUpload = false;
		
		String uploadPath = "D:/upload/";
		
		Iterator<String> iterator = mRequest.getFileNames();
		while(iterator.hasNext()){
			String upFileName = iterator.next();
			System.out.println(upFileName + "\n");
			MultipartFile mFile = mRequest.getFile(upFileName);
			String originFileName = mFile.getOriginalFilename();
			String saveFileName = originFileName;
			if(saveFileName != null && !saveFileName.equals("")){
				if(new File(uploadPath + saveFileName).exists()){
					saveFileName = saveFileName + "_" + System.currentTimeMillis();
				}
			}
			try {
				mFile.transferTo(new File(uploadPath + saveFileName));
				isUpload = true;
			} catch (IllegalStateException e) {
				e.printStackTrace();
				isUpload = false;
			} catch (IOException e) {
				e.printStackTrace();
				isUpload = false;
			}
		}
		return isUpload;
	}
}
