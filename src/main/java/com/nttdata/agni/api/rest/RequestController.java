package com.nttdata.agni.api.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import com.nttdata.agni.cda2fhir.CDA2FHIRTransformer;
import com.nttdata.agni.domain.ProfileDetail;
import com.nttdata.agni.domain.ProfileMaster;
import com.nttdata.agni.exception.DataFormatException;
import com.nttdata.agni.service.ProfileService;

import org.openhealthtools.mdht.uml.cda.util.CDAUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * Demonstrates how to set up RESTful API endpoints using Spring MVC
 */

@RestController
@RequestMapping(value = "/fhirtranslator/v1/cda2fhir")
@Api(tags = {"cda2fhir"})
public class RequestController extends AbstractRestHandler {

    @Autowired
    private CDA2FHIRTransformer transformer;

    @RequestMapping(value = "/",
            method = RequestMethod.POST,
            consumes = {"application/json", "application/xml"},
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "CDA to FHIR.", notes = "Returns FHIR resource bundle.")
    public String hl72fhir2(@RequestBody String payload,
                                 HttpServletRequest request, HttpServletResponse response) {
    	//log.info("mapname"+mapname);
    	CDAUtil.loadPackages();
        try {
			return transformer.transform(request.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
        
    }

   }
