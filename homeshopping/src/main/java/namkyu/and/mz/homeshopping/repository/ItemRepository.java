package namkyu.and.mz.homeshopping.repository;

import namkyu.and.mz.homeshopping.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
