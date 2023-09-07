package namkyu.and.mz.homeshopping.repository;

import namkyu.and.mz.homeshopping.dto.ItemSearchDto;
import namkyu.and.mz.homeshopping.dto.MainItemDto;
import namkyu.and.mz.homeshopping.entity.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ItemRepositoryCustom {

    Page<Item> getAdminItemPage(ItemSearchDto itemSearchDto, Pageable pageable);

    Page<MainItemDto> getMainItemPage(ItemSearchDto itemSearchDto, Pageable pageable);
}
