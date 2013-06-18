package org.motechproject.care.reporting.domain.measure;

// Generated Jun 4, 2013 4:50:32 PM by Hibernate Tools 3.4.0.CR1

import org.hibernate.annotations.Cascade;
import org.motechproject.care.reporting.domain.dimension.Flw;
import org.motechproject.care.reporting.domain.dimension.MotherCase;
import org.motechproject.care.reporting.utils.FormToString;

import javax.persistence.*;
import java.util.Date;

/**
 * UiMotherForm generated by hbm2java
 */
@Entity
@Table(name = "ui_mother_form", uniqueConstraints = @UniqueConstraint(columnNames = "instance_id"))
public class UiMotherForm extends Form {

	private int id;
	private Flw flw;
	private MotherCase motherCase;
	private Date timeEnd;
	private Date timeStart;
	private Date dateModified;
	private Boolean detailsAvailable;
	private Date tt1Date;
	private Date tt2Date;
	private Date ttBoosterDate;
	private Boolean receivedTt1;
	private Boolean receivedTt2;
	private String upToDate;
	private Short numChildren;
	private Boolean updateMother;
	private Date ttBooster;
    private Date creationTime = new Date();

    public UiMotherForm() {
	}

	public UiMotherForm(int id) {
		this.id = id;
	}

	public UiMotherForm(int id, Flw flw, MotherCase motherCase,
                        String instanceId, Date timeEnd, Date timeStart, Date dateModified,
                        Boolean detailsAvailable, Date tt1Date, Date tt2Date,
                        Date ttBoosterDate, Boolean receivedTt1, Boolean receivedTt2,
                        String upToDate, Short numChildren, Boolean updateMother,
                        Date ttBooster, Date creationTime) {
        super(instanceId);
        this.id = id;
		this.flw = flw;
		this.motherCase = motherCase;
		this.timeEnd = timeEnd;
		this.timeStart = timeStart;
		this.dateModified = dateModified;
		this.detailsAvailable = detailsAvailable;
		this.tt1Date = tt1Date;
		this.tt2Date = tt2Date;
		this.ttBoosterDate = ttBoosterDate;
		this.receivedTt1 = receivedTt1;
		this.receivedTt2 = receivedTt2;
		this.upToDate = upToDate;
		this.numChildren = numChildren;
		this.updateMother = updateMother;
		this.ttBooster = ttBooster;
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

	@Column(name = "details_available")
	public Boolean getDetailsAvailable() {
		return this.detailsAvailable;
	}

	public void setDetailsAvailable(Boolean detailsAvailable) {
		this.detailsAvailable = detailsAvailable;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "tt_1_date", length = 13)
	public Date getTt1Date() {
		return this.tt1Date;
	}

	public void setTt1Date(Date tt1Date) {
		this.tt1Date = tt1Date;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "tt_2_date", length = 13)
	public Date getTt2Date() {
		return this.tt2Date;
	}

	public void setTt2Date(Date tt2Date) {
		this.tt2Date = tt2Date;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "tt_booster_date", length = 13)
	public Date getTtBoosterDate() {
		return this.ttBoosterDate;
	}

	public void setTtBoosterDate(Date ttBoosterDate) {
		this.ttBoosterDate = ttBoosterDate;
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

	@Column(name = "up_to_date", length = 15)
	public String getUpToDate() {
		return this.upToDate;
	}

	public void setUpToDate(String upToDate) {
		this.upToDate = upToDate;
	}

	@Column(name = "num_children")
	public Short getNumChildren() {
		return this.numChildren;
	}

	public void setNumChildren(Short numChildren) {
		this.numChildren = numChildren;
	}

	@Column(name = "update_mother")
	public Boolean getUpdateMother() {
		return this.updateMother;
	}

	public void setUpdateMother(Boolean updateMother) {
		this.updateMother = updateMother;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "tt_booster", length = 13)
	public Date getTtBooster() {
		return this.ttBooster;
	}

	public void setTtBooster(Date ttBooster) {
		this.ttBooster = ttBooster;
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
