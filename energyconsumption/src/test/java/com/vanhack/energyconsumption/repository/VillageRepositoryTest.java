package com.vanhack.energyconsumption.repository;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.vanhack.energyconsumption.domain.Village;

/**
 * Test Class for VillageRepository
 * 
 * @Created By Thangamani Palanivel
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
public class VillageRepositoryTest {
	
	private static final long VILLAGE_ID = Long.valueOf(98);
	
	@Autowired
    private TestEntityManager entityManager;
	
	@Autowired
    private VillageRepository repository;

    @Test
    public void testFindByVillageId() {
    	Village village = new Village();
    	village.setVillageId(VILLAGE_ID);
    	village.setCounterId(VILLAGE_ID);
    	village.setVillageName("Berlin");
    	
    	entityManager.persist(village);
    	
        Optional<Village> counterRes = repository.findByCounterId(VILLAGE_ID);
        assertThat(counterRes.get().getVillageId(), is(VILLAGE_ID));
    }
}
