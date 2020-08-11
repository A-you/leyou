package com.leyou.Item.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.leyou.Item.mapper.SkuMapper;
import com.leyou.Item.mapper.SpuDetailMapper;
import com.leyou.Item.mapper.SpuMapper;
import com.leyou.Item.mapper.StockMapper;
import com.leyou.common.enums.ExceptionEnum;
import com.leyou.common.exception.LyException;
import com.leyou.common.vo.PageResult;
import com.leyou.item.pojo.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class GoodsService {

    @Autowired
    private SpuMapper spuMapper;

    @Autowired
    private SpuDetailMapper spuDetailMapper;

    @Autowired
    private SkuMapper skuMapper;

    @Autowired
    private StockMapper stockMapper;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private BrandService brandService;


    public PageResult<Spu> querySpuByPage(Integer page, Integer rows, Boolean saleable, String key) {
        PageHelper.startPage(page,rows);

        Example example = new Example(Spu.class);
        Example.Criteria criteria = example.createCriteria();
        if(StringUtils.isNotBlank(key)){
            criteria.andLike("title","%"+key+"%");
        }

        if(saleable != null){
            criteria.andEqualTo("saleable",saleable);
        }

        //默认排序
        example.setOrderByClause("create_time DESC");

        List<Spu> spus = spuMapper.selectByExample(example);


        /*暂定抛出异常，其实是不用抛出的*/
        if(CollectionUtils.isEmpty(spus)){
            throw new LyException(ExceptionEnum.NOT_GOODS);
        }

        loadCategoryAndBrandName(spus);

        PageInfo<Spu> info = new PageInfo<>(spus);
        return new PageResult<>(info.getTotal(),spus);
    }

    private void loadCategoryAndBrandName(List<Spu> spus) {

        for (Spu spu : spus) {
            //分类处理
            List<String> cnameList = categoryService.queryByIds(Arrays.asList(spu.getCid1(), spu.getCid2(), spu.getCid3()))
                    .stream().map(Category::getName).collect(Collectors.toList());
            spu.setCname(StringUtils.join(cnameList,"/"));

            //品牌处理
            String bname = brandService.queryBrandById(spu.getBrandId()).getName();
            spu.setBname(bname);
        }
    }

    /**
     * 保存商品列表
     * @param spu
     */
    @Transactional
    public void saveGoods(Spu spu) {
        spu.setCreateTime(new Date());
        spu.setLastUpdateTime(new Date());
        spu.setSaleable(true);
        spu.setValid(true);

        int count = spuMapper.insert(spu);
        if(count != 1){
            throw new LyException(ExceptionEnum.NOT_CATEGORY);
        }
        /* 添加spuDetail */
        SpuDetail spuDetail = spu.getSpuDetail();
        spuDetail.setSpuId(spu.getId());
        count = spuDetailMapper.insert(spuDetail);
        if(count !=1 ){
            throw new LyException(ExceptionEnum.NOT_CATEGORY);
        }
        /* 添加sku */
        List<Sku> skus = spu.getSkus();
        for (Sku sku : skus) {
            sku.setSpuId(spu.getId());
            sku.setCreateTime(new Date());
            sku.setLastUpdateTime(new Date());
            count = skuMapper.insert(sku);
            if(count !=1){
                throw new LyException(ExceptionEnum.NOT_CATEGORY);
            }
            Stock stock = new Stock();
            stock.setSkuId(sku.getId());
            stock.setStock(sku.getStock());
            count = stockMapper.insert(stock);
            if(count !=1){
                throw new LyException(ExceptionEnum.NOT_CATEGORY);
            }
        }


    }

    public SpuDetail querySpuDetaiByPid(Long pid) {
        SpuDetail spuDetail = new SpuDetail();
        spuDetail.setSpuId(pid);
        SpuDetail spuDetail1 = spuDetailMapper.selectByPrimaryKey(pid);
        return spuDetail1;

    }

    public List<Sku> querySkuListById(Long id) {
        Sku sku = new Sku();
        sku.setSpuId(id);
        List<Sku> skus = skuMapper.select(sku);

        List<Long> ids = skus.stream().map(Sku::getId).collect(Collectors.toList());
        List<Stock> stocks = stockMapper.selectByIdList(ids);
//        System.out.println("打印、"+stocks.toString());
//        for (Sku s : skus) {
//            Stock stock = new Stock();
//            stock.setSkuId(s.getId());
//            Stock sto = stockMapper.selectByPrimaryKey(s.getId());
//            s.setStock(sto.getStock());
//        }
        Map<Long, Long> stockMap = stocks.stream().collect(Collectors.toMap(Stock::getSkuId, Stock::getStock));
        skus.forEach(s->s.setStock(stockMap.get(s.getId())));
        return skus;
    }
}
