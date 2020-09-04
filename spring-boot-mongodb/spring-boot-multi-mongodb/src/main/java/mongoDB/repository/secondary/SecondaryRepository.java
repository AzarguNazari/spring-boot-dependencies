package mongoDB.repository.secondary;

import mongoDB.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SecondaryRepository extends MongoRepository<User, String> {
}
