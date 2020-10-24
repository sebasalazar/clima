package cl.sebastian.utem.climate.persistence.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "countries")
public class Country extends PkEntityBase {

    @Column(name = "name_es", nullable = false)
    private String nameEs = null;

    @Column(name = "name_en", nullable = false)
    private String nameEn = null;

    @Column(name = "name_fr", nullable = false)
    private String nameFr = null;

    @Column(name = "iso2", nullable = false)
    private String iso2 = null;

    @Column(name = "iso3", nullable = false)
    private String iso3 = null;

    @Column(name = "phone_code", nullable = false)
    private String phoneCode = null;

    public String getNameEs() {
        return nameEs;
    }

    public void setNameEs(String nameEs) {
        this.nameEs = nameEs;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getNameFr() {
        return nameFr;
    }

    public void setNameFr(String nameFr) {
        this.nameFr = nameFr;
    }

    public String getIso2() {
        return iso2;
    }

    public void setIso2(String iso2) {
        this.iso2 = iso2;
    }

    public String getIso3() {
        return iso3;
    }

    public void setIso3(String iso3) {
        this.iso3 = iso3;
    }

    public String getPhoneCode() {
        return phoneCode;
    }

    public void setPhoneCode(String phoneCode) {
        this.phoneCode = phoneCode;
    }
}
