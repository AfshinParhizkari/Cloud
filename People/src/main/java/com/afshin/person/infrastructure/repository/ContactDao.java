package com.afshin.person.infrastructure.repository;
/**
 * @Project DDD
 * @Author Afshin Parhizkari
 * @Date Apr 26, 2021 2:58:19 AM
 * @version
 * Created by Eclipse 2020-09
 * Email:       Afshin.Parhizkari@gmail.com
 * Description: 
*/
import java.util.UUID;
import com.afshin.person.domain.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface ContactDao extends JpaRepository<Contact, UUID>{
    @Modifying
    @Transactional
    @Query("delete from Contact c where c.personfk = :personCode")
    int deleteByPersonfk(Integer personCode);
}