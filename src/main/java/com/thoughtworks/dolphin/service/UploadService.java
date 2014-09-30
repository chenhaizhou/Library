package com.thoughtworks.dolphin.service;

import com.thoughtworks.dolphin.model.Book;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UploadService {

    int uploadFile(MultipartFile file, String realPath, String contextPath);

}
