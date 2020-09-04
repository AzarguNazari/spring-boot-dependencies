package mybatis.mapper.test2;

import java.util.List;

import mybatis.model.User;

public interface User2Mapper {
	List<User> getAll();
	User getOne(Long id);
	void insert(User user);
	void update(User user);
	void delete(Long id);
}