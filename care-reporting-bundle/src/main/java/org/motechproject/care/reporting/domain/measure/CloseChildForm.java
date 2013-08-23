package org.motechproject.care.reporting.domain.measure;

import org.hibernate.annotations.Cascade;
import org.motechproject.care.reporting.domain.dimension.ChildCase;
import org.motechproject.care.reporting.domain.dimension.Flw;
import org.motechproject.care.reporting.utils.FormToString;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "close_child_form", uniqueConstraints = @UniqueConstraint(columnNames = {"instance_id","case_id"}))
public class CloseChildForm extends Form {

	private int id;
	private Flw flw;
	private ChildCase childCase;
	private Date timeEnd;
	private Date timeStart;
	private Date dateModified;
	private Boolean childAlive;
    private Boolean closeChild;
    private Boolean confirmClose;
    private Date dateDeath;
    private Boolean died;
    private Boolean diedVillage;
    private Boolean dupeReg;
    private Boolean finishedContinuum;
    private String siteDeath;
    private String placeDeath;
    private Date creationTime = new Date();
    private Boolean close;


    public CloseChildForm() {
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

    @Column(name = "close")
    public Boolean getClose() {
        return close;
    }

    public void setClose(Boolean close) {
        this.close = close;
    }


    @Column(name = "child_alive")
	public Boolean getChildAlive() {
		return this.childAlive;
	}

	public void setChildAlive(Boolean childAlive) {
		this.childAlive = childAlive;
	}

	@Column(name = "close_child")
	public Boolean getCloseChild() {
		return this.closeChild;
	}

	public void setCloseChild(Boolean closeChild) {
		this.closeChild = closeChild;
	}

	@Column(name = "confirm_close")
	public Boolean getConfirmClose() {
		return this.confirmClose;
	}

	public void setConfirmClose(Boolean confirmClose) {
		this.confirmClose = confirmClose;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_death")
	public Date getDateDeath() {
		return this.dateDeath;
	}

	public void setDateDeath(Date dateDeath) {
		this.dateDeath = dateDeath;
	}

	@Column(name = "died")
	public Boolean getDied() {
		return this.died;
	}

	public void setDied(Boolean died) {
		this.died = died;
	}

	@Column(name = "died_village")
	public Boolean getDiedVillage() {
		return this.diedVillage;
	}

	public void setDiedVillage(Boolean diedVillage) {
		this.diedVillage = diedVillage;
	}

	@Column(name = "dupe_reg")
	public Boolean getDupeReg() {
		return this.dupeReg;
	}

	public void setDupeReg(Boolean dupeReg) {
		this.dupeReg = dupeReg;
	}

	@Column(name = "finished_continuum")
	public Boolean getFinishedContinuum() {
		return this.finishedContinuum;
	}

	public void setFinishedContinuum(Boolean finishedContinuum) {
		this.finishedContinuum = finishedContinuum;
	}

	@Column(name = "site_death")
	public String getSiteDeath() {
		return this.siteDeath;
	}

	public void setSiteDeath(String siteDeath) {
		this.siteDeath = siteDeath;
	}

	@Column(name = "place_death")
	public String getPlaceDeath() {
		return this.placeDeath;
	}

	public void setPlaceDeath(String placeDeath) {
		this.placeDeath = placeDeath;
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
