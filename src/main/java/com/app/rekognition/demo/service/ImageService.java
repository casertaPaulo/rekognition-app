package com.app.rekognition.demo.service;

import com.app.rekognition.demo.DTO.ImageResponseDTO;
import com.app.rekognition.demo.DTO.ModerationLabelDTO;
import com.app.rekognition.demo.exception.RekognitionApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.services.rekognition.model.DetectModerationLabelsResponse;

import java.io.IOException;

@Service
public class ImageService {

    @Autowired
    private RekognitionService service;

    @Autowired
    private S3Service s3Service;

    public ImageResponseDTO analyzeImage(MultipartFile file) {

        try {
            DetectModerationLabelsResponse response = service.detectModeration(file);

            var result = response.moderationLabels().stream().map(
                    label -> new ModerationLabelDTO(
                            label.name(),
                            label.parentName(),
                            label.confidence()
                    )
            ).toList();

            if (result.isEmpty()) {
                String urlToImage = s3Service.uploadImage(file);
                return new ImageResponseDTO(true, result, urlToImage);
            }

            return new ImageResponseDTO(false, result, null);

        } catch (Exception e) {
            throw new RekognitionApiException(e.getMessage());
        }
    }
}
