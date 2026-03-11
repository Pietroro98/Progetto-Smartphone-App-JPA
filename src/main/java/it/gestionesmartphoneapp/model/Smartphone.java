package it.gestionesmartphoneapp.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "smartphone")
public class Smartphone 
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

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
    @JoinTable(
            name = "smartphone_app",
            joinColumns = @JoinColumn(name = "smartphone_id"),
            inverseJoinColumns = @JoinColumn(name = "app_id")
    )
    private Set<App> apps = new HashSet<App>();

    public Smartphone() {

    }

    public Smartphone(String nome, LocalDate dataInstallazione, LocalDate dataUltimoAggiornamento, Float versione) {
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

    public Set<App> getApps() {
        return apps;
    }

    public void setApps(Set<App> apps) {
        this.apps = apps;
    }

    public void addApp(App app) {
        apps.add(app);
        app.getSmartphones().add(this);
    }

    public void removeApp(App app) {
        apps.remove(app);
        app.getSmartphones().remove(this);
    }



    @Override
    public String toString() {
        return "Smartphone{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", dataInstallazione=" + dataInstallazione +
                ", dataUltimoAggiornamento=" + dataUltimoAggiornamento +
                ", versione=" + versione +
                '}';
    }
}
