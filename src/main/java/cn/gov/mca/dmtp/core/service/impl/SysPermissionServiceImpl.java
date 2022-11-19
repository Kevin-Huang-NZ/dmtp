package cn.gov.mca.dmtp.core.service.impl;

import cn.gov.mca.dmtp.core.dao.SysPermissionRepository;
import cn.gov.mca.dmtp.core.dao.SysRolePermissionRepository;
import cn.gov.mca.dmtp.core.model.SysPermission;
import cn.gov.mca.dmtp.core.service.SysPermissionService;
import cn.gov.mca.dmtp.error.CustomizedException;
import cn.gov.mca.dmtp.error.PredefinedError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class SysPermissionServiceImpl implements SysPermissionService {
  @Autowired
  private SysRolePermissionRepository rolePermissionRepository;
  @Autowired
  private SysPermissionRepository permissionRepository;

  @Transactional
  @Override
  public void deletePermission(Long id) {
    if (permissionRepository.existsById(id)) {
      permissionRepository.deleteById(id);
      rolePermissionRepository.deleteByPermission(id);
    }
  }

  @Override
  public SysPermission createPermission(SysPermission entity) throws CustomizedException {
    var checkExist = permissionRepository.findByUniqueKey(entity.getPermission(), 0l);
    if (checkExist.isPresent()) {
      throw new CustomizedException(PredefinedError.DATA_NOT_EXIST, "权限重复，保存失败。");
    } else {
      return permissionRepository.save(entity);
    }
  }

  @Override
  public SysPermission updatePermission(SysPermission entity) throws CustomizedException {
    var checkExist = permissionRepository.findByUniqueKey(entity.getPermission(), entity.getId());
    if (checkExist.isPresent()) {
      throw new CustomizedException(PredefinedError.DATA_NOT_EXIST, "权限重复，保存失败。");
    } else {
      return permissionRepository.save(entity);
    }
  }
}
