package refer.spring.boot.structure.service.reference;

import refer.spring.boot.structure.domain.reference.Currency;

import java.util.List;
import java.util.Optional;

public interface CurrencyService {

    List<Currency> findCurrencies();

    Optional<Currency> findCurrency(String code);

    Currency saveCurrency(Currency currency);

    Currency updateCurrency(String code, Currency currency);

    Optional<Currency> deleteCurrency(String code);
}
