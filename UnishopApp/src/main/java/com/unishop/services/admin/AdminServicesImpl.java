package com.unishop.services.admin;

import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unishop.enums.Category;
import com.unishop.enums.OrderStatus;
import com.unishop.exception.AdminException;
import com.unishop.exception.OrderException;
import com.unishop.exception.ProductException;
import com.unishop.exception.UserException;
import com.unishop.model.admin.Admin;
import com.unishop.model.admin.CurrentAdminSession;
import com.unishop.model.admin.Product;
import com.unishop.model.admin.Product;
import com.unishop.model.user.Orders;
import com.unishop.model.user.User;
import com.unishop.repository.admin.AdminDao;
import com.unishop.repository.admin.AdminSessionDao;
import com.unishop.repository.admin.ProductDao;
import com.unishop.repository.user.OrderDao;
import com.unishop.repository.user.UserDao;

@Service
public class AdminServicesImpl implements AdminServices{

    @Autowired
    private AdminDao adminDao;

    @Autowired
    private AdminSessionDao adminSessionDao;

    @Autowired
    private ProductDao productDao;
    
    @Autowired
    private UserDao userDao;
    
    @Autowired
    private OrderDao orderDao;

    @Override
    public Admin regAdmin(Admin admin) throws AdminException {
        Admin existingAdmin = adminDao.findByMobileNumber(admin.getMobileNumber());
        if(existingAdmin != null){
            throw new AdminException("Admin with this mobile number already exist");
        }
        return adminDao.save(admin);
    }

    @Override
    public Admin getAdminByID(Integer aid) throws AdminException {
        Optional<Admin> opt = adminDao.findById(aid);
        if(opt.isPresent()){
            return opt.get();
        }
        throw new AdminException("Admin does not exist with Admin ID : " + aid);
    }

    @Override
    public Admin updateAdmin(Admin admin, String key) throws AdminException {
        CurrentAdminSession loggedInUser = adminSessionDao.findByUuid(key);
        if(loggedInUser == null){
            throw new AdminException("Kindly enter valid key to update admin");
        }

        if(admin.getAdminID() == loggedInUser.getAdminID()){
            return adminDao.save(admin);
        }

        throw new AdminException("Invalid admin details, Kindly login again");
    }

    @Override
    public Product addProduct(Product product) throws ProductException {
        Product product1 = productDao.save(product);
        if(product1 != null){
            return  product1;
        }
        throw new ProductException("Something went wrong product has not been added successfully");
    }



    @Override
    public List<Product> getAllProduct() throws ProductException {
        List<Product> allProducts = productDao.findAll();
        if(allProducts.isEmpty()){
            throw new ProductException("No any products found... ");
        }
        return allProducts;
    }

    @Override
    public Product getProductByID(Integer productID) throws ProductException {
        Optional<Product> product = productDao.findById(productID);
        if(product.isPresent()){
            return product.get();
        }

        throw new ProductException("Product does not exist with Product ID : "+productID);
    }

    @Override
    public Product deleteProductByID(Integer productID) throws ProductException {
        Optional<Product> product = productDao.findById(productID);
        if(product.isPresent()){
            productDao.delete(product.get());
            return product.get();
        }
        throw new ProductException("Product does not exist with Product ID : "+productID);
    }

	@Override
	public Product updateQuantityByID(Integer productID, Integer quan) throws ProductException {
		  Optional<Product> product = productDao.findById(productID);
	        if(product.isPresent()){
	        	product.get().setQuantity(quan);
	            productDao.save(product.get());
	            return product.get();
	        }
	        throw new ProductException("Product does not exist with Product ID : "+productID);
	    }

	@Override
	public List<Orders> getAllOrders() throws OrderException {
		
		return orderDao.findAll();
	}

	@Override
	public Orders gerOrderById(Integer orderId) throws OrderException {
		
		Optional<Orders> opt = orderDao.findById(orderId);
		if(opt.isPresent()) {
			return opt.get();
		}else {
			throw new OrderException("Order doesn't exist with order id : "+orderId);
		}
	}

	@Override
	public Orders updateOrderStatus(Integer orderId,OrderStatus status) throws OrderException {
		Optional<Orders> opt = orderDao.findById(orderId);
		if(opt.isPresent()) {
			opt.get().setOrderStatus(status);
			return orderDao.save(opt.get());
		}else {
			throw new OrderException("Order doesn't exist with order id : "+orderId);
		}
	}

	@Override
	public User userFindById(Integer userId) throws UserException {
		
		Optional<User> opt = userDao.findById(userId);
		
		if(opt.isPresent()) {
			
			return opt.get();
			
		}else {
			throw new UserException("User doesn't exist with user Id : "+userId);
		}
	}

	@Override
	public User userFindByEmail(String email) throws UserException {
		 List<User> opt = userDao.findAll();
		 List<User> users = new ArrayList<>();
		 for(User o : opt) {
			 if(o.getEmail().equals(email)) {
				 users.add(o);
			 }
		 }
			
			if(users.size()!=0) {
				
				return users.get(0);
				
			}else {
				throw new UserException("User doesn't exist with user Name : "+email);
			}
	}

