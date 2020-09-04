
package elasticSearch.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

@Document(indexName = "customer", type = "customer", shards = 1, replicas = 0, refreshInterval = "-1")
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Customer {

	@Id @NonNull
	private String id;

	@NonNull
	private String userName;

	private String address;

	@NonNull
	private int age;
}
