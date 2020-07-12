package com.ivelinnikolov.ProjectSAPSummer.repository;

import com.ivelinnikolov.ProjectSAPSummer.models.ServiceDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceDetailsRepository extends JpaRepository<ServiceDetails, Integer>
{
}
