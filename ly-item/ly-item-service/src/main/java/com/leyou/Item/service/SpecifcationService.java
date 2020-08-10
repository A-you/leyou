package com.leyou.Item.service;

import com.leyou.Item.mapper.SpecGroupMapper;
import com.leyou.Item.mapper.SpecParamMapper;
import com.leyou.item.pojo.SpecGroup;
import com.leyou.item.pojo.SpecParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpecifcationService {

    @Autowired
    private SpecGroupMapper specGroupMapper;

    @Autowired
    private SpecParamMapper specParamMapper;

    public List<SpecGroup> querSpecGroupByCid(Long cid) {
        System.out.println("进来没");
        SpecGroup specGroup = new SpecGroup();
        specGroup.setCid(cid);
        List<SpecGroup> groups = specGroupMapper.select(specGroup);
        return groups;
    }

    /**
     * 根据groupId 查寻参数
     * @param gid
     * @return
     */
    public List<SpecParam> getParamsByGid(Long gid) {
        SpecParam specParam = new SpecParam();
        specParam.setGroupId(gid);
        List<SpecParam> params = specParamMapper.select(specParam);
        return params;
    }

    public void createParam(SpecParam specParam) {
        System.out.println("接收到的参数"+specParam);
        int count = specParamMapper.insert(specParam);
        System.out.println("创建是否成功"+count);

    }
}
