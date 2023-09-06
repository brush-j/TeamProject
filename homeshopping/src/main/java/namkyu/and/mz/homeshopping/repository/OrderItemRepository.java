package namkyu.and.mz.homeshopping.repository;

import namkyu.and.mz.homeshopping.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
