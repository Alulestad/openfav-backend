package com.prismatech.prismaproject.repository;

import com.prismatech.prismaproject.model.Relacion;
import com.prismatech.prismaproject.model.Relacion.RelacionId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RelacionRepository extends JpaRepository<Relacion, RelacionId> {
}
