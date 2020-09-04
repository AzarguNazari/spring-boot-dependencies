package elasticSearch.service;

import elasticSearch.model.Customer;

import java.util.List;

public interface CustomersInterface {
    List<Customer> searchCity(Integer pageNumber, Integer pageSize, String searchContent);
}
