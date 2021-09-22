package KingsATM.respository;
import KingsATM.model.Cash;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CashRepository extends JpaRepository<Cash, Float> {
	List<Cash> findByOrderByValueDesc();
}
