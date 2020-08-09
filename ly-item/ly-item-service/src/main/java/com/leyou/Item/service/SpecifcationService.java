package com.leyou.Item.service;

import com.leyou.Item.mapper.SpecGroupMapper;
import com.leyou.item.pojo.SpecGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpecifcationService {

    @Autowired
    private SpecGroupMapper specGroupMapper;

    public List<SpecGroup> querSpecGroupByCid(Long cid) {
        System.out.println("进来没");
        SpecGroup specGroup = new SpecGroup();
        specGroup.setCid(cid);
        List<SpecGroup> groups = specGroupMapper.select(specGroup);
        return groups;
    }
}
