package com.thoughtworks.dolphin.service.impl;

import com.thoughtworks.dolphin.common.Constants;
import com.thoughtworks.dolphin.dao.ImageDAO;
import com.thoughtworks.dolphin.model.Image;
import com.thoughtworks.dolphin.service.UploadService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

@Service
public class UploadServiceImpl implements UploadService {

    private final Log logger = LogFactory.getLog(UploadServiceImpl.class);

    @Autowired
    private ImageDAO imageDAO;

    private int uploadFile(MultipartFile file, String realPath, String contextPath) {
        String fileName = generateFileName(file.getOriginalFilename());
        if (!saveFile2Disk(file, realPath, fileName)) {
            return 0;
        }

        Image image = saveImage2DB(contextPath, fileName);
        return image.getImageId();
    }

    public int uploadFile(MultipartFile file, HttpServletRequest request) {
        String realPath = request.getSession().getServletContext().getRealPath(Constants.IMAGE_UPLOAD_RELATIVE_PATH);
        String contextPath = request.getSession().getServletContext().getContextPath();
        return uploadFile(file, realPath, contextPath);
    }

    public void deleteImage(String path, String fileName) {
        try {
            File file = new File(path, fileName);
            if(file.exists()){
                file.delete();
            }
        } catch(Exception e){}

    }


    private Image saveImage2DB(String contextPath, String fileName) {
        Image image = new Image();
        String imageUrl = generateImageUrl(contextPath, fileName);
        image.setImageUrl(imageUrl);
        imageDAO.addImage(image);
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
            logger.error("upload file fail. filename:" + targetFile.getAbsolutePath());
            return false;
        } finally {

        }
        logger.info("upload file. filename:" + targetFile.getAbsolutePath());

        return true;
    }

    private String generateImageUrl(String contextPath, String fileName) {
        return Constants.IMAGE_UPLOAD_RELATIVE_PATH + "/" + fileName;
    }

    private String generateFileName(String fileName) {
        int lastIndex = fileName.lastIndexOf(".");
        String baseFileName = fileName.substring(0, lastIndex);
        String fileNameSuffix = fileName.substring(lastIndex, fileName.length());
        baseFileName += "_" + System.currentTimeMillis();
        return baseFileName + fileNameSuffix;
    }

}
