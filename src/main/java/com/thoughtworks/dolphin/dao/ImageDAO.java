package com.thoughtworks.dolphin.dao;

import com.thoughtworks.dolphin.model.Image;

public interface ImageDAO {

    public Image selectImage(String imageUrl);

}
