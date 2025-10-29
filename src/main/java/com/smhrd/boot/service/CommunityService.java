package com.smhrd.boot.service;

import com.smhrd.boot.repository.CommunityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommunityService {
    @Autowired
    private CommunityRepository communityrepo;
}
