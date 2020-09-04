package elasticSearch.service.impl;

import elasticSearch.model.Customer;
import elasticSearch.repository.CustomerRepository;
import elasticSearch.service.CustomersInterface;
import lombok.extern.log4j.Log4j2;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;

import java.util.List;

@Log4j2
public class CustomersInterfaceImpl implements CustomersInterface {

    @Autowired
    private CustomerRepository customerRepository;

   @Override
    public List<Customer> searchCity(Integer pageNumber, Integer pageSize, String searchContent) {

        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        // Function Score Query
        FunctionScoreQueryBuilder functionScoreQueryBuilder = QueryBuilders.functionScoreQuery(QueryBuilders.boolQuery().should(QueryBuilders.matchQuery("cityname", searchContent)),
                ScoreFunctionBuilders.weightFactorFunction(1000));
                /*
                .add(QueryBuilders.boolQuery().should(QueryBuilders.matchQuery("description", searchContent)),
                        ScoreFunctionBuilders.weightFactorFunction(100));*/


        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withPageable(pageable)
                .withQuery(functionScoreQueryBuilder).build();

       log.info("\n searchCity(): searchContent [" + searchContent + "] \n DSL  = \n " + searchQuery.getQuery().toString());

        Page<Customer> searchPageResults = customerRepository.search(searchQuery);
        return searchPageResults.getContent();
    }
}
