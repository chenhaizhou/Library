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

        String fileName = file.getOriginalFilename();
        if (saveFile2Disk(file, realPath, fileName)) {
            return 0;
        }

        System.out.println("fileName:" + fileName);
        Image image = saveImage2DB(contextPath, fileName);
        return image.getImageId();
    }

    private Image saveImage2DB(String contextPath, String fileName) {
        Image image = new Image();
        String imageUrl = generateImageUrl(contextPath, fileName);
        image.setImageUrl(imageUrl);
        imageDAO.addImage(image);
        image = imageDAO.selectImage(imageUrl);
        return image;
    }

    private boolean saveFile2Disk(MultipartFile file, String realPath, String fileName) {
        File targetFile = new File(realPath, fileName);
        if(!targetFile.exists()){
            targetFile.mkdirs();
        }
        try {
            file.transferTo(targetFile);
        } catch (Exception e) {
            e.printStackTrace();
            return true;
        } finally {

        }
        return false;
    }

    private String generateImageUrl(String contextPath, String fileName) {
        return contextPath + "/" + Constants.IMAGE_UPLOAD_RELATIVE_PATH + "/" + generateFileName(fileName);
    }

    private String generateFileName(String fileName) {
        int lastIndex = fileName.lastIndexOf(".");
        String baseFileName = fileName.substring(0, lastIndex);
        String fileNameSuffix = fileName.substring(lastIndex, fileName.length());
        baseFileName += "_" + System.currentTimeMillis();
        return baseFileName + fileNameSuffix;
    }

}
