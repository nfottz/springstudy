package com.gdu.staff;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.gdu.staff.domain.StaffDTO;
import com.gdu.staff.service.StaffService;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
public class StaffUnitTest {

	@Autowired
	private StaffService staffService;
	
	@Before
	@Test
	public void 삽입테스트() {
		
		StaffDTO staffDTO = new StaffDTO();
		staffDTO.setSno("99999");
		staffDTO.setName("김기획");
		staffDTO.setDept("기획부");
		assertEquals(1, staffService.addStaff(staffDTO));
		
	}
	
	@Test
	public void 조회테스트() {
		
		assertNotNull(staffService.searchStaff(11111));
		
	}
	
}
