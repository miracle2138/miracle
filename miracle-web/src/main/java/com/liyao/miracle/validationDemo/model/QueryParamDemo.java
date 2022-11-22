package com.liyao.miracle.validationDemo.model;

import com.liyao.miracle.common.ValidateGroup;

import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Singular;

/**
 * @Author 栗垚
 * @Date 2022/11/21
 */
@Builder
@Data
@AllArgsConstructor
public class QueryParamDemo {

    @Min(value = 1L, message = "id需要等于等于1")
    @Max(value = 10L, message = "{k.v.message}")
    private Long id;

    @NotBlank(message = "value不能为空")
    private String value;
    @NotEmpty
    @Singular
    private List<String> names;

    @NotNull(message = "projectId不能为空", groups = ValidateGroup.GroupAdd.class)
    @Min(value = 1L, message = "projectId大于1", groups = ValidateGroup.GroupUpdate.class)
    private Long projectId;
}
