package com.masai.entity;

import java.util.Date;
import java.util.Set;
import java.util.HashSet;

import org.hibernate.annotations.SQLDelete;

import jakarta.persistence.CascadeType;

//import org.hibernate.annotations.SQLDelete;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PreRemove;
import jakarta.persistence.Table;

@Entity
@SQLDelete(sql = "UPDATE User SET deleted = true WHERE user_id = ?")
@Table(name="user")
public class User {  //owning side
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId; // Primary key
    
	@Column(length = 80, nullable=false, name="user_name",unique=true)
	private String username;
	
	@Column(length = 80, nullable=false, name="user_email",unique=true)
    private String email;
	
	@Column(length = 80, nullable=false, name="user_password")
    private String password;
	
	@Column(name = "created_at")
    private Date createdAt;
	
	@Column(name = "updated_at")
    private Date updatedAt;
	
	@Column(name = "deleted",columnDefinition = "boolean default false")
    private boolean isdeleted;

	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<RecipeLike> likes;
    public User() {
		super();
		
	}
	
	@PreRemove
    public void softDelete() {
        this.isdeleted = true;
        
    }
	
//	public User(String username, String email, String password, Date createdAt, Date updatedAt,Role role, 
//			Set<Like> likes) {}

	public User(String username, String email, String password, Date createdAt, Date updatedAt) {
		super();
	
		this.username = username;
		this.email = email;
		this.password = password;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.likes = new HashSet<>();
	
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	

	
	public boolean isDeleted() {
		return isdeleted;
	}

	public void setDeleted(boolean deleted) {
		this.isdeleted = deleted;
	}

	public Set<RecipeLike> getLikes() {
        return likes;
    }

    public void setLikes(Set<RecipeLike> likes) {
        this.likes = likes;
    }

    public void addLike(RecipeLike like) {
        likes.add(like);
        like.setUser(this);
    }

    public void removeLike(RecipeLike like) {
        likes.remove(like);
        like.setUser(null);
    }
	@Override
	public String toString() {
		return "User [userId=" + userId + ", username=" + username + ", email=" + email + ", password=" + password
				+ ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", deleted=" + isdeleted + "]";
	}

	

	
	
    
}
