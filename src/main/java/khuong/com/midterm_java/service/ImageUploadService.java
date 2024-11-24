package khuong.com.midterm_java.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ImageUploadService {
  @Autowired
  private Cloudinary cloudinary;

  @SuppressWarnings("unchecked")
  public String uploadImage(MultipartFile file) throws IOException {
    Map<String, Object> uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
    return (String) uploadResult.get("url");
  }

  public String getImageUrl(String publicId) {
    return cloudinary.url().resourceType("image").publicId(publicId).generate();
  }
}
