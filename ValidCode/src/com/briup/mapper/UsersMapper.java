package com.briup.mapper;

import org.apache.ibatis.annotations.Select;

import com.briup.pojo.Users;

public interface UsersMapper {
	@Select("select * from user where username=#{username} and password=#{password}")
	Users selByUsersPwd(Users users);
}
