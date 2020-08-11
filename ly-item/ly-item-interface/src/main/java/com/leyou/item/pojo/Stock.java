package com.leyou.item.pojo;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "tb_stock")
public class Stock {

//    @Id
//    @KeySql(useGeneratedKeys = true)
//    private Long id;
    @Id
    private Long skuId;
    private Long seckillStock;
    private Long seckillTotal;
    private Long stock;
}
