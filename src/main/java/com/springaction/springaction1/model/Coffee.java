package com.springaction.springaction1.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Coffee implements Serializable{
    private Long id;
    private String name;
    private BigDecimal price;
    private Date createTime;
    private Date updateTime;
}
