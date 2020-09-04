package mybatis.mapper;

import java.util.List;

import mybatis.model.User;

public interface UserMapper {
	List<User> getAll();
	User getOne(Long id);
	void insert(User user);
	void update(User user);
	void delete(Long id);
}