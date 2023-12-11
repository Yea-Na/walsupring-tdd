package com.walsupring.admin.service;

import com.walsupring.admin.domain.Admin;
import com.walsupring.admin.domain.AdminRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AdminServiceImpl implements AdminService{

    private final AdminRepository adminRepository;

    public AdminServiceImpl(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    public Admin join(String loginId, String password, String name) {
        return adminRepository.save(new Admin(loginId,password,name));
    }

    @Override
    public Admin getAdmin(Long id) {
        Admin admin = adminRepository.findById(id).orElseThrow(() -> new RuntimeException("admin is not found"));
        return admin;
    }

    @Override
    public Admin changePassword(Long id, String password) {
        Admin admin = adminRepository.findById(id).orElseThrow(() -> new RuntimeException("admin is not found"));
        admin.changePassword(password);
        return admin;
    }
}
