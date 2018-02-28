package com.nttdata.agni.service;


import com.nttdata.agni.domain.ProfileDetail;
import com.nttdata.agni.domain.ProfileMaster;
import com.nttdata.agni.dao.jpa.ProfileMasterRepository;
import com.nttdata.agni.dao.jpa.ProfileDetailRepository;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

/*
 * Sample service to demonstrate what the API would use to get things done
 */
@Service
public class ProfileService {

    private static final Logger log = LoggerFactory.getLogger(ProfileService.class);



    @Autowired
    private ProfileMasterRepository profileRepository; 
 
    @Autowired
    ProfileDetailRepository profileDetailRepository;

    public ProfileService() {
    
    }
     
    public ProfileMaster createProfileMaster(ProfileMaster profileMaster) {
        return profileRepository.save(profileMaster);
    }
    
    
    public ProfileMaster getProfileMaster(long id) {
        return profileRepository.findOne(id);
    }

    public void updateProfileMaster(ProfileMaster profileMaster) {
        profileRepository.save(profileMaster);
    }

    public void deleteProfileMaster(Long id) {
        profileRepository.delete(id);
    }

	public List<ProfileMaster> getAllProfiles() {
		// TODO Auto-generated method stub
		return (List<ProfileMaster>) profileRepository.findAll();
	}
    public ProfileDetail getProfileDetail(long id) {
        return profileDetailRepository.findOne(id);
    }

    public void updateProfileDetail(ProfileDetail profileMaster) {
        profileDetailRepository.save(profileMaster);
    }
	public void deleteProfileDetail(Long id) {
		// TODO Auto-generated method stub
		 profileDetailRepository.delete(id);
	}



    
}


