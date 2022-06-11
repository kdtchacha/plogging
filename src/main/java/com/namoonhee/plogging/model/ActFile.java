package com.namoonhee.plogging.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.ToString;

@Data
@Entity
public class ActFile {
    
    @Id
    @GeneratedValue
    Long afId;
    String originalFileName;
    String saveFileName;

    @ManyToOne
    @ToString.Exclude
    Activity activity;

}