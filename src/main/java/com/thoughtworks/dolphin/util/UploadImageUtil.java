package com.thoughtworks.dolphin.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public class UploadImageUtil {

    public static String generateFileName(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        int lastIndex = fileName.lastIndexOf(".");
        String baseFileName = fileName.substring(0, lastIndex);
        String fileNameSuffix = fileName.substring(lastIndex, fileName.length());
        baseFileName += "_" + System.currentTimeMillis();
        return baseFileName + fileNameSuffix;
    }

    public static boolean saveFile2Disk(MultipartFile file, String realPath, String fileName) {
        File targetFile = new File(realPath, fileName);
        if(!targetFile.exists()){
            targetFile.mkdirs();
        }
        try {
            file.transferTo(targetFile);
        } catch (Exception e) {
            return false;
        } finally {

        }

        return true;
    }

}
