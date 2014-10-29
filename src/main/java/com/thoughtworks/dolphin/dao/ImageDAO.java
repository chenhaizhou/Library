package com.thoughtworks.dolphin.dao;

import com.thoughtworks.dolphin.model.Image;
import org.apache.ibatis.annotations.Select;

public interface ImageDAO {

    void addImage(Image image);

    Image getImage(int imageId);

    void deleteImage(int coverImageId);
}
