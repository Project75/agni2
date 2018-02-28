/**
 * 
 */
package com.nttdata.agni.cda2fhir;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.hl7.fhir.dstu3.model.Bundle;
import org.hl7.fhir.dstu3.model.Composition;
import org.hl7.fhir.dstu3.model.Reference;
import org.openhealthtools.mdht.uml.cda.ClinicalDocument;
import org.openhealthtools.mdht.uml.cda.ccd.CCDPackage;
import org.openhealthtools.mdht.uml.cda.consol.ContinuityOfCareDocument;
import org.openhealthtools.mdht.uml.cda.util.CDAUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author harendra
 *
 */
@Service
public class CDA2FHIRTransformer extends AbstractTransformer {
	@Autowired
	private ResourceTransformer resTransformer;
    //private int counter;
    private IdGeneratorEnum idGenerator=IdGeneratorEnum.UUID;
    /**
     * Default constructor that initiates with a UUID resource id generator
     */
    //static {
    //	CDAUtil.loadPackages();
    //}
    private Reference patientRef=null;
	public String transformDocument(ClinicalDocument cda) {
		// Explicit initialization
		
        if(cda == null)
            return null;

        ContinuityOfCareDocument ccd = null;

        // first, cast the ClinicalDocument to ContinuityOfCareDocument
        try {
            ccd = (ContinuityOfCareDocument) cda;
        } catch (ClassCastException ex) {
            logger.error("ClinicalDocument could not be cast to ContinuityOfCareDocument. Returning null", ex);
            return null;
        }

        // init the global ccd bundle via a call to resource transformer, which handles cda header data (in fact, all except the sections)
        Bundle ccdBundle = resTransformer.tClinicalDocument2Composition(ccd);
        if(ccdBundle != null)
            FHIRUtil.printJSON(ccdBundle, "src/test/resources/output/test_ccd_bundle_intermed.json");
        System.out.println("Haren-bundle-after resTransform:\n"+ccdBundle.getTotal()+"-"+ccdBundle);
        
        // the first bundle entry is always the composition
        Composition ccdComposition = (Composition)ccdBundle.getEntry().get(0).getResource();
        // init the patient id reference if it is not given externally. the patient is always the 2nd bundle entry
        System.out.println("Haren-ccdComposition-"+ccdComposition);
        if (patientRef == null)
            patientRef = new Reference(ccdBundle.getEntry().get(1).getResource().getId());
        else // Correct the subject at composition with given patient reference.
            ccdComposition.setSubject(patientRef);
        System.out.println("Haren-patientRef-"+patientRef);
        //return ccdBundle;
        return FHIRUtil.printJSON(ccdBundle);
	}
	public String transform(String payload) {
		// TODO Auto-generated method stub
		
		InputStream stream = new ByteArrayInputStream(payload.getBytes(StandardCharsets.UTF_8));
		ClinicalDocument cda = null;
		try {
			if (stream!=null)
				cda =  CDAUtil.load(stream);
			else
				return null;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("Cannot create cda instance");
			e.printStackTrace();
		}
        //CCDTransformerImpl ccdTransformer = new CCDTransformerImpl(IdGeneratorEnum.COUNTER);
		return transformDocument(cda);
	}
	public String transform(InputStream stream) {
		// TODO Auto-generated method stub
		ClinicalDocument cda = null;
		try {
			if (stream!=null)
				cda =  CDAUtil.load(stream);
			else
				return null;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("Cannot create cda instance");
			e.printStackTrace();
		}
        //CCDTransformerImpl ccdTransformer = new CCDTransformerImpl(IdGeneratorEnum.COUNTER);
		return transformDocument(cda);
	}
}
