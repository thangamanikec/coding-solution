package com.vanhack.energyconsumption.service;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.vanhack.energyconsumption.domain.Counter;
import com.vanhack.energyconsumption.domain.Village;
import com.vanhack.energyconsumption.repository.CounterRepository;
import com.vanhack.energyconsumption.repository.VillageRepository;

/**
 * Junit Test Class for ConsumptionReportService
 * 
 * @Created By Thangamani Palanivel
 */
@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
public class ConsumptionReportServiceUnitTest {
	
	@Mock
	private CounterRepository counterRepositoryMock;
	
	@Mock
	private VillageRepository villageRepositoryMock;
	
	@InjectMocks
	private ConsumptionReportService consumptionReportService;	
	
	@Mock
	private Counter counterMock;
	
	@Mock 
	private Village villageMock;
	
    private static final Long COUNTER_ID = Long.valueOf(100);
    private static final Long VILLAGE_ID = Long.valueOf(90);
	private static final Double CONSUMPTION = Double.valueOf(567.54);
	private static final String VILLAGE_NAME = "Test";
	
	@Before
    public void setup() {
		
	}
	
	/**
	 * Add Counter
	 */
	@Test
	public void testAddCounter() {
		
		Counter aMockCounter = new Counter();
		aMockCounter.setCounterId(COUNTER_ID);
		aMockCounter.setConsumption(CONSUMPTION);
		
		assertEquals(CONSUMPTION, aMockCounter.getConsumption());
	}
	
	/**
	 * LookUp Counter
	 */
	@Test
    public void lookUpAllCounter() {
		when(counterRepositoryMock.findAll()).thenReturn(Arrays.asList(counterMock));

        assertThat(consumptionReportService.lookUpAllCounter(), is(Arrays.asList(counterMock)));
    }
	
    /**
	 * Add Village
	 */
	@Test
	public void testAddVillage() {
		
		Village aMockVillage= new Village();
		aMockVillage.setVillageId(VILLAGE_ID);
		aMockVillage.setVillageName(VILLAGE_NAME);
		aMockVillage.setCounterId(COUNTER_ID);
		
		assertEquals(VILLAGE_NAME, aMockVillage.getVillageName());
	}
	
	/**
	 * LookUp Counter
	 */
	@Test
    public void lookUpAllVillage() {
		when(villageRepositoryMock.findAll()).thenReturn(Arrays.asList((villageMock)));

        assertThat(consumptionReportService.lookUpAllCounterFromVillage(), is(Arrays.asList(villageMock)));
    }
	
}
