package com.liyao.miracle.excel.converter;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.data.WriteCellData;
import com.alibaba.excel.metadata.property.ExcelContentProperty;
import com.jssgwl.data.abroad.constant.BuildType;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author 栗垚
 * @Date 2022/11/30
 */
@Slf4j
public class BuildTypeConverter implements Converter<Integer> {
    @Override
    public Class<?> supportJavaTypeKey() {
        return Integer.class;
    }

    @Override
    public WriteCellData<?> convertToExcelData(Integer value, ExcelContentProperty contentProperty, GlobalConfiguration globalConfiguration) throws Exception {
        if (value == null) {
            throw new RuntimeException("build type is null");
        }

        BuildType buildType = BuildType.fromValue(value);
        if (buildType == null) {
            log.warn("invalid build type val: {}", value);
            throw new RuntimeException("build type is invalid");
        }

        return new WriteCellData<String>(buildType.getName());
    }
}

