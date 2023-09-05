package namkyu.and.mz.homeshopping.repository;

import namkyu.and.mz.homeshopping.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Member findByEmail(String email);
}
