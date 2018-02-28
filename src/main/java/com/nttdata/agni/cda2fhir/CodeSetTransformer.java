package com.nttdata.agni.cda2fhir;

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

//import ca.uhn.fhir.model.dstu2.valueset.*;

import org.hl7.fhir.dstu3.model.Address.AddressType;
import org.hl7.fhir.dstu3.model.Address.AddressUse;
import org.hl7.fhir.dstu3.model.AllergyIntolerance.AllergyIntoleranceCategory;
import org.hl7.fhir.dstu3.model.AllergyIntolerance.AllergyIntoleranceCriticality;
import org.hl7.fhir.dstu3.model.AllergyIntolerance.AllergyIntoleranceSeverity;



import org.hl7.fhir.dstu3.model.Coding;
import org.hl7.fhir.dstu3.model.Condition.ConditionClinicalStatus;
import org.hl7.fhir.dstu3.model.ContactPoint.ContactPointSystem;
import org.hl7.fhir.dstu3.model.ContactPoint.ContactPointUse;
import org.hl7.fhir.dstu3.model.DiagnosticReport.DiagnosticReportStatus;
import org.hl7.fhir.dstu3.model.Encounter;
import org.hl7.fhir.dstu3.model.Encounter.EncounterStatus;
import org.hl7.fhir.dstu3.model.Enumerations.*;
import org.hl7.fhir.dstu3.model.FamilyMemberHistory.FamilyHistoryStatus;
import org.hl7.fhir.dstu3.model.Group.GroupType;
import org.hl7.fhir.dstu3.model.HumanName.NameUse;
import org.hl7.fhir.dstu3.model.MedicationDispense.MedicationDispenseStatus;
import org.hl7.fhir.dstu3.model.MedicationStatement.MedicationStatementStatus;
import org.hl7.fhir.dstu3.model.Observation.ObservationStatus;
import org.hl7.fhir.dstu3.model.Procedure.ProcedureStatus;
import org.hl7.fhir.dstu3.model.Timing.UnitsOfTime;
import org.openhealthtools.mdht.uml.hl7.vocab.EntityClassRoot;
import org.springframework.stereotype.Component;


@Component
public class CodeSetTransformer  {

	
	
	public AdministrativeGender tAdministrativeGenderCode2AdministrativeGender(String cdaAdministrativeGenderCode) {
		switch (cdaAdministrativeGenderCode.toLowerCase()) {
			case "f":
				return AdministrativeGender.FEMALE;
			case "m":
				return AdministrativeGender.MALE;
			case "un":
				return AdministrativeGender.UNKNOWN;
			default:
				return AdministrativeGender.UNKNOWN;
		}
	}
	
	public String tAgeObservationUnit2AgeUnit(String cdaAgeObservationUnit) {
		if(cdaAgeObservationUnit == null || cdaAgeObservationUnit.isEmpty())
			return null;
		
		switch(cdaAgeObservationUnit.toLowerCase()) {
			case "a":
				return "Year";
			case "mo":
				return "Month";
			case "wk":
				return "Week";
			case "d":
				return "Day";
			case "h":
				return "Hour";
			case "min":
				return "Minute";
			default:
				return null;
		}
	}
	
	public AllergyIntoleranceCategory tAllergyCategoryCode2AllergyIntoleranceCategory(String cdaAllergyCategoryCode) {
		if(cdaAllergyCategoryCode == null)
			return null;
		switch(cdaAllergyCategoryCode) {
			case "416098002":
			case "59037007":
			case "419511003":
				return AllergyIntoleranceCategory.MEDICATION;
			case "414285001": 
			case "235719002":
			case "418471000":
				return AllergyIntoleranceCategory.FOOD;
			case "232347008":
				return AllergyIntoleranceCategory.ENVIRONMENT;
			case "420134006":
			case "418038007":
			case "419199007": 
				return AllergyIntoleranceCategory.NULL;
			default:
				return null;
		}
	}
	
