package com.zhengguan.crush.service;

import com.zhengguan.crush.entity.Extra;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ExtraService {
    public Extra findById(int id);

    public List<Extra> findAll();
}