	@Override
	public List<User> userFindByName(String name) throws UserException {
		 List<User> opt = userDao.findAll();
		 List<User> users = new ArrayList<>();
		 for(User o : opt) {
			 if(o.getUserName().equals(name)) {
				 users.add(o);
			 }
		 }
			
			if(users.size()!=0) {
				
				return users;
				
			}else {
				throw new UserException("User doesn't exist with user Name : "+name);
			}
	}

	@Override
	public User userFindByMob(String mob) throws UserException {
		
		  User opt = userDao.findByMob(mob);
			
			if(opt != null) {
				
				return opt;
				
			}else {
				throw new UserException("User doesn't exist with Mobile Number : "+mob);
			}
	}

	@Override
	public List<Orders> cancelOrRefunedOrders() throws OrderException {
		
		List<Orders> orders = orderDao.findAll();
		return orders.stream().filter(e -> (e.getOrderStatus().equals(OrderStatus.RETURNED) || e.getOrderStatus().equals(OrderStatus.CANCELED))).collect(Collectors.toList());
	}

	@Override
	public List<Product> salesMadeToday() throws OrderException {
		List<Orders> orders = orderDao.findAll();
		LocalDate now = LocalDate.now();
		List<Product> sales = new ArrayList<>();
		for(Orders o : orders) {
			if(o.getDate().toLocalDate().equals(now)) {
				sales.addAll(o.getProductList());
			}
		}
		
		return sales;
	}

	@Override
	public List<Product> salesMadeLastWeek() throws OrderException {
		List<Orders> orders = orderDao.findAll();
		LocalDate now = LocalDate.now();
		List<Product> sales = new ArrayList<>();
		for(Orders o : orders) {
			if(o.getDate().toLocalDate().compareTo(now.with(ChronoField.DAY_OF_WEEK, 1))>=0) {
				sales.addAll(o.getProductList());
			}
		}
		
		return sales;
	}

	@Override
	public List<Product> salesMadeLastMonth() throws OrderException {
		List<Orders> orders = orderDao.findAll();
		LocalDate now = LocalDate.now();
		List<Product> sales = new ArrayList<>();
		for(Orders o : orders) {
			if(o.getDate().toLocalDate().compareTo(now.with(ChronoField.DAY_OF_MONTH, 1))>0) {
				sales.addAll(o.getProductList());
			}
		}
		
		return sales;

	}

	@Override
	public List<Product> salesMadeJantoDec() throws OrderException {
		List<Orders> orders = orderDao.findAll();
		LocalDate now = LocalDate.now();
		List<Product> sales = new ArrayList<>();
		for(Orders o : orders) {
			if(o.getDate().toLocalDate().compareTo(now.with(ChronoField.DAY_OF_YEAR, 1))>0) {
				sales.addAll(o.getProductList());
			}
		}
		
		return sales;
	}

	@Override
	public Product highestSoldProductByRating(LocalDate date1, LocalDate date2) throws ProductException {
		List<Orders> orders = orderDao.findAll();
		
		List<Product> sales = new ArrayList<>();
		Product p = null;
		int price = 0;
		for(Orders o : orders) {
			if(o.getDate().toLocalDate().compareTo(date1)>=0 && o.getDate().toLocalDate().compareTo(date2)<0) {
				sales.addAll(o.getProductList());
			}
		}
		for(Product pro : sales) {
			if(pro.getProductPrice()>price) {
				price = pro.getProductPrice();
				p = pro;
			}
		}
		
		return p;
	}

	@Override
	public Product highestRatedProductByDuration(LocalDate date1, LocalDate date2) throws ProductException {
List<Orders> orders = orderDao.findAll();
		
		List<Product> sales = new ArrayList<>();
		Product p = null;
		int rating = 0;
		for(Orders o : orders) {
			if(o.getDate().toLocalDate().compareTo(date1)>=0 && o.getDate().toLocalDate().compareTo(date2)<0) {
				sales.addAll(o.getProductList());
			}
		}
		for(Product pro : sales) {
			if(pro.getRating()>rating) {
				rating = pro.getRating();
				p = pro;
			}
		}
		
		return p;
	}

	@Override
	public Product highestSoldProductBySalePrice(Integer price1, Integer price2) throws ProductException {
		
		List<Orders> orders = orderDao.findAll();
		
		List<Product> sales = new ArrayList<>();
		Product p = null;
		int price = 0;
		for(Orders o : orders) {
			
				sales.addAll(o.getProductList());
		
		}
		for(Product pro : sales) {
			if(pro.getProductPrice()>price1 && pro.getProductPrice()<price2 && pro.getProductPrice()>price) {
				price = pro.getProductPrice();
				p = pro;
			}
		}
		if(p!=null) {
			return p;	
		}else {
			throw new ProductException("No Product found with in this range..");
		}
		
	}

	@Override
	public Product highestSoldProductByCategory(Category category) throws ProductException {
List<Orders> orders = orderDao.findAll();
		
		List<Product> sales = new ArrayList<>();
		Product p = null;
		int price = 0;
		for(Orders o : orders) {
			
				sales.addAll(o.getProductList());
		
		}
		for(Product pro : sales) {
			if(pro.getCategory().equals(category) && pro.getProductPrice()>price) {
				price = pro.getProductPrice();
				p = pro;
			}
		}
		if(p!=null) {
			return p;	
		}else {
			throw new ProductException("No Product found with this category : "+category);
		}
	}
	}

