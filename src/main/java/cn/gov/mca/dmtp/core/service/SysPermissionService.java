package cn.gov.mca.dmtp.core.service;

import cn.gov.mca.dmtp.core.model.SysPermission;
import cn.gov.mca.dmtp.error.CustomizedException;

public interface SysPermissionService {
  void deletePermission(Long id);

  SysPermission createPermission(SysPermission entity) throws CustomizedException;

  SysPermission updatePermission(SysPermission entity) throws CustomizedException;
}
