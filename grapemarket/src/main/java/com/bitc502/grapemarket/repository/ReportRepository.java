package com.bitc502.grapemarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.bitc502.grapemarket.model.Report;

public interface ReportRepository extends JpaRepository<Report, Integer>, JpaSpecificationExecutor<Report>  {

}
