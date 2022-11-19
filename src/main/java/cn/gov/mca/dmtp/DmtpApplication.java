package cn.gov.mca.dmtp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"cn.gov.mca.dmtp"})
public class DmtpApplication {

  public static void main(String[] args) {
    SpringApplication.run(DmtpApplication.class, args);
  }
}
