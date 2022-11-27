package cn.gov.mca.dmtp.core.model;

import cn.gov.mca.dmtp.util.ValidatorPattern;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;
import javax.validation.groups.Default;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
public class FileUpload implements Serializable {
  public interface Create extends Default {
  }

  public interface Update extends Default {
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @NotNull(
      message = "请指定要修改的对象。",
      groups = {Update.class})
  @Null(
      message = "新建对象时，不能指定id。",
      groups = {Create.class})
  private Long id;

  @NotBlank(message = "表名不能为空。")
  @Length(max = 100, message = "表名超长，最多50字符。")
  private String tableName;

  private Long rowId = -1l;

  @NotBlank(message = "路径不能为空。")
  private String fullPath;

  @NotBlank(message = "文件名不能为空。")
  private String fileName;

  @Pattern(regexp = "^[012]$", message = "删除状态选择范围：0-未删除；1-逻辑删除；2-物理删除。")
  private String deleteStatus = "0";
}
