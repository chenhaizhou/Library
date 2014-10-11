package com.thoughtworks.dolphin.dao;

import com.thoughtworks.dolphin.model.Image;
import org.apache.ibatis.annotations.Select;

public interface ImageDAO {

    public Image selectImage(String imageUrl);

    void addImage(Image image);

    @Select("select image_id as imageId, image_url as imageUrl from images where image_id=#{imageId}")
    public Image getImage(int imageId);
}
