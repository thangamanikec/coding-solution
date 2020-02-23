package com.vanhack.energyconsumption.repository;

import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.hamcrest.CoreMatchers.is;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.vanhack.energyconsumption.domain.Counter;
import com.vanhack.energyconsumption.web.EachVillageReport;

/**
 * Test Class for CounterRepository
 * 
 * @Created By Thangamani Palanivel
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
public class CounterRepositoryTest {
	
	private static final long COUNTER_ID = Long.valueOf(98);
	
	@Autowired
    private TestEntityManager entityManager;
	
	@Autowired
    private CounterRepository repository;

    @Test
    public void testFindByCounterId() {
    	Counter counter = new Counter();
    	counter.setCounterId(COUNTER_ID);
    	
    	entityManager.persist(counter);
    	
        Optional<Counter> counterRes = repository.findByCounterId(COUNTER_ID);
        assertThat(counterRes.get().getCounterId(), is(COUNTER_ID));
    }
    
    @Test
    public void testGetConsumptionReports() {
        List<EachVillageReport> villageReports = repository.findAllCounterConsumptionReadingBetweenHours
        		(new Date(), new Date());
        assertEquals(0, villageReports.size());
    }


}
