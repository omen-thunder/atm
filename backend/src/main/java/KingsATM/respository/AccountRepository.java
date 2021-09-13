package KingsATM.respository;

import KingsATM.model.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends CrudRepository<Account, Integer> {

    @Override
    Optional<Account> findById(Integer integer);
}

