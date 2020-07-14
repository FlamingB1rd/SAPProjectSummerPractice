package com.ivelinnikolov.ProjectSAPSummer.repository;

import com.ivelinnikolov.ProjectSAPSummer.models.OfferedServices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfferedServicesRepository extends JpaRepository<OfferedServices, Integer>
{
}

