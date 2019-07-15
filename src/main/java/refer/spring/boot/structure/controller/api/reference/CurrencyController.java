package refer.spring.boot.structure.controller.api.reference;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import refer.spring.boot.structure.controller.api.Exchange;
import refer.spring.boot.structure.controller.api.reference.request.RequestCurrency;
import refer.spring.boot.structure.controller.api.reference.response.ResponseCurrency;
import refer.spring.boot.structure.domain.reference.Currency;
import refer.spring.boot.structure.domain.reference.CurrencyNotFoundException;
import refer.spring.boot.structure.service.reference.CurrencyService;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/reference/currency")
public class CurrencyController {

    private final CurrencyService currencyService;

    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @GetMapping
    public ResponseEntity<List<ResponseCurrency>> index() {
        List<Currency> currencies = currencyService.findCurrencies();

        return ResponseEntity.ok(currencies
                .stream()
                .map(Exchange::mapToResponse)
                .collect(Collectors.toList()));
    }

    @GetMapping("/{code}")
    public ResponseEntity<ResponseCurrency> show(@PathVariable String code) {
        Currency currency = currencyService.findCurrency(code)
                .orElseThrow(() ->
                        new CurrencyNotFoundException(String.format("No currency found for code: %s", code)));

        return ResponseEntity.ok(Exchange.mapToResponse(currency));
    }

    @PostMapping
    public ResponseEntity<ResponseCurrency> save(@Valid @RequestBody RequestCurrency request) {
        Currency currency = currencyService.saveCurrency(Exchange.mapToCurrency(request));

        return ResponseEntity.ok(Exchange.mapToResponse(currency));
    }

    @PutMapping("/{code}")
    public ResponseEntity<ResponseCurrency> update(@PathVariable String code,
                                                   @Valid @RequestBody RequestCurrency request) {
        Currency currency = currencyService.updateCurrency(code, Exchange.mapToCurrency(request));

        return ResponseEntity.ok(Exchange.mapToResponse(currency));
    }

    @DeleteMapping("/{code}")
    public ResponseEntity<ResponseCurrency> delete(@PathVariable String code) {
        Currency currency = currencyService.deleteCurrency(code)
                .orElseThrow(() ->
                        new CurrencyNotFoundException(String.format("No currency found for code: %s", code)));

        return ResponseEntity.ok(Exchange.mapToResponse(currency));
    }
}
