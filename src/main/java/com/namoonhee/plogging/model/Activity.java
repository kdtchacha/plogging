package com.namoonhee.plogging.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

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

    


    @ManyToOne
    User user;

}
