package com.atguigu.springcloud.alibaba.domain;

import lombok.Data;

/**
 * @author zhangpeng
 * @create 2020/8/31 21:32
 */
@Data
public class Account {
    //id  user_id  total   used    residue
    private Long id;
    private Long userId;
    private Integer total; //总额度
    private Integer used; //使用掉的money
    private Integer residue; //剩余可用额度
}
