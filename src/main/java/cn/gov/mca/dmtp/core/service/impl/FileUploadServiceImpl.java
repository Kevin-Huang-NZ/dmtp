package cn.gov.mca.dmtp.core.service.impl;

import cn.gov.mca.dmtp.GlobalConst;
import cn.gov.mca.dmtp.core.dao.FileUploadRepository;
import cn.gov.mca.dmtp.core.model.FileUpload;
import cn.gov.mca.dmtp.core.service.FileUploadService;
import cn.gov.mca.dmtp.error.CustomizedException;
import cn.gov.mca.dmtp.error.PredefinedError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
@Slf4j
public class FileUploadServiceImpl implements FileUploadService {
  @Autowired
  private FileUploadRepository repository;

  @Value("${file-upload.location}")
  private String location;

  private Path rootLocation;

  @Override
  public void init() throws CustomizedException {
    try {
      this.rootLocation = Paths.get(this.location);
      Files.createDirectories(this.rootLocation);
    }
    catch (IOException e) {
      log.error("文件上传路径创建失败。", e);
      throw new CustomizedException(PredefinedError.UNKNOWN_ERROR, "文件上传路径创建失败，请联系管理员。");
    }
  }

  @Override
  public FileUpload store(MultipartFile file, FileUpload record) throws CustomizedException {
    try {
      String uploadedFileName = getUUID();

      Path destinationFile = this.rootLocation.resolve(Paths.get(uploadedFileName)).normalize().toAbsolutePath();
      if (!destinationFile.getParent().equals(this.rootLocation.toAbsolutePath())) {
        throw new CustomizedException(PredefinedError.UNKNOWN_ERROR, "上传文件路径不正确，请联系管理员。");
      }
      try (InputStream inputStream = file.getInputStream()) {
        Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);
      }

      record.setDeleteStatus("0");
      record.setFileName(file.getOriginalFilename());
      record.setFullPath(GlobalConst.UPLOADED_FILE_ACCESS_URL_PREFIX + uploadedFileName);
      repository.save(record);

      return record;
    }
    catch (IOException e) {
      log.error("文件保存失败。", e);
      throw new CustomizedException(PredefinedError.UNKNOWN_ERROR, "文件保存失败，请联系管理员。");
    }
  }

  private String getUUID(){
    return UUID.randomUUID().toString().replace("-", "");
  }
}
