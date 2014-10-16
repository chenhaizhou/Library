package com.thoughtworks.dolphin.service;

import com.thoughtworks.dolphin.AbstractUnitTest;
import com.thoughtworks.dolphin.common.Constants;
import com.thoughtworks.dolphin.dao.ImageDAO;
import com.thoughtworks.dolphin.model.Image;
import com.thoughtworks.dolphin.service.impl.UploadServiceImpl;
import com.thoughtworks.dolphin.util.UploadImageUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.IdGenerator;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

@RunWith(PowerMockRunner.class)
@PrepareForTest(UploadImageUtil.class)
public class UploadImageServiceTest {

    @InjectMocks
    private UploadServiceImpl uploadService;

    @Mock
    private ImageDAO imageDAO;

    @Before
    public void initMocks(){
        MockitoAnnotations.initMocks(this);
        mockStatic(UploadImageUtil.class);
    }

    @Test
    public void shouldUploadFileWhenSaveFileFail(){

        String realPath = "/home/zj/upload";
        String contextPath = "Library";
        String fileName = "xxxxxxxx_203023843243.jpg";
        String imageUrl = "upload/xxxxxxxx_203023843243.jpg";

        HttpServletRequest request = getHttpServletRequest(realPath, contextPath);

        MultipartFile file = mock(MultipartFile.class);


        when(UploadImageUtil.generateFileName(file)).thenReturn(fileName);
        when(UploadImageUtil.saveFile2Disk(file, realPath, fileName)).thenReturn(false);


        int result = uploadService.uploadFile(file, request);

        assertTrue(result == 0);

    }

    private HttpServletRequest getHttpServletRequest(String realPath, String contextPath) {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpSession session = mock(HttpSession.class);
        ServletContext context = mock(ServletContext.class);
        when(request.getSession()).thenReturn(session);
        when(session.getServletContext()).thenReturn(context);
        when(context.getRealPath(Constants.IMAGE_UPLOAD_RELATIVE_PATH)).thenReturn(realPath);
        when(context.getContextPath()).thenReturn(contextPath);
        return request;
    }

}
