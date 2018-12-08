package viti.kaf22.itemaccounting.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "tickets")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Setter
    private long id;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private List<ItemModel> itemModels;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private PlaceholderModel placeholderModel;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private Location location;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private MilitaryUnit ownerUnit;

    private String qrBase64;

}
