package com.thoughtworks.dolphin.service.impl;

import com.thoughtworks.dolphin.common.Constants;
import com.thoughtworks.dolphin.dao.ImageDAO;
import com.thoughtworks.dolphin.model.Image;
import com.thoughtworks.dolphin.service.UploadService;
import com.thoughtworks.dolphin.util.UploadImageUtil;
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
        String fileName = UploadImageUtil.generateFileName(file);
        if (!UploadImageUtil.saveFile2Disk(file, realPath, fileName)) {
            return 0;
        }

        return saveImage2DB(fileName);
    }

    public int uploadFile(MultipartFile file, HttpServletRequest request) {
        String realPath = request.getSession().getServletContext().getRealPath(Constants.IMAGE_UPLOAD_RELATIVE_PATH);
        String contextPath = request.getSession().getServletContext().getContextPath();
        return uploadFile(file, realPath, contextPath);
    }

    public boolean deleteImage(String path, String fileName) {
        boolean result = true;
        try {
            File file = new File(path, fileName);
            if(file.exists()) {
                file.delete();
            }
        } catch(Exception e){
            result = false;
        }
        return result;
    }


    private int saveImage2DB(String fileName) {
        Image image = new Image();
        String imageUrl = generateImageUrl(fileName);
        image.setImageUrl(imageUrl);
        imageDAO.addImage(image);
        return image.getImageId();
    }

    private String generateImageUrl(String fileName) {
        return Constants.IMAGE_UPLOAD_RELATIVE_PATH + "/" + fileName;
    }

}
