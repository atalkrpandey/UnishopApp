package com.unishop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.unishop.enums.Category;
import com.unishop.exception.CartException;
import com.unishop.exception.OrderException;
import com.unishop.exception.ProductException;
import com.unishop.exception.UserException;
import com.unishop.model.user.Orders;
import com.unishop.model.user.User;
import com.unishop.repository.user.OrderDao;
import com.unishop.repository.user.UserDao;
import com.unishop.services.user.UserServices;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServices userServices;
    
    static boolean isLogin = false;
    
    @Autowired
    private UserDao userDao;

    @Autowired
    private OrderDao orderDao;

    @PostMapping("/registerUser")
    public ResponseEntity<User> registerNewUserHandler(@RequestBody User user) throws UserException{
        User user1 = userServices.regUser(user);
        return new ResponseEntity<>(user1, HttpStatus.OK);
    }

    @PutMapping("/updateUser")
    public ResponseEntity<Object> updateUserHandler(@RequestBody User user,@RequestParam(required = false) String key) throws UserException{
        if(isLogin){
            User user1 = userServices.updateUser(user,key);
            return new ResponseEntity<>(user1,HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>("Please Login First ! ",HttpStatus.OK);
    }

    @PostMapping("/addToCart")
    public ResponseEntity<Object> addToCartHandler(@RequestParam("userID") Integer userID,@RequestParam("productID") Integer productID) throws ProductException,UserException, CartException {
        if(isLogin){

            return new ResponseEntity<>(userServices.addProductToCart(userID,productID),HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>("Please Login First ! ",HttpStatus.OK);
    }

    @DeleteMapping("/removeSingleProductFromCart/{userId}/{productId}")
    public ResponseEntity<Object> removeProductFromCart(@PathVariable("userId") Integer userId,
                                                      @PathVariable("productId") Integer productId) throws CartException, UserException, ProductException {
    	
    	if(!isLogin)  return new ResponseEntity<>("Please Login First ! ",HttpStatus.OK); 
    	
        return new ResponseEntity<>(userServices.removeProductFromCart(userId, productId), HttpStatus.OK);
    }
    @DeleteMapping("/makeCartEmpty/{userId}")
    public ResponseEntity<Object> removeAllProduct(@PathVariable("userId") Integer userId) throws CartException, UserException {
    	
    	if(!isLogin)  return new ResponseEntity<>("Please Login First ! ",HttpStatus.OK); 
    	
        return new ResponseEntity<>(userServices.removeAllProduct(userId), HttpStatus.OK);
    }
    
    @DeleteMapping("/CancalOrderByID/{orderId}")
    public ResponseEntity<Object> removeOrderHandler(@PathVariable("orderId") Integer orderId) throws  OrderException {
    	
    	if(!isLogin)  return new ResponseEntity<>("Please Login First ! ",HttpStatus.OK); 
    	
        return new ResponseEntity<>(userServices.cancelOrderByOrderId(orderId), HttpStatus.OK);
    }

    @PutMapping("/increase/{userId}/{productId}")
    public ResponseEntity<Object> increaseProductQuantity(@PathVariable("userId") Integer cartId, @PathVariable("productId") Integer productId) throws CartException, UserException, ProductException {
    	
    	if(!isLogin)  return new ResponseEntity<>("Please Login First ! ",HttpStatus.OK); 
    	
        return new ResponseEntity<>(userServices.increaseProductQuantity(cartId, productId), HttpStatus.OK);
    }

    @PutMapping("/decrease/{userId}/{productId}")
    public ResponseEntity<Object> decreaseProductQuantity(@PathVariable("userId") Integer cartId, @PathVariable("productId") Integer productId) throws CartException, UserException, ProductException {
        
    	if(!isLogin)  return new ResponseEntity<>("Please Login First ! ",HttpStatus.OK); 
    	
    	return new ResponseEntity<>(userServices.decreaseProductQuantity(cartId, productId), HttpStatus.OK);
    }

    @PostMapping("/orderSingleProductFromCart")
    public ResponseEntity<Object> OrderSingleProductHander(@RequestParam("userId") Integer userId,@RequestParam("productId") Integer productId,@RequestBody Orders order)
            throws OrderException, UserException, CartException {
      
    	if(!isLogin)  return new ResponseEntity<>("Please Login First ! ",HttpStatus.OK); 
    	
    	return new ResponseEntity<>(userServices.addASingleOrder(userId,productId,order), HttpStatus.CREATED);
    }
    
    @PostMapping("/orderAllProductFromCart")
    public ResponseEntity<Object> OrderAllProductsOfCart(@RequestParam("userId") Integer userId,@RequestBody Orders order)
            throws OrderException, UserException, CartException {
        
    	if(!isLogin)  return new ResponseEntity<>("Please Login First ! ",HttpStatus.OK); 
    	
    	return new ResponseEntity<>(userServices.addAllOrder(userId,order), HttpStatus.CREATED);
    }


    @GetMapping("/viewOrder/{id}")
    public ResponseEntity<Object> viewOrderById(@PathVariable("id") Integer orderId) throws OrderException {
      
    	if(!isLogin)  return new ResponseEntity<>("Please Login First ! ",HttpStatus.OK); 
    	
    	return new ResponseEntity<>(userServices.viewOrder(orderId), HttpStatus.OK);
    }
    

    @GetMapping("/viewOrderByUserId/{userId}")
    public ResponseEntity<Object> viewOrderByUserId(@PathVariable("userId") Integer userId)
            throws OrderException {
       
    	if(!isLogin)  return new ResponseEntity<>("Please Login First ! ",HttpStatus.OK); 
    	
    	return new ResponseEntity<>(userServices.viewAllOrdersByUserId(userId), HttpStatus.OK);
    }
    
    @GetMapping("/viewAllProductsOfCart/{userId}")
    public ResponseEntity<Object> viewProductsOfCartByUserId(@PathVariable("userId") Integer userId)
            throws OrderException {
       
    	if(!isLogin)  return new ResponseEntity<>("Please Login First ! ",HttpStatus.OK); 
    	
    	return new ResponseEntity<>(userServices.viewCart(userId), HttpStatus.OK);
    }
    
    @GetMapping("/CheckStatusOfOrder/{orderId}")
    public ResponseEntity<Object> viewStatusByOrderId(@PathVariable("orderId") Integer userId)
            throws OrderException {
        
    	if(!isLogin)  return new ResponseEntity<>("Please Login First ! ",HttpStatus.OK); 
    	
    	return new ResponseEntity<>(userServices.checkStatus(userId), HttpStatus.OK);
    }
    
    @GetMapping("/getAllProducts")
    public ResponseEntity<Object> getAllProductsHandler()
            throws OrderException {
      
    	if(!isLogin)  return new ResponseEntity<>("Please Login First ! ",HttpStatus.OK); 
    	
    	return new ResponseEntity<>(userServices.veiwAllProducts(), HttpStatus.OK);
    }
    
    @GetMapping("/getAllProductsByCategory")
    public ResponseEntity<Object> getAllProductsHandlerByCategory(@RequestParam("category") Category category)
            throws OrderException {
       
    	if(!isLogin)  return new ResponseEntity<>("Please Login First ! ",HttpStatus.OK); 
    	
    	return new ResponseEntity<>(userServices.veiwProductByCategory(category), HttpStatus.OK);
    }
    
    @GetMapping("/getAllProductsSortedByPrice")
    public ResponseEntity<Object> getAllProductsHandlerInSortedOrder()
            throws OrderException {
       
    	if(!isLogin)  return new ResponseEntity<>("Please Login First ! ",HttpStatus.OK); 
    	
    	return new ResponseEntity<>(userServices.sortedByPrice(), HttpStatus.OK);
    }
    
    @GetMapping("/getAllProductsBelowPrice")
    public ResponseEntity<Object> getFilteredProductsHandlerByPrice(@RequestParam("price") Integer price)
            throws OrderException {
       
    	if(!isLogin)  return new ResponseEntity<>("Please Login First ! ",HttpStatus.OK); 
    	
    	return new ResponseEntity<>(userServices.filterByPrice(price), HttpStatus.OK);
    }
}
