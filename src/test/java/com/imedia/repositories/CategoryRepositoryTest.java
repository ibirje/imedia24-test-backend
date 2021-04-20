package com.imedia.repositories;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.HashSet;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.imedia.ImediaApplication;
import com.imedia.models.Category;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=ImediaApplication.class)
class CategoryRepositoryTest {
	
	@Autowired CategoryRepository repo;

	@Test
	void checkRepository() {
		assertNotNull(repo);
	}
	
	
	@Test
	void testFindByName() {
		Category cat = repo.findByName("food");
		assertNotNull(cat);
		assertEquals(cat.getName(), "Food"); 
	}

	
	@Test
	void testFindChildrenById() {
		HashSet<Long> children = repo.findChildrenById(10l);
		System.err.println(children.size() + " children found : " + Arrays.toString(children.toArray()) );
		assertNotNull(children);
	}

}
