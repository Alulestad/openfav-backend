package com.prismatech.prismaproject.repository;

import com.prismatech.prismaproject.model.Kpi;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KpiRepository extends JpaRepository<Kpi, Integer> {
}
