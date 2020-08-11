package com.leyou.item.pojo;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "tb_spu_detail")
public class SpuDetail {

//    @Id
//    @KeySql(useGeneratedKeys = true)
//    private Long id;
    @Id
    private Long spuId;
    private String description;
    private String specialSpec; /* 商品特殊规格的名称及可选值模板 */
    private String genericSpec; /* 商品的全局规格属性 */
    private String packingList; /* 包装清单 */
    private String afterService; // 售后服务
}
