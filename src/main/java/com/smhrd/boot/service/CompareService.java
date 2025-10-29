package com.smhrd.boot.service;

import com.smhrd.boot.repository.CompareRepository;
import com.smhrd.boot.repository.MypageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompareService {
    @Autowired
    private CompareRepository comparerepo;
}
