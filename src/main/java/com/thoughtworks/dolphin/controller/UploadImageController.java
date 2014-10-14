package com.thoughtworks.dolphin.controller;

import com.thoughtworks.dolphin.service.UploadService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@Controller
public class UploadImageController {

    @Autowired
    private UploadService uploadService;

    @RequestMapping(value = "/upload.do")
    @ResponseBody
    public String upload(@RequestParam(value = "cover", required = false) MultipartFile file, HttpServletRequest request) {
        String coverImageId = String.valueOf(uploadService.uploadFile(file, request));

        JSONObject reponseCode = new JSONObject();
        reponseCode.put("resultCode", "success");
        reponseCode.put("coverImageId", coverImageId);

        return reponseCode.toString();
    }


}
