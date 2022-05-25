package com.namoonhee.plogging.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


import lombok.Data;

@Data
@Entity
public class Activity {
    
    @GeneratedValue
    @Id
    Long id;


    String startTime;
    String endTime;



    @ManyToOne
    User user;

}
