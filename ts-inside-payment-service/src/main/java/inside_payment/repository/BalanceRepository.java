package inside_payment.repository;

import inside_payment.domain.Balance;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Administrator on 2017/6/20.
 */
public interface BalanceRepository extends CrudRepository<Balance,String> {
    Balance findById(String userId);
}