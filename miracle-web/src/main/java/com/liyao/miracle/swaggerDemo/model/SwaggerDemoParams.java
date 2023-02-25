package com.liyao.miracle.swaggerDemo.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author 栗垚
 * @Date 2022/11/29
 */
@Data
@ApiModel("新建接口参数")
public class SwaggerDemoParams {

    @ApiModelProperty(name = "名称", required = true, example = "巴西项目")
    private String name;
    @ApiModelProperty(name = "编码", required = true)
    private String code;
    @ApiModelProperty(name = "类型", required = true, notes = "1:统建;2:续建")
    private Integer type;

}
