package nongsan.webmvc.service;

import nongsan.webmvc.dto.FileDTO;

public interface IStorageStrategy {
    FileDTO generateSignedUrl(String path);

    Boolean deleteImage(String filename);
}
