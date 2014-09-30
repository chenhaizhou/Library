package com.thoughtworks.dolphin.controller;

import com.thoughtworks.dolphin.common.Constants;
import com.thoughtworks.dolphin.service.UploadService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;

@Controller
public class UploadImageController {

    @Autowired
    private UploadService uploadService;

    @RequestMapping(value = "/upload.do")
    @ResponseBody
    public String upload(@RequestParam(value = "cover", required = false) MultipartFile file, HttpServletRequest request, ModelMap model) {
        System.out.println("---------------enter upload()-------------");
        String realPath = request.getSession().getServletContext().getRealPath(Constants.IMAGE_UPLOAD_RELATIVE_PATH);

        String contextPath = request.getSession().getServletContext().getContextPath();

//        reponseCode.put("coverImageId", contextPath + "/" + Constants.IMAGE_UPLOAD_RELATIVE_PATH + "/" + fileName);

        String coverImageId = String.valueOf(uploadService.uploadFile(file, realPath, contextPath));

        JSONObject reponseCode = new JSONObject();
        reponseCode.put("resultCode", "success");
        reponseCode.put("coverImageId", coverImageId);


        return reponseCode.toString();
    }


}
