package com.leyou.Item.service;

import com.leyou.Item.mapper.CategoryMapper;
import com.leyou.Item.mapper.SpecParamMapper;
import com.leyou.common.enums.ExceptionEnum;
import com.leyou.common.exception.LyException;
import com.leyou.item.pojo.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private SpecParamMapper specParamMapper;

    public List<Category> queryCategoryByPid(Long pid) {
        Category category = new Category();
        category.setParentId(pid);
        List<Category> list = categoryMapper.select(category);
        if(CollectionUtils.isEmpty(list)){
           throw new LyException(ExceptionEnum.NOT_CATEGORY);
        }
        return list;
    }


    /**
     * 同个ids查询
     * @param ids
     * @return
     */
    public List<Category> queryByIds(List<Long> ids){
        List<Category> list = categoryMapper.selectByIdList(ids);
        if(CollectionUtils.isEmpty(list)){
            throw new LyException(ExceptionEnum.NOT_CATEGORY);
        }
        return list;
    }
}
