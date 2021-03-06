package org.isj.ing3.isi.webservice.webservicerest.model.entities;
/**
 * importation des classes
 */

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

/**
 * cette classe crée la table module dans la base de données
 * cette classe herite de la classe Securite
 * @author traitement metier
 */

@NoArgsConstructor
@Data
@Entity
@Table(name = "module")

public class Module extends Securite implements Serializable {

    @Column(name= "code_module",unique = true)
    private String codeModule;

   /* @OneToMany(mappedBy = "module",fetch = FetchType.LAZY,cascade = {CascadeType.REFRESH,CascadeType.PERSIST,CascadeType.MERGE})
    private List<UE> ues = new ArrayList<>();*/

    @Override
    public void setSignature() {
        setSignature(String.valueOf(hashCode()));
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getCodeModule());
    }

    @Override
    public String toString() {
        return getCodeModule()+" - "+getLibelle();
    }

    public Module(String libelle, String description, String codeModule) {
        super(libelle, description);
        this.codeModule = codeModule;
    }
}
