package com.namoonhee.plogging.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.ToString;

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
    @ToString.Exclude
    User user;

    @OneToMany(mappedBy = "activity", cascade = CascadeType.REMOVE)
    @JsonIgnore
    List<ActFile> actFiles = new ArrayList<>();

    @OneToMany(mappedBy = "activity", cascade = CascadeType.REMOVE)
    @JsonIgnore
    List<ActAnswer> answers = new ArrayList<>();

    @OneToMany(mappedBy = "activity", cascade = CascadeType.REMOVE)
    @JsonIgnore
    List<ActLike> actLikes = new ArrayList<>();

}
