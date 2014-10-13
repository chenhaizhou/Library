package com.thoughtworks.dolphin.dao;

import com.thoughtworks.dolphin.model.Image;
import org.apache.ibatis.annotations.Select;

public interface ImageDAO {

    public Image selectImage(String imageUrl);

    void addImage(Image image);

    public Image getImage(int imageId);

    void deleteImage(int coverImageId);
}
