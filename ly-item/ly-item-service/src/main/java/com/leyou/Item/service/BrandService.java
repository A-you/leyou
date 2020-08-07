package com.leyou.Item.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.leyou.Item.mapper.BrandMapper;
import com.leyou.common.vo.PageResult;
import com.leyou.item.pojo.Brand;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class BrandService {
    
    @Autowired
    private BrandMapper brandMapper;


    public PageResult<Brand> getBrandByPageMethod(Integer page, Integer rows, String key, String sortBy, Boolean desc) {
        //开始分页
        System.out.println("进服务");
        PageHelper.startPage(page,rows);
        Example example = new Example(Brand.class);
        if(StringUtils.isNotBlank(key)){
            example.createCriteria().andLike("name","%"+key+"%")
                    .orEqualTo("letter",key.toUpperCase());
        }
        if(StringUtils.isNotBlank(sortBy)) {
            String orderBy = sortBy + (desc ? " DESC" : " ASC");
            example.setOrderByClause(orderBy);
        }
        brandMapper.selectByExample(example);

        List<Brand> brands = brandMapper.selectAll();

        PageInfo<Brand> brandPageInfo = new PageInfo<>(brands);
        Long total = brandPageInfo.getTotal();
        Long pages = total/rows;
        return new PageResult<Brand>(total,pages,brands);
    }

}
