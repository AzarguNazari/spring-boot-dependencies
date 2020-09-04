package mongoDB.repository;

import mongoDB.model.User;
import mongoDB.repository.primary.PrimaryRepository;
import mongoDB.repository.secondary.SecondaryRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class MuliDatabaseTest {

    @Autowired
    private PrimaryRepository primaryRepository;

    @Autowired
    private SecondaryRepository secondaryRepository;

    @Test
    public void TestSave() {
        System.out.println("************************************************************");
        System.out.println("start");
        System.out.println("************************************************************");
        this.primaryRepository.save(new User("ahmad", "123456"));
        this.secondaryRepository.save(new User("karim", "654321"));
        List<User> primaries = this.primaryRepository.findAll();
        for (User primary : primaries) {
            System.out.println(primary.toString());
        }
        List<User> secondaries = this.secondaryRepository.findAll();
        for (User secondary : secondaries) {
            System.out.println(secondary.toString());
        }
        System.out.println("************************************************************");
        System.out.println("end");
        System.out.println("************************************************************");
    }

}
