package com.nwnu.averweb.controller;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping(value = "UploadFile")
public class UploadFileController {

	@RequestMapping(value = "index")
	public String index() {
		return "UploadFile/index";
	}

	@RequestMapping(value="upload")
	public String upload()
	{
		return "UploadFile/upload";
	}
	
	@RequestMapping(value="springUpload")
	@ResponseBody
	 public Map<String, Object> springUpload(@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request) {  
		Map<String, Object> result = new HashMap<String, Object>();
        System.out.println("开始");  
        String path = request.getSession().getServletContext().getRealPath("upload");  
        String fileName = file.getOriginalFilename();   
        System.out.println(path);  
        File targetFile = new File(path, fileName);  
        if(!targetFile.exists()){  
            targetFile.mkdirs();  
        }  
  
        //保存  
        try {  
            file.transferTo(targetFile);  
            result.put("IsSuccess", true);
			result.put("Message", "上传成功");
			result.put("fileUrl",request.getContextPath()+"/upload/"+fileName);
        } catch (Exception e) {  
            e.printStackTrace();  
            result.put("IsSuccess", false);
			result.put("Message",e.getMessage());
        }          
        return result;  
    }  
}
