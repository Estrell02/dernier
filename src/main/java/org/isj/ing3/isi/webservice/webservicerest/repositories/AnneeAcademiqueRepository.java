package org.isj.ing3.isi.webservice.webservicerest.repositories;

import org.isj.ing3.isi.webservice.webservicerest.model.entities.AnneeAcademique;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface AnneeAcademiqueRepository extends JpaRepository<AnneeAcademique, Long> {

    /******la fonction qui retrouve l'annee academique à partir de la date******/

    @Query(value = "SELECT anne_ac FROM AnneeAcademique anne_ac WHERE :date BETWEEN anne_ac.dateDebut AND anne_ac.dateCloture",nativeQuery = true)
    public Optional<AnneeAcademique> retrouverAnneeAcademique(@Param("date") Date date);
}