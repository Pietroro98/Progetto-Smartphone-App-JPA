package it.gestionesmartphoneapp.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "app")
public class App
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "marca")
    private String marca;

    @Column(name = "modello")
    private String modello;

    @Column(name = "prezzo")
    private Float prezzo;

    @Column(name = "versioneOS")
    private Float versioneOS;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "apps")
    private Set<Smartphone> smartphones = new HashSet<Smartphone>();

    public App() {}
    public App(String marca, String modello, Float prezzo, Float versioneOS) {
        this.marca = marca;
        this.modello = modello;
        this.prezzo = prezzo;
        this.versioneOS = versioneOS;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModello() {
        return modello;
    }

    public void setModello(String modello) {
        this.modello = modello;
    }

    public Float getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(Float prezzo) {
        this.prezzo = prezzo;
    }

    public Float getVersioneOS() {
        return versioneOS;
    }

    public void setVersioneOS(Float versioneOS) {
        this.versioneOS = versioneOS;
    }

    public Set<Smartphone> getSmartphones() {
        return smartphones;
    }

    public void setSmartphones(Set<Smartphone> smartphones) {
        this.smartphones = smartphones;
    }

    @Override
    public String toString() {
        return "App{" +
                "id=" + id +
                ", marca='" + marca + '\'' +
                ", modello='" + modello + '\'' +
                ", prezzo=" + prezzo +
                ", versioneOS=" + versioneOS +
                '}';
    }
}
