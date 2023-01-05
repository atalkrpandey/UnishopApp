package com.unishop.services.user;




import java.util.List;

import com.unishop.enums.Category;
import com.unishop.exception.CartException;
import com.unishop.exception.OrderException;
import com.unishop.exception.ProductException;
import com.unishop.exception.UserException;
import com.unishop.model.admin.Product;
import com.unishop.model.user.Cart;
import com.unishop.model.user.CartDTO;
import com.unishop.model.user.OrderDTO;
import com.unishop.model.user.Orders;
import com.unishop.model.user.User;

public interface UserServices {
	
	
    public User regUser(User user)throws UserException;
    
    public User updateUser(User user,String key) throws UserException;
    
    public List<Product> veiwAllProducts() throws ProductException;
    
    public List<Product> veiwProductByCategory(Category category) throws ProductException;
    
    public List<Product> sortedByPrice() throws ProductException;
    
    public List<Product> filterByPrice(Integer price) throws ProductException;

    public Cart addProductToCart(Integer userId, Integer productId) throws CartException, UserException, ProductException;

    public Cart removeProductFromCart(Integer userId, Integer productId) throws CartException, UserException, ProductException;

    public Cart removeAllProduct(Integer userId) throws CartException, UserException;
    
    public Cart increaseProductQuantity(Integer userId, Integer productId) throws CartException, UserException, ProductException;
    
    public Cart decreaseProductQuantity(Integer userId, Integer productId) throws CartException, UserException, ProductException;
    
    public Orders addASingleOrder(Integer userid, Integer productid, Orders order) throws OrderException, UserException, CartException;
    
    public Orders addAllOrder(Integer userid, Orders order) throws OrderException, UserException, CartException;

    public Orders viewOrder(Integer orderId) throws OrderException;
    
    public OrderDTO checkStatus(Integer orderId) throws OrderException;
    
    public CartDTO viewCart(Integer userId) throws CartException, UserException;

    public List<Orders> viewAllOrder() throws OrderException;

    public List<Orders> viewAllOrdersByUserId(Integer userId) throws OrderException;
    
    public Orders cancelOrderByOrderId(Integer orderID) throws OrderException;

}
