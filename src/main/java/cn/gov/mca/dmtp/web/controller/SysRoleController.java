package cn.gov.mca.dmtp.web.controller;

import cn.gov.mca.dmtp.GlobalConst;
import cn.gov.mca.dmtp.core.dao.SysRoleRepository;
import cn.gov.mca.dmtp.core.model.SysRole;
import cn.gov.mca.dmtp.core.model.SysRolePermission;
import cn.gov.mca.dmtp.core.service.SysRoleService;
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

@Tag(name = "角色")
@RestController
@RequestMapping("/api/sys/roles")
public class SysRoleController {
  @Autowired
  private SysRoleRepository roleRepository;
  @Autowired
  private SysRoleService roleService;

  @Operation(summary = "分页查询角色")
  @SecurityRequirement(name = GlobalConst.SECURITY_SCHEMES_KEY)
  @PreAuthorize("hasAnyAuthority('sys-role:*', 'sys-role:retrieve')")
  @GetMapping("/")
  public Root search(
      @Parameter(name = "paginationIn", description = "分页信息，包含页码(number)和页大小(size)") @Validated
      PaginationIn paginationIn,
      @Parameter(name = "keyword", description = "查询关键字") @RequestParam(required = false)
      String keyword) {
    var searchResult = roleRepository.search(keyword, paginationIn);
    return Root.create(searchResult);
  }

  @Operation(summary = "使用ID查询角色")
  @SecurityRequirement(name = GlobalConst.SECURITY_SCHEMES_KEY)
  @PreAuthorize("hasAnyAuthority('sys-role:*', 'sys-role:retrieve')")
  @GetMapping("/{id}")
  public Root findOne(
      @Parameter(name = "id", description = "角色的ID") @PathVariable @Min(0l) Long id) {
    var bean = roleRepository.findById(id);
    if (bean.isPresent()) {
      return Root.create(bean.get());
    } else {
      return Root.create(PredefinedError.DATA_NOT_EXIST);
    }
  }

  @Operation(summary = "新建角色")
  @SecurityRequirement(name = GlobalConst.SECURITY_SCHEMES_KEY)
  @PreAuthorize("hasAnyAuthority('sys-role:*', 'sys-role:create')")
  @PostMapping("/create")
  public Root create(@RequestBody @Validated(value = SysRole.Create.class) SysRole entity)
      throws CustomizedException {
    return Root.create(roleService.createRole(entity));
  }

  @Operation(summary = "更新角色")
  @SecurityRequirement(name = GlobalConst.SECURITY_SCHEMES_KEY)
  @PreAuthorize("hasAnyAuthority('sys-role:*', 'sys-role:update')")
  @PostMapping("/save")
  public Root update(@RequestBody @Validated(value = SysRole.Update.class) SysRole entity)
      throws CustomizedException {
    return Root.create(roleService.updateRole(entity));
  }

  @Operation(summary = "使用ID删除角色")
  @SecurityRequirement(name = GlobalConst.SECURITY_SCHEMES_KEY)
  @PreAuthorize("hasAnyAuthority('sys-role:*', 'sys-role:delete')")
  @PostMapping("/{id}/del")
  public Root delete(
      @Parameter(name = "id", description = "角色的ID") @PathVariable @Min(0l) Long id) {
    roleService.deleteRole(id);
    return Root.create();
  }

  @Operation(summary = "为角色增加某个权限")
  @SecurityRequirement(name = GlobalConst.SECURITY_SCHEMES_KEY)
  @PreAuthorize("hasAnyAuthority('sys-role:*', 'sys-role:add-permission')")
  @PostMapping("/{id}/add-perms/{permissionId}")
  public Root addPermission(
      @Parameter(name = "id", description = "角色的ID") @PathVariable @Min(0l) Long id,
      @Parameter(name = "permissionId", description = "权限的ID") @PathVariable @Min(0l)
      Long permissionId) {
    roleService.addPermission(new SysRolePermission(id, permissionId));
    return Root.create();
  }

  @Operation(summary = "删除角色的某个权限")
  @SecurityRequirement(name = GlobalConst.SECURITY_SCHEMES_KEY)
  @PreAuthorize("hasAnyAuthority('sys-role:*', 'sys-role:remove-permission')")
  @PostMapping("/{id}/remove-perms/{permissionId}")
  public Root removePermission(
      @Parameter(name = "id", description = "角色的ID") @PathVariable @Min(0l) Long id,
      @Parameter(name = "permissionId", description = "权限的ID") @PathVariable @Min(0l)
      Long permissionId) {
    roleService.removePermission(new SysRolePermission(id, permissionId));
    return Root.create();
  }
}
