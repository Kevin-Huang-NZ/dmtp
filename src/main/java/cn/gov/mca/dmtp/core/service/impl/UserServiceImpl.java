package cn.gov.mca.dmtp.core.service.impl;

import cn.gov.mca.dmtp.core.dao.*;
import cn.gov.mca.dmtp.core.model.SysPermission;
import cn.gov.mca.dmtp.core.model.SysRole;
import cn.gov.mca.dmtp.core.model.User;
import cn.gov.mca.dmtp.core.model.UserRole;
import cn.gov.mca.dmtp.core.service.UserService;
import cn.gov.mca.dmtp.error.CustomizedException;
import cn.gov.mca.dmtp.error.PredefinedError;
import cn.gov.mca.dmtp.security.PasswordEncoderUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
  @Autowired
  private UserRoleRepository repository;
  @Autowired
  private UserRepository userRepository;
  @Autowired
  private SysRoleRepository roleRepository;
  @Autowired
  private SysPermissionRepository permissionRepository;
  @Autowired
  private FileUploadRepository fileUploadRepository;
  @Autowired
  private PasswordEncoderUtil passwordEncoderUtil;

  @Override
  public void addRole(UserRole entity) {
    repository.save(entity);
  }

  @Override
  public void removeRole(UserRole entity) {
    repository.deleteByUniqueKey(entity.getUserId(), entity.getRoleId());
  }

  @Transactional
  @Override
  public void deleteUser(Long id) {
    if (userRepository.existsById(id)) {
      userRepository.deleteById(id);
      repository.deleteByUser(id);
    }
  }

  @Override
  public Optional<User> findByUniqueKey(String phone) {
    return userRepository.findByUniqueKey(phone, null);
  }

  //    @Override
  //    public Optional<User> checkPhone(String phone, Long id) {
  //        return userRepository.findByUniqueKey(phone, id);
  //    }

  @Override
  public List<SysRole> getUserRoles(Long userId) {
    return roleRepository.getByUserId(userId);
  }

  @Override
  public List<SysPermission> getUserPermissions(List<Long> roleIds) {
    return permissionRepository.getByRoleIds(roleIds);
  }

  @Transactional
  @Override
  public User createUser(User entity, Long fileId) throws CustomizedException {
    var checkExist = userRepository.findByUniqueKey(entity.getPhone(), 0l);
    if (checkExist.isPresent()) {
      throw new CustomizedException(PredefinedError.DATA_NOT_EXIST, "手机号重复，保存失败。");
    } else {
      log.debug("User password before encoding: {}", entity.getPassword());
      var encodedPassword = passwordEncoderUtil.getPasswordEncoder().encode(entity.getPassword());
      entity.setPassword(encodedPassword);
      log.debug("User password after encoding: {}", entity.getPassword());
      var user = userRepository.save(entity);
      if (fileId != null) {
        fileUploadRepository.updateRowId(fileId, user.getId());
      }
      return user;
    }
  }

  @Transactional
  @Override
  public User updateUser(User entity, Long fileId) throws CustomizedException {
    var checkExist = userRepository.findByUniqueKey(entity.getPhone(), entity.getId());
    if (checkExist.isPresent()) {
      throw new CustomizedException(PredefinedError.DATA_NOT_EXIST, "手机号重复，保存失败。");
    } else {
      var encodedPassword = passwordEncoderUtil.getPasswordEncoder().encode(entity.getPassword());
      entity.setPassword(encodedPassword);
      var user = userRepository.save(entity);
      if (fileId != null) {
        fileUploadRepository.updateRowId(fileId, user.getId());
      }
      return user;
    }
  }
}
