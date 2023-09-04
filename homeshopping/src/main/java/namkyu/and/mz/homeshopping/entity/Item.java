package namkyu.and.mz.homeshopping.entity;

import jakarta.persistence.*;
import lombok.Data;
import namkyu.and.mz.homeshopping.constant.ItemSellStatus;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name ="item")
public class Item {
    @Id
    @Column(name="item_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, length = 50)
    private String itemNm;

    @Column(name="price", nullable = false)
    private int price;

    @Column(nullable = false)
    private int stockNumber;

    @Lob
    @Column(nullable = false)
    private String itemDetail;

    @Enumerated(EnumType.STRING)
    private ItemSellStatus itemSellStatus;

    private LocalDateTime regTime;

    private LocalDateTime updateTime;
}