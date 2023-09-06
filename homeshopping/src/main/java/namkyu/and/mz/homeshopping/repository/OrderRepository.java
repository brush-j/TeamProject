package namkyu.and.mz.homeshopping.repository;

import namkyu.and.mz.homeshopping.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