	public AllergyIntoleranceCriticality tCriticalityObservationValue2AllergyIntoleranceCriticality(String cdaCriticalityObservationValue) {
		if(cdaCriticalityObservationValue == null || cdaCriticalityObservationValue.isEmpty())
			return null;
		switch(cdaCriticalityObservationValue.toLowerCase()) {
			case "critl": 
				return AllergyIntoleranceCriticality.LOW;
			case "crith": 
				return AllergyIntoleranceCriticality.HIGH;
			case "critu": 
				return AllergyIntoleranceCriticality.UNABLETOASSESS;
			default: 
				return null;
		}
	}
	/*
	public Encounter tEncounterCode2EncounterClass(String cdaEncounterCode) {
		if(cdaEncounterCode == null)
			return null;
		switch(cdaEncounterCode.toLowerCase()) {
			case "amb": 
			case "ambulatory":
				return Encounter.EncounterLocationStatus.EncounterStatus.AMBULATORY;
			case "out": 
			case "outpatient":
					return EncounterClass.OUTPATIENT;
			case "in":
			case "inp":
			case "inpatient":
					return EncounterClass.INPATIENT;
			case "day":
			case "daytime":
				return EncounterClass.DAYTIME;
			case "em":
			case "eme":
			case "emergency":
				return EncounterClass.EMERGENCY;
			case "hom":
			case "home":
				return EncounterClass.HOME;
			case "vir":
			case "virtual":
				return EncounterClass.VIRTUAL;
			case "fie":
			case "field":
				return EncounterClass.FIELD;
			case "other":
			case "oth":
				return EncounterClass.OTHER;
			default:
				return null;
				
		}
	}
	*/
	
	public GroupType tEntityClassRoot2GroupType(EntityClassRoot cdaEntityClassRoot) {
		switch(cdaEntityClassRoot) {
			case PSN:
				return GroupType.PERSON;
			case ANM:
				return GroupType.ANIMAL;
			case DEV:
				return GroupType.DEVICE;
			case MMAT:
				return GroupType.MEDICATION;
			default:
				return null;
		}
	}

	
	public NameUse tEntityNameUse2NameUse(String cdaEntityNameUse) {
		switch(cdaEntityNameUse) {
			case "C": return NameUse.USUAL;
			case "P": return NameUse.NICKNAME;
			default: return NameUse.USUAL;
		}
	}

	public FamilyHistoryStatus tFamilyHistoryOrganizerStatusCode2FamilyHistoryStatus(String cdaFamilyHistoryOrganizerStatusCode) {
		switch(cdaFamilyHistoryOrganizerStatusCode.toLowerCase()){
		case "completed":
			return FamilyHistoryStatus.COMPLETED;
		case "error":
			return FamilyHistoryStatus.ENTEREDINERROR;
		case "un":
			return FamilyHistoryStatus.HEALTHUNKNOWN;
		case "part":
			return FamilyHistoryStatus.PARTIAL;
		default:
			return null;
		}
	}




	public ObservationStatus tObservationStatusCode2ObservationStatus(String cdaObservationStatusCode) {
		switch(cdaObservationStatusCode.toLowerCase()) {
		// TODO: https://www.hl7.org/fhir/valueset-observation-status.html and pdf page 476
		// Check the following mapping
			case "new":	
			case "held":
				return ObservationStatus.REGISTERED;
			case "normal":
			case "active":
				return ObservationStatus.PRELIMINARY;
			case "completed":
				return ObservationStatus.FINAL;
			case "error":
				return ObservationStatus.ENTEREDINERROR;
			case "cancelled":
			case "aborted":
			case "nullified":
			case "suspended":
				return ObservationStatus.CANCELLED;
			case "obsolete":
			default:
				return ObservationStatus.UNKNOWN;
		}
	}

