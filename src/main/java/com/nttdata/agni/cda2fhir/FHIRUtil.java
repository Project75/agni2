package com.nttdata.agni.cda2fhir;

import ca.uhn.fhir.context.FhirContext;

/*
 * #%L
 * CDA to FHIR Transformer Library
 * %%
 * Copyright (C) 2016 SRDC Yazilim Arastirma ve Gelistirme ve Danismanlik Tic. A.S.
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import ca.uhn.fhir.model.api.IResource;
import ca.uhn.fhir.parser.IParser;
import ca.uhn.fhir.parser.LenientErrorHandler;

import org.hl7.fhir.dstu3.model.Bundle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.charset.Charset;

public class FHIRUtil {

    private static IParser jsonParser;
    //private static IParser xmlParser;
    private static FhirContext fhirCtx;
    private final static Logger logger = LoggerFactory.getLogger(FHIRUtil.class);
    public static FhirContext getFhirContext() {
        return fhirCtx;
    }
    static {
        fhirCtx = FhirContext.forDstu3();
        fhirCtx.setParserErrorHandler(new LenientErrorHandler());
        jsonParser = getFhirContext().newJsonParser();
        //xmlParser = Config.getFhirContext().newXmlParser();
        jsonParser.setPrettyPrint(true);
        //xmlParser.setPrettyPrint(true);
        //narrativeGenerator = new CustomThymeleafNarrativeGenerator(NARRATIVE_PROPERTIES_FILE_PATH);
        //if(generateNarrative)
          //  fhirCtx.setNarrativeGenerator(narrativeGenerator);
        logger.info("System init: " );
        logger.info("System file encoding is: " + Charset.defaultCharset().displayName());
    }

    
    
    
    public static String encodeToJSON(IResource res) {
    	return jsonParser.encodeResourceToString(res);
    }
    

    public static void printJSON(IResource res) {
        System.out.println(jsonParser.encodeResourceToString(res));
    }

    public static String printJSON(Bundle ccdBundle) {
        
        return jsonParser.encodeResourceToString(ccdBundle);
    }

    public static void printJSON(Bundle ccdBundle, String filePath) {
        File f = new File(filePath);
        f.getParentFile().mkdirs();
        try {
            jsonParser.encodeResourceToWriter(ccdBundle, new FileWriter(f));
        } catch (IOException e) {
            logger.error("Could not print FHIR JSON to file", e);
        }
    }
    public static void printJSON(IResource res, Writer writer) {
        try {
            jsonParser.encodeResourceToWriter(res, writer);
        } catch (IOException e) {
            logger.error("Could not print FHIR JSON to writer", e);
        }
    }
    
    /*public static String encodeToXML(IResource res) {
    return xmlParser.encodeResourceToString(res);
}
    public static void printXML(IResource res) {
    System.out.println(xmlParser.encodeResourceToString(res));
}


    public static void printXML(IResource res, String filePath) {
        File f = new File(filePath);
        f.getParentFile().mkdirs();
        try {
            xmlParser.encodeResourceToWriter(res, new FileWriter(f));
        } catch (IOException e) {
            logger.error("Could not print FHIR XML to file", e);
        }
    }

   

    public static void printXML(IResource res, Writer writer) {
        try {
            xmlParser.encodeResourceToWriter(res, writer);
        } catch (IOException e) {
            logger.error("Could not print FHIR XML to writer", e);
        }
    }
*/
}
