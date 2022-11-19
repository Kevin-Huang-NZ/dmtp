package cn.gov.mca.dmtp.core.dao;

import cn.gov.mca.dmtp.core.model.SysRole;
import cn.gov.mca.dmtp.web.request.PaginationIn;
import cn.gov.mca.dmtp.web.response.PaginationOut;

import java.util.List;
import java.util.Optional;

public interface SysRoleJdbc {
  PaginationOut<SysRole> search(String keyword, PaginationIn pi);

  List<SysRole> getByUserId(Long userId);

  Optional<SysRole> findByUniqueKey(String roleName, Long id);
}
