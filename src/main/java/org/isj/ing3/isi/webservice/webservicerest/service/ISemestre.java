package org.isj.ing3.isi.webservice.webservicerest.service;

import org.isj.ing3.isi.webservice.webservicerest.exception.IsjException;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Semestre;

import java.util.List;

public interface ISemestre {
    Long saveSemestre(Semestre semestre) throws IsjException;
    List<Semestre> listSemestre();
    int deleteSemestreByCode(Long code) throws IsjException;
    Semestre searchSemestreByLibelleOrAnneeAcademique(String libelle, Long anneeAcademique) throws IsjException;
    Long updateSemestre(Semestre semestre) throws IsjException;

}
