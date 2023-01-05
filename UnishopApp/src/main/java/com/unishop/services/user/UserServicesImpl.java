package com.unishop.services.user;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unishop.enums.Category;
import com.unishop.enums.OrderStatus;
import com.unishop.exception.CartException;
import com.unishop.exception.OrderException;
import com.unishop.exception.ProductException;
import com.unishop.exception.UserException;
import com.unishop.model.admin.Product;
import com.unishop.model.user.Cart;
import com.unishop.model.user.CartDTO;
import com.unishop.model.user.CurrentUserSession;
import com.unishop.model.user.OrderDTO;
import com.unishop.model.user.Orders;
import com.unishop.model.user.User;
import com.unishop.repository.admin.ProductDao;
import com.unishop.repository.user.CartDao;
import com.unishop.repository.user.OrderDao;
import com.unishop.repository.user.UserDao;
import com.unishop.repository.user.UserSessionDao;

@Service
public class UserServicesImpl implements UserServices{

    @Autowired
    private UserDao userDao;
    @Autowired
    private UserSessionDao userSessionDao;
    @Autowired
    private ProductDao productDao;

    @Autowired
    private CartDao cartDao;

    @Autowired
    private OrderDao orderDao;

    @Override
    public User regUser(User user) throws UserException {
        User existingUser = userDao.findByMob(user.getMob());
        if(existingUser==null){
            throw new UserException("User already exist with this mobile number : "+user.getMob());
        }
        
        Cart cart = new Cart();
         
        user.setCart(cart);
         
        
        return userDao.save(user);
    }

    @Override
    public User updateUser(User user,String key) throws UserException {
        CurrentUserSession currentUserSession = userSessionDao.findByUnqID(key);
        if(currentUserSession == null){
            throw new UserException("kindly enter valid key for update user details !");
        }

        if(Objects.equals(user.getUserID(), currentUserSession.getUserID())){
            return userDao.save(user);
        }
        throw new UserException("Invalid key ,kindly login again");
    }

    @Override
    public Cart addProductToCart(Integer userId, Integer productId) throws CartException, UserException, ProductException {
        Optional<User> existingUserOpt = userDao.findById(userId);
        if(existingUserOpt.isEmpty())
            throw new UserException("User does not exist with the userID : "+userId);

        Optional<Product> existingProductOpt = productDao.findById(productId);
        if(existingProductOpt.isEmpty())
            throw new ProductException("Product does not exist with the ProductID : "+productId);


        User user = existingUserOpt.get();
        Cart cart = user.getCart();

        List<Product> productList = cart.getProducts();
        boolean flag = true ;
        for (int i = 0; i < productList.size(); i++) {
            Product el = productList.get(i);
            if (el.getProductID() == productId) {
                if (cart.getProduct_quantity() == null) {
                  
                    el.setQuantity(1);
                } else {
                   el.setQuantity(el.getQuantity()+1);
                }
                flag = false;
            }
        }
        if(flag){
            cart.getProducts().add(existingProductOpt.get());
        }
        cart.setProduct_quantity(cart.getProducts().size());
        cartDao.save(cart);
        return cart;
    }

    @Override
    public Cart removeProductFromCart(Integer userId, Integer productId) throws CartException, UserException, ProductException {
        Optional<User> opt = userDao.findById(userId);
        if (opt.isEmpty())
            throw new UserException("Customer not found!");

        Optional<Product> itemOpt = productDao.findById(productId);
        if (itemOpt.isEmpty())
            throw new ProductException("Product not found!");
        User user = opt.get();
        Cart cart = user.getCart();
        List<Product> itemList = cart.getProducts();
        boolean flag = false;
        for (int i = 0; i < itemList.size(); i++) {
            Product element = itemList.get(i);
            if (element.getProductID() == productId) {
                itemList.remove(element);
                flag = true;
                break;
            }
        }
        if (!flag) {
            throw new CartException("Product not removed from cart");
        }
        cart.setProducts(itemList);
        cartDao.save(cart);
        return cart;
    }

    @Override
    public Cart removeAllProduct(Integer userId) throws CartException, UserException {
        Optional<User> opt = userDao.findById(userId);
        if (opt.isEmpty())
            throw new UserException("Customer not found!");
        Cart c = opt.get().getCart();
        if (c == null) {
            throw new CartException("cart not found");
        }
        c.getProducts().clear();
        return cartDao.save(c);
    }

    @Override
    public Cart increaseProductQuantity(Integer userId, Integer productId) throws CartException, UserException, ProductException {
        Optional<User> opt = userDao.findById(userId);
        if (opt.isEmpty())
            throw new UserException("Customer not found!");

        Optional<Product> itemOpt = productDao.findById(productId);
        if (itemOpt.isEmpty())
            throw new ProductException("Product not found!");

        User user = opt.get();
        Cart cart = user.getCart();
        List<Product> itemList = cart.getProducts();
      
        for (int i = 0; i < itemList.size(); i++) {
            Product element = itemList.get(i);
            if (element.getProductID() == productId) {
                element.setQuantity(element.getQuantity()+1);
                
            }
        }
      
        cartDao.save(cart);
        return cart;

    }

    @Override
    public Cart decreaseProductQuantity(Integer userId, Integer productId) throws CartException, UserException, ProductException {
        Optional<User> opt = userDao.findById(userId);
        if (opt.isEmpty()) throw new UserException("Customer not found!");

        Optional<Product> itemOpt = productDao.findById(productId);
        if (itemOpt.isEmpty())
            throw new ProductException("Product not found!");

        User user = opt.get();
        Cart cart = user.getCart();
        List<Product> itemList = cart.getProducts();
      
        if (itemList.size() > 0) {
            for (int i = 0; i < itemList.size(); i++) {
                Product element = itemList.get(i);
                if (element.getProductID() == productId) {
                	
                 if(element.getQuantity()>0) {
                	 
                	 element.setQuantity(element.getQuantity()-1); 
                 }else {
                	 throw new ProductException("You have quantity allready 0 for this product");
                 }
               
                }
            }
        }


        cartDao.save(cart);
        return cart;
    }

    @Override
    public Orders addASingleOrder(Integer cid, Integer productId, Orders order) throws OrderException, UserException, CartException {
        Optional<User> opt = userDao.findById(cid);
        if (opt.isEmpty()) {
            throw new UserException("Customer not found");
        }

        User user = opt.get();
        Cart cart = user.getCart();
         boolean flag = false;
        List<Product> products = cart.getProducts();
        if(products.size()!=0) {
        	for(Product p : products) {
        		if(p.getProductID()==productId) {
        			if(order.getPayment().getAmount()==p.getProductPrice()) {
        				flag = true;
        				order.getProductList().add(p);
            			break;
        			}else {
        				throw new OrderException("Payment Amount Should be equals to : "+p.getProductPrice());
        			}
        		  
        		}
        	}
        	
        	if(flag) {
        		
        		order.setUser(user);
        		return orderDao.save(order);
        	}else {
        		throw new CartException("Product does not exist in the cart with id "+productId);
        	}
        }else {
        	throw new CartException("Product does not exist in the cart with id "+productId);
        }
       
    }

    @Override
    public Orders viewOrder(Integer orderId) throws OrderException {
        Optional<Orders> o = orderDao.findById(orderId);
        if (o.isPresent()) {
            return o.get();
        } else {
            throw new OrderException("order not present!!");
        }
    }

    @Override
    public List<Orders> viewAllOrder() throws OrderException {
        List<Orders> orders = orderDao.findAll();
        if (orders.size() > 0) {
            return orders;
        } else {
            throw new OrderException("Order not found");
        }
    }

    @Override
    public List<Orders> viewAllOrdersByUserId(Integer userId) throws OrderException {
         Optional<User> opt = userDao.findById(userId);
         if(opt.isPresent()) {
        	List<Orders> list = orderDao.findAll();
         return list.stream().filter(e -> e.getUser().getUserID()==userId).collect(Collectors.toList());
         }else {
        	 throw new OrderException("User does't exist with user Id : "+userId);
         }
    }

	@Override
	public Orders addAllOrder(Integer userid, Orders order) throws OrderException, UserException, CartException {
		  Optional<User> opt = userDao.findById(userid);
	        if (opt.isEmpty()) {
	            throw new UserException("Customer not found");
	        }

	        User user = opt.get();
	        Cart cart = user.getCart();
	         
	        List<Product> products = cart.getProducts();
	        if(products.size()!=0) {
	        	int sum = 0;
	        	for(Product p : products) {
	        		sum += p.getProductPrice();
	        		order.getProductList().add(p);
	        	}
	        	    if(sum == order.getPayment().getAmount()) {
	        	    	order.setUser(user);
		        		return orderDao.save(order);
	        	    }else {
	        	    	throw new OrderException("Payment Amount should be equals to : "+sum);
	        	    }
	        		
	        
	        }else {
	        	throw new CartException("Any Product does not exist in the cart");
	        }
	       
	}

	@Override
	public CartDTO viewCart(Integer userId) throws CartException, UserException {
		
		Optional<User> opt = userDao.findById(userId);
		
		if(opt.isPresent()) {
			User user = opt.get();
			CartDTO cart = new CartDTO();
			cart.setCartId(user.getCart().getCartId());
			cart.setProduct_quantity(user.getCart().getProduct_quantity());
			int sum  = 0;
			List<Product> products = user.getCart().getProducts();
			for(Product p : products) {
				sum += p.getProductPrice()*p.getQuantity();
			}
			cart.setTotal_price(sum);
			cart.setProducts(products);
			return cart;
			
		}else {
			throw new UserException("User doesn't exist with user id : "+userId);
		}
	}

	
	@Override
	public OrderDTO checkStatus(Integer orderId) throws OrderException {
		
		Optional<Orders> opt = orderDao.findById(orderId);
		
		if(opt.isPresent()) {
			
			OrderDTO orderDto = new OrderDTO();
			orderDto.setOrderId(orderId);
			orderDto.setDate(opt.get().getDate());
			orderDto.setOrderStatus(opt.get().getOrderStatus());
			return orderDto;
			
		}else {
			throw new OrderException("Order doesn't exist with order id : "+orderId);
		}
	}

	
	@Override
	public List<Product> veiwAllProducts() throws ProductException {
		
		return productDao.findAll();
	}

	
	@Override
	public List<Product> veiwProductByCategory(Category category) throws ProductException {
	      
		  List<Product> products = productDao.findAll();
		  return products.stream().filter(e -> e.getCategory().equals(category)).collect(Collectors.toList());
	}

	
	@Override
	public List<Product> sortedByPrice() throws ProductException {
		
		List<Product> products = productDao.findAll();
		Collections.sort(products,(a,b) ->(a.getProductPrice()-b.getProductPrice()));
		return products;
	}

	
	@Override
	public List<Product> filterByPrice(Integer price) throws ProductException {
		List<Product> products = productDao.findAll();
		  return products.stream().filter(e -> e.getProductPrice()<=price).collect(Collectors.toList());
	}

	@Override
	public Orders cancelOrderByOrderId(Integer orderID) throws OrderException {
		Optional<Orders> opt = orderDao.findById(orderID);
		
		if(opt.isPresent()) {
			orderDao.delete(opt.get());
			return opt.get();
		}else {
			throw new OrderException("Order doesn't exist with order Id : "+orderID);
		}
	}


}
