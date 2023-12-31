package com.skilldistillery.pawrentsplace.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "pet_comment")
public class PetComment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String body;

	@Column(name = "created_at")
	@CreationTimestamp
	private LocalDateTime createdAt;

	@Column(name = "updated_at")
	@UpdateTimestamp
	private LocalDateTime updatedAt;

	@Column(name = "image_url")
	private String imageUrl;

	@JsonIgnoreProperties(value={"user"}, allowSetters=true)
	@ManyToOne
	@JoinColumn(name = "pet_id")
	private Pet pet;
	
	@JsonIgnoreProperties(value={"medicalNotes", "pets", "petComments", "petClients"}, allowSetters=true)
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;


	@JsonIgnoreProperties({"pet","user", "mainComment", "replies"})
	@ManyToOne
	@JoinColumn(name = "replying_to_id")
	private PetComment mainComment;


	@JsonIgnoreProperties({"pet", "replies"})
	@OneToMany(mappedBy = "mainComment")
	private List<PetComment> replies;

	public PetComment() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Pet getPet() {
		return pet;
	}

	public void setPet(Pet pet) {
		this.pet = pet;
	}
	

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public PetComment getMainComment() {
		return mainComment;
	}

	public void setMainComment(PetComment mainComment) {
		this.mainComment = mainComment;
	}

	public List<PetComment> getReplies() {
		return replies;
	}

	public void setReplies(List<PetComment> replies) {
		this.replies = replies;
	}
	
	public void addReply(PetComment reply) {
		if (replies == null) {
			replies = new ArrayList<>();
		}
		if (!replies.contains(reply)) {
			replies.add(reply);
			reply.setMainComment(this);
		}
	}
	
	public void removeReply(PetComment reply) {
		if (replies != null && replies.contains(reply)) {
			replies.remove(reply);
			reply.setMainComment(null);
		}
	}

	@Override
	public String toString() {
		return "PetComment [id=" + id + ", body=" + body + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt
				+ ", imageUrl=" + imageUrl + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PetComment other = (PetComment) obj;
		return id == other.id;
	}

}
