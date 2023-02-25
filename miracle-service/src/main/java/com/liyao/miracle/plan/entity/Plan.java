package com.liyao.miracle.plan.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Data;

/**
 * @Author 栗垚
 * @Date 2022/12/26
 */
@Data
public class Plan {
    private Long id;
    private String planName;
    private LocalDateTime updatedTime;
    private Integer status;
    private BigDecimal amount;
}
