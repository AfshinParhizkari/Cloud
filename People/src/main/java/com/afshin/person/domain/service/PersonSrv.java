package com.afshin.person.domain.service;
/**
 * @Project DDD
 * @Author Afshin Parhizkari
 * @Date 2021 - 10 - 31
 * @Time 11:06 PM
 * Created by   IntelliJ IDEA
 * Email:       Afshin.Parhizkari@gmail.com
 * Description: Business Logic
 */
import com.afshin.person.domain.entity.Person;
import com.afshin.person.infrastructure.repository.ContactDao;
import com.afshin.person.infrastructure.repository.PersonDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service//Logic
public class PersonSrv {
    @Autowired private PersonDao perDao;
    @Autowired private ContactDao conDao;
    public static final Logger logger  = LoggerFactory.getLogger(PersonSrv.class);

    public List<Person> find(Integer code) throws Exception {
        List<Person> returnData;
        if(code==0) returnData = perDao.findAll();
        else returnData=perDao.findByPersonpk(code);
        return returnData;
    }
    @Transactional
    public String delete(Integer code) throws Exception {
            conDao.deleteByPersonfk(code);
            perDao.deleteById(code);
            return "{'code':1,'message':'record code "+code+" is deleted'}";
    }

    public String save(Person viewPerson) throws Exception {
        if(viewPerson.getPersonpk()==null) {//not pk => maybe New person
            if(perDao.findByNationalkey(viewPerson.getNationalkey()).size()==0) //No NationalKey => Add New person
                return "{'code':1,'message':'record code "+insertPerson(viewPerson)+" is added'}";
            else
                return "{'code':0,'message':'National code "+ viewPerson.getNationalkey()+" is duplicate'}";
        }else {//Exist NationalKey  => Edit Current person
            List<Person> dbPeople = perDao.findByPersonpk(viewPerson.getPersonpk());
            if(dbPeople.size()==0) return "{'code':0,'message':'record code "+ viewPerson.getPersonpk()+" is Not found'}";
            else return "{'code':2,'message':'record code "+updatePerson(viewPerson, dbPeople.get(0))+" is updated'}";
        }
    }
    @Transactional
    public Integer insertPerson(Person viewPerson){
        viewPerson = perDao.save(viewPerson);
        if(viewPerson.getContactsByPersonpk()!=null) {
            if (viewPerson.getContactsByPersonpk().size() > 0) {
                Integer personCode = viewPerson.getPersonpk();
                viewPerson.getContactsByPersonpk().forEach(contact -> contact.setPersonfk(personCode));
                conDao.saveAll(viewPerson.getContactsByPersonpk());
            }
            return viewPerson.getPersonpk();
        }else return 0;
    }
    @Transactional
    public Integer updatePerson(Person viewPerson, Person dbPerson){
        //update person
        dbPerson.setPersontypeid(viewPerson.getPersontypeid());
        dbPerson.setTypedetailid(viewPerson.getTypedetailid());
        dbPerson.setNationalkey(viewPerson.getNationalkey());
        dbPerson.setBooknumber(viewPerson.getBooknumber());
        dbPerson.setBookserial(viewPerson.getBookserial());
        dbPerson.setBookserie(viewPerson.getBookserie());
        dbPerson.setPassportno(viewPerson.getPassportno());
        dbPerson.setLastname(viewPerson.getLastname());
        dbPerson.setFirstname(viewPerson.getFirstname());
        dbPerson.setCountryid(viewPerson.getCountryid());
        dbPerson.setCityid(viewPerson.getCityid());
        dbPerson.setBirthdate(viewPerson.getBirthdate());
        dbPerson =perDao.save(dbPerson);
        //update Contacts
        if(viewPerson.getContactsByPersonpk().size()>0) {
            perDao.deleteById(dbPerson.getPersonpk());
            Integer personCode = viewPerson.getPersonpk();
            viewPerson.getContactsByPersonpk().forEach(contact -> contact.setPersonfk(personCode));
            conDao.saveAll(viewPerson.getContactsByPersonpk());
        }
        return dbPerson.getPersonpk();
    }

    public static void main(String[] args) {
        logger.info("test");
    }
}