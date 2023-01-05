package com.unishop.controller;

import java.time.LocalDate;
import java.util.List;

import javax.websocket.server.PathParam;

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
import com.unishop.enums.OrderStatus;
import com.unishop.exception.AdminException;
import com.unishop.exception.OrderException;
import com.unishop.exception.ProductException;
import com.unishop.exception.UserException;
import com.unishop.model.admin.Admin;
import com.unishop.model.admin.Product;
import com.unishop.model.user.Orders;
import com.unishop.model.user.User;
import com.unishop.services.admin.AdminServices;
import com.unishop.services.user.UserServices;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminServices adminServices;
    
    @Autowired
    private UserServices userServices;


    static boolean isLogin = false;

    @PostMapping("/registerAdmin")
    public ResponseEntity<Admin> registerAdminHandler(@RequestBody Admin admin)throws AdminException {
        Admin admin1 = adminServices.regAdmin(admin);
        return new ResponseEntity<>(admin1, HttpStatus.ACCEPTED);
    }

    @GetMapping("/admin/{adminID}")
    public ResponseEntity<Admin> getAdminByIDHandler(@PathVariable("adminID") Integer aid){
        Admin admin = adminServices.getAdminByID(aid);
        return new ResponseEntity<>(admin,HttpStatus.OK);
    }

    @PutMapping("/updateAdmin")
    public ResponseEntity<Object> updateAdminHandler(@RequestBody Admin admin,@RequestParam(required = false) String key) throws AdminException{
        if(isLogin){
            Admin updatedAdmin = adminServices.updateAdmin(admin,key);
            return new ResponseEntity<>(updatedAdmin,HttpStatus.ACCEPTED);
        }else{
            return new ResponseEntity<>("Please login first",HttpStatus.OK);
        }
    }


    @PostMapping("/addProduct")
    public ResponseEntity<Object> addProductHandler(@RequestBody Product product) throws ProductException{
        if(isLogin){
            Product product1 = adminServices.addProduct(product);
            return new ResponseEntity<>(product1,HttpStatus.ACCEPTED);
        }else {
            return new ResponseEntity<>("Please Login First !",HttpStatus.OK);
        }
    }



    @GetMapping("/getAllProducts")
    public  ResponseEntity<Object> getAllProductHandler()throws ProductException{
        if(isLogin){
            List<Product> products = adminServices.getAllProduct();
            return new ResponseEntity<>(products, HttpStatus.OK);
        }
        return new ResponseEntity<>("Please Login First ! ",HttpStatus.OK);
    }

    @GetMapping("/getProductDetails/{productID}")
    public ResponseEntity<Object> getProductDetailsByIDHandler(@PathVariable("productID") Integer productID) throws ProductException{
        if(isLogin){
            Product product = adminServices.getProductByID(productID);
            return new ResponseEntity<>(product,HttpStatus.OK);
        }
        return new ResponseEntity<>("Please Login First ! ",HttpStatus.OK);
    }

    @DeleteMapping("/deleteProduct/{productID}")
    public ResponseEntity<Object> deleteProductByIDHandler(@PathVariable("productID") Integer productID) throws ProductException{
        if(isLogin){
            Product product = adminServices.getProductByID(productID);
            return new ResponseEntity<>(product,HttpStatus.OK);
        }
        return new ResponseEntity<>("Please Login First ! ",HttpStatus.OK);
    }
    
    @PutMapping("/updateQuantityByID/{productId}/{quantity}")
    public ResponseEntity<Object> updateProductQuantityHandler(@PathVariable("productId") Integer id, @PathVariable("quantity") Integer quan){
        if(isLogin){
        	
        	Product pro = adminServices.updateQuantityByID(id, quan);
        	
           return new ResponseEntity<Object>(pro,HttpStatus.ACCEPTED);
        }else{
            return new ResponseEntity<>("Please login first",HttpStatus.OK);
        }
    }
    
    @GetMapping("/viewListOfOrders")
    public ResponseEntity<Object> viewAllOrder() throws OrderException {
    	if(isLogin){
        return new ResponseEntity<>(userServices.viewAllOrder(), HttpStatus.OK);
    	}
    	 return new ResponseEntity<>("Please Login First ! ",HttpStatus.OK);
    }
    
    
    @GetMapping("/getAllOrdersDetails")
    public ResponseEntity<Object> getOrdersDetailsByIDHandler() throws ProductException{
        if(isLogin){
            List<Orders> order = adminServices.getAllOrders();
            return new ResponseEntity<>(order,HttpStatus.OK);
        }
        return new ResponseEntity<>("Please Login First ! ",HttpStatus.OK);
    }  
    
    @GetMapping("/getOrdersDetailsById/{id}")
    public ResponseEntity<Object> getOrdersDetailsByIDHandlerById(@PathVariable("id") Integer orderId) throws ProductException{
        if(isLogin){
            Orders order = adminServices.gerOrderById(orderId);
            return new ResponseEntity<>(order,HttpStatus.OK);
        }
        return new ResponseEntity<>("Please Login First ! ",HttpStatus.OK);
    }  
    
    
    @PutMapping("/updateOrderStatus/{orderId}")
    public ResponseEntity<Object> updateProductQuantityHandler(@PathVariable("orderId") Integer id, @RequestParam("status") OrderStatus status){
        if(isLogin){
        	
        	Orders order = adminServices.updateOrderStatus(id, status);
        	
           return new ResponseEntity<Object>(order,HttpStatus.ACCEPTED);
        }else{
            return new ResponseEntity<>("Please login first",HttpStatus.OK);
        }
        
    }
    
    
    @GetMapping("/getUserDetailsById/{userId}")
    public ResponseEntity<Object> getUserDetailsByIDHandlerById(@PathVariable("userId") Integer id) throws ProductException{
        if(isLogin){
            User user = adminServices.userFindById(id);
            return new ResponseEntity<>(user,HttpStatus.OK);
        }
        return new ResponseEntity<>("Please Login First ! ",HttpStatus.OK);
    } 
    
    @GetMapping("/getUserDetailsByEmailId/{emailId}")
    public ResponseEntity<Object> getUserDetailsByIDHandlerByEmailId(@PathVariable("emailId") String id) throws ProductException{
        if(isLogin){
            User user = adminServices.userFindByEmail(id);
            return new ResponseEntity<>(user,HttpStatus.OK);
        }
        return new ResponseEntity<>("Please Login First ! ",HttpStatus.OK);
    } 
    
    @GetMapping("/getUserDetailsByName/{name}")
    public ResponseEntity<Object> getUserDetailsByIDHandlerByName(@PathVariable("name") String name) throws ProductException{
        if(isLogin){
            List<User> users = adminServices.userFindByName(name);
            return new ResponseEntity<>(users,HttpStatus.OK);
        }
        return new ResponseEntity<>("Please Login First ! ",HttpStatus.OK);
    } 
    
    @GetMapping("/getUserDetailsByMob/{moblie}")
    public ResponseEntity<Object> getUserDetailsByIDHandlerByMob(@PathVariable("name") String mob) throws ProductException{
        if(isLogin){
            User user = adminServices.userFindByMob(mob);
            return new ResponseEntity<>(user,HttpStatus.OK);
        }
        return new ResponseEntity<>("Please Login First ! ",HttpStatus.OK);
    } 
    
    
    @GetMapping("/getCancelOrRefunedOrders")
    public ResponseEntity<Object> cancelOrRefunedOrder() throws ProductException{
        if(isLogin){
            List<Orders> orders = adminServices.cancelOrRefunedOrders();
            return new ResponseEntity<>(orders,HttpStatus.OK);
        }
        return new ResponseEntity<>("Please Login First ! ",HttpStatus.OK);
    } 
    
    @GetMapping("/salesMadeToday")
    public ResponseEntity<Object> SalesMadeToday() throws ProductException{
        if(isLogin){
            List<Product> product = adminServices.salesMadeToday();
            return new ResponseEntity<>(product,HttpStatus.OK);
        }
        return new ResponseEntity<>("Please Login First ! ",HttpStatus.OK);
    } 
    
    @GetMapping("/salesMadeLastWeek")
    public ResponseEntity<Object> SalesMadeLastWeek() throws ProductException{
        if(isLogin){
            List<Product> product = adminServices.salesMadeLastWeek();
            return new ResponseEntity<>(product,HttpStatus.OK);
        }
        return new ResponseEntity<>("Please Login First ! ",HttpStatus.OK);
    } 
    
    @GetMapping("/salesMadeLastMonth")
    public ResponseEntity<Object> SalesMadeLastMonth() throws ProductException{
        if(isLogin){
            List<Product> product = adminServices.salesMadeLastMonth();
            return new ResponseEntity<>(product,HttpStatus.OK);
        }
        return new ResponseEntity<>("Please Login First ! ",HttpStatus.OK);
    } 
    
    @GetMapping("/salesMadeJanToDec")
    public ResponseEntity<Object> SalesMadeJantoDec() throws ProductException{
        if(isLogin){
            List<Product> product = adminServices.salesMadeJantoDec();
            return new ResponseEntity<>(product,HttpStatus.OK);
        }
        return new ResponseEntity<>("Please Login First ! ",HttpStatus.OK);
    } 
    
    @GetMapping("/highestSoldProductByRating")
    public ResponseEntity<Object> highestSoldProductByRating(@PathParam("date1") LocalDate date1, @PathParam("date2") LocalDate date2) throws ProductException{
        if(isLogin){
            Product product = adminServices.highestSoldProductByRating(date1, date2);
            return new ResponseEntity<>(product,HttpStatus.OK);
        }
        return new ResponseEntity<>("Please Login First ! ",HttpStatus.OK);
    } 
    
    @GetMapping("/highestRatedProductByDuration")
    public ResponseEntity<Object> highestRatedProductByDuration(@PathParam("category") Category category) throws ProductException{
        if(isLogin){
            Product product = adminServices.highestSoldProductByCategory(category);
            return new ResponseEntity<>(product,HttpStatus.OK);
        }
        return new ResponseEntity<>("Please Login First ! ",HttpStatus.OK);
    } 
    
    @GetMapping("/highestSoldProductBySalePrice")
    public ResponseEntity<Object> highestSoldProductBySalePrice(@PathParam("from") Integer from, @PathParam("to") Integer to) throws ProductException{
        if(isLogin){
            Product product = adminServices.highestSoldProductBySalePrice(from, to);
            return new ResponseEntity<>(product,HttpStatus.OK);
        }
        return new ResponseEntity<>("Please Login First ! ",HttpStatus.OK);
    } 
    
    @GetMapping("/highestSoldProductByCategory")
    public ResponseEntity<Object> highestSoldProductByCategory(@PathParam("date1") LocalDate date1, @PathParam("date2") LocalDate date2) throws ProductException{
        if(isLogin){
            Product product = adminServices.highestRatedProductByDuration(date1, date2);
            return new ResponseEntity<>(product,HttpStatus.OK);
        }
        return new ResponseEntity<>("Please Login First ! ",HttpStatus.OK);
    } 
}
