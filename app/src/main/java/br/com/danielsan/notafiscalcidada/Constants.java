package br.com.danielsan.notafiscalcidada;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * Created by daniel on 30/09/17.
 */
public final class Constants {

    public static final String CNPJ_MASK = "##.###.###/####-##";
    public static final Locale LOCALE = new Locale("pt", "BR");
    public static final NumberFormat CURRENCY_FORMAT = NumberFormat.getCurrencyInstance(LOCALE);
    public static final DateFormat DATE_FORMAT = DateFormat.getDateInstance(DateFormat.SHORT, LOCALE);

    private Constants() {
        throw new UnsupportedOperationException("This is a pure static class!");
    }

}
