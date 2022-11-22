package cn.gov.mca.dmtp.web.controller;

import cn.gov.mca.dmtp.GlobalConst;
import cn.gov.mca.dmtp.core.dao.UserRepository;
import cn.gov.mca.dmtp.core.model.FileUpload;
import cn.gov.mca.dmtp.core.model.User;
import cn.gov.mca.dmtp.core.model.UserRole;
import cn.gov.mca.dmtp.core.service.FileUploadService;
import cn.gov.mca.dmtp.core.service.UserService;
import cn.gov.mca.dmtp.error.CustomizedException;
import cn.gov.mca.dmtp.error.PredefinedError;
import cn.gov.mca.dmtp.web.request.PaginationIn;
import cn.gov.mca.dmtp.web.response.Root;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Min;

import static org.apache.commons.lang3.StringUtils.isEmpty;

@Tag(name = "文件上传")
@Slf4j
@RestController
@RequestMapping("/api/file")
public class FileUploadController {
  @Autowired
  private FileUploadService fleUploadService;

  @Operation(summary = "上传文件")
  @SecurityRequirement(name = GlobalConst.SECURITY_SCHEMES_KEY)
  @PreAuthorize("hasAnyAuthority('file-upload:*', 'file-upload:upload')")
  @PostMapping(path = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public Root search(
      @Parameter(name = "file", description = "上传的文件对象") @RequestPart(value = "file", required = true)
      MultipartFile file,
      @Parameter(name = "tableName", description = "文件所属对象所在表") @RequestParam("tableName")
      String tableName,
      @Parameter(name = "rowId", description = "文件所属对象的ID") @RequestParam(name = "rowId", required = false)
      Long rowId) throws CustomizedException {
    if (file == null || isEmpty(tableName)) {
      throw new CustomizedException(PredefinedError.BAD_REQUEST, "未指定上传文件用途，上传失败。");
    }
    FileUpload fileUpload = new FileUpload();
    fileUpload.setTableName(tableName);
    if (rowId != null) {
      fileUpload.setRowId(rowId);
    }
    var uploadedFile = fleUploadService.store(file, fileUpload);
    return Root.create(uploadedFile);
  }
}
