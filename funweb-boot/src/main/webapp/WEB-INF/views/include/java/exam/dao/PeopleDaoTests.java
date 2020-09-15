package exam.dao;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.SQLException;
import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import exam.domain.PeopleVo;
import lombok.extern.java.Log;

@Log
public class PeopleDaoTests {
	private PeopleDao dao;
	private PeopleVo people1, people2;
	@BeforeEach
	void init() {
		dao = PeopleDao.getInstance();
		people1 = new PeopleVo("admin", "4321", "김관리자", "admin@naver.com", LocalDateTime.now(), LocalDateTime.now());
		people2 = new PeopleVo("aaa", "1234", "김에이", "aaa@naver.com", LocalDateTime.now(), LocalDateTime.now());
	}
	
	@Test
	void testInsert() {
		dao.deleteAll();
		assertEquals(0, dao.getTotalPeople());
		
		dao.insert(people1);
		assertEquals(1, dao.getTotalPeople());
		
		dao.insert(people2);
		assertEquals(2, dao.getTotalPeople());
	}
	
	@Test
	void testDeleteById() {
		dao.deleteAll();
		assertEquals(0, dao.getTotalPeople());
		
		dao.insert(people2);
		assertEquals(1, dao.getTotalPeople());
		
		dao.deleteById(people2.getId());
		assertEquals(0, dao.getTotalPeople());
	}
	
	@Test
	void testUpdate() throws SQLException {
		dao.deleteAll();
		assertEquals(0, dao.getTotalPeople());
			
		dao.insert(people2);
		assertEquals(1, dao.getTotalPeople());
		
		people2.setName("박에이");
		dao.testUpdate(people2);
		
		PeopleVo peopleGet1 = dao.getPeopleById(people2.getId());
		assertNotNull(peopleGet1);
		assertEquals(people2.getName(), peopleGet1.getName());
	}
	
	@Test
	void tsetIsIdDuplicated() {
		dao.deleteAll();
		assertEquals(0, dao.getTotalPeople());
		assertFalse(dao.isIdDuplicated(people2.getId()));
		
		dao.insert(people2);
		assertEquals(1, dao.getTotalPeople());
		assertTrue(dao.isIdDuplicated(people2.getId()));
	}
	
	
}
