package com.leyou.item.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;
import java.util.List;

@Table(name = "tb_spu")
@Data
public class Spu {

    @Id
    @KeySql(useGeneratedKeys = true)
    private Long id;
    private Long brandId;
    private Long cid1;
    private Long cid2;
    private Long cid3;

    private String title;
    private String subTitle;
    private Boolean saleable; /* 是否上架 */
    private Boolean valid;
    private Date createTime;

    /*映射到json处理试忽略*/
    @JsonIgnore
    private Date lastUpdateTime; /* 最后修改时间 */

    /* 映射到数据库时忽略 */
    @Transient
    private String cname;
    @Transient
    private String bname;

    @Transient
    private List<Sku> skus;
    @Transient
    private SpuDetail spuDetail;
}
