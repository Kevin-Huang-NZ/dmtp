package cn.gov.mca.dmtp.core.dao;

import cn.gov.mca.dmtp.core.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long>, UserJdbc {
}
