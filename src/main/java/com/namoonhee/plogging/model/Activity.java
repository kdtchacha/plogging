package com.namoonhee.plogging.model;

import java.util.Date;

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


    String actName;

    String actTime;

    String actDistance;


    Date createDate;

    Integer visibility;
    


    @ManyToOne
    User user;

}
