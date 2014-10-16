package com.thoughtworks.dolphin.dao;

import com.thoughtworks.dolphin.AbstractUnitTest;
import com.thoughtworks.dolphin.model.Image;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static junit.framework.Assert.*;

/**
 * Created by lzwu on 10/16/14.
 */
public class ImageDAOTest extends AbstractUnitTest {
    @Autowired
    private ImageDAO imageDAO;

    @Test
    public void shouldAddImageAndGetId() throws Exception {
        Image image = prepareImage();
        assertTrue(image.getImageId() > 0);
    }

    @Test
    public void shouldSelectImage() throws Exception {
        Image image = prepareImage();
        Image resultImage = imageDAO.getImage(image.getImageId());
        assertNotNull(resultImage);
        assertEquals(image.getImageUrl(), resultImage.getImageUrl());
    }

    @Test
    public void shouldDeleteImage() throws Exception {
        Image image = prepareImage();
        int imageId = image.getImageId();
        imageDAO.deleteImage(image.getImageId());
        assertNull(imageDAO.getImage(imageId));
    }

    private Image prepareImage() {
        Image image = new Image();
        image.setImageUrl("/upload/abc.jpg");
        imageDAO.addImage(image);
        return image;
    }
}