	public String tOid2Url(String codeSystem) {
		String system = null;
		switch (codeSystem) {
	        case "2.16.840.1.113883.6.96":
	            system = "http://snomed.info/sct";
	            break;
	        case "2.16.840.1.113883.6.88":
	            system = "http://www.nlm.nih.gov/research/umls/rxnorm";
	            break;
	        case "2.16.840.1.113883.6.1":
	            system = "http://loinc.org";
	            break;
	        case "2.16.840.1.113883.6.8":
	            system = "http://unitsofmeasure.org";
	            break;
	        case "2.16.840.1.113883.3.26.1.2":
	            system = "http://ncimeta.nci.nih.gov";
	            break;
	        case "2.16.840.1.113883.6.12":
	            system = "http://www.ama-assn.org/go/cpt";
	            break;
	        case "2.16.840.1.113883.6.209":
	            system = "http://hl7.org/fhir/ndfrt";
	            break;
	        case "2.16.840.1.113883.4.9":
	            system = "http://fdasis.nlm.nih.gov";
	            break;
	        case "2.16.840.1.113883.12.292":
	            system = "http://www2a.cdc.gov/vaccines/iis/iisstandards/vaccines.asp?rpt=cvx";
	            break;
	        case "1.0.3166.1.2.2":
	            system = "urn:iso:std:iso:3166";
	            break;
	        case "2.16.840.1.113883.6.301.5":
	            system = "http://www.nubc.org/patient-discharge";
	            break;
	        case "2.16.840.1.113883.6.256":
	            system = "http://www.radlex.org";
	            break;
	        case "2.16.840.1.113883.6.3":
	            system = "http://hl7.org/fhir/sid/icd-10";
	            break;
	        case "2.16.840.1.113883.6.4":
	            system = "http://www.icd10data.com/icd10pcs";
	            break;
	        case "2.16.840.1.113883.6.42":
	            system = "http://hl7.org/fhir/sid/icd-9";
	            break;
	        case "2.16.840.1.113883.6.73":
	            system = "http://www.whocc.no/atc";
	            break;
	        case "2.16.840.1.113883.6.24":
	            system = "urn:std:iso:11073:10101";
	            break;
	        case "1.2.840.10008.2.16.4":
	            system = "http://nema.org/dicom/dicm";
	            break;
	        case "2.16.840.1.113883.6.281":
	            system = "http://www.genenames.org";
	            break;
	        case "2.16.840.1.113883.6.280":
	            system = "http://www.ncbi.nlm.nih.gov/nuccore";
	            break;
	        case "2.16.840.1.113883.6.282":
	            system = "http://www.hgvs.org/mutnomen";
	            break;
	        case "2.16.840.1.113883.6.284":
	            system = "http://www.ncbi.nlm.nih.gov/projects/SNP";
	            break;
	        case "2.16.840.1.113883.3.912":
	            system = "http://cancer.sanger.ac.uk/cancergenome/projects/cosmic";
	            break;
	        case "2.16.840.1.113883.6.283":
	            system = "http://www.hgvs.org/mutnomen";
	            break;
	        case "2.16.840.1.113883.6.174":
	            system = "http://www.omim.org";
	            break;
	        case "2.16.840.1.113883.13.191":
	            system = "http://www.ncbi.nlm.nih.gov/pubmed";
	            break;
	        case "2.16.840.1.113883.3.913":
	            system = "http://www.pharmgkb.org";
	            break;
	        case "2.16.840.1.113883.3.1077":
	            system = "http://clinicaltrials.gov";
	            break;
	        default:
	            system = "urn:oid:" + codeSystem;
	            break;
	    }
		return system;
	}
	
