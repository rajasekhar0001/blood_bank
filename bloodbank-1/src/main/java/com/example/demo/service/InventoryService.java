package com.example.demo.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Inventory;
import com.example.demo.repository.InventoryRepository;

import jakarta.transaction.Transactional;

@Service
public class InventoryService {
	
	
	@Autowired
	private InventoryRepository repo;
	
	
	public List<Inventory> getInventoryDetails(){
		return repo.findAll();
	
	}
	
//	public List<Inventory> getInventoryDetailsByBloodGroup(String BloodGroup) {
//		return repo.findByBloodGroup(BloodGroup);
//	}
	public List<Inventory> findByBloodGroup(String bloodGroup) {
        return repo.findByBloodGroup(bloodGroup);
	}
	
	

    public List<Object[]> findQuantityByBloodGroup() {
    	
    	
    	
        return repo.findQuantityByBloodGroup();
    }
	
//	 public List<Object[]> findQuantityByBloodGroup() {
//	        return repo.findDistinctBloodGroupAndSumQuantityBy();
//	    }
    
    @Transactional
    public List<Inventory> checkForOldBloodSamples(List<Inventory> donors) {
        List<Inventory> deletedItems = new ArrayList<>();
        
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
        Date date = new Date();  
        String todayDate = formatter.format(date);

        for (Inventory donorlist : donors) {
            String donationDate = donorlist.getDateOfDonation();
            long days = findDifference(donationDate, todayDate);

            if (days > 90) {
                int id = donorlist.getBloodId();
                repo.deleteByBloodId(id);
                deletedItems.add(donorlist);
            }
        }

        return deletedItems;
    }

    static long findDifference(String donationDate, String todayDate) {
        long daysDifference = 0;
        
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date1 = sdf.parse(donationDate);
            Date date2 = sdf.parse(todayDate);
            long timeDifference = date2.getTime() - date1.getTime();
            daysDifference = (timeDifference / (1000 * 60 * 60 * 24)) % 365;
            System.out.println("The Blood sample is " + daysDifference + " days older.");
        } catch (Exception e) {
            System.out.print(e);
        }
        
        return daysDifference;
    }

//	public List<Inventory> checkForOldBloodSamples(List<Inventory> donors) {
//		// TODO Auto-generated method stub
//		return null;
//	}

}
    
    
    

	

