package com.pharmacy.inventory.repository;

import com.pharmacy.inventory.entity.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MedicineRepository extends JpaRepository<Medicine, Long>{
    List<Medicine> findByNameContainingIgnoreCaseOrCategoryContainingIgnoreCase(String name, String category);
}
