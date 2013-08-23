package org.motechproject.care.reporting.domain.measure;

import org.hibernate.annotations.Cascade;
import org.motechproject.care.reporting.domain.dimension.ChildCase;
import org.motechproject.care.reporting.domain.dimension.Flw;
import org.motechproject.care.reporting.utils.FormToString;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "pnc_child_form", uniqueConstraints = @UniqueConstraint(columnNames = {"instance_id","case_id"}))
public class PncChildForm extends Form {

	private int id;
	private Flw flw;
	private ChildCase childCase;
	private Date timeEnd;
	private Date timeStart;
	private Date dateModified;
	private Boolean ableExpressedMilk;
	private Boolean adequateSupport;
	private Boolean appliedToStump;
	private Boolean babyActive;
	private Boolean breastfeedingWell;
	private Boolean childAlive;
	private Boolean childDiedVillage;
	private String childPlaceDeath;
	private String childSiteDeath;
	private Date chldDateDeath;
	private Boolean cordFallen;
	private Boolean correctPosition;
	private Boolean counselCordCare;
	private Boolean counselExclusiveBf;
	private Boolean counselExpressMilk;
	private Boolean counselSkin;
	private Boolean couselBfCorrect;
	private Boolean demonstrateExpressed;
	private Boolean demonstrateSkin;
	private Boolean easyAwake;
	private Boolean feedVigour;
	private Boolean goodLatch;
	private Boolean improvementsBf;
	private Boolean observedBf;
	private Boolean otherMilkToChild;
	private Boolean secondObservation;
	private Boolean skinToSkin;
	private Boolean warmToTouch;
	private String whatApplied;
	private Boolean wrapped;
    private Boolean close;
    private Date creationTime = new Date();

    public PncChildForm() {
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
	public ChildCase getChildCase() {
		return this.childCase;
	}

	public void setChildCase(ChildCase childCase) {
		this.childCase = childCase;
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

	@Column(name = "able_expressed_milk")
	public Boolean getAbleExpressedMilk() {
		return this.ableExpressedMilk;
	}

	public void setAbleExpressedMilk(Boolean ableExpressedMilk) {
		this.ableExpressedMilk = ableExpressedMilk;
	}

	@Column(name = "adequate_support")
	public Boolean getAdequateSupport() {
		return this.adequateSupport;
	}

	public void setAdequateSupport(Boolean adequateSupport) {
		this.adequateSupport = adequateSupport;
	}

	@Column(name = "applied_to_stump")
	public Boolean getAppliedToStump() {
		return this.appliedToStump;
	}

	public void setAppliedToStump(Boolean appliedToStump) {
		this.appliedToStump = appliedToStump;
	}

	@Column(name = "baby_active")
	public Boolean getBabyActive() {
		return this.babyActive;
	}

	public void setBabyActive(Boolean babyActive) {
		this.babyActive = babyActive;
	}

	@Column(name = "breastfeeding_well")
	public Boolean getBreastfeedingWell() {
		return this.breastfeedingWell;
	}

	public void setBreastfeedingWell(Boolean breastfeedingWell) {
		this.breastfeedingWell = breastfeedingWell;
	}

	@Column(name = "child_alive")
	public Boolean getChildAlive() {
		return this.childAlive;
	}

	public void setChildAlive(Boolean childAlive) {
		this.childAlive = childAlive;
	}

	@Column(name = "child_died_village")
	public Boolean getChildDiedVillage() {
		return this.childDiedVillage;
	}

	public void setChildDiedVillage(Boolean childDiedVillage) {
		this.childDiedVillage = childDiedVillage;
	}

	@Column(name = "child_place_death")
	public String getChildPlaceDeath() {
		return this.childPlaceDeath;
	}

	public void setChildPlaceDeath(String childPlaceDeath) {
		this.childPlaceDeath = childPlaceDeath;
	}

	@Column(name = "child_site_death")
	public String getChildSiteDeath() {
		return this.childSiteDeath;
	}

	public void setChildSiteDeath(String childSiteDeath) {
		this.childSiteDeath = childSiteDeath;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "chld_date_death")
	public Date getChldDateDeath() {
		return this.chldDateDeath;
	}

	public void setChldDateDeath(Date chldDateDeath) {
		this.chldDateDeath = chldDateDeath;
	}

	@Column(name = "cord_fallen")
	public Boolean getCordFallen() {
		return this.cordFallen;
	}

	public void setCordFallen(Boolean cordFallen) {
		this.cordFallen = cordFallen;
	}

	@Column(name = "correct_position")
	public Boolean getCorrectPosition() {
		return this.correctPosition;
	}

	public void setCorrectPosition(Boolean correctPosition) {
		this.correctPosition = correctPosition;
	}

	@Column(name = "counsel_cord_care")
	public Boolean getCounselCordCare() {
		return this.counselCordCare;
	}

	public void setCounselCordCare(Boolean counselCordCare) {
		this.counselCordCare = counselCordCare;
	}

	@Column(name = "counsel_exclusive_bf")
	public Boolean getCounselExclusiveBf() {
		return this.counselExclusiveBf;
	}

	public void setCounselExclusiveBf(Boolean counselExclusiveBf) {
		this.counselExclusiveBf = counselExclusiveBf;
	}

	@Column(name = "counsel_express_milk")
	public Boolean getCounselExpressMilk() {
		return this.counselExpressMilk;
	}

	public void setCounselExpressMilk(Boolean counselExpressMilk) {
		this.counselExpressMilk = counselExpressMilk;
	}

	@Column(name = "counsel_skin")
	public Boolean getCounselSkin() {
		return this.counselSkin;
	}

	public void setCounselSkin(Boolean counselSkin) {
		this.counselSkin = counselSkin;
	}

	@Column(name = "cousel_bf_correct")
	public Boolean getCouselBfCorrect() {
		return this.couselBfCorrect;
	}

	public void setCouselBfCorrect(Boolean couselBfCorrect) {
		this.couselBfCorrect = couselBfCorrect;
	}

	@Column(name = "demonstrate_expressed")
	public Boolean getDemonstrateExpressed() {
		return this.demonstrateExpressed;
	}

	public void setDemonstrateExpressed(Boolean demonstrateExpressed) {
		this.demonstrateExpressed = demonstrateExpressed;
	}

	@Column(name = "demonstrate_skin")
	public Boolean getDemonstrateSkin() {
		return this.demonstrateSkin;
	}

	public void setDemonstrateSkin(Boolean demonstrateSkin) {
		this.demonstrateSkin = demonstrateSkin;
	}

	@Column(name = "easy_awake")
	public Boolean getEasyAwake() {
		return this.easyAwake;
	}

	public void setEasyAwake(Boolean easyAwake) {
		this.easyAwake = easyAwake;
	}

	@Column(name = "feed_vigour")
	public Boolean getFeedVigour() {
		return this.feedVigour;
	}

	public void setFeedVigour(Boolean feedVigour) {
		this.feedVigour = feedVigour;
	}

	@Column(name = "good_latch")
	public Boolean getGoodLatch() {
		return this.goodLatch;
	}

	public void setGoodLatch(Boolean goodLatch) {
		this.goodLatch = goodLatch;
	}

	@Column(name = "improvements_bf")
	public Boolean getImprovementsBf() {
		return this.improvementsBf;
	}

	public void setImprovementsBf(Boolean improvementsBf) {
		this.improvementsBf = improvementsBf;
	}

	@Column(name = "observed_bf")
	public Boolean getObservedBf() {
		return this.observedBf;
	}

	public void setObservedBf(Boolean observedBf) {
		this.observedBf = observedBf;
	}

	@Column(name = "other_milk_to_child")
	public Boolean getOtherMilkToChild() {
		return this.otherMilkToChild;
	}

	public void setOtherMilkToChild(Boolean otherMilkToChild) {
		this.otherMilkToChild = otherMilkToChild;
	}

	@Column(name = "second_observation")
	public Boolean getSecondObservation() {
		return this.secondObservation;
	}

	public void setSecondObservation(Boolean secondObservation) {
		this.secondObservation = secondObservation;
	}

	@Column(name = "skin_to_skin")
	public Boolean getSkinToSkin() {
		return this.skinToSkin;
	}

	public void setSkinToSkin(Boolean skinToSkin) {
		this.skinToSkin = skinToSkin;
	}

	@Column(name = "warm_to_touch")
	public Boolean getWarmToTouch() {
		return this.warmToTouch;
	}

	public void setWarmToTouch(Boolean warmToTouch) {
		this.warmToTouch = warmToTouch;
	}

	@Column(name = "what_applied")
	public String getWhatApplied() {
		return this.whatApplied;
	}

	public void setWhatApplied(String whatApplied) {
		this.whatApplied = whatApplied;
	}

	@Column(name = "wrapped")
	public Boolean getWrapped() {
		return this.wrapped;
	}

	public void setWrapped(Boolean wrapped) {
		this.wrapped = wrapped;
	}

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "creation_time")
    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    @Column(name = "close")
    public Boolean getClose() {
        return close;
    }

    public void setClose(Boolean close) {
        this.close = close;
    }

    @Override
    public String toString() {
        return FormToString.toString(this);
    }
}
