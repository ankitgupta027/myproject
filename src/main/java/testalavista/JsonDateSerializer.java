package testalavista;
import org.apache.htrace.fasterxml.jackson.core.JsonGenerator;
import org.apache.htrace.fasterxml.jackson.core.JsonProcessingException;
import org.apache.htrace.fasterxml.jackson.databind.JsonSerializer;
import org.apache.htrace.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Used to serialize Java.util.Date, which is not a common JSON
 * type, so we have to create a custom serialize method;.
 *
 * @author Loiane Groner
 * http://loianegroner.com (English)
 * http://loiane.com (Portuguese)
 */
public class JsonDateSerializer extends JsonSerializer<Date> {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("MMM-dd-yyyy hh:mm:ss");
    @Override
    public void serialize(Date date, JsonGenerator gen, SerializerProvider provider)
            throws IOException, JsonProcessingException {
        String formattedDate = dateFormat.format(date);
        gen.writeString(formattedDate);
    }
}