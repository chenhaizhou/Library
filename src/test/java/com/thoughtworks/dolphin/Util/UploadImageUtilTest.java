package com.thoughtworks.dolphin.util;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.web.multipart.MultipartFile;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
public class UploadImageUtilTest {

    @Test
    public void shouldGenerateFileName(){

        MultipartFile file = mock(MultipartFile.class);
        when(file.getOriginalFilename()).thenReturn("xxxx.jpg");

        String resultFileName = UploadImageUtil.generateFileName(file);
        int index = resultFileName.indexOf("_");

        resultFileName = resultFileName.substring(0, index);
        assertEquals("xxxx", resultFileName);

    }


}
