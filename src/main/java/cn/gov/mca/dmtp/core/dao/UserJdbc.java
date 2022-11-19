package cn.gov.mca.dmtp.core.dao;

import cn.gov.mca.dmtp.core.model.User;
import cn.gov.mca.dmtp.web.request.PaginationIn;
import cn.gov.mca.dmtp.web.response.PaginationOut;

import java.util.Optional;

public interface UserJdbc {
  PaginationOut<User> search(String keyword, PaginationIn pi);

  Optional<User> findByUniqueKey(String phone, Long id);
}
