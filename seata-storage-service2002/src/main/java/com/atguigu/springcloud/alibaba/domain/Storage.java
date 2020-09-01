package com.atguigu.springcloud.alibaba.domain;

import lombok.Data;

/**
 * @author zhangpeng
 * @create 2020/8/31 21:28
 */
@Data
public class Storage {
    //id  product_id   total    used  residue
    private Long id;
    private Long productId;
    private Integer total;
    private Integer used;
    private Integer residue;
}
