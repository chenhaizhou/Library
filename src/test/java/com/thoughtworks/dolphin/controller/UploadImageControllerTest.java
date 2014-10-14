package com.thoughtworks.dolphin.controller;

import com.thoughtworks.dolphin.service.UploadService;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UploadImageControllerTest {

    @InjectMocks
    private UploadImageController uploadImageController;

    @Mock
    private UploadService uploadService;

    @Before
    public void initMocks(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldUpload(){
        MultipartFile file = mock(MultipartFile.class);

        HttpServletRequest request = mock(HttpServletRequest.class);


        when(uploadService.uploadFile(file, request)).thenReturn(100);

        String resultStr = uploadImageController.upload(file, request);
        JSONObject expectedResult = new JSONObject();
        expectedResult.put("resultCode", "success");
        expectedResult.put("coverImageId", "100");
        assertTrue(expectedResult.toString().equals(resultStr));

    }


}
