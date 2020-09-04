package springBootJPA.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;
import springBootJPA.param.UserDetailParam;
import springBootJPA.service.UserDetailService;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JpaSpecificationTests {

	@Resource
	private UserDetailService userDetailService;

	@Test
	public void testFindByCondition()  {
		int page=0,size=10;
		Sort sort = Sort.by(Sort.Direction.DESC, "id");
		Pageable pageable = PageRequest.of(page, size, sort);
		UserDetailParam param=new UserDetailParam();
		param.setIntroduction("some introduction");
		param.setMinAge(10);
		param.setMaxAge(30);
		userDetailService.findByCondition(param,pageable).forEach(userDetail -> {
			System.out.println("userDetail: "+userDetail.toString());
		});
	}

}