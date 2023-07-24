package com.liyao.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.liyao.model.UserDO;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

/**
 * @Author 栗垚
 * @Date 2023/3/8
 */
@Mapper
public interface UserMapper extends BaseMapper<UserDO> {

    default UserDO selectByUsername(@Param("username") String username) {
        return selectOne(new QueryWrapper<UserDO>().eq("username", username));
    }

    default List<UserDO> selectByIds(@Param("ids") Collection<Integer> ids) {
        return selectBatchIds(ids);
    }

    default IPage<UserDO> selectPageByCreateTime(IPage<UserDO> page, @Param("createTime") LocalDateTime createTime) {
        return selectPage(page,
                new QueryWrapper<UserDO>().gt("create_time", createTime));
    }

}
