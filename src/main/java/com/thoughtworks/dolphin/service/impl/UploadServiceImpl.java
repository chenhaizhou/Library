package com.thoughtworks.dolphin.service.impl;

import com.thoughtworks.dolphin.common.Constants;
import com.thoughtworks.dolphin.dao.ImageDAO;
import com.thoughtworks.dolphin.model.Image;
import com.thoughtworks.dolphin.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Service
public class UploadServiceImpl implements UploadService {

    @Autowired
    private ImageDAO imageDAO;

    public int uploadFile(MultipartFile file, String realPath, String contextPath) {
        // 1. upload image.
        String fileName = file.getOriginalFilename();
        File targetFile = new File(realPath, fileName);
        if(!targetFile.exists()){
            targetFile.mkdirs();
        }

        try {
            file.transferTo(targetFile);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        } finally {

        }

        // 2. save the imageurl to db
        Image image = new Image();
        String imageUrl = generateImageUrl(contextPath, fileName);
        image.setImageUrl(imageUrl);
        imageDAO.addImage(image);
        image = imageDAO.selectImage(imageUrl);

        return image.getImageId();
    }

    private String generateImageUrl(String contextPath, String fileName) {
        return contextPath + "/" + Constants.IMAGE_UPLOAD_RELATIVE_PATH + "/" + fileName + "_" + System.currentTimeMillis();
    }

}
