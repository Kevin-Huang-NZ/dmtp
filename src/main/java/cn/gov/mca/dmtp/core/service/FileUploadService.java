package cn.gov.mca.dmtp.core.service;

import cn.gov.mca.dmtp.core.model.FileUpload;
import cn.gov.mca.dmtp.error.CustomizedException;
import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {
  void init() throws CustomizedException;
  FileUpload store(MultipartFile file, FileUpload record) throws CustomizedException;
}
