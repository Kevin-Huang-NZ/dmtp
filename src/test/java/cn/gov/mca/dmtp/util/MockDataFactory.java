package cn.gov.mca.dmtp.util;

import com.alibaba.fastjson.JSONObject;
import cn.gov.mca.dmtp.core.dao.UserRepository;
import cn.gov.mca.dmtp.security.JwtUtil;
import cn.gov.mca.dmtp.security.PasswordEncoderUtil;
import cn.gov.mca.dmtp.web.request.PaginationIn;
import cn.gov.mca.dmtp.web.vo.LoginUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
public class MockDataFactory {
  public static final String ADMIN_USER_NAME = "13811650908";
  public static final String ADMIN_USER_PASSWORD = "1234Qwer";

  @Autowired
  private JwtUtil jwtUtil;
  @Autowired
  private UserRepository userRepository;

  @Cacheable("login-user-cache-for-test")
  public LoginUser loginUser() {
    LoginUser loginUser = new LoginUser();
    loginUser.setUserName(ADMIN_USER_NAME);
    loginUser.setPassword(ADMIN_USER_PASSWORD);

    return loginUser;
  }

  @Cacheable("jwt-cache-for-test")
  public String jwt() {
    var user = userRepository.findByUniqueKey(ADMIN_USER_NAME, null);
    if (user.isPresent()) {
      return "Bearer " + jwtUtil.sign(ADMIN_USER_NAME, user.get().getPassword());
    }
    return "";
  }

//  public JSONObject queryParams(Optional<PaginationIn> pagenationIn) {
//    JSONObject params = new JSONObject();
//    if (pagenationIn.isPresent()) {
//      params.put("number", pagenationIn.get().getNumber());
//      params.put("size", pagenationIn.get().getSize());
//    }
//    return params;
//  }
}
