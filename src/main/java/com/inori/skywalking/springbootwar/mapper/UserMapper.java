package com.inori.skywalking.springbootwar.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.inori.skywalking.springbootwar.model.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User> {

    List<User> getUserInfo(String schema);
}
