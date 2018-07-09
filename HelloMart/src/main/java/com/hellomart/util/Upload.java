package com.hellomart.util;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Component
public class Upload {
	
	public Map<String, Object> fileUpload(MultipartHttpServletRequest mRequest){
		boolean isUpload = false;
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String uploadPath = "D:/upload/";
		if(!new File(uploadPath).exists()){
			new File(uploadPath).mkdirs();
		}
		String saveFileName = null;
		Iterator<String> iterator = mRequest.getFileNames();
		while(iterator.hasNext()){
			String upFileName = iterator.next();
			System.out.println(upFileName + "\n");
			MultipartFile mFile = mRequest.getFile(upFileName);
			String originFileName = mFile.getOriginalFilename();
			saveFileName = originFileName;
			if(saveFileName != null && !saveFileName.equals("")){
				if(new File(uploadPath + saveFileName).exists()){
					String fileName, extention; 
					fileName = saveFileName.substring(0, saveFileName.lastIndexOf("."));
					extention = saveFileName.substring(saveFileName.lastIndexOf("."), saveFileName.length());
					saveFileName = fileName + "_" + System.currentTimeMillis() + extention;
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
		resultMap.put("isUpload", isUpload);
		resultMap.put("imagePath",uploadPath + saveFileName);
		return resultMap;
	}
}