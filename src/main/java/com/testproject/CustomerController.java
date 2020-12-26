package com.testproject;


import com.testproject.models.Customer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class CustomerController {


    CustomerDao customerDao = new CustomerDao();

/*
    Повне видалення об'єкта з бд
 */
    @DeleteMapping("/api/customers/{id}")
    public ResponseEntity<Long> deleteCustomer(@PathVariable Long id){
        customerDao.sqlDeleteCustomer(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

/*
     "Видалення" об'єкта , залишаючи його в базі
 */
//    @DeleteMapping("/api/customers/{id}")
//    public ResponseEntity<Long> fakeDelete(@PathVariable Long id){
//        customerDao.fakeDelete(id);
//        return new ResponseEntity<>(id, HttpStatus.OK);
//    }

    @PostMapping("/api/customers")
    @ResponseBody
    public Customer addCustomer(@RequestBody Customer customer) {

        customerDao.sqlAddCustomer(customer);
        return customer;
    }

    @GetMapping("/api/customers")
    public String readAllCustomers() {
        return customerDao.sqlGetAllCustomers().toString();
    }

    @GetMapping("/api/customers/{id}")
    public String readCustomer(@PathVariable String id) {
        return customerDao.sqlFindCustomerById(Long.valueOf(id)).toString();
    }

    @PutMapping("/api/customers/{id}")
    @ResponseBody
    public String updateCustomer(@PathVariable(value = "id") Long customerId,@RequestBody Customer customer) {

        customerDao.sqlUpdateCustomer(customer.getId(), customer.getFull_name(), customer.getPhone());
        return customerDao.sqlFindCustomerById(customerId).toString();
    }


}



