package com.leyou.item.pojo;


import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;

@Data
@Table(name = "tb_sku")
public class Sku {

    @Id
    @KeySql(useGeneratedKeys = true)
    private Long id;
    private String images;
    private String indexes;
    private String ownSpec;
    private String price;
    private String title;
    private Long spuId;
    private Boolean enable;
    private Date createTime;
    private Date lastUpdateTime;
    @Transient
    private Long stock;
}
