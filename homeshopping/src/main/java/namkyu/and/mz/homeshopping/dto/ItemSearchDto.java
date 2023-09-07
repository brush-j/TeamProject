package namkyu.and.mz.homeshopping.dto;

import lombok.Getter;
import lombok.Setter;
import namkyu.and.mz.homeshopping.constant.ItemSellStatus;

@Getter
@Setter
public class ItemSearchDto {

    private String searchDateType;

    private ItemSellStatus searchSellStatus;

    private String searchBy;

    private String searchQuery = "";
}
