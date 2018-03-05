/**
 * 
 */
package com.nttdata.agni.cda2fhir;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import org.hl7.fhir.dstu3.model.Bundle;
import org.openhealthtools.mdht.uml.cda.ClinicalDocument;
import org.openhealthtools.mdht.uml.cda.util.CDAUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nttdata.agni.exception.ProcessingErrorException;

/**
 * @author harendra
 *
 */

@Service
public class CDA2FHIRTransformer extends AbstractTransformer {
	@Autowired
	private ResourceTransformer resTransformer;

    static {
    	CDAUtil.loadPackages();
    }

	public String transformDocument(ClinicalDocument cda) {
		// Initialization
		
        if(cda == null)
        	throw new ProcessingErrorException("cannot Create CDA instance from payload");

        // resource transformer,  handles cda header data ( all except the sections)
        Bundle ccdBundle = resTransformer.tClinicalDocument2Composition(cda);
        //FHIRUtil.printJSON(ccdBundle, "src/test/resources/output/test_ccd_bundle_intermed.json");

        return FHIRUtil.printJSON(ccdBundle);
	}
	

	public String transform(String payload) {
		// TODO Auto-generated method stub

		InputStream stream = new ByteArrayInputStream(payload.getBytes(StandardCharsets.UTF_8));
		ClinicalDocument cda = null;
		try {
			if (stream!=null)				
				cda=  CDAUtil.load(stream);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("Cannot create cda instance");
			e.printStackTrace();
			throw new ProcessingErrorException("cannot Create CDA instance from payload");
		}
   
		return transformDocument(cda);
	}
	
}