	public Coding tParticipationType2ParticipationTypeCode(String cdaParticipationType) {
		Coding fhirParticipationType = new Coding();
		fhirParticipationType.setSystem("http://hl7.org/fhir/v3/ParticipationType");
		String code = null;
		String display = null;

		switch(cdaParticipationType) {
		case "PRF": code = "PRF"; display = "performer";
		break;
		case "SBJ": code = "SBJ"; display ="subject";
				break;
		case "ADM": code = "ADM"; display = "admitter";
				break;
		case "ATND": code = "ATND"; display = "attender";
				break;
		case "AUT": code = "AUT"; display = "author";
				break;
		case "AUTHEN": code = "AUTHEN"; display = "authenticator";
			break;
		case "BBY": code = "BBY"; display = "baby";
			break;
		case "BEN": code = "BEN"; display = "beneficiary";
			break;
		case "CALLBCK": code = "CALLBCK"; display = "callback contact";
			break;
		case "CON": code = "CON"; display = "consultant";
			break;
		case "COV": code = "COV"; display = "coverage target";
			break;
		case "CSM": code = "CSM"; display = "consumable";
			break;
		case "CST": code = "CST"; display = "custodian";
			break;
		case "DEV": code = "DEV"; display = "device";
			break;
		case "DIR": code = "DIR"; display = "direct target";
			break;
		case "DIS": code = "DIS"; display = "discharger";
			break;
		case "DIST": code = "DIST"; display = "distributor";
			break;
		case "DON": code = "DON"; display = "donor";
			break;
		case "DST": code = "DST"; display = "destination";
			break;
		case "ELOC": code = "ELOC"; display = "entry location";
			break;
		case "ENT": code = "ENT"; display = " data entry person";
			break;
		case "ESC": code = "ESC"; display = "escort";
			break;
		case "HLD": code = "HLD"; display = "holder";
			break;
		case "IND": code = "IND"; display = "indirect target";
			break;
		case "INF": code = "INF"; display = "informant";
			break;
		case "IRCP": code = "IRCP"; display = "information recipient";
			break;
		case "LA": code = "LA"; display = "legal authenticator";
			break;
		case "LOC": code = "LOC"; display = "location";
			break;
		case "NOT": code = "NOT"; display = "ugent notification contact";
			break;
		case "NRD": code = "NRD"; display = "non-reuseable device";
			break;
		case "ORG": code = "ORG"; display = "origin";
			break;
		case "PPRF": code = "PPRF"; display = "primary performer";
			break;
		case "PRCP": code = "PRCP"; display = "primary information recipient";
			break;
		case "PRD": code = "PRD"; display = "product";
			break;
		case "RCT": code = "RCT"; display = "record target";
			break;
		case "RCV": code = "RCV"; display = "receiver";
			break;
		case "RDV": code = "RDV"; display = "reusable device";
			break;
		case "REF": code = "REF"; display = "referrer";
			break;
		case "REFB": code = "REFB"; display = "Referred By";
			break;
		case "REFT": code = "REFT"; display = "Referred to";
			break;
		case "RESP": code = "RESP"; display = "responsible party";
			break;
		case "RML": code = "RML"; display = "remote";
			break;
		case "SPC": code = "SPC"; display = "specimen";
			break;
		case "SPRF": code = "SPRF"; display = "secondary performer";
			break;
		case "TRC": code = "TRC"; display = "tracker";
			break;
		case "VIA": code = "VIA"; display = "via";
			break;
		case "VRF": code = "VRF"; display = "verifier";
			break;
		
		case "WIT": code = "WIT"; display = "witness";
				break;
			default:
				break;
		}
		if(code != null && display != null) {
			fhirParticipationType.setCode(code);
			fhirParticipationType.setDisplay(display);
		}
		return fhirParticipationType;
	}

	public UnitsOfTime tPeriodUnit2UnitsOfTime(String cdaPeriodUnit) {
		switch(cdaPeriodUnit.toLowerCase()) {
			case "a":
				return UnitsOfTime.A;
			case "d":
				return UnitsOfTime.D;
			case "h":
				return UnitsOfTime.H;
			case "min":
				return UnitsOfTime.MIN;
			case "mo":
				return UnitsOfTime.MO;
			case "s":
				return UnitsOfTime.S;
			case "wk":
				return UnitsOfTime.WK;
			default:
				return null;
		}
	}
	
	public AddressType tPostalAddressUse2AddressType(String cdaPostalAddressUse) {
		switch(cdaPostalAddressUse) {
			case "PHYS":
				return AddressType.PHYSICAL;
			case "PST":
				return AddressType.POSTAL;
			default:
				return null;
		}
	}

	public AddressUse tPostalAdressUse2AddressUse(String cdaPostalAddressUse) {
		switch(cdaPostalAddressUse) {
			case "HP":
			case "H":
				return AddressUse.HOME;
			case "WP":
				return AddressUse.WORK;
			case "TMP":
				return AddressUse.TEMP;
			//case "BAD": return AddressUse.OLD;
			default:
				return AddressUse.TEMP;
		}
	}
/*
	public ConditionCategoryCodes tProblemType2ConditionCategoryCodes(String cdaProblemType) {
		if(cdaProblemType == null)
			return null;
		switch(cdaProblemType) {
			case "248536006":
			case "373930000":
			case "404684003": 
			case "75321-0":
			case "75312-9":
				return ConditionCategoryCodes.FINDING;
			case "409586006": 
			case "75322-8":
			case "75313-7":
				return ConditionCategoryCodes.COMPLAINT;
			case "282291009": 
			case "29308-4":
			case "75314-5":
			case "55607006": // problem
			case "75318-6": 
			case "75323-6": // condition
			case "75315-2":
			case "64572001": 
				return ConditionCategoryCodes.DIAGNOSIS;
			case "418799008": 
			case "75325-1":
			case "75317-8":
				return ConditionCategoryCodes.SYMPTOM;
				
			default: 
				return null;
		}
	}
	*/
	public DiagnosticReportStatus tResultOrganizerStatusCode2DiagnosticReportStatus(String cdaResultOrganizerStatusCode) {
		if(cdaResultOrganizerStatusCode == null)
			return null;
		
		switch(cdaResultOrganizerStatusCode.toLowerCase()) {
			case "aborted":
				return DiagnosticReportStatus.CANCELLED;
			case "active":
				return DiagnosticReportStatus.PARTIAL;
			case "cancelled":
				return DiagnosticReportStatus.CANCELLED;
			case "completed":
				return DiagnosticReportStatus.FINAL;
			case "held":
				return DiagnosticReportStatus.REGISTERED;
			case "suspended":
				return DiagnosticReportStatus.ENTEREDINERROR;
			default:
				return null;
		}
	}
	
	public Coding tRoleCode2PatientContactRelationshipCode(String cdaRoleCode) {
		if(cdaRoleCode == null)
			return null;
		
		Coding fhirPatientContactRelationshipCode = new Coding();
		fhirPatientContactRelationshipCode.setSystem("http://hl7.org/fhir/patient-contact-relationship");
		String code = null;
		String display = null;
		
		switch(cdaRoleCode.toLowerCase()) {
			case "econ": // emergency contact
				code="emergency"; display = "Emergency"; break;
			case "ext": // extended family member
			case "fammemb": // family member
				code = "family"; display = "Family"; break;
			case "guard": // guardian
				code = "guardian"; display = "Guardian"; break;
			case "frnd": // friend
				code = "friend"; display = "Friend"; break;
			case "sps": // spouse
			case "dompart": // domestic partner
			case "husb": // husband
			case "wife": // wife
				code = "partner"; display = "Partner"; break;
			case "work": 
				code = "work"; display = "Work"; break;
			case "gt": 
				code = "guarantor"; display = "Guarantor"; break;
			case "prn": // parent
			case "fth": // father
			case "mth": // mother
			case "nprn": // natural parent
			case "nfth": // natural father
			case "nmth": // natural mother
			case "prinlaw": // parent in-law
			case "fthinlaw": // father in-law
			case "mthinlaw": // mother in-law
			case "stpprn": // step parent
			case "stpfth": // stepfather
			case "stpmth": // stepmother
				code = "parent"; display = "Parent"; break;
			case "powatt":
				code = "agent"; display = "Agent"; break;
			default:
				return null;
		}
		fhirPatientContactRelationshipCode.setCode(code);
		fhirPatientContactRelationshipCode.setDisplay(display);
		return fhirPatientContactRelationshipCode;
	}
	
	public AllergyIntoleranceSeverity tSeverityCode2AllergyIntoleranceSeverity(String cdaSeverityCode) {
		if(cdaSeverityCode == null)
			return null;
		switch(cdaSeverityCode) {
			case "255604002": 
				return AllergyIntoleranceSeverity.MILD;
			case "371923003": 
				return AllergyIntoleranceSeverity.MILD;
			case "6736007": 
				return AllergyIntoleranceSeverity.MODERATE;
			case "371924009": 
				return AllergyIntoleranceSeverity.MODERATE;
			case "24484000": 
				return AllergyIntoleranceSeverity.SEVERE;
			case "399166001": 
				return AllergyIntoleranceSeverity.SEVERE;
			default: 
				return null;
		}
	}
	/*
	public AllergyIntoleranceStatus tStatusCode2AllergyIntoleranceStatus(String cdaStatusCode) {
		switch(cdaStatusCode.toLowerCase()) {
			case "active":
				return AllergyIntoleranceStatus.ACTIVE;
			case "nullified":
			case "error":
				return AllergyIntoleranceStatus.ENTERED_IN_ERROR;
			case "confirmed":
				return AllergyIntoleranceStatus.CONFIRMED;
			case "unconfirmed":
				return AllergyIntoleranceStatus.UNCONFIRMED;
			case "refuted":
				return AllergyIntoleranceStatus.REFUTED;
			case "inactive":
				return AllergyIntoleranceStatus.INACTIVE;
			case "resolved":
				return AllergyIntoleranceStatus.RESOLVED;
			default:
				return null;
		}
	}*/

	public ConditionClinicalStatus tStatusCode2ConditionClinicalStatusCodes(String cdaStatusCode) {
		switch (cdaStatusCode.toLowerCase()) {
			// semantically not the same, but at least outcome-wise it is similar
			case "aborted":
				return ConditionClinicalStatus.RESOLVED;
			case "active":
				return ConditionClinicalStatus.ACTIVE;
			case "completed":
				return ConditionClinicalStatus.RESOLVED;
			case "suspended":
				return ConditionClinicalStatus.REMISSION;
			default:
				return null;
		}
	}

	public EncounterStatus tStatusCode2EncounterStatus(String cdaStatusCode) {
		switch(cdaStatusCode.toLowerCase()) {
			case "in-progress":
			case "active":
				return EncounterStatus.INPROGRESS;
			case "onleave":
				return EncounterStatus.ONLEAVE;
			case "finished":
			case "completed":
				return EncounterStatus.FINISHED;
			case "cancelled":
				return EncounterStatus.CANCELLED;
			case "planned":
				return EncounterStatus.PLANNED;
			case "arrived":
				return EncounterStatus.ARRIVED;
			case "triaged":
				return EncounterStatus.TRIAGED;
			default:
				return null;
		}
	}

	public MedicationDispenseStatus tStatusCode2MedicationDispenseStatus(String cdaStatusCode) {
		switch(cdaStatusCode.toLowerCase()) {
			case "active":
			case "in-progress":
			case "inprogress":
				return MedicationDispenseStatus.INPROGRESS;
			case "on-hold":
			case "onhold":
			case "suspended":
				return MedicationDispenseStatus.ONHOLD;
			case "completed":
				return MedicationDispenseStatus.COMPLETED;
			case "nullified":
			case "error":
			case "entered-in-error":
				return MedicationDispenseStatus.ENTEREDINERROR;
			case "stopped":
				return MedicationDispenseStatus.STOPPED;
			default:
				return null;
		}
	}

	public MedicationStatementStatus tStatusCode2MedicationStatementStatus(String cdaStatusCode) {
		switch(cdaStatusCode.toLowerCase()) {
			case "active":
				return MedicationStatementStatus.ACTIVE;
			case "intended":
				return MedicationStatementStatus.INTENDED;
			case "completed":
				return MedicationStatementStatus.COMPLETED;
			case "nullified":
				return MedicationStatementStatus.ENTEREDINERROR;
			default:
				return null;
		}
	}

	public ProcedureStatus tStatusCode2ProcedureStatus(String cdaStatusCode) {
		switch(cdaStatusCode.toLowerCase()) {
			case "active":
				return ProcedureStatus.INPROGRESS;
			case "completed":
				return ProcedureStatus.COMPLETED;
			case "aborted":
			case "aboted":
				return ProcedureStatus.ABORTED;
			case "error":
				return ProcedureStatus.ENTEREDINERROR;
			default:
				return null;
		}
	}

	public ContactPointUse tTelecommunicationAddressUse2ContactPointUse(String cdaTelecommunicationAddressUse) {
		switch(cdaTelecommunicationAddressUse) {
			case "H":
			case "HP":
				return ContactPointUse.HOME;
			case "WP":
				return ContactPointUse.WORK;
			case "TMP":
				return ContactPointUse.TEMP;
			case "BAD":
				return ContactPointUse.OLD;
			case "MC":
				return ContactPointUse.MOBILE;
			default:
				return ContactPointUse.TEMP;
		}

	}

	public ContactPointSystem tTelValue2ContactPointSystem(String cdaTelValue) {
		if(cdaTelValue == null)
			return null;
		
		switch(cdaTelValue.toLowerCase()) {
			case "phone":
			case "tel":
				return ContactPointSystem.PHONE;
			case "email":
			case "mailto":
				return ContactPointSystem.EMAIL;
			case "fax":
				return ContactPointSystem.FAX;
			case "http":
			case "https":
				return ContactPointSystem.URL;
			default:
				return null;
		}
	}
	
}