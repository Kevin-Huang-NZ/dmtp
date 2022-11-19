package cn.gov.mca.dmtp.core.dao.impl;

import cn.gov.mca.dmtp.core.dao.UserJdbc;
import cn.gov.mca.dmtp.core.model.User;
import cn.gov.mca.dmtp.util.JDBCTemplateUtil;
import cn.gov.mca.dmtp.web.request.PaginationIn;
import cn.gov.mca.dmtp.web.response.PaginationOut;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserJdbcImpl implements UserJdbc {
  @Autowired
  private JDBCTemplateUtil jtUtil;

  private RowMapper<User> rowMapper =
      (rs, row) -> {
        User bean = new User();
        bean.setId(rs.getLong("id"));
        bean.setUserName(rs.getString("user_name"));
        bean.setAvatar(rs.getString("avatar"));
        bean.setGender(rs.getString("gender"));
        bean.setBirthday(rs.getString("birthday"));
        bean.setPhone(rs.getString("phone"));
        bean.setPassword(rs.getString("password"));
        bean.setStatus(rs.getString("status"));
        return bean;
      };

  @Override
  public PaginationOut<User> search(String keyword, PaginationIn pi) {
    String anyPosition = "%" + keyword + "%";
    String endWith = "%" + keyword;
    String startWith = keyword + "%";

    StringBuilder sql = new StringBuilder("select * from user where 1=1 ");
    MapSqlParameterSource params = new MapSqlParameterSource();
    if (!StringUtils.isEmpty(keyword)) {
      sql.append("and (user_name like :userName or phone like :phone) ");
      params.addValue("userName", anyPosition);
      params.addValue("phone", endWith);
    }

    return jtUtil.queryForPagination(sql.toString(), params, rowMapper, pi);
  }

  @Override
  public Optional<User> findByUniqueKey(String phone, Long id) {
    StringBuilder sql = new StringBuilder("select * from user where phone=:phone ");
    MapSqlParameterSource params = new MapSqlParameterSource();
    params.addValue("phone", phone);
    if (id != null) {
      sql.append("AND id!=:id ");
      params.addValue("id", id);
    }
    var tmp = jtUtil.npjt().query(sql.toString(), params, rowMapper);
    if (tmp == null || tmp.size() == 0) {
      return Optional.ofNullable(null);
    } else {
      return Optional.ofNullable(tmp.get(0));
    }
  }
}
