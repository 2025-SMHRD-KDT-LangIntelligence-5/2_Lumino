package com.smhrd.boot.service;

import com.smhrd.boot.repository.MypageRepository;
import com.smhrd.boot.repository.WorkspaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MypageService {
    @Autowired
    private MypageRepository mypagerepo;

}
