package com.namoonhee.plogging.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Data;

@Data
@Entity
public class Activity {

    @GeneratedValue
    @Id
    Long id;
    String actDistance;
    String actTime;
    String actName;
    String actMemo;
    Date createDate;
    int visibility;

    @Lob
    String latlng;

    @ManyToOne
    User user;

    @OneToMany(mappedBy = "activity", cascade = CascadeType.REMOVE)
    List<ActFile> actFiles = new ArrayList<>();


    @OneToMany(mappedBy = "activity", cascade = CascadeType.REMOVE)
    List<ActAnswer> answers = new ArrayList<>();


}
