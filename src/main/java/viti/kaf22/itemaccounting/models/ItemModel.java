package viti.kaf22.itemaccounting.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import viti.kaf22.itemaccounting.models.enums.CheckingState;
import viti.kaf22.itemaccounting.models.enums.ItemState;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "items")
public class ItemModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String imageBase64;
    private String qrCodeBase64;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private ItemType type;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private PlaceholderModel placeholderModel;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private Location location;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private MilitaryUnit ownerUnit;
    @Enumerated(value = EnumType.ORDINAL)
    private ItemState itemState;
    @Enumerated(value = EnumType.ORDINAL)
    private CheckingState checkingState;
    private String base64KeyOfObject;
    private String keySigning;
    private Date timeOfSigning;

}
