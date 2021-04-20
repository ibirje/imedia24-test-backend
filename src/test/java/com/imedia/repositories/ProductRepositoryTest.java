package com.imedia.repositories;

import static org.junit.Assert.assertNotNull;

import java.util.HashSet;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.imedia.ImediaApplication;
import com.imedia.dto.ProductDto;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ImediaApplication.class)
class ProductRepositoryTest {

	@Autowired
	ProductRepository repo;

	@Test
	void checkRepository() {
		assertNotNull(repo);
	}

	@Test
	void testfindByCategoryIdList() {
		HashSet<Long> ids = new HashSet<Long>();
		ids.add(4l);
		ids.add(1l);
		ids.add(3l);

		List<ProductDto> prods = (List<ProductDto>) repo.findByCategoryIdList(ids);

		assertNotNull(prods);
		System.err.println(prods.size() + "found : " + prods);
	}

	public boolean verifiyPalyndrome(String text) {
		if (text == null)
			return false;
		if (text.length() < 2)
			return true;

		int d = text.length() / 2;
		for (int i = 0; i < d; i++)
			if (text.charAt(i) != text.charAt(text.length() - i - 1))
				return false;
		return true;
	}

	@Test
	public void test() {
		System.err.println(verifiyPalyndrome("BOB"));
		System.err.println(verifiyPalyndrome("MAMAM"));
		System.err.println(verifiyPalyndrome("TPH"));
		System.err.println(verifiyPalyndrome("Mars"));
	}

}
