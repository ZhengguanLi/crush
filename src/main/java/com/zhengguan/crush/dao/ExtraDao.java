package com.zhengguan.crush.dao;

import com.zhengguan.crush.entity.Extra;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExtraDao {
    public Extra findById(int id);

    public List<Extra> findAll();
}
