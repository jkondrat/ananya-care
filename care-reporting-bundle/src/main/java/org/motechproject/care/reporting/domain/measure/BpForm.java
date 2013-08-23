package org.motechproject.care.reporting.domain.measure;

import org.hibernate.annotations.Cascade;
import org.motechproject.care.reporting.domain.dimension.Flw;
import org.motechproject.care.reporting.domain.dimension.MotherCase;
import org.motechproject.care.reporting.utils.FormToString;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "bp_form", uniqueConstraints = @UniqueConstraint(columnNames = "instance_id"))
public class BpForm extends Form {

	private int id;
	private Flw flw;
	private MotherCase motherCase;
	private Date timeEnd;
	private Date timeStart;
	private Date dateModified;
	private Date ancLatestDate;
	private Integer ancLatestNum;
	private String anc1_AbdominalExam;
	private Boolean anc1_Abnormalities;
	private String anc1_BloodPressure;
	private Date anc1_Date;
	private String anc1_Facility;
	private Boolean anc1_Details;
	private String anc2_AbdominalExam;
	private Boolean anc2_Abnormalities;
	private String anc2_BloodPressure;
	private Date anc2_Date;
	private String anc2_Facility;
	private Boolean anc2_Details;
	private String anc3_AbdominalExam;
	private Boolean anc3_Abnormalities;
	private String anc3_BloodPressure;
	private Date anc3_Date;
	private String anc3_Facility;
	private Boolean anc3_Details;
	private String anc4_AbdominalExam;
	private Boolean anc4_Abnormalities;
	private String anc4_BloodPressure;
	private Date anc4_Date;
	private String anc4_Facility;
	private Boolean anc4_Details;
	private Boolean counselIfa;
	private Boolean counselTt;
	private Boolean eatingExtra;
	private Short ifaTabletsIssued;
	private String reasonNoIfa;
	private Boolean receivedTt1;
	private Boolean receivedTt2;
	private Boolean resting;
	private Date tt1_Date;
	private Date tt2_Date;
	private Boolean ttBooster;
	private Date ttBoosterDate;
	private Boolean usingIfa;
	private Boolean sba;
	private Boolean sbaPhone;
	private Boolean accompany;
	private Boolean careOfHome;
	private Boolean cleanCloth;
	private Boolean cordCare;
	private Boolean counselHomeDelivery;
	private Boolean counselInstitutional;
	private Boolean counselPreparation;
	private Boolean dangerInstitution;
	private Boolean dangerNumber;
	private Boolean hasDangerSigns;
	private Boolean immediateBreastfeeding;
	private Boolean informDangerSigns;
	private Boolean materials;
	private Boolean maternalDangerSigns;
	private Boolean nowInstitutional;
	private Boolean phoneVehicle;
	private Boolean playBirthPreparednessVid;
	private Boolean playCordCareVid;
	private Boolean savingMoney;
	private Boolean skinToSkin;
	private Boolean vehicle;
	private Boolean wrapping;
	private Short bpVisitNum;
	private Date anc1Date;
	private Date anc2Date;
	private Date anc3Date;
	private Date anc4Date;
	private String coupleInterested;
	private Date dateBp1;
	private Date dateBp2;
	private Date dateBp3;
	private Date dateLastVisit;
	private Date dateNextBp;
	private String deliveryType;
	private Short ifaTablets;
	private Date ifaTablets100;
	private String lastVisitType;
	private Boolean maternalEmergency;
	private Boolean maternalEmergencyNumber;
	private Date tt1Date;
	private Date tt2Date;
	private Boolean conceive;
	private Date delFup;
	private Boolean availImmediate;
	private Boolean counselAccessible;
	private Boolean counselBenefits;
	private Boolean counselDisqualification;
	private Boolean counselInstitution;
	private Boolean counselMethods;
	private Boolean counselNearest;
	private Boolean counselOptions;
	private Boolean counselStay;
	private Boolean immediateAppropriate;
	private Boolean institutionImmediate;
	private Boolean postponeConception;
	private Boolean riskOfPreg;
	private Boolean spacingMethods;
	private String stopChildren;
	private Short ifaTabletsTotal;
	private String nextvisittype;
	private Boolean playFamilyPlanningVid;
	private String postponing;
    private String institutional;
    private Date creationTime = new Date();

    public BpForm() {
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
    @Cascade(value = org.hibernate.annotations.CascadeType.SAVE_UPDATE)
	public Flw getFlw() {
		return this.flw;
	}

	public void setFlw(Flw flw) {
		this.flw = flw;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "case_id")
    @Cascade(value = org.hibernate.annotations.CascadeType.SAVE_UPDATE)
	public MotherCase getMotherCase() {
		return this.motherCase;
	}

	public void setMotherCase(MotherCase motherCase) {
		this.motherCase = motherCase;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "time_end")
	public Date getTimeEnd() {
		return this.timeEnd;
	}

	public void setTimeEnd(Date timeEnd) {
		this.timeEnd = timeEnd;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "time_start")
	public Date getTimeStart() {
		return this.timeStart;
	}

	public void setTimeStart(Date timeStart) {
		this.timeStart = timeStart;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_modified")
	public Date getDateModified() {
		return this.dateModified;
	}

	public void setDateModified(Date dateModified) {
		this.dateModified = dateModified;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "anc_latest_date")
	public Date getAncLatestDate() {
		return this.ancLatestDate;
	}

	public void setAncLatestDate(Date ancLatestDate) {
		this.ancLatestDate = ancLatestDate;
	}

	@Column(name = "anc_latest_num")
	public Integer getAncLatestNum() {
		return this.ancLatestNum;
	}

	public void setAncLatestNum(Integer ancLatestNum) {
		this.ancLatestNum = ancLatestNum;
	}

	@Column(name = "anc1_abdominal_exam")
	public String getAnc1_AbdominalExam() {
		return this.anc1_AbdominalExam;
	}

	public void setAnc1_AbdominalExam(String anc1_AbdominalExam) {
		this.anc1_AbdominalExam = anc1_AbdominalExam;
	}

	@Column(name = "anc1_abnormalities")
	public Boolean getAnc1_Abnormalities() {
		return this.anc1_Abnormalities;
	}

	public void setAnc1_Abnormalities(Boolean anc1_Abnormalities) {
		this.anc1_Abnormalities = anc1_Abnormalities;
	}

	@Column(name = "anc1_blood_pressure")
	public String getAnc1_BloodPressure() {
		return this.anc1_BloodPressure;
	}

	public void setAnc1_BloodPressure(String anc1_BloodPressure) {
		this.anc1_BloodPressure = anc1_BloodPressure;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "anc1_date")
	public Date getAnc1_Date() {
		return this.anc1_Date;
	}

	public void setAnc1_Date(Date anc1_Date) {
		this.anc1_Date = anc1_Date;
	}

	@Column(name = "anc1_facility")
	public String getAnc1_Facility() {
		return this.anc1_Facility;
	}

	public void setAnc1_Facility(String anc1_Facility) {
		this.anc1_Facility = anc1_Facility;
	}

	@Column(name = "anc1_details")
	public Boolean getAnc1_Details() {
		return this.anc1_Details;
	}

	public void setAnc1_Details(Boolean anc1_Details) {
		this.anc1_Details = anc1_Details;
	}

	@Column(name = "anc2_abdominal_exam")
	public String getAnc2_AbdominalExam() {
		return this.anc2_AbdominalExam;
	}

	public void setAnc2_AbdominalExam(String anc2_AbdominalExam) {
		this.anc2_AbdominalExam = anc2_AbdominalExam;
	}

	@Column(name = "anc2_abnormalities")
	public Boolean getAnc2_Abnormalities() {
		return this.anc2_Abnormalities;
	}

	public void setAnc2_Abnormalities(Boolean anc2_Abnormalities) {
		this.anc2_Abnormalities = anc2_Abnormalities;
	}

	@Column(name = "anc2_blood_pressure")
	public String getAnc2_BloodPressure() {
		return this.anc2_BloodPressure;
	}

	public void setAnc2_BloodPressure(String anc2_BloodPressure) {
		this.anc2_BloodPressure = anc2_BloodPressure;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "anc2_date")
	public Date getAnc2_Date() {
		return this.anc2_Date;
	}

	public void setAnc2_Date(Date anc2_Date) {
		this.anc2_Date = anc2_Date;
	}

	@Column(name = "anc2_facility")
	public String getAnc2_Facility() {
		return this.anc2_Facility;
	}

	public void setAnc2_Facility(String anc2_Facility) {
		this.anc2_Facility = anc2_Facility;
	}

	@Column(name = "anc2_details")
	public Boolean getAnc2_Details() {
		return this.anc2_Details;
	}

	public void setAnc2_Details(Boolean anc2_Details) {
		this.anc2_Details = anc2_Details;
	}

	@Column(name = "anc3_abdominal_exam")
	public String getAnc3_AbdominalExam() {
		return this.anc3_AbdominalExam;
	}

	public void setAnc3_AbdominalExam(String anc3_AbdominalExam) {
		this.anc3_AbdominalExam = anc3_AbdominalExam;
	}

	@Column(name = "anc3_abnormalities")
	public Boolean getAnc3_Abnormalities() {
		return this.anc3_Abnormalities;
	}

	public void setAnc3_Abnormalities(Boolean anc3_Abnormalities) {
		this.anc3_Abnormalities = anc3_Abnormalities;
	}

	@Column(name = "anc3_blood_pressure")
	public String getAnc3_BloodPressure() {
		return this.anc3_BloodPressure;
	}

	public void setAnc3_BloodPressure(String anc3_BloodPressure) {
		this.anc3_BloodPressure = anc3_BloodPressure;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "anc3_date")
	public Date getAnc3_Date() {
		return this.anc3_Date;
	}

	public void setAnc3_Date(Date anc3_Date) {
		this.anc3_Date = anc3_Date;
	}

	@Column(name = "anc3_facility")
	public String getAnc3_Facility() {
		return this.anc3_Facility;
	}

	public void setAnc3_Facility(String anc3_Facility) {
		this.anc3_Facility = anc3_Facility;
	}

	@Column(name = "anc3_details")
	public Boolean getAnc3_Details() {
		return this.anc3_Details;
	}

	public void setAnc3_Details(Boolean anc3_Details) {
		this.anc3_Details = anc3_Details;
	}

	@Column(name = "anc4_abdominal_exam")
	public String getAnc4_AbdominalExam() {
		return this.anc4_AbdominalExam;
	}

	public void setAnc4_AbdominalExam(String anc4_AbdominalExam) {
		this.anc4_AbdominalExam = anc4_AbdominalExam;
	}

	@Column(name = "anc4_abnormalities")
	public Boolean getAnc4_Abnormalities() {
		return this.anc4_Abnormalities;
	}

	public void setAnc4_Abnormalities(Boolean anc4_Abnormalities) {
		this.anc4_Abnormalities = anc4_Abnormalities;
	}

	@Column(name = "anc4_blood_pressure")
	public String getAnc4_BloodPressure() {
		return this.anc4_BloodPressure;
	}

	public void setAnc4_BloodPressure(String anc4_BloodPressure) {
		this.anc4_BloodPressure = anc4_BloodPressure;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "anc4_date")
	public Date getAnc4_Date() {
		return this.anc4_Date;
	}

	public void setAnc4_Date(Date anc4_Date) {
		this.anc4_Date = anc4_Date;
	}

	@Column(name = "anc4_facility")
	public String getAnc4_Facility() {
		return this.anc4_Facility;
	}

	public void setAnc4_Facility(String anc4_Facility) {
		this.anc4_Facility = anc4_Facility;
	}

	@Column(name = "anc4_details")
	public Boolean getAnc4_Details() {
		return this.anc4_Details;
	}

	public void setAnc4_Details(Boolean anc4_Details) {
		this.anc4_Details = anc4_Details;
	}

	@Column(name = "counsel_ifa")
	public Boolean getCounselIfa() {
		return this.counselIfa;
	}

	public void setCounselIfa(Boolean counselIfa) {
		this.counselIfa = counselIfa;
	}

	@Column(name = "counsel_tt")
	public Boolean getCounselTt() {
		return this.counselTt;
	}

	public void setCounselTt(Boolean counselTt) {
		this.counselTt = counselTt;
	}

	@Column(name = "eating_extra")
	public Boolean getEatingExtra() {
		return this.eatingExtra;
	}

	public void setEatingExtra(Boolean eatingExtra) {
		this.eatingExtra = eatingExtra;
	}

	@Column(name = "ifa_tablets_issued")
	public Short getIfaTabletsIssued() {
		return this.ifaTabletsIssued;
	}

	public void setIfaTabletsIssued(Short ifaTabletsIssued) {
		this.ifaTabletsIssued = ifaTabletsIssued;
	}

	@Column(name = "reason_no_ifa")
	public String getReasonNoIfa() {
		return this.reasonNoIfa;
	}

	public void setReasonNoIfa(String reasonNoIfa) {
		this.reasonNoIfa = reasonNoIfa;
	}

	@Column(name = "received_tt1")
	public Boolean getReceivedTt1() {
		return this.receivedTt1;
	}

	public void setReceivedTt1(Boolean receivedTt1) {
		this.receivedTt1 = receivedTt1;
	}

	@Column(name = "received_tt2")
	public Boolean getReceivedTt2() {
		return this.receivedTt2;
	}

	public void setReceivedTt2(Boolean receivedTt2) {
		this.receivedTt2 = receivedTt2;
	}

	@Column(name = "resting")
	public Boolean getResting() {
		return this.resting;
	}

	public void setResting(Boolean resting) {
		this.resting = resting;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "tt1_date")
	public Date getTt1_Date() {
		return this.tt1_Date;
	}

	public void setTt1_Date(Date tt1_Date) {
		this.tt1_Date = tt1_Date;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "tt2_date")
	public Date getTt2_Date() {
		return this.tt2_Date;
	}

	public void setTt2_Date(Date tt2_Date) {
		this.tt2_Date = tt2_Date;
	}

	@Column(name = "tt_booster")
	public Boolean getTtBooster() {
		return this.ttBooster;
	}

	public void setTtBooster(Boolean ttBooster) {
		this.ttBooster = ttBooster;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "tt_booster_date")
	public Date getTtBoosterDate() {
		return this.ttBoosterDate;
	}

	public void setTtBoosterDate(Date ttBoosterDate) {
		this.ttBoosterDate = ttBoosterDate;
	}

	@Column(name = "using_ifa")
	public Boolean getUsingIfa() {
		return this.usingIfa;
	}

	public void setUsingIfa(Boolean usingIfa) {
		this.usingIfa = usingIfa;
	}

	@Column(name = "sba")
	public Boolean getSba() {
		return this.sba;
	}

	public void setSba(Boolean sba) {
		this.sba = sba;
	}

	@Column(name = "sba_phone")
	public Boolean getSbaPhone() {
		return this.sbaPhone;
	}

	public void setSbaPhone(Boolean sbaPhone) {
		this.sbaPhone = sbaPhone;
	}

	@Column(name = "accompany")
	public Boolean getAccompany() {
		return this.accompany;
	}

	public void setAccompany(Boolean accompany) {
		this.accompany = accompany;
	}

	@Column(name = "care_of_home")
	public Boolean getCareOfHome() {
		return this.careOfHome;
	}

	public void setCareOfHome(Boolean careOfHome) {
		this.careOfHome = careOfHome;
	}

	@Column(name = "clean_cloth")
	public Boolean getCleanCloth() {
		return this.cleanCloth;
	}

	public void setCleanCloth(Boolean cleanCloth) {
		this.cleanCloth = cleanCloth;
	}

	@Column(name = "cord_care")
	public Boolean getCordCare() {
		return this.cordCare;
	}

	public void setCordCare(Boolean cordCare) {
		this.cordCare = cordCare;
	}

	@Column(name = "counsel_home_delivery")
	public Boolean getCounselHomeDelivery() {
		return this.counselHomeDelivery;
	}

	public void setCounselHomeDelivery(Boolean counselHomeDelivery) {
		this.counselHomeDelivery = counselHomeDelivery;
	}

	@Column(name = "counsel_institutional")
	public Boolean getCounselInstitutional() {
		return this.counselInstitutional;
	}

	public void setCounselInstitutional(Boolean counselInstitutional) {
		this.counselInstitutional = counselInstitutional;
	}

	@Column(name = "counsel_preparation")
	public Boolean getCounselPreparation() {
		return this.counselPreparation;
	}

	public void setCounselPreparation(Boolean counselPreparation) {
		this.counselPreparation = counselPreparation;
	}

	@Column(name = "danger_institution")
	public Boolean getDangerInstitution() {
		return this.dangerInstitution;
	}

	public void setDangerInstitution(Boolean dangerInstitution) {
		this.dangerInstitution = dangerInstitution;
	}

	@Column(name = "danger_number")
	public Boolean getDangerNumber() {
		return this.dangerNumber;
	}

	public void setDangerNumber(Boolean dangerNumber) {
		this.dangerNumber = dangerNumber;
	}

	@Column(name = "has_danger_signs")
	public Boolean getHasDangerSigns() {
		return this.hasDangerSigns;
	}

	public void setHasDangerSigns(Boolean hasDangerSigns) {
		this.hasDangerSigns = hasDangerSigns;
	}

	@Column(name = "immediate_breastfeeding")
	public Boolean getImmediateBreastfeeding() {
		return this.immediateBreastfeeding;
	}

	public void setImmediateBreastfeeding(Boolean immediateBreastfeeding) {
		this.immediateBreastfeeding = immediateBreastfeeding;
	}

	@Column(name = "inform_danger_signs")
	public Boolean getInformDangerSigns() {
		return this.informDangerSigns;
	}

	public void setInformDangerSigns(Boolean informDangerSigns) {
		this.informDangerSigns = informDangerSigns;
	}

	@Column(name = "materials")
	public Boolean getMaterials() {
		return this.materials;
	}

	public void setMaterials(Boolean materials) {
		this.materials = materials;
	}

	@Column(name = "maternal_danger_signs")
	public Boolean getMaternalDangerSigns() {
		return this.maternalDangerSigns;
	}

	public void setMaternalDangerSigns(Boolean maternalDangerSigns) {
		this.maternalDangerSigns = maternalDangerSigns;
	}

	@Column(name = "now_institutional")
	public Boolean getNowInstitutional() {
		return this.nowInstitutional;
	}

	public void setNowInstitutional(Boolean nowInstitutional) {
		this.nowInstitutional = nowInstitutional;
	}

	@Column(name = "phone_vehicle")
	public Boolean getPhoneVehicle() {
		return this.phoneVehicle;
	}

	public void setPhoneVehicle(Boolean phoneVehicle) {
		this.phoneVehicle = phoneVehicle;
	}

	@Column(name = "play_birth_preparedness_vid")
	public Boolean getPlayBirthPreparednessVid() {
		return this.playBirthPreparednessVid;
	}

	public void setPlayBirthPreparednessVid(Boolean playBirthPreparednessVid) {
		this.playBirthPreparednessVid = playBirthPreparednessVid;
	}

	@Column(name = "play_cord_care_vid")
	public Boolean getPlayCordCareVid() {
		return this.playCordCareVid;
	}

	public void setPlayCordCareVid(Boolean playCordCareVid) {
		this.playCordCareVid = playCordCareVid;
	}

	@Column(name = "saving_money")
	public Boolean getSavingMoney() {
		return this.savingMoney;
	}

	public void setSavingMoney(Boolean savingMoney) {
		this.savingMoney = savingMoney;
	}

	@Column(name = "skin_to_skin")
	public Boolean getSkinToSkin() {
		return this.skinToSkin;
	}

	public void setSkinToSkin(Boolean skinToSkin) {
		this.skinToSkin = skinToSkin;
	}

	@Column(name = "vehicle")
	public Boolean getVehicle() {
		return this.vehicle;
	}

	public void setVehicle(Boolean vehicle) {
		this.vehicle = vehicle;
	}

	@Column(name = "wrapping")
	public Boolean getWrapping() {
		return this.wrapping;
	}

	public void setWrapping(Boolean wrapping) {
		this.wrapping = wrapping;
	}

	@Column(name = "bp_visit_num")
	public Short getBpVisitNum() {
		return this.bpVisitNum;
	}

	public void setBpVisitNum(Short bpVisitNum) {
		this.bpVisitNum = bpVisitNum;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "anc_1_date")
	public Date getAnc1Date() {
		return this.anc1Date;
	}

	public void setAnc1Date(Date anc1Date) {
		this.anc1Date = anc1Date;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "anc_2_date")
	public Date getAnc2Date() {
		return this.anc2Date;
	}

	public void setAnc2Date(Date anc2Date) {
		this.anc2Date = anc2Date;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "anc_3_date")
	public Date getAnc3Date() {
		return this.anc3Date;
	}

	public void setAnc3Date(Date anc3Date) {
		this.anc3Date = anc3Date;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "anc_4_date")
	public Date getAnc4Date() {
		return this.anc4Date;
	}

	public void setAnc4Date(Date anc4Date) {
		this.anc4Date = anc4Date;
	}

	@Column(name = "couple_interested")
	public String getCoupleInterested() {
		return this.coupleInterested;
	}

	public void setCoupleInterested(String coupleInterested) {
		this.coupleInterested = coupleInterested;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_bp_1")
	public Date getDateBp1() {
		return this.dateBp1;
	}

	public void setDateBp1(Date dateBp1) {
		this.dateBp1 = dateBp1;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_bp_2")
	public Date getDateBp2() {
		return this.dateBp2;
	}

	public void setDateBp2(Date dateBp2) {
		this.dateBp2 = dateBp2;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_bp_3")
	public Date getDateBp3() {
		return this.dateBp3;
	}

	public void setDateBp3(Date dateBp3) {
		this.dateBp3 = dateBp3;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_last_visit")
	public Date getDateLastVisit() {
		return this.dateLastVisit;
	}

	public void setDateLastVisit(Date dateLastVisit) {
		this.dateLastVisit = dateLastVisit;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_next_bp")
	public Date getDateNextBp() {
		return this.dateNextBp;
	}

	public void setDateNextBp(Date dateNextBp) {
		this.dateNextBp = dateNextBp;
	}

	@Column(name = "delivery_type")
	public String getDeliveryType() {
		return this.deliveryType;
	}

	public void setDeliveryType(String deliveryType) {
		this.deliveryType = deliveryType;
	}

	@Column(name = "ifa_tablets")
	public Short getIfaTablets() {
		return this.ifaTablets;
	}

	public void setIfaTablets(Short ifaTablets) {
		this.ifaTablets = ifaTablets;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "ifa_tablets_100")
	public Date getIfaTablets100() {
		return this.ifaTablets100;
	}

	public void setIfaTablets100(Date ifaTablets100) {
		this.ifaTablets100 = ifaTablets100;
	}

	@Column(name = "last_visit_type")
	public String getLastVisitType() {
		return this.lastVisitType;
	}

	public void setLastVisitType(String lastVisitType) {
		this.lastVisitType = lastVisitType;
	}

	@Column(name = "maternal_emergency")
	public Boolean getMaternalEmergency() {
		return this.maternalEmergency;
	}

	public void setMaternalEmergency(Boolean maternalEmergency) {
		this.maternalEmergency = maternalEmergency;
	}

	@Column(name = "maternal_emergency_number")
	public Boolean getMaternalEmergencyNumber() {
		return this.maternalEmergencyNumber;
	}

	public void setMaternalEmergencyNumber(Boolean maternalEmergencyNumber) {
		this.maternalEmergencyNumber = maternalEmergencyNumber;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "tt_1_date")
	public Date getTt1Date() {
		return this.tt1Date;
	}

	public void setTt1Date(Date tt1Date) {
		this.tt1Date = tt1Date;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "tt_2_date")
	public Date getTt2Date() {
		return this.tt2Date;
	}

	public void setTt2Date(Date tt2Date) {
		this.tt2Date = tt2Date;
	}

	@Column(name = "conceive")
	public Boolean getConceive() {
		return this.conceive;
	}

	public void setConceive(Boolean conceive) {
		this.conceive = conceive;
	}

	@Column(name = "del_fup")
	public Date getDelFup() {
		return this.delFup;
	}

	public void setDelFup(Date delFup) {
		this.delFup = delFup;
	}

	@Column(name = "avail_immediate")
	public Boolean getAvailImmediate() {
		return this.availImmediate;
	}

	public void setAvailImmediate(Boolean availImmediate) {
		this.availImmediate = availImmediate;
	}

	@Column(name = "counsel_accessible")
	public Boolean getCounselAccessible() {
		return this.counselAccessible;
	}

	public void setCounselAccessible(Boolean counselAccessible) {
		this.counselAccessible = counselAccessible;
	}

	@Column(name = "counsel_benefits")
	public Boolean getCounselBenefits() {
		return this.counselBenefits;
	}

	public void setCounselBenefits(Boolean counselBenefits) {
		this.counselBenefits = counselBenefits;
	}

	@Column(name = "counsel_disqualification")
	public Boolean getCounselDisqualification() {
		return this.counselDisqualification;
	}

	public void setCounselDisqualification(Boolean counselDisqualification) {
		this.counselDisqualification = counselDisqualification;
	}

	@Column(name = "counsel_institution")
	public Boolean getCounselInstitution() {
		return this.counselInstitution;
	}

	public void setCounselInstitution(Boolean counselInstitution) {
		this.counselInstitution = counselInstitution;
	}

	@Column(name = "counsel_methods")
	public Boolean getCounselMethods() {
		return this.counselMethods;
	}

	public void setCounselMethods(Boolean counselMethods) {
		this.counselMethods = counselMethods;
	}

	@Column(name = "counsel_nearest")
	public Boolean getCounselNearest() {
		return this.counselNearest;
	}

	public void setCounselNearest(Boolean counselNearest) {
		this.counselNearest = counselNearest;
	}

	@Column(name = "counsel_options")
	public Boolean getCounselOptions() {
		return this.counselOptions;
	}

	public void setCounselOptions(Boolean counselOptions) {
		this.counselOptions = counselOptions;
	}

	@Column(name = "counsel_stay")
	public Boolean getCounselStay() {
		return this.counselStay;
	}

	public void setCounselStay(Boolean counselStay) {
		this.counselStay = counselStay;
	}

	@Column(name = "immediate_appropriate")
	public Boolean getImmediateAppropriate() {
		return this.immediateAppropriate;
	}

	public void setImmediateAppropriate(Boolean immediateAppropriate) {
		this.immediateAppropriate = immediateAppropriate;
	}

	@Column(name = "institution_immediate")
	public Boolean getInstitutionImmediate() {
		return this.institutionImmediate;
	}

	public void setInstitutionImmediate(Boolean institutionImmediate) {
		this.institutionImmediate = institutionImmediate;
	}

	@Column(name = "postpone_conception")
	public Boolean getPostponeConception() {
		return this.postponeConception;
	}

	public void setPostponeConception(Boolean postponeConception) {
		this.postponeConception = postponeConception;
	}

	@Column(name = "risk_of_preg")
	public Boolean getRiskOfPreg() {
		return this.riskOfPreg;
	}

	public void setRiskOfPreg(Boolean riskOfPreg) {
		this.riskOfPreg = riskOfPreg;
	}

	@Column(name = "spacing_methods")
	public Boolean getSpacingMethods() {
		return this.spacingMethods;
	}

	public void setSpacingMethods(Boolean spacingMethods) {
		this.spacingMethods = spacingMethods;
	}

	@Column(name = "stop_children")
	public String getStopChildren() {
		return this.stopChildren;
	}

	public void setStopChildren(String stopChildren) {
		this.stopChildren = stopChildren;
	}

	@Column(name = "ifa_tablets_total")
	public Short getIfaTabletsTotal() {
		return this.ifaTabletsTotal;
	}

	public void setIfaTabletsTotal(Short ifaTabletsTotal) {
		this.ifaTabletsTotal = ifaTabletsTotal;
	}

	@Column(name = "nextvisittype")
	public String getNextvisittype() {
		return this.nextvisittype;
	}

	public void setNextvisittype(String nextvisittype) {
		this.nextvisittype = nextvisittype;
	}

	@Column(name = "play_family_planning_vid")
	public Boolean getPlayFamilyPlanningVid() {
		return this.playFamilyPlanningVid;
	}

	public void setPlayFamilyPlanningVid(Boolean playFamilyPlanningVid) {
		this.playFamilyPlanningVid = playFamilyPlanningVid;
	}

	@Column(name = "postponing")
	public String getPostponing() {
		return this.postponing;
	}

	public void setPostponing(String postponing) {
		this.postponing = postponing;
	}


    @Column(name = "institutional")
    public String getInstitutional() {
        return this.institutional;
    }

    public void setInstitutional(String institutional) {
        this.institutional = institutional;
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
