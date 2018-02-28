package com.nttdata.agni.dao.jpa;

import com.nttdata.agni.domain.ProfileDetail;
import com.nttdata.agni.domain.ProfileMaster;
import org.springframework.data.repository.CrudRepository;

/**
 * Repository can be used to delegate CRUD operations against the data source: http://goo.gl/P1J8QH
 */
public interface ProfileDetailRepository extends CrudRepository<ProfileDetail, Long> {
    
	
}
