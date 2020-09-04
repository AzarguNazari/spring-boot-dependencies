package elasticSearch.repository;

import elasticSearch.model.Customer;
import lombok.extern.log4j.Log4j2;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.metrics.sum.InternalSum;
import org.elasticsearch.search.aggregations.metrics.sum.SumAggregationBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.ResultsExtractor;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
@Log4j2
public class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository repository;

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Test
    public void saveCustomers() {
        repository.save(new Customer("Alice", "bamberg 1",13));
        repository.save(new Customer("Bob", "New York",23));
        repository.save(new Customer("neo", "Chikago",30));
        repository.save(new Customer("summer", "Japan",22));
    }

    @Test
    public void fetchAllCustomers() {
        log.info("Customers found with findAll():");
        log.info("-------------------------------");
        repository.findAll().forEach(log::info);
    }

    @Test
    public void deleteCustomers() {
        repository.deleteAll();
//        repository.deleteByUserName("neo");
    }

    @Test
    public void updateCustomers() {
        Customer customer= repository.findByUserName("summer");
        log.info(customer);
        customer.setAddress("new address");
        repository.save(customer);
        Customer xcustomer=repository.findByUserName("summer");
        log.info(xcustomer);
    }

    @Test
    public void fetchIndividualCustomers() {
        log.info("Customer found with findByUserName('summer'):");
        log.info("--------------------------------");
        log.info(repository.findByUserName("summer"));
        log.info("--------------------------------");
        log.info("Customers found with findByAddress(\"Chikago\"):");
        String q= "Chikago";
        repository.findByAddress(q).forEach(log::info);
    }

    @Test
    public void fetchPageCustomers() {
        log.info("Customers found with fetchPageCustomers:");
        log.info("-------------------------------");
        Sort sort = Sort.by(Sort.Direction.DESC, "address.keyword");
        Pageable pageable = PageRequest.of(0, 10, sort);
        Page<Customer> customers = repository.findByAddress("Chikago", pageable);
        log.info("Page customers " + customers.getContent().toString());
    }

    @Test
    public void fetchPage2Customers() {
        log.info("Customers found with fetchPageCustomers:");
        log.info("-------------------------------");
       QueryBuilder customerQuery = QueryBuilders.boolQuery()
                .must(QueryBuilders.matchQuery("address", "Chikago"));
        Page<Customer> page = repository.search(customerQuery, PageRequest.of(0, 10));
        log.info("Page customers "+page.getContent().toString());
        page = repository.search(customerQuery, PageRequest.of(1, 10));
        log.info("Page customers "+page.getContent().toString());
    }

    @Test
    public void fetchAggregation() {
        log.info("Customers found with fetchAggregation:");
        log.info("-------------------------------");

       QueryBuilder customerQuery = QueryBuilders.boolQuery()
                .must(QueryBuilders.matchQuery("address", "Chikago"));

        SumAggregationBuilder sumBuilder = AggregationBuilders.sum("sumAge").field("age");

        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(customerQuery)
                .addAggregation(sumBuilder)
                .build();

        Aggregations aggregations = elasticsearchTemplate.query(searchQuery, new ResultsExtractor<Aggregations>() {
            @Override
            public Aggregations extract(SearchResponse response) {
                return response.getAggregations();
            }
        });


        Map<String, Aggregation> aggregationMap = aggregations.asMap();
        InternalSum sumAge = (InternalSum) aggregationMap.get("sumAge");
        log.info("sum age is "+sumAge.getValue());
    }

}
