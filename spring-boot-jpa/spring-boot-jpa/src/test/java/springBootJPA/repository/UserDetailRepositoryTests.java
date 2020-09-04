package springBootJPA.repository;

import springBootJPA.model.Address;
import springBootJPA.model.UserDetail;
import springBootJPA.model.UserInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDetailRepositoryTests {

	@Resource
	private AddressRepository addressRepository;
	@Resource
	private UserDetailRepository userDetailRepository;

	@Test
	public void testSaveAddress() {
		Address address = new Address();
		address.setUserId(1L);
		address.setCity("Hong Kong");
		address.setProvince("A provience");
		address.setStreet("a street");
		addressRepository.save(address);
	}

	@Test
	public void testSaveUserDetail() {
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG);
		String formattedDate = dateFormat.format(date);
		UserDetail userDetail=new UserDetail();
		userDetail.setUserId(3L);
		userDetail.setHobby("A hobby");
		userDetail.setAge(28);
		userDetail.setIntroduction("an introduction");
		userDetailRepository.save(userDetail);
	}

	@Test
	public void testUserInfo()  {
		List<UserInfo> userInfos=userDetailRepository.findUserInfo("A hobby");
		for (UserInfo userInfo:userInfos){
			System.out.println("userInfo: "+userInfo.getUserName()+"-"+userInfo.getEmail()+"-"+userInfo.getHobby()+"-"+userInfo.getIntroduction());
		}
	}
}