package org.motechproject.care.reporting.domain.measure;

// Generated Jun 4, 2013 4:50:32 PM by Hibernate Tools 3.4.0.CR1

import org.hibernate.annotations.Cascade;
import org.motechproject.care.reporting.domain.dimension.Flw;
import org.motechproject.care.reporting.domain.dimension.MotherCase;
import org.motechproject.care.reporting.utils.FormToString;

import javax.persistence.*;
import java.util.Date;

/**
 * EbfMotherForm generated by hbm2java
 */
@Entity
@Table(name = "ebf_mother_form", uniqueConstraints = @UniqueConstraint(columnNames = "instance_id"))
public class EbfMotherForm extends Form {

	private int id;
	private Flw flw;
	private MotherCase motherCase;
	private Date timeEnd;
	private Date timeStart;
	private Date dateModified;
	private Date addval;
	private Boolean adoptImmediately;
	private Boolean askPpiud;
	private Boolean awareOfFailure;
	private Boolean bleeding;
    private Boolean complications;
	private Boolean condoms;
	private Boolean counselFollowUpPpiud;
	private Boolean counselFollowUpPptl;
	private Boolean counselMenstrualCycle;
	private Boolean counselMethods;
	private Boolean counselPpfp;
	private Boolean counselTimeIud;
	private Date dateEb1;
	private Date dateEb2;
	private Date dateEb3;
	private Date dateEb4;
	private Date dateEb5;
	private Date dateEb6;
	private Date dateIudAdopted;
	private Date dateLastInj;
	private Date dateLastVisit;
	private Date dateNextCf;
	private Date dateNextEb;
	private Boolean discharge;
	private Boolean distension;
	private Short ebVisitNum;
	private String familyPlanningType;
	private Boolean fever;
	private Boolean haveCondoms;
	private Boolean headaches;
	private Boolean highBp;
	private Boolean injMenstrualIrregularity;
	private Boolean injectable;
	private Boolean intendToContinue;
	private Boolean intervalPpfpInterest;
	private Boolean iud;
	private Boolean iudAdopted;
	private Boolean iudCounselDuration;
	private Boolean iudCounselFollowUp;
	private Boolean iudCounselHospital;
	private Boolean iudCounselPlacement;
	private Boolean iudCounselScreening;
	private Boolean iudCounselSideEffects;
	private String lastVisitType;
	private Boolean menstrualIrregularity;
	private Date nextInjCalc;
	private String nextvisittype;
	private Short numChildren;
	private Boolean ocp;
	private Boolean ocpContinue;
	private Boolean ocpCounselRegularity;
	private Boolean painSwelling;
	private Boolean ppfpInterest;
	private Boolean ppiudAbdominalPain;
	private Boolean ppiudProblems;
	private Boolean pptlAbdominalPain;
	private Boolean pptlPainSurgery;
	private Boolean pptlProblems;
	private Boolean regularPeriods;
	private Boolean tabletsReceived;
	private Boolean takenAsPrescribed;
	private Boolean tl;
	private Boolean tlAdopted;
	private Boolean tlConselIncentives;
	private Boolean tlCounselFollowUp;
	private Boolean tlCounselHospital;
	private Boolean tlCounselIrreversible;
	private Boolean tlCounselScreening;
	private Boolean tlCounselSideEffects;
	private Boolean tlCounselTiming;
	private Boolean understandTablets;
	private Boolean usingCorrectly;
	private Boolean whereReplace;
	private String whyNoPpffp;
	private Boolean within42;
	private Date dateTlAdopted;
	private Boolean abdominalPain;
	private Boolean painUrination;
	private Boolean ppiudBleeding;
	private Boolean ppiudDischarge;
	private Boolean ppiudFever;
    private Date creationTime = new Date();

    public EbfMotherForm() {
	}

	public EbfMotherForm(int id) {
		this.id = id;
	}

