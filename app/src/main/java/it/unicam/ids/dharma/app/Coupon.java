package it.unicam.ids.dharma.app;

import java.time.LocalDate;

public record Coupon(LocalDate dataScadenza, double totaleSconto) {
}
