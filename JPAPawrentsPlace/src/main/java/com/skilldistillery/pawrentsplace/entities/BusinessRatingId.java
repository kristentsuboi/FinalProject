package com.skilldistillery.pawrentsplace.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Embeddable
public class BusinessRatingId implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonIgnore
	@Column(name = "user_id")
	private int userId;

	@JsonIgnore
	@Column(name = "business_id")
	private int businessId;

	public BusinessRatingId() {
		super();
	}

	public BusinessRatingId(int userId, int businessId) {
		super();
		this.userId = userId;
		this.businessId = businessId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getBusinessId() {
		return businessId;
	}

	public void setBusinessId(int businessId) {
		this.businessId = businessId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "BusinessRatingId [userId=" + userId + ", businessId=" + businessId + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(businessId, userId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BusinessRatingId other = (BusinessRatingId) obj;
		return businessId == other.businessId && userId == other.userId;
	}
	
	
	
}
