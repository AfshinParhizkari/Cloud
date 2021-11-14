package com.afshin.finance.infrastructure.repository;
/**
 * @Project DDD
 * @Author Afshin Parhizkari
 * @Date Apr 26, 2021 2:58:19 AM
 * @version
 * Created by Eclipse 2020-09
 * Email:       Afshin.Parhizkari@gmail.com
 * Description: 
*/

import com.afshin.finance.domain.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PaymentDao extends JpaRepository<Payment, Integer>{
    List<Payment> findByPaymentpk(Integer paymentpk);
}