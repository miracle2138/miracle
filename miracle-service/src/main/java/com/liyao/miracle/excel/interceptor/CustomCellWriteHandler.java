package com.liyao.miracle.excel.interceptor;

import com.alibaba.excel.util.BooleanUtils;
import com.alibaba.excel.write.handler.CellWriteHandler;
import com.alibaba.excel.write.handler.context.CellWriteHandlerContext;

import org.apache.poi.common.usermodel.HyperlinkType;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Hyperlink;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author 栗垚
 * @Date 2022/11/29
 */
@Slf4j
public class CustomCellWriteHandler implements CellWriteHandler {

    @Override
    public void afterCellDispose(CellWriteHandlerContext context) {
        Cell cell = context.getCell();
        // 这里可以对cell进行任何操作
        log.info("第{}行，第{}列写入完成。", cell.getRowIndex(), cell.getColumnIndex());
        if (BooleanUtils.isTrue(context.getHead()) && cell.getColumnIndex() == 0) {
            CreationHelper createHelper = context.getWriteSheetHolder().getSheet().getWorkbook().getCreationHelper();
            Hyperlink hyperlink = createHelper.createHyperlink(HyperlinkType.URL);
            hyperlink.setAddress("https://github.com/alibaba/easyexcel");
            cell.setHyperlink(hyperlink);
        }
    }
}