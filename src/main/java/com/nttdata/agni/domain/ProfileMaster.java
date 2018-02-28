package com.nttdata.agni.domain;

import java.util.List;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/*
 * @author Harendra
 */
@Entity
@Table(name = "profile_master")
@Data @NoArgsConstructor @RequiredArgsConstructor
public class ProfileMaster {

    


	


	@Id
    @GeneratedValue()
    private long id;
    
	@NonNull
    String profile;

 
    @OneToMany(mappedBy = "profileMaster", cascade = CascadeType.ALL,orphanRemoval=true, targetEntity=ProfileDetail.class)
    @Column(nullable = true)
    @JsonManagedReference
    List <ProfileDetail> profileDetail;
    

    String status;
    

    String resources;
    
 
 

    
}



