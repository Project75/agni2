/**
 * 
 */
package com.nttdata.agni.cda2fhir;

import org.hl7.fhir.dstu3.model.Bundle;
import org.openhealthtools.mdht.uml.cda.ClinicalDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;



/**
 * Copyright NTT Data
 * cda2fhir
 * @author Harendra Pandey
 *
 */
@Service
public class AbstractTransformer {
	
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    public String transformCDA(ClinicalDocument cda){
		return null;
	}
}
