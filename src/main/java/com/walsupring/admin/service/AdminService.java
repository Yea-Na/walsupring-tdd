package com.walsupring.admin.service;

import com.walsupring.admin.domain.Admin;

public interface AdminService  {
     Admin join(String loginId, String password, String name);
     Admin getAdmin(Long id);

     Admin changePassword(Long id, String password);
}
