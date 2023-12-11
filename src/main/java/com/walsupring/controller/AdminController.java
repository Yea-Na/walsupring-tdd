package com.walsupring.controller;

import com.walsupring.admin.domain.Admin;
import com.walsupring.admin.service.AdminService;
import com.walsupring.controller.dto.admin.AdminChangePasswordDto;
import com.walsupring.controller.dto.admin.AdminJoinDto;
import com.walsupring.controller.dto.admin.GetAdminDto;
import com.walsupring.core.response.ApiResult;
import com.walsupring.core.response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admins")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @PostMapping
    public ApiResult<?> join(@RequestBody AdminJoinDto.Request request) {
        adminService.join(request.getLoginId(), request.getPassword(),request.getName());
        return Response.created();
    }

    @GetMapping("/{id}")
    public ApiResult<GetAdminDto.Response> getAdmin(@PathVariable Long id) {
        Admin admin = adminService.getAdmin(id);
        return Response.ok(new GetAdminDto.Response(admin));
    }

    @PatchMapping("/{id}/change-password")
    public ApiResult<AdminChangePasswordDto.Response> changePassword(@PathVariable Long id, @RequestBody AdminChangePasswordDto.Request request) {
        Admin admin = adminService.changePassword(id, request.getPassword());
        return Response.changed(new AdminChangePasswordDto.Response(admin));
    }


}
