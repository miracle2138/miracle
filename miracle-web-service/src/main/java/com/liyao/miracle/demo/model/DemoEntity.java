package com.liyao.miracle.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @Author 栗垚
 * @Date 2023/1/1
 */
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DemoEntity {
    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