	public EbfMotherForm(int id, Flw flw, MotherCase motherCase,
                         String instanceId, Date timeEnd, Date timeStart, Date dateModified,
                         Date addval, Boolean adoptImmediately, Boolean askPpiud,
                         Boolean awareOfFailure, Boolean bleeding,
                         Boolean complications, Boolean condoms,
                         Boolean counselFollowUpPpiud, Boolean counselFollowUpPptl,
                         Boolean counselMenstrualCycle, Boolean counselMethods,
                         Boolean counselPpfp, Boolean counselTimeIud, Date dateEb1,
                         Date dateEb2, Date dateEb3, Date dateEb4, Date dateEb5,
                         Date dateEb6, Date dateIudAdopted, Date dateLastInj,
                         Date dateLastVisit, Date dateNextCf, Date dateNextEb,
                         Boolean discharge, Boolean distension, Short ebVisitNum,
                         String familyPlanningType, Boolean fever, Boolean haveCondoms,
                         Boolean headaches, Boolean highBp,
                         Boolean injMenstrualIrregularity, Boolean injectable,
                         Boolean intendToContinue, Boolean intervalPpfpInterest,
                         Boolean iud, Boolean iudAdopted, Boolean iudCounselDuration,
                         Boolean iudCounselFollowUp, Boolean iudCounselHospital,
                         Boolean iudCounselPlacement, Boolean iudCounselScreening,
                         Boolean iudCounselSideEffects, String lastVisitType,
                         Boolean menstrualIrregularity, Date nextInjCalc,
                         String nextvisittype, Short numChildren, Boolean ocp,
                         Boolean ocpContinue, Boolean ocpCounselRegularity,
                         Boolean painSwelling, Boolean ppfpInterest,
                         Boolean ppiudAbdominalPain, Boolean ppiudProblems,
                         Boolean pptlAbdominalPain, Boolean pptlPainSurgery,
                         Boolean pptlProblems, Boolean regularPeriods,
                         Boolean tabletsReceived, Boolean takenAsPrescribed, Boolean tl,
                         Boolean tlAdopted, Boolean tlConselIncentives,
                         Boolean tlCounselFollowUp, Boolean tlCounselHospital,
                         Boolean tlCounselIrreversible, Boolean tlCounselScreening,
                         Boolean tlCounselSideEffects, Boolean tlCounselTiming,
                         Boolean understandTablets, Boolean usingCorrectly,
                         Boolean whereReplace, String whyNoPpffp, Boolean within42,
                         Date dateTlAdopted, Boolean abdominalPain, Boolean painUrination,
                         Boolean ppiudBleeding, Boolean ppiudDischarge, Boolean ppiudFever, Date creationTime) {
        super(instanceId);
        this.id = id;
		this.flw = flw;
		this.motherCase = motherCase;
		this.timeEnd = timeEnd;
		this.timeStart = timeStart;
		this.dateModified = dateModified;
		this.addval = addval;
		this.adoptImmediately = adoptImmediately;
		this.askPpiud = askPpiud;
		this.awareOfFailure = awareOfFailure;
		this.bleeding = bleeding;
        this.complications = complications;
		this.condoms = condoms;
		this.counselFollowUpPpiud = counselFollowUpPpiud;
		this.counselFollowUpPptl = counselFollowUpPptl;
		this.counselMenstrualCycle = counselMenstrualCycle;
		this.counselMethods = counselMethods;
		this.counselPpfp = counselPpfp;
		this.counselTimeIud = counselTimeIud;
		this.dateEb1 = dateEb1;
		this.dateEb2 = dateEb2;
		this.dateEb3 = dateEb3;
		this.dateEb4 = dateEb4;
		this.dateEb5 = dateEb5;
		this.dateEb6 = dateEb6;
		this.dateIudAdopted = dateIudAdopted;
		this.dateLastInj = dateLastInj;
		this.dateLastVisit = dateLastVisit;
		this.dateNextCf = dateNextCf;
		this.dateNextEb = dateNextEb;
		this.discharge = discharge;
		this.distension = distension;
		this.ebVisitNum = ebVisitNum;
		this.familyPlanningType = familyPlanningType;
		this.fever = fever;
		this.haveCondoms = haveCondoms;
		this.headaches = headaches;
		this.highBp = highBp;
		this.injMenstrualIrregularity = injMenstrualIrregularity;
		this.injectable = injectable;
		this.intendToContinue = intendToContinue;
		this.intervalPpfpInterest = intervalPpfpInterest;
		this.iud = iud;
		this.iudAdopted = iudAdopted;
		this.iudCounselDuration = iudCounselDuration;
		this.iudCounselFollowUp = iudCounselFollowUp;
		this.iudCounselHospital = iudCounselHospital;
		this.iudCounselPlacement = iudCounselPlacement;
		this.iudCounselScreening = iudCounselScreening;
		this.iudCounselSideEffects = iudCounselSideEffects;
		this.lastVisitType = lastVisitType;
		this.menstrualIrregularity = menstrualIrregularity;
		this.nextInjCalc = nextInjCalc;
		this.nextvisittype = nextvisittype;
		this.numChildren = numChildren;
		this.ocp = ocp;
		this.ocpContinue = ocpContinue;
		this.ocpCounselRegularity = ocpCounselRegularity;
		this.painSwelling = painSwelling;
		this.ppfpInterest = ppfpInterest;
		this.ppiudAbdominalPain = ppiudAbdominalPain;
		this.ppiudProblems = ppiudProblems;
		this.pptlAbdominalPain = pptlAbdominalPain;
		this.pptlPainSurgery = pptlPainSurgery;
		this.pptlProblems = pptlProblems;
		this.regularPeriods = regularPeriods;
		this.tabletsReceived = tabletsReceived;
		this.takenAsPrescribed = takenAsPrescribed;
		this.tl = tl;
		this.tlAdopted = tlAdopted;
		this.tlConselIncentives = tlConselIncentives;
		this.tlCounselFollowUp = tlCounselFollowUp;
		this.tlCounselHospital = tlCounselHospital;
		this.tlCounselIrreversible = tlCounselIrreversible;
		this.tlCounselScreening = tlCounselScreening;
		this.tlCounselSideEffects = tlCounselSideEffects;
		this.tlCounselTiming = tlCounselTiming;
		this.understandTablets = understandTablets;
		this.usingCorrectly = usingCorrectly;
		this.whereReplace = whereReplace;
		this.whyNoPpffp = whyNoPpffp;
		this.within42 = within42;
		this.dateTlAdopted = dateTlAdopted;
		this.abdominalPain = abdominalPain;
		this.painUrination = painUrination;
		this.ppiudBleeding = ppiudBleeding;
		this.ppiudDischarge = ppiudDischarge;
		this.ppiudFever = ppiudFever;
        this.creationTime = creationTime;
    }

