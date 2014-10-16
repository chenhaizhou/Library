package com.thoughtworks.dolphin.util;

import com.thoughtworks.dolphin.service.impl.UploadServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.web.multipart.MultipartFile;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

@RunWith(PowerMockRunner.class)
@PrepareForTest({UploadImageUtil.class, System.class})
public class UploadImageUtilTest {

    @Test
    public void shouldGenerateFileName(){

        MultipartFile file = mock(MultipartFile.class);
        when(file.getOriginalFilename()).thenReturn("xxxx.jpg");

        mockStatic(System.class);
        when(System.currentTimeMillis()).thenReturn(1111111111L);

        String resultFileName = UploadImageUtil.generateFileName(file);

        assertEquals("xxxx_1111111111.jpg", resultFileName);
    }

    @Test
    public void shouldSaveFile2Disk(){

        MultipartFile file = mock(MultipartFile.class);
        String realPath = "fttfyf/asdaxasd/xasdasd/xasx" + System.currentTimeMillis();
        String fileName = "xxxxxxxx_203023843243.jpg";

        assertTrue(UploadImageUtil.saveFile2Disk(file, realPath, fileName));
    }


}
