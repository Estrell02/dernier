package org.isj.ing3.isi.webservice.webservicerest.serviceImpl;

import org.isj.ing3.isi.webservice.webservicerest.exception.ErrorInfo;
import org.isj.ing3.isi.webservice.webservicerest.exception.IsjException;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Classe;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Niveau;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Specialite;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Utilisateur;
import org.isj.ing3.isi.webservice.webservicerest.repositories.ClasseRepository;
import org.isj.ing3.isi.webservice.webservicerest.repositories.NiveauRepository;
import org.isj.ing3.isi.webservice.webservicerest.repositories.SpecialiteRepository;
import org.isj.ing3.isi.webservice.webservicerest.repositories.UtilisateurRepository;
import org.isj.ing3.isi.webservice.webservicerest.service.IClasse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zalando.problem.Status;

import java.util.List;

@Service
@Transactional
public class ClasseServiceImpl implements IClasse {
    @Autowired
    ClasseRepository classeRepository;
    @Autowired
    SpecialiteRepository specialiteRepository;
    @Autowired
    NiveauRepository niveauRepository;
    @Autowired
    UtilisateurRepository utilisateurRepository;

    @Override
    public int saveClasse(Classe classe) throws IsjException {
        Utilisateur createur = utilisateurRepository.findById(classe.getCreateur().getCode()).orElseThrow(() -> new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND));
        Utilisateur modificateur = createur;
        Specialite specialite = specialiteRepository.findById(classe.getSpecialite().getCode()).orElseThrow(() -> new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND));
        Niveau niveau = niveauRepository.findById(classe.getNiveau().getCode()).orElseThrow(() -> new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND));

        classe.setCreateur(createur);
        classe.setModificateur(modificateur);
        classe.setNiveau(niveau);
        classe.setSpecialite(specialite);
        Classe classeSave = classeRepository.save(classe);
        if (classeSave == null) {
            throw new IsjException("Un probl??me est survenu lors de l'enregistrement', veuillez r??essayer plus tard", Status.INTERNAL_SERVER_ERROR);
        }
        return classeSave.getCode().intValue();
    }

    @Override
    public List<Classe> listClasses() {
        return classeRepository.findAll();
    }

    @Override
    public int deleteClass(Long code) throws IsjException {
        classeRepository.deleteById(classeRepository.findById(code).get().getCode());

        return 1;
    }

    @Override
    public Classe getClasseByCode(Long code) throws IsjException {
        return classeRepository.findById(code).orElseThrow(() -> new IsjException("Classe introuvable ", Status.NOT_FOUND));
    }

    @Override
    public Classe searchClasseBylibClasse(String libClasse) throws IsjException {
        return classeRepository.retrouverClasse(libClasse);
    }

    @Override
    public int updateClasse(Classe classe) throws IsjException {
        Utilisateur createur = utilisateurRepository.findById(classe.getCreateur().getCode()).orElseThrow(() -> new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND));
        Utilisateur modificateur = utilisateurRepository.findById(classe.getCreateur().getCode()).orElseThrow(() -> new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND));
        Specialite specialite = specialiteRepository.findById(classe.getSpecialite().getCode()).orElseThrow(() -> new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND));
        Niveau niveau = niveauRepository.findById(classe.getNiveau().getCode()).orElseThrow(() -> new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND));

        classe.setCreateur(createur);
        classe.setModificateur(modificateur);
        classe.setNiveau(niveau);
        classe.setSpecialite(specialite);
        Classe classeUpdate = classeRepository.save(classe);
        if (classeUpdate == null) {
            throw new IsjException("Un probl??me est survenu lors de la modification, veuillez r??essayer plus tard", Status.INTERNAL_SERVER_ERROR);
        }
        return classeUpdate.getCode().intValue();
    }
}
