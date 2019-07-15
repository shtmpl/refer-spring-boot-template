package refer.spring.boot.structure.repository.reference;

import org.springframework.data.jpa.repository.JpaRepository;
import refer.spring.boot.structure.domain.reference.Currency;

import java.util.Optional;

public interface CurrencyRepository extends JpaRepository<Currency, Long> {

    Optional<Currency> findByCodeIgnoreCase(String code);
}
