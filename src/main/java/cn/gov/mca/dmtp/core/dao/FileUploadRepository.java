package cn.gov.mca.dmtp.core.dao;

import cn.gov.mca.dmtp.core.model.FileUpload;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FileUploadRepository extends CrudRepository<FileUpload, Long> {

  @Query(
      value = "select * from file_upload where table_name=:tableName and row_id=:rowId",
      nativeQuery = true)
  List<FileUpload> findByOwner(@Param("tableName") String tableName, @Param("rowId") Long rowId);

  @Modifying
  @Query(
      value = "delete from file_upload where table_name=:tableName and row_id=:rowId",
      nativeQuery = true)
  List<FileUpload> deleteByOwner(@Param("tableName") String tableName, @Param("rowId") Long rowId);

  @Modifying
  @Query(
      value = "update file_upload set row_id=:rowId where id=:id",
      nativeQuery = true)
  void updateRowId(@Param("id") Long id, @Param("rowId") Long rowId);
}
