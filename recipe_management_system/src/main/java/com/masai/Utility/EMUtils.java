package com.masai.Utility;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EMUtils {
	static EntityManagerFactory emf;

	static {
		emf = Persistence.createEntityManagerFactory("B26_SB101_project");
	}

	public static EntityManager getEntityManager() {
		return emf.createEntityManager();
	}

}
