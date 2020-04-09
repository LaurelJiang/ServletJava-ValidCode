package com.briup.serviceimpl;

import com.briup.mapper.UsersMapper;
import com.briup.pojo.Users;
import com.briup.service.UsersService;

public class UsersServiceImp implements UsersService{
	private UsersMapper usersMapper;
	
	public UsersMapper getUsersMapper() {
		return usersMapper;
	}

	public void setUsersMapper(UsersMapper usersMapper) {
		this.usersMapper = usersMapper;
	}

	@Override
	public Users login(Users users) {
		return usersMapper.selByUsersPwd(users);
	}
	
	
}
