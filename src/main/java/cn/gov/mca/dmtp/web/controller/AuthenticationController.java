package cn.gov.mca.dmtp.web.controller;

import cn.gov.mca.dmtp.core.dao.UserRepository;
import cn.gov.mca.dmtp.core.model.User;
import cn.gov.mca.dmtp.core.service.UserService;
import cn.gov.mca.dmtp.error.CustomizedException;
import cn.gov.mca.dmtp.error.PredefinedError;
import cn.gov.mca.dmtp.security.JwtUtil;
import cn.gov.mca.dmtp.security.PasswordEncoderUtil;
import cn.gov.mca.dmtp.web.response.Root;
import cn.gov.mca.dmtp.web.vo.LoginUser;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name = "用户登录")
@RestController
@RequestMapping("/api")
@Slf4j
public class AuthenticationController {
  @Autowired
  private UserRepository userRepository;
  @Autowired
  private UserService userService;
  @Autowired
  private JwtUtil jwtUtil;
  @Autowired
  private PasswordEncoderUtil passwordEncoderUtil;

  @Operation(summary = "用户登录")
  @PostMapping("/login")
  public Root login(@RequestBody @Validated LoginUser entity)
      throws CustomizedException {
    log.debug("Username: {}, password: {}", entity.getUserName(), entity.getPassword());
    var user = userService.findByUniqueKey(entity.getUserName());
    if (user.isEmpty()) {
      throw new CustomizedException(PredefinedError.UNAUTHORIZED, "手机号和密码不匹配，登录失败。");
    }
    log.debug("User from db, password: {}", user.get().getPassword());
    if (!passwordEncoderUtil.getPasswordEncoder().matches(entity.getPassword(), user.get().getPassword())) {
      throw new CustomizedException(PredefinedError.UNAUTHORIZED, "手机号和密码不匹配，登录失败。");
    }

    return Root.create(jwtUtil.sign(user.get().getPhone(), user.get().getPassword()));
  }
}
