package it.gestionesmartphoneapp.model;

import javax.persistence.*;
import java.time.LocalDate;
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

    @Column(name = "nome")
    private String nome;

    @Column(name = "dataInstallazione")
    private LocalDate dataInstallazione;

    @Column(name = "dataUltimoAggiornamento")
    private LocalDate dataUltimoAggiornamento;

    @Column(name = "versione")
    private Float versione;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "apps")
    private Set<Smartphone> smartphones = new HashSet<Smartphone>();

    public App() {}

    public App(String nome, LocalDate dataInstallazione, LocalDate dataUltimoAggiornamento, Float versione) {
        this.nome = nome;
        this.dataInstallazione = dataInstallazione;
        this.dataUltimoAggiornamento = dataUltimoAggiornamento;
        this.versione = versione;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataInstallazione() {
        return dataInstallazione;
    }

    public void setDataInstallazione(LocalDate dataInstallazione) {
        this.dataInstallazione = dataInstallazione;
    }

    public LocalDate getDataUltimoAggiornamento() {
        return dataUltimoAggiornamento;
    }

    public void setDataUltimoAggiornamento(LocalDate dataUltimoAggiornamento) {
        this.dataUltimoAggiornamento = dataUltimoAggiornamento;
    }

    public Float getVersione() {
        return versione;
    }

    public void setVersione(Float versione) {
        this.versione = versione;
    }

    public Set<Smartphone> getSmartphones() {
        return smartphones;
    }

    public void setSmartphones(Set<Smartphone> smartphones) {
        this.smartphones = smartphones;
    }

    public void addSmartphone(Smartphone smartphone) {
        smartphones.add(smartphone);
        smartphone.getApps().add(this);
    }

    public void removeSmartphone(Smartphone smartphone) {
        smartphones.remove(smartphone);
        smartphone.getApps().remove(this);
    }

    @Override
    public String toString() {
        return "App{" +
                "versione=" + versione +
                ", dataUltimoAggiornamento=" + dataUltimoAggiornamento +
                ", dataInstallazione=" + dataInstallazione +
                ", nome='" + nome + '\'' +
                ", id=" + id +
                '}';
    }
}
