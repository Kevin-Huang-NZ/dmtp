package cn.gov.mca.dmtp.core.service;

import cn.gov.mca.dmtp.core.model.SysRole;
import cn.gov.mca.dmtp.core.model.SysRolePermission;
import cn.gov.mca.dmtp.error.CustomizedException;

public interface SysRoleService {
  void addPermission(SysRolePermission entity);

  void removePermission(SysRolePermission entity);

  void deleteRole(Long id);

  SysRole createRole(SysRole entity) throws CustomizedException;

  SysRole updateRole(SysRole entity) throws CustomizedException;
}
