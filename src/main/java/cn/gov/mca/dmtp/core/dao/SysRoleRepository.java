package cn.gov.mca.dmtp.core.dao;

import cn.gov.mca.dmtp.core.model.SysRole;
import org.springframework.data.repository.CrudRepository;

public interface SysRoleRepository extends CrudRepository<SysRole, Long>, SysRoleJdbc {
}
