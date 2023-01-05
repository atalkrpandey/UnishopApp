package com.unishop.services.admin;



import java.time.LocalDate;
import java.util.List;

import com.unishop.enums.Category;
import com.unishop.enums.OrderStatus;
import com.unishop.exception.AdminException;
import com.unishop.exception.OrderException;
import com.unishop.exception.ProductException;
import com.unishop.exception.UserException;
import com.unishop.model.admin.Admin;
import com.unishop.model.admin.Product;
import com.unishop.model.admin.Product;
import com.unishop.model.user.Orders;
import com.unishop.model.user.User;

public interface AdminServices {
	
    public Admin regAdmin(Admin admin)throws AdminException;
    
    public Admin getAdminByID(Integer aid) throws AdminException;
    
    public Admin updateAdmin(Admin admin,String key) throws AdminException;
    
    public Product addProduct(Product product) throws ProductException;

    public List<Product> getAllProduct() throws ProductException;
    
    public Product getProductByID(Integer productID) throws ProductException;
    
    public Product deleteProductByID(Integer productID) throws ProductException;
    
    public Product updateQuantityByID(Integer productID, Integer quantity) throws ProductException;
    
    public List<Orders> getAllOrders() throws OrderException;
    
    public Orders gerOrderById(Integer orderId) throws OrderException;
    
    public Orders updateOrderStatus(Integer orderId, OrderStatus status) throws OrderException;
    
    public User userFindById(Integer userId) throws UserException;
    
    public User userFindByEmail(String email) throws UserException;
    
    public List<User> userFindByName(String name) throws UserException;
    
    public User userFindByMob(String mob) throws UserException;
    
    public List<Orders> cancelOrRefunedOrders() throws OrderException;
    
    public List<Product> salesMadeToday() throws OrderException;
    
    public List<Product> salesMadeLastWeek() throws OrderException;
    
    public List<Product> salesMadeLastMonth() throws OrderException;
    
    public List<Product> salesMadeJantoDec() throws OrderException;
    
    public Product highestSoldProductByRating(LocalDate date1, LocalDate date2) throws ProductException;
    
    public Product highestRatedProductByDuration(LocalDate date1, LocalDate date2) throws ProductException;
    
    public Product highestSoldProductBySalePrice(Integer price1, Integer price2) throws ProductException;
    
    public Product highestSoldProductByCategory(Category category) throws ProductException;
    
    
}
