package com.leyou.item.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "tb_brand")
@Data
public class Brand {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @KeySql(useGeneratedKeys = true)
    private Long id;
    private String name;
    private String image;
    private String letter;
}