	@Id
	@Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
    @Cascade(value = org.hibernate.annotations.CascadeType.ALL)
	public Flw getFlw() {
		return this.flw;
	}

	public void setFlw(Flw flw) {
		this.flw = flw;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "case_id")
    @Cascade(value = org.hibernate.annotations.CascadeType.ALL)
	public MotherCase getMotherCase() {
		return this.motherCase;
	}

	public void setMotherCase(MotherCase motherCase) {
		this.motherCase = motherCase;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "time_end", length = 35)
	public Date getTimeEnd() {
		return this.timeEnd;
	}

	public void setTimeEnd(Date timeEnd) {
		this.timeEnd = timeEnd;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "time_start", length = 35)
	public Date getTimeStart() {
		return this.timeStart;
	}

	public void setTimeStart(Date timeStart) {
		this.timeStart = timeStart;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_modified", length = 35)
	public Date getDateModified() {
		return this.dateModified;
	}

	public void setDateModified(Date dateModified) {
		this.dateModified = dateModified;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "addval", length = 13)
	public Date getAddval() {
		return this.addval;
	}

	public void setAddval(Date addval) {
		this.addval = addval;
	}

	@Column(name = "adopt_immediately")
	public Boolean getAdoptImmediately() {
		return this.adoptImmediately;
	}

	public void setAdoptImmediately(Boolean adoptImmediately) {
		this.adoptImmediately = adoptImmediately;
	}

	@Column(name = "ask_ppiud")
	public Boolean getAskPpiud() {
		return this.askPpiud;
	}

	public void setAskPpiud(Boolean askPpiud) {
		this.askPpiud = askPpiud;
	}

	@Column(name = "aware_of_failure")
	public Boolean getAwareOfFailure() {
		return this.awareOfFailure;
	}

	public void setAwareOfFailure(Boolean awareOfFailure) {
		this.awareOfFailure = awareOfFailure;
	}

	@Column(name = "bleeding")
	public Boolean getBleeding() {
		return this.bleeding;
	}

	public void setBleeding(Boolean bleeding) {
		this.bleeding = bleeding;
	}

    @Column(name = "complications")
	public Boolean getComplications() {
		return this.complications;
	}

	public void setComplications(Boolean complications) {
		this.complications = complications;
	}

	@Column(name = "condoms")
	public Boolean getCondoms() {
		return this.condoms;
	}

	public void setCondoms(Boolean condoms) {
		this.condoms = condoms;
	}

	@Column(name = "counsel_follow_up_ppiud")
	public Boolean getCounselFollowUpPpiud() {
		return this.counselFollowUpPpiud;
	}

	public void setCounselFollowUpPpiud(Boolean counselFollowUpPpiud) {
		this.counselFollowUpPpiud = counselFollowUpPpiud;
	}

	@Column(name = "counsel_follow_up_pptl")
	public Boolean getCounselFollowUpPptl() {
		return this.counselFollowUpPptl;
	}

	public void setCounselFollowUpPptl(Boolean counselFollowUpPptl) {
		this.counselFollowUpPptl = counselFollowUpPptl;
	}

	@Column(name = "counsel_menstrual_cycle")
	public Boolean getCounselMenstrualCycle() {
		return this.counselMenstrualCycle;
	}

	public void setCounselMenstrualCycle(Boolean counselMenstrualCycle) {
		this.counselMenstrualCycle = counselMenstrualCycle;
	}

	@Column(name = "counsel_methods")
	public Boolean getCounselMethods() {
		return this.counselMethods;
	}

	public void setCounselMethods(Boolean counselMethods) {
		this.counselMethods = counselMethods;
	}

	@Column(name = "counsel_ppfp")
	public Boolean getCounselPpfp() {
		return this.counselPpfp;
	}

	public void setCounselPpfp(Boolean counselPpfp) {
		this.counselPpfp = counselPpfp;
	}

	@Column(name = "counsel_time_iud")
	public Boolean getCounselTimeIud() {
		return this.counselTimeIud;
	}

	public void setCounselTimeIud(Boolean counselTimeIud) {
		this.counselTimeIud = counselTimeIud;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_eb_1", length = 13)
	public Date getDateEb1() {
		return this.dateEb1;
	}

	public void setDateEb1(Date dateEb1) {
		this.dateEb1 = dateEb1;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_eb_2", length = 13)
	public Date getDateEb2() {
		return this.dateEb2;
	}

	public void setDateEb2(Date dateEb2) {
		this.dateEb2 = dateEb2;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_eb_3", length = 13)
	public Date getDateEb3() {
		return this.dateEb3;
	}

	public void setDateEb3(Date dateEb3) {
		this.dateEb3 = dateEb3;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_eb_4", length = 13)
	public Date getDateEb4() {
		return this.dateEb4;
	}

	public void setDateEb4(Date dateEb4) {
		this.dateEb4 = dateEb4;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_eb_5", length = 13)
	public Date getDateEb5() {
		return this.dateEb5;
	}

	public void setDateEb5(Date dateEb5) {
		this.dateEb5 = dateEb5;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_eb_6", length = 13)
	public Date getDateEb6() {
		return this.dateEb6;
	}

	public void setDateEb6(Date dateEb6) {
		this.dateEb6 = dateEb6;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_iud_adopted", length = 13)
	public Date getDateIudAdopted() {
		return this.dateIudAdopted;
	}

	public void setDateIudAdopted(Date dateIudAdopted) {
		this.dateIudAdopted = dateIudAdopted;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_last_inj", length = 13)
	public Date getDateLastInj() {
		return this.dateLastInj;
	}

	public void setDateLastInj(Date dateLastInj) {
		this.dateLastInj = dateLastInj;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_last_visit", length = 13)
	public Date getDateLastVisit() {
		return this.dateLastVisit;
	}

	public void setDateLastVisit(Date dateLastVisit) {
		this.dateLastVisit = dateLastVisit;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_next_cf", length = 13)
	public Date getDateNextCf() {
		return this.dateNextCf;
	}

	public void setDateNextCf(Date dateNextCf) {
		this.dateNextCf = dateNextCf;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_next_eb", length = 13)
	public Date getDateNextEb() {
		return this.dateNextEb;
	}

	public void setDateNextEb(Date dateNextEb) {
		this.dateNextEb = dateNextEb;
	}

	@Column(name = "discharge")
	public Boolean getDischarge() {
		return this.discharge;
	}

	public void setDischarge(Boolean discharge) {
		this.discharge = discharge;
	}

	@Column(name = "distension")
	public Boolean getDistension() {
		return this.distension;
	}

	public void setDistension(Boolean distension) {
		this.distension = distension;
	}

	@Column(name = "eb_visit_num")
	public Short getEbVisitNum() {
		return this.ebVisitNum;
	}

	public void setEbVisitNum(Short ebVisitNum) {
		this.ebVisitNum = ebVisitNum;
	}

	@Column(name = "family_planning_type")
	public String getFamilyPlanningType() {
		return this.familyPlanningType;
	}

	public void setFamilyPlanningType(String familyPlanningType) {
		this.familyPlanningType = familyPlanningType;
	}

	@Column(name = "fever")
	public Boolean getFever() {
		return this.fever;
	}

	public void setFever(Boolean fever) {
		this.fever = fever;
	}

	@Column(name = "have_condoms")
	public Boolean getHaveCondoms() {
		return this.haveCondoms;
	}

	public void setHaveCondoms(Boolean haveCondoms) {
		this.haveCondoms = haveCondoms;
	}

	@Column(name = "headaches")
	public Boolean getHeadaches() {
		return this.headaches;
	}

	public void setHeadaches(Boolean headaches) {
		this.headaches = headaches;
	}

	@Column(name = "high_bp")
	public Boolean getHighBp() {
		return this.highBp;
	}

	public void setHighBp(Boolean highBp) {
		this.highBp = highBp;
	}

	@Column(name = "inj_menstrual_irregularity")
	public Boolean getInjMenstrualIrregularity() {
		return this.injMenstrualIrregularity;
	}

	public void setInjMenstrualIrregularity(Boolean injMenstrualIrregularity) {
		this.injMenstrualIrregularity = injMenstrualIrregularity;
	}

	@Column(name = "injectable")
	public Boolean getInjectable() {
		return this.injectable;
	}

	public void setInjectable(Boolean injectable) {
		this.injectable = injectable;
	}

	@Column(name = "intend_to_continue")
	public Boolean getIntendToContinue() {
		return this.intendToContinue;
	}

	public void setIntendToContinue(Boolean intendToContinue) {
		this.intendToContinue = intendToContinue;
	}

	@Column(name = "interval_ppfp_interest")
	public Boolean getIntervalPpfpInterest() {
		return this.intervalPpfpInterest;
	}

	public void setIntervalPpfpInterest(Boolean intervalPpfpInterest) {
		this.intervalPpfpInterest = intervalPpfpInterest;
	}

	@Column(name = "iud")
	public Boolean getIud() {
		return this.iud;
	}

	public void setIud(Boolean iud) {
		this.iud = iud;
	}

	@Column(name = "iud_adopted")
	public Boolean getIudAdopted() {
		return this.iudAdopted;
	}

	public void setIudAdopted(Boolean iudAdopted) {
		this.iudAdopted = iudAdopted;
	}

	@Column(name = "iud_counsel_duration")
	public Boolean getIudCounselDuration() {
		return this.iudCounselDuration;
	}

	public void setIudCounselDuration(Boolean iudCounselDuration) {
		this.iudCounselDuration = iudCounselDuration;
	}

	@Column(name = "iud_counsel_follow_up")
	public Boolean getIudCounselFollowUp() {
		return this.iudCounselFollowUp;
	}

	public void setIudCounselFollowUp(Boolean iudCounselFollowUp) {
		this.iudCounselFollowUp = iudCounselFollowUp;
	}

	@Column(name = "iud_counsel_hospital")
	public Boolean getIudCounselHospital() {
		return this.iudCounselHospital;
	}

	public void setIudCounselHospital(Boolean iudCounselHospital) {
		this.iudCounselHospital = iudCounselHospital;
	}

	@Column(name = "iud_counsel_placement")
	public Boolean getIudCounselPlacement() {
		return this.iudCounselPlacement;
	}

	public void setIudCounselPlacement(Boolean iudCounselPlacement) {
		this.iudCounselPlacement = iudCounselPlacement;
	}

	@Column(name = "iud_counsel_screening")
	public Boolean getIudCounselScreening() {
		return this.iudCounselScreening;
	}

	public void setIudCounselScreening(Boolean iudCounselScreening) {
		this.iudCounselScreening = iudCounselScreening;
	}

	@Column(name = "iud_counsel_side_effects")
	public Boolean getIudCounselSideEffects() {
		return this.iudCounselSideEffects;
	}

	public void setIudCounselSideEffects(Boolean iudCounselSideEffects) {
		this.iudCounselSideEffects = iudCounselSideEffects;
	}

	@Column(name = "last_visit_type", length = 20)
	public String getLastVisitType() {
		return this.lastVisitType;
	}

	public void setLastVisitType(String lastVisitType) {
		this.lastVisitType = lastVisitType;
	}

	@Column(name = "menstrual_irregularity")
	public Boolean getMenstrualIrregularity() {
		return this.menstrualIrregularity;
	}

	public void setMenstrualIrregularity(Boolean menstrualIrregularity) {
		this.menstrualIrregularity = menstrualIrregularity;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "next_inj_calc", length = 13)
	public Date getNextInjCalc() {
		return this.nextInjCalc;
	}

	public void setNextInjCalc(Date nextInjCalc) {
		this.nextInjCalc = nextInjCalc;
	}

	@Column(name = "nextvisittype", length = 20)
	public String getNextvisittype() {
		return this.nextvisittype;
	}

	public void setNextvisittype(String nextvisittype) {
		this.nextvisittype = nextvisittype;
	}

	@Column(name = "num_children")
	public Short getNumChildren() {
		return this.numChildren;
	}

	public void setNumChildren(Short numChildren) {
		this.numChildren = numChildren;
	}

	@Column(name = "ocp")
	public Boolean getOcp() {
		return this.ocp;
	}

	public void setOcp(Boolean ocp) {
		this.ocp = ocp;
	}

	@Column(name = "ocp_continue")
	public Boolean getOcpContinue() {
		return this.ocpContinue;
	}

	public void setOcpContinue(Boolean ocpContinue) {
		this.ocpContinue = ocpContinue;
	}

	@Column(name = "ocp_counsel_regularity")
	public Boolean getOcpCounselRegularity() {
		return this.ocpCounselRegularity;
	}

	public void setOcpCounselRegularity(Boolean ocpCounselRegularity) {
		this.ocpCounselRegularity = ocpCounselRegularity;
	}

	@Column(name = "pain_swelling")
	public Boolean getPainSwelling() {
		return this.painSwelling;
	}

	public void setPainSwelling(Boolean painSwelling) {
		this.painSwelling = painSwelling;
	}

	@Column(name = "ppfp_interest")
	public Boolean getPpfpInterest() {
		return this.ppfpInterest;
	}

	public void setPpfpInterest(Boolean ppfpInterest) {
		this.ppfpInterest = ppfpInterest;
	}

	@Column(name = "ppiud_abdominal_pain")
	public Boolean getPpiudAbdominalPain() {
		return this.ppiudAbdominalPain;
	}

	public void setPpiudAbdominalPain(Boolean ppiudAbdominalPain) {
		this.ppiudAbdominalPain = ppiudAbdominalPain;
	}

	@Column(name = "ppiud_problems")
	public Boolean getPpiudProblems() {
		return this.ppiudProblems;
	}

	public void setPpiudProblems(Boolean ppiudProblems) {
		this.ppiudProblems = ppiudProblems;
	}

	@Column(name = "pptl_abdominal_pain")
	public Boolean getPptlAbdominalPain() {
		return this.pptlAbdominalPain;
	}

	public void setPptlAbdominalPain(Boolean pptlAbdominalPain) {
		this.pptlAbdominalPain = pptlAbdominalPain;
	}

	@Column(name = "pptl_pain_surgery")
	public Boolean getPptlPainSurgery() {
		return this.pptlPainSurgery;
	}

	public void setPptlPainSurgery(Boolean pptlPainSurgery) {
		this.pptlPainSurgery = pptlPainSurgery;
	}

	@Column(name = "pptl_problems")
	public Boolean getPptlProblems() {
		return this.pptlProblems;
	}

	public void setPptlProblems(Boolean pptlProblems) {
		this.pptlProblems = pptlProblems;
	}

	@Column(name = "regular_periods")
	public Boolean getRegularPeriods() {
		return this.regularPeriods;
	}

	public void setRegularPeriods(Boolean regularPeriods) {
		this.regularPeriods = regularPeriods;
	}

	@Column(name = "tablets_received")
	public Boolean getTabletsReceived() {
		return this.tabletsReceived;
	}

	public void setTabletsReceived(Boolean tabletsReceived) {
		this.tabletsReceived = tabletsReceived;
	}

	@Column(name = "taken_as_prescribed")
	public Boolean getTakenAsPrescribed() {
		return this.takenAsPrescribed;
	}

	public void setTakenAsPrescribed(Boolean takenAsPrescribed) {
		this.takenAsPrescribed = takenAsPrescribed;
	}

	@Column(name = "tl")
	public Boolean getTl() {
		return this.tl;
	}

	public void setTl(Boolean tl) {
		this.tl = tl;
	}

	@Column(name = "tl_adopted")
	public Boolean getTlAdopted() {
		return this.tlAdopted;
	}

	public void setTlAdopted(Boolean tlAdopted) {
		this.tlAdopted = tlAdopted;
	}

	@Column(name = "tl_consel_incentives")
	public Boolean getTlConselIncentives() {
		return this.tlConselIncentives;
	}

	public void setTlConselIncentives(Boolean tlConselIncentives) {
		this.tlConselIncentives = tlConselIncentives;
	}

	@Column(name = "tl_counsel_follow_up")
	public Boolean getTlCounselFollowUp() {
		return this.tlCounselFollowUp;
	}

	public void setTlCounselFollowUp(Boolean tlCounselFollowUp) {
		this.tlCounselFollowUp = tlCounselFollowUp;
	}

	@Column(name = "tl_counsel_hospital")
	public Boolean getTlCounselHospital() {
		return this.tlCounselHospital;
	}

	public void setTlCounselHospital(Boolean tlCounselHospital) {
		this.tlCounselHospital = tlCounselHospital;
	}

	@Column(name = "tl_counsel_irreversible")
	public Boolean getTlCounselIrreversible() {
		return this.tlCounselIrreversible;
	}

	public void setTlCounselIrreversible(Boolean tlCounselIrreversible) {
		this.tlCounselIrreversible = tlCounselIrreversible;
	}

	@Column(name = "tl_counsel_screening")
	public Boolean getTlCounselScreening() {
		return this.tlCounselScreening;
	}

	public void setTlCounselScreening(Boolean tlCounselScreening) {
		this.tlCounselScreening = tlCounselScreening;
	}

	@Column(name = "tl_counsel_side_effects")
	public Boolean getTlCounselSideEffects() {
		return this.tlCounselSideEffects;
	}

	public void setTlCounselSideEffects(Boolean tlCounselSideEffects) {
		this.tlCounselSideEffects = tlCounselSideEffects;
	}

	@Column(name = "tl_counsel_timing")
	public Boolean getTlCounselTiming() {
		return this.tlCounselTiming;
	}

	public void setTlCounselTiming(Boolean tlCounselTiming) {
		this.tlCounselTiming = tlCounselTiming;
	}

	@Column(name = "understand_tablets")
	public Boolean getUnderstandTablets() {
		return this.understandTablets;
	}

	public void setUnderstandTablets(Boolean understandTablets) {
		this.understandTablets = understandTablets;
	}

	@Column(name = "using_correctly")
	public Boolean getUsingCorrectly() {
		return this.usingCorrectly;
	}

	public void setUsingCorrectly(Boolean usingCorrectly) {
		this.usingCorrectly = usingCorrectly;
	}

	@Column(name = "where_replace")
	public Boolean getWhereReplace() {
		return this.whereReplace;
	}

	public void setWhereReplace(Boolean whereReplace) {
		this.whereReplace = whereReplace;
	}

	@Column(name = "why_no_ppffp")
	public String getWhyNoPpffp() {
		return this.whyNoPpffp;
	}

	public void setWhyNoPpffp(String whyNoPpffp) {
		this.whyNoPpffp = whyNoPpffp;
	}

	@Column(name = "within_42")
	public Boolean getWithin42() {
		return this.within42;
	}

	public void setWithin42(Boolean within42) {
		this.within42 = within42;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_tl_adopted", length = 13)
	public Date getDateTlAdopted() {
		return this.dateTlAdopted;
	}

	public void setDateTlAdopted(Date dateTlAdopted) {
		this.dateTlAdopted = dateTlAdopted;
	}

	@Column(name = "abdominal_pain")
	public Boolean getAbdominalPain() {
		return this.abdominalPain;
	}

	public void setAbdominalPain(Boolean abdominalPain) {
		this.abdominalPain = abdominalPain;
	}

	@Column(name = "pain_urination")
	public Boolean getPainUrination() {
		return this.painUrination;
	}

	public void setPainUrination(Boolean painUrination) {
		this.painUrination = painUrination;
	}

	@Column(name = "ppiud_bleeding")
	public Boolean getPpiudBleeding() {
		return this.ppiudBleeding;
	}

	public void setPpiudBleeding(Boolean ppiudBleeding) {
		this.ppiudBleeding = ppiudBleeding;
	}

	@Column(name = "ppiud_discharge")
	public Boolean getPpiudDischarge() {
		return this.ppiudDischarge;
	}

	public void setPpiudDischarge(Boolean ppiudDischarge) {
		this.ppiudDischarge = ppiudDischarge;
	}

	@Column(name = "ppiud_fever")
	public Boolean getPpiudFever() {
		return this.ppiudFever;
	}

	public void setPpiudFever(Boolean ppiudFever) {
		this.ppiudFever = ppiudFever;
	}

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "creation_time")
    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    @Override
    public String toString() {
        return FormToString.toString(this);
    }
}
