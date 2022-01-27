package com.afshin.shopping.domain.service;
/**
 * @Project DDD
 * @Author Afshin Parhizkari
 * @Date 2021 - 11 - 02
 * @Time 4:29 AM
 * Created by   IntelliJ IDEA
 * Email:       Afshin.Parhizkari@gmail.com
 * Description:Logic
 */
import com.afshin.shopping.domain.entity.Cart;
import com.afshin.shopping.infrastructure.mq.OrderMq;
import com.afshin.shopping.infrastructure.repository.CartDao;
import com.afshin.shopping.infrastructure.resource.PeopleRso;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class CartSrv {
    @Autowired private CartDao cartDao;
    @Autowired private OrderMq orderMq;

    public List<Cart> showCart(Integer customerCode) throws Exception {
        return cartDao.findByCustomerfk(customerCode);
    }
    public String deleteFromCart(Integer customercode,Integer productcode) throws Exception {
        cartDao.deleteProduct(customercode,productcode);
        return "{'code':1,'message':'record code "+productcode+" is deleted'}";
    }
    public String cancelCart(Integer code) throws Exception {
        cartDao.deleteByCustomer(code);
        return "{'code':1,'message':'record code "+code+" is deleted'}";
    }
    public String addToCart(Cart cart) throws Exception {
            return "{'code':1,'message':'record code "+cartDao.save(cart).getProductfk()+" is added'}";
     }

    @Transactional(rollbackFor=Exception.class, propagation= Propagation.REQUIRED)
    public String closeCart(Integer customercode) throws Exception {
        List<Cart> cartList=cartDao.findByCustomerfk(customercode);
        if(orderMq.sendOrder(cartList)==0) {
            cartDao.deleteByCustomer(customercode);
            return "{'code':0,'message':'your product with customer code " + customercode + " closed and send to payment part'}";
        }else return "{'code':1,'message':'Some problem is happened'}";
    }
}