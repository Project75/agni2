package com.nttdata.agni.domain;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/*
 * @author Harendra
 */
@Entity
@Table(name = "profile_detail")
@Data
@NoArgsConstructor
public class ProfileDetail {

    public ProfileDetail(String resource) {
		// TODO Auto-generated constructor stub
    	setResource(resource);
    	
	}

	@Id
    @GeneratedValue()
    @Column(name="id")
    private long id;

    //@NonNull
    String resource;
    
//    @NonNull
    @ManyToOne()
    @JoinColumn(name = "profile_master_id")
    @JsonBackReference
    ProfileMaster profileMaster;


 

    String field;

    String type;  

    Boolean isRequired;  
    
    String status;
   
 

    
}



