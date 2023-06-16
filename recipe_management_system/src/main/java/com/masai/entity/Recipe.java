package com.masai.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.SQLDelete;

import jakarta.persistence.CascadeType;
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
@Table(name = "Recipe")
@SQLDelete(sql = "UPDATE User SET deleted = true WHERE user_id = ?")
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recipe_id")
    private int recipeId; // Primary key

    @Column(name = "recipe_name")
    private String recipeName;

    @Column(name = "ingredients")
    private String ingredients;

    @Column(name = "preparation_steps")
    private String preparationSteps;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;

    @Column(name = "deleted",columnDefinition = "boolean default false")
    private boolean isDeleted;
    
    @OneToMany(mappedBy = "recipe", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<RecipeLike> recipeLikes;

    
	@PreRemove
    public void softDelete() {
        this.isDeleted = true;
        
    }
    
	public Recipe() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Recipe(String recipeName, String ingredients, String preparationSteps, Date createdAt, Date updatedAt) {
		super();
		this.recipeName = recipeName;
		this.ingredients = ingredients;
		this.preparationSteps = preparationSteps;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.recipeLikes = new HashSet<>();
	}

	public int getRecipeId() {
		return recipeId;
	}

	public void setRecipeId(int recipeId) {
		this.recipeId = recipeId;
	}

	public String getRecipeName() {
		return recipeName;
	}

	public void setRecipeName(String recipeName) {
		this.recipeName = recipeName;
	}

	public String getIngredients() {
		return ingredients;
	}

	public void setIngredients(String ingredients) {
		this.ingredients = ingredients;
	}

	public String getPreparationSteps() {
		return preparationSteps;
	}

	public void setPreparationSteps(String preparationSteps) {
		this.preparationSteps = preparationSteps;
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

	public Set<RecipeLike> getLikes() {
        return recipeLikes ;
    }

    public void setLikes(Set<RecipeLike> likes) {
        this.recipeLikes  = likes;
    }

    public void addLike(RecipeLike like) {
    	recipeLikes .add(like);
        like.setRecipe(this);
    }

    public void removeLike(RecipeLike like) {
    	recipeLikes .remove(like);
        like.setRecipe(null);
    }
	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean deleted) {
		this.isDeleted = deleted;
	}

	@Override
	public String toString() {
		return "Recipe [recipeId=" + recipeId + ", recipeName=" + recipeName + ", ingredients=" + ingredients
				+ ", preparationSteps=" + preparationSteps + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt
				+ "]";
	}

    
}

