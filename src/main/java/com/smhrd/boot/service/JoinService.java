package com.smhrd.boot.service;

import com.smhrd.boot.repository.JoinRepository;
import com.smhrd.boot.repository.MypageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JoinService {
    @Autowired
    private JoinRepository joinrepo;
}
