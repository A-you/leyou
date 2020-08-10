package com.leyou.item.pojo;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "tb_spec_param")
@Data
public class SpecParam {

    @Id
    @KeySql(useGeneratedKeys = true)
    private Long id;
    private String name;
    private Long cid;
    private Long groupId;

    @Column(name="`numeric`")
    private Boolean numeric;

    private String unit;
    private Boolean generic; /* 是都是sku的特有属性 */

    private Boolean searching; /* 是否用于搜索 */
    private String segments; /* 搜索描述 */
}
