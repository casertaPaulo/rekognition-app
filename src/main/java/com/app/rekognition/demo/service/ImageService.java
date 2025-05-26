package com.app.rekognition.demo.service;

import com.app.rekognition.demo.entity.ImageEntity;
import com.app.rekognition.demo.repository.ImagesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageService {

    @Autowired
    private ImagesRepository repository;

    public void saveImageUrl(String url) {
        repository.save(new ImageEntity(url));
    }


}
