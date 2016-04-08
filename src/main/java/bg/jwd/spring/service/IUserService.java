package bg.jwd.spring.service;

import java.util.List;

import bg.jwd.spring.dto.CustomerDTO;
import bg.jwd.spring.model.security.User;


public interface IUserService {

	User createCustomer(String customerName);

	User findCustomerById(long customerId);

	User findCustomerByName(String customerName);

	User saveCustomer(User customer);

	List<CustomerDTO> getAll(CustomerDTO searchPrototype);

}