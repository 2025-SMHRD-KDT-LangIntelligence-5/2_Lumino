package com.smhrd.boot.service;

import com.smhrd.boot.repository.LoginRepository;
import com.smhrd.boot.repository.MypageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    @Autowired
    private LoginRepository loginrepo;
}
