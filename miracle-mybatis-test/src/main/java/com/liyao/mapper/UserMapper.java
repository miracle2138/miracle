package com.liyao.mapper;

import com.liyao.model.UserDO;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

/**
 * @Author 栗垚
 * @Date 2023/3/8
 */
@Repository
public interface UserMapper {

    int insert(UserDO user);

    int updateById(UserDO user);

    int deleteById(@Param("id") Integer id);

    UserDO selectById(@Param("id") Integer id);

    UserDO selectByUsername(@Param("username") String username);

    List<UserDO> selectByIds(@Param("ids") Collection<Integer> ids);

}
