package cn.gov.mca.dmtp.core.dao;

import cn.gov.mca.dmtp.core.model.SysPermission;
import cn.gov.mca.dmtp.web.request.PaginationIn;
import cn.gov.mca.dmtp.web.response.PaginationOut;

import java.util.List;
import java.util.Optional;

public interface SysPermissionJdbc {
  PaginationOut<SysPermission> search(String keyword, PaginationIn pi);

  List<SysPermission> getByRoleIds(List<Long> roleIds);

  Optional<SysPermission> findByUniqueKey(String permission, Long id);
}
