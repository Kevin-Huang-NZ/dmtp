package cn.gov.mca.dmtp.core.dao;

import cn.gov.mca.dmtp.core.model.SysPermission;
import org.springframework.data.repository.CrudRepository;

public interface SysPermissionRepository
    extends CrudRepository<SysPermission, Long>, SysPermissionJdbc {
}
