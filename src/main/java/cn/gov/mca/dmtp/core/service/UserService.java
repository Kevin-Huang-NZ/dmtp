package cn.gov.mca.dmtp.core.service;

import cn.gov.mca.dmtp.core.model.SysPermission;
import cn.gov.mca.dmtp.core.model.SysRole;
import cn.gov.mca.dmtp.core.model.User;
import cn.gov.mca.dmtp.core.model.UserRole;
import cn.gov.mca.dmtp.error.CustomizedException;

import java.util.List;
import java.util.Optional;

public interface UserService {
  void addRole(UserRole entity);

  void removeRole(UserRole entity);

  void deleteUser(Long id);

  Optional<User> findByUniqueKey(String phone);

  //    Optional<User> checkPhone(String phone, Long id);
  List<SysRole> getUserRoles(Long userId);

  List<SysPermission> getUserPermissions(List<Long> roleIds);

  User createUser(User entity, Long fileId) throws CustomizedException;

  User updateUser(User entity, Long fileId) throws CustomizedException;
}
