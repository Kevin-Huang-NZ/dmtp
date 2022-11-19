package cn.gov.mca.dmtp.web.controller;

import cn.gov.mca.dmtp.GlobalConst;
import cn.gov.mca.dmtp.core.dao.SysPermissionRepository;
import cn.gov.mca.dmtp.core.model.SysPermission;
import cn.gov.mca.dmtp.core.service.SysPermissionService;
import cn.gov.mca.dmtp.error.CustomizedException;
import cn.gov.mca.dmtp.error.PredefinedError;
import cn.gov.mca.dmtp.web.request.PaginationIn;
import cn.gov.mca.dmtp.web.response.Root;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;

@Tag(name = "权限")
@RestController
@RequestMapping("/api/sys/permission")
public class SysPermissionController {
  @Autowired
  private SysPermissionRepository permissionRepository;
  @Autowired
  private SysPermissionService permissionService;

  @Operation(summary = "分页查询权限")
  @SecurityRequirement(name = GlobalConst.SECURITY_SCHEMES_KEY)
  @PreAuthorize("hasAnyAuthority('sys-permission:*', 'sys-permission:retrieve')")
  @GetMapping("/")
  public Root search(
      @Parameter(name = "paginationIn", description = "分页信息，包含页码(number)和页大小(size)") @Validated
      PaginationIn paginationIn,
      @Parameter(name = "permission", description = "查询关键字") @RequestParam(required = false)
      String keyword) {
    var searchResult = permissionRepository.search(keyword, paginationIn);
    return Root.create(searchResult);
  }

  @Operation(summary = "使用ID查询权限")
  @SecurityRequirement(name = GlobalConst.SECURITY_SCHEMES_KEY)
  @PreAuthorize("hasAnyAuthority('sys-permission:*', 'sys-permission:retrieve')")
  @GetMapping("/{id}")
  public Root findOne(
      @Parameter(name = "id", description = "权限的ID") @PathVariable @Min(0l) Long id) {
    var bean = permissionRepository.findById(id);
    if (bean.isPresent()) {
      return Root.create(bean.get());
    } else {
      return Root.create(PredefinedError.DATA_NOT_EXIST);
    }
  }

  @Operation(summary = "新建权限")
  @SecurityRequirement(name = GlobalConst.SECURITY_SCHEMES_KEY)
  @PreAuthorize("hasAnyAuthority('sys-permission:*', 'sys-permission:create')")
  @PostMapping("/create")
  public Root create(@RequestBody @Validated(value = SysPermission.Create.class) SysPermission bean)
      throws CustomizedException {
    return Root.create(permissionService.createPermission(bean));
  }

  @Operation(summary = "更新权限")
  @PreAuthorize("hasAnyAuthority('sys-permission:*', 'sys-permission:update')")
  @PostMapping("/update")
  public Root update(@RequestBody @Validated(value = SysPermission.Update.class) SysPermission bean)
      throws CustomizedException {
    return Root.create(permissionService.updatePermission(bean));
  }

  @Operation(summary = "使用ID删除权限")
  @SecurityRequirement(name = GlobalConst.SECURITY_SCHEMES_KEY)
  @PreAuthorize("hasAnyAuthority('sys-permission:*', 'sys-permission:delete')")
  @PostMapping("/{id}/del")
  public Root delete(
      @Parameter(name = "id", description = "权限的ID") @PathVariable @Min(0l) Long id) {
    permissionService.deletePermission(id);
    return Root.create();
  }
}
