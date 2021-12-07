package nongsan.webmvc.service;

import nongsan.webmvc.dto.FileDTO;

public interface IStorageStrategy {
    FileDTO generateSignedUrl(String path);

    FileDTO generateSignedUrlUpdate(String filename);

    Boolean deleteImage(String filename);
}
