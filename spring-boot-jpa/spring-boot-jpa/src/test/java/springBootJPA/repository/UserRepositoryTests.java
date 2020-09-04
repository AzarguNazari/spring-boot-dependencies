package springBootJPA.repository;

import lombok.extern.log4j.Log4j2;
import springBootJPA.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
@Log4j2
public class UserRepositoryTests {

	@Resource
    private UserRepository userRepository;

	@Test
	public void testSave() {
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG);
		String formattedDate = dateFormat.format(date);

		User user1 = User.builder().nickName("aa").userName("aa123456").email("aa@126.com").passWord("aa").regTime(formattedDate).build();
		log.info(user1);
		userRepository.save(user1);

		User user2 = User.builder().nickName("bb").userName("bb123456").email("bb@126.com").passWord("bb").regTime(formattedDate).build();
		log.info(user2);
		userRepository.save(user2);

		User user3 = User.builder().nickName("cc").userName("cc123456").email("cc@126.com").passWord("cc").regTime(formattedDate).build();
		log.info(user3);
		userRepository.save(user3);

//		Assert.assertEquals(3, userRepository.findAll().size());
//		Assert.assertEquals("bb", userRepository.findByUserNameOrEmail("bb", "bb@126.com").getNickName());
//		userRepository.delete(userRepository.findByUserName("aa"));
	}


	@Test
	public void testBaseQuery() {
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG);
		String formattedDate = dateFormat.format(date);
		User user = userRepository.save(User.builder().nickName("ff").userName("ff123456").email("ff@126.com").passWord("ff").regTime(formattedDate).build());

		userRepository.findAll();
		userRepository.findById(3L);
		userRepository.save(user);
		user.setId(2L);
		userRepository.delete(user);
		userRepository.count();
		userRepository.existsById(3L);
	}

	@Test
	public void testCustomSql() {
		userRepository.modifyById("neo",3L);
		userRepository.deleteById(3L);
		userRepository.findByEmail("ff@126.com");
	}


	@Test
	public void testPageQuery()  {
		int page=1,size=2;
		Sort sort = Sort.by(Sort.Direction.DESC, "id");
		Pageable pageable = PageRequest.of(page, size, sort);
		userRepository.findALL(pageable);
		userRepository.findByNickName("aa", pageable);
	}

}