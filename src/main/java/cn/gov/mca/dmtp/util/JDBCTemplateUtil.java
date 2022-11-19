package cn.gov.mca.dmtp.util;

import cn.gov.mca.dmtp.web.request.PaginationIn;
import cn.gov.mca.dmtp.web.response.PaginationOut;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

public interface JDBCTemplateUtil {
  JdbcTemplate jt();

  NamedParameterJdbcTemplate npjt();

  <T> PaginationOut<T> queryForPagination(
      String sql,
      SqlParameterSource paramSource,
      RowMapper<T> rowMapper,
      PaginationIn paginationIn);

  String generatedKeyName(String columnName);
}
