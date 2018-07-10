package com.avsoftware.data.gson

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type
import java.text.SimpleDateFormat
import java.util.*

class DateDeserializer : JsonDeserializer<Date> {
    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): Date {
        val dateString = json.asString
        val format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        format.timeZone = TimeZone.getDefault()
        return try { format.parse(dateString) } catch (e: Exception) { Date() }
    }
}

/*
public class DateDeserializer implements JsonDeserializer<Date> {

  @Override
  public Date deserialize(JsonElement element, Type arg1, JsonDeserializationContext arg2) throws JsonParseException {
      String date = element.getAsString();

      SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
      format.setTimeZone(TimeZone.getTimeZone("GMT"));

      try {
          return format.parse(date);
      } catch (ParseException exp) {
          System.err.println("Failed to parse Date:", exp);
          return null;
      }
   }
}
 */