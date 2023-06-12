package com.relations.all.repository;

import com.relations.all.dto.EmployeeDTO;
import com.relations.all.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EmployeeCustomRepo {
    public Page<Employee> dynamicFiltersWholeEntityOptimized(Pageable pageable, String companyId, String name, Integer age, String sex, String city);

    Page<EmployeeDTO> dynamicFiltersDTO(Pageable pageable, String companyId, String name, Integer age, String sex, String city);
}
