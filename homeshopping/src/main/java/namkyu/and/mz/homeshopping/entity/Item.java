package namkyu.and.mz.homeshopping.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import namkyu.and.mz.homeshopping.constant.ItemSellStatus;
import namkyu.and.mz.homeshopping.dto.ItemFormDto;
import namkyu.and.mz.homeshopping.exception.OutOfStockException;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name ="item")
@ToString
public class Item extends BaseEntity{
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

    public void updateItem(ItemFormDto itemFormDto){
        this.itemNm = itemFormDto.getItemNm();
        this.price = itemFormDto.getPrice();
        this.stockNumber = itemFormDto.getStockNumber();
        this.itemDetail = itemFormDto.getItemDetail();
        this.itemSellStatus = itemFormDto.getItemSellStatus();
    }
    public void removeStock(int stockNumber){
        int restStock = this.stockNumber - stockNumber;
        if(restStock<0){
            throw new OutOfStockException("상품의 재고가 부족 합니다." +
                    "(현재 재고 수량: " + this.stockNumber + ")");
        }
        this.stockNumber = restStock;
    }
    public void addStock(int stockNumber){
        this.stockNumber += stockNumber;
    }
}