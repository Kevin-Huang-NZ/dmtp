package cn.gov.mca.dmtp.web.vo;

import cn.gov.mca.dmtp.core.model.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;

@Data
@NoArgsConstructor
public class UserVo {
  @Valid
  private User user;
  private Long fileId;
}