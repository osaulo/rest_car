package br.com.microservice.domain;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;
import org.hibernate.envers.Audited;
import org.joda.time.DateTime;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * <p> Base abstract class for entities which will hold definitions for created,
 * last modified by and created, last modified by date.</p>
 * 
 * These can be described by using annotations @CreatedBy, @LastModifiedBy, @CreatedDate, 
 * and @LastModifiedDate. In this case I used joda time to define the type of date. The reference 
 * documentation also mentions adding AuditingEntityListener to the orm.xml file, however, 
 * if you wish to not include a xml file, you can add 
 * @EntityListeners(AuditingEntityListener.class) annotation to the abstract entity class
 * 
 * For created by and last modified by to be filled in with spring security principal 
 * information you use the AuitorAware<T> interface {@link br.com.mv.security.SpringSecurityAuditorAware}
 * 
 * Finally, add the annotation @EnableJpaAuditing to have the configuration be loaded 
 * {@link br.com.mv.config.DatabaseConfiguration}. Also be sure to add jadira.usertype.databaseZone 
 * to the hibernation configuration (in this case) to set the timezone for joda time.
 * 
 */
@MappedSuperclass
@Audited
@EntityListeners(AuditingEntityListener.class)
public class AbstractAuditingEntity {

	@CreatedBy
	@NotNull
	@Column(name = "created_by", nullable = false, length = 50, updatable = false)
	@JsonIgnore
	private String createdBy;

	@CreatedDate
	@NotNull
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	@Column(name = "created_date", nullable = false)
	@JsonIgnore
	private DateTime createdDate = DateTime.now();

	@LastModifiedBy
	@Column(name = "last_modified_by", length = 50)
	@JsonIgnore
	private String lastModifiedBy;

	@LastModifiedDate
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	@Column(name = "last_modified_date")
	@JsonIgnore
	private DateTime lastModifiedDate = DateTime.now();
	
	@Column(nullable = false)
	private boolean deleted = false;

	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public DateTime getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(DateTime createdDate) {
		this.createdDate = createdDate;
	}
	public String getLastModifiedBy() {
		return lastModifiedBy;
	}
	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}
	public DateTime getLastModifiedDate() {
		return lastModifiedDate;
	}
	public void setLastModifiedDate(DateTime lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}
	public boolean isDeleted() {
		return deleted;
	}
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
}
