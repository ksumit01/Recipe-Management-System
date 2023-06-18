package com.masai.service;

import com.masai.exception.NoRecordFoundException;
import com.masai.exception.SomeThingWentWrongException;

public interface RecipeLikeSer {
	int getLikesCountByRecipeId(int recipeId) throws SomeThingWentWrongException,NoRecordFoundException;
}
