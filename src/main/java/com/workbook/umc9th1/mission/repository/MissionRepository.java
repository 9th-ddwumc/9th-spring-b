package com.workbook.umc9th1.mission.repository;

import com.workbook.umc9th1.mission.domain.Mission;
import com.workbook.umc9th1.store.domain.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MissionRepository extends JpaRepository<Mission, Long> {

    Page<Mission> findAllByStore(Store store, Pageable pageable);

}
