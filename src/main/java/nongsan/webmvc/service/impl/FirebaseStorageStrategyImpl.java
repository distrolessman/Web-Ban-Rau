package nongsan.webmvc.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.*;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.StorageClient;
import nongsan.webmvc.dto.FileDTO;
import nongsan.webmvc.dto.FirebaseCredential;
import nongsan.webmvc.service.IStorageStrategy;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.RandomStringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.concurrent.TimeUnit;


public class FirebaseStorageStrategyImpl implements IStorageStrategy {
    private static Storage storage;
    private static Bucket bucket;

    static {
        FirebaseCredential firebaseCredential = new FirebaseCredential();
        String privateKey = System.getenv("FIREBASE_PRIVATE_KEY")
                .replace("\\n", "\n");
        firebaseCredential.setType(System.getenv("FIREBASE_TYPE"));
        firebaseCredential.setProject_id(System.getenv("FIREBASE_PROJECT_ID"));
        firebaseCredential.setPrivate_key_id(System.getenv("FIREBASE_PRIVATE_KEY_ID"));
        firebaseCredential.setPrivate_key(privateKey);
        firebaseCredential.setClient_email(System.getenv("FIREBASE_CLIENT_EMAIL"));
        firebaseCredential.setClient_id(System.getenv("FIREBASE_CLIENT_ID"));
        firebaseCredential.setAuth_uri(System.getenv("FIREBASE_AUTH_URI"));
        firebaseCredential.setToken_uri(System.getenv("FIREBASE_TOKEN_URI"));
        firebaseCredential.setAuth_provider_x509_cert_url(System.getenv("FIREBASE_AUTH_PROVIDER_X509_CERT_URL"));
        firebaseCredential.setClient_x509_cert_url(System.getenv("FIREBASE_CLIENT_X509_CERT_URL"));
        //serialize with jackson
        ObjectMapper mapper = new ObjectMapper();
        FirebaseOptions options = null;
        try {
            //convert jsonString to InputStream using Apache Commons
            String jsonString = mapper.writeValueAsString(firebaseCredential);
            InputStream firebaseStream = IOUtils.toInputStream(jsonString);
            options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(firebaseStream))
                    .setStorageBucket(System.getenv("FIREBASE_BUCKET_NAME"))
                    .build();
            FirebaseApp firebaseApp = FirebaseApp.initializeApp(options);
            StorageClient storageClient = StorageClient.getInstance(firebaseApp);
            storage = storageClient.bucket().getStorage();
            bucket = storageClient.bucket();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public FirebaseStorageStrategyImpl() {

    }

    @Override
    public FileDTO generateSignedUrl(String path) {
        FileDTO fileDTO = new FileDTO();
        fileDTO.setFileName(path + "/" + RandomStringUtils.randomAlphanumeric(20) + ".png");
        BlobInfo blobInfo = BlobInfo.newBuilder(bucket.getName(), fileDTO.getFileName()).setContentType("image/png").build();
        storage.create(blobInfo);
        URL url = storage.signUrl(blobInfo, 1, TimeUnit.MINUTES,
                Storage.SignUrlOption.withV4Signature(),
                Storage.SignUrlOption.httpMethod(HttpMethod.PUT));
        storage.createAcl(blobInfo.getBlobId(), Acl.of(Acl.User.ofAllUsers(), Acl.Role.READER));
        fileDTO.setUrl(url.toString());
        return fileDTO;
    }

    @Override
    public FileDTO generateSignedUrlUpdate(String filename) {
        filename = replacePreSuf(filename);
        FileDTO fileDTO = new FileDTO();
        fileDTO.setFileName(filename);
        BlobId blobId = BlobId.of(bucket.getName(), filename);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();
        URL url = storage.signUrl(blobInfo, 1, TimeUnit.MINUTES,
                Storage.SignUrlOption.withV4Signature(),
                Storage.SignUrlOption.httpMethod(HttpMethod.PUT));
        storage.createAcl(blobInfo.getBlobId(), Acl.of(Acl.User.ofAllUsers(), Acl.Role.READER));
        fileDTO.setUrl(url.toString());
        return fileDTO;
    }

    @Override
    public Boolean deleteImage(String filename) {
        filename = replacePreSuf(filename);
        try {
            BlobId blobId = BlobId.of(bucket.getName(), filename);
            storage.delete(blobId);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private String replacePreSuf(String filename) {
        filename = filename.replace(System.getenv("FIREBASE_IMAGE_PREFIX"), "");
        filename = filename.replace("?alt=media", "");
        filename = filename.replace("%2F", "/");
        return filename;
    }
}
