package com.masai.entity;

import java.util.Date;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "recipe_like")
public class RecipeLike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "like_id")
    private int likeId; // Primary key

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user; // Foreign key

    @ManyToOne
    @JoinColumn(name = "recipe_id")
    private Recipe recipe; // Foreign key

    @Column(name = "created_at")
    private Date createdAt;

	public RecipeLike() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RecipeLike(User user, Recipe recipe, Date createdAt) {
		super();
		this.user = user;
		this.recipe = recipe;
		this.createdAt = createdAt;
	}

	public int getLikeId() {
		return likeId;
	}


	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Recipe getRecipe() {
		return recipe;
	}

	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	@Override
	public String toString() {
		return "Like [likeId=" + likeId + ", user=" + user + ", recipe=" + recipe + ", createdAt=" + createdAt + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(createdAt, likeId, recipe, user);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RecipeLike other = (RecipeLike) obj;
		return Objects.equals(createdAt, other.createdAt) && likeId == other.likeId
				&& Objects.equals(recipe, other.recipe) && Objects.equals(user, other.user);
	}

    
}

