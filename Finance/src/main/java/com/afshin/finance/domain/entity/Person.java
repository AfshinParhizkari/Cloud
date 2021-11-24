package com.afshin.finance.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @Project DDD
 * @Author Afshin Parhizkari
 * @Date Apr 26, 2021 2:58:19 AM
 * @version 2
 * Created by Eclipse 2020-09
 * Email:       Afshin.Parhizkari@gmail.com
 * Description: ValueObject
*/

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Person {
    private Integer personpk;
    private Integer persontypeid;
    private Integer typedetailid;
    private String nationalkey;
    private String lastname;
    private String firstname;
}
