package fdse.microservice.repository;

import fdse.microservice.domain.Station;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Chenjie Xu on 2017/5/9.
 */
public interface StationRepository extends CrudRepository<Station,String> {
    Station findByName(String name);
}
