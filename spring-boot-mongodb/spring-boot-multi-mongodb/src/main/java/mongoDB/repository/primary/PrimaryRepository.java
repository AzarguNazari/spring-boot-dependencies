package mongoDB.repository.primary;

import mongoDB.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PrimaryRepository extends MongoRepository<User, String> {
}
