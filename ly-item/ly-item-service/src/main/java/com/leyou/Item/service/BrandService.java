package com.leyou.Item.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.leyou.Item.mapper.BrandMapper;
import com.leyou.common.enums.ExceptionEnum;
import com.leyou.common.exception.LyException;
import com.leyou.common.vo.PageResult;
import com.leyou.item.pojo.Brand;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class BrandService {
    
    @Autowired
    private BrandMapper brandMapper;


    public PageResult<Brand> getBrandByPageMethod(Integer page, Integer rows, String key, String sortBy, Boolean desc) {
        //开始分页
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
        List<Brand> brands = brandMapper.selectByExample(example);;
        PageInfo<Brand> brandPageInfo = new PageInfo<>(brands);
        List<Brand> list = brandPageInfo.getList();
        Long total = brandPageInfo.getTotal();
        Long pages = total/rows;
        return new PageResult<Brand>(total,pages,list);
    }



    @Transactional
    public void saveBrand(Brand brand, List<Long> cids) {
        brand.setId(null);
        int count = brandMapper.insert(brand);
        if(count !=1 ){
            throw new LyException(ExceptionEnum.CREATE_FAIL);
        }
        for (Long cid : cids) {
            count = brandMapper.insertCategoryBrand(cid,brand.getId());
            if(count != 1){
                throw new LyException(ExceptionEnum.CREATE_FAIL);
            }
        }

    }

    public Brand queryBrandById(Long id){
        Brand brand = brandMapper.selectByPrimaryKey(id);
        if(brand == null){
            throw new LyException(ExceptionEnum.NOT_GOODS);
        }
        return brand;
    }

    public List<Brand> queryBrandByCid(Long cid) {
        Brand brand = new Brand();
        List<Brand> brands = brandMapper.queryByCategoryId(cid);
        return brands;
    }
}
