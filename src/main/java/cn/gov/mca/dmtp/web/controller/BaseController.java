package cn.gov.mca.dmtp.web.controller;

import cn.gov.mca.dmtp.GlobalConst;
import cn.gov.mca.dmtp.core.model.User;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;

@Deprecated
public class BaseController {

  public ThreadLocal<User> authContext = new ThreadLocal<>();

  @ModelAttribute
  public void initUser(HttpServletRequest request) {
    Object obj = request.getAttribute(GlobalConst.TOKEN_USER);
    if (obj instanceof User) {
      User loginUser = (User) obj;
      authContext.set(loginUser);
    }
  }
}
