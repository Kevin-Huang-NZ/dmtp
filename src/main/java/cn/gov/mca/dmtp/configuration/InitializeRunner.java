package cn.gov.mca.dmtp.configuration;

import cn.gov.mca.dmtp.core.service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class InitializeRunner implements ApplicationRunner {

  @Autowired
  private FileUploadService fileUploadService;

  @Override
  public void run(ApplicationArguments args) throws Exception {
    fileUploadService.init();
  }
}
