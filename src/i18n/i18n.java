package i18n;

import java.text.MessageFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Locale;

public class i18n {
    public static void main(String... args) throws ParseException {
        Arrays.stream(Locale.getAvailableLocales()).limit(10).forEach( l ->
                System.out.println(l.getCountry()+ " "+ l.getDisplayCountry()+" "+l.getDisplayLanguage()+" "+l.getDisplayScript())
        );

        double d = 10.55;
        String decimalLocal = NumberFormat.getInstance(Locale.getDefault()).format(d);
        System.out.println("decimal Local format: "+decimalLocal);
        String message = "{0}:{1}";
        System.out.println(MessageFormat.format(message, Locale.getDefault(),
                NumberFormat.getInstance().parse(decimalLocal)));

        System.out.println(MessageFormat.format(message, Locale.GERMAN,
                NumberFormat.getInstance(Locale.GERMAN).parse(decimalLocal)));

        LocalDateTime dateTime = LocalDateTime.now();
        System.out.println(String.format("%1$s %2$tF", Locale.getDefault().getDisplayCountry(), dateTime));
    }
}
