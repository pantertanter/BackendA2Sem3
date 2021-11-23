package deserializer;

import com.google.gson.*;
import dtos.BookDTO;
import dtos.BookSearchResultsDTO;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class BookSearchResultsDTODeserializer implements JsonDeserializer<BookSearchResultsDTO> {
    @Override
    public BookSearchResultsDTO deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        int numFound = jsonObject.get("numFound").getAsInt();
        int start = jsonObject.get("start").getAsInt();

        int offset = 0;
        JsonElement offsetElement = jsonObject.get("offset");
        if (!offsetElement.isJsonNull()) {
            offset = offsetElement.getAsInt();
        }

        String query = jsonObject.get("q").getAsString();

        JsonArray docs = jsonObject.get("docs").getAsJsonArray();
        List<BookDTO> results = new ArrayList<>();
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(BookDTO.class, new BookDTODeserializerFromSearchResult())
                .create();
        for (JsonElement doc : docs) {
            results.add(gson.fromJson(doc, BookDTO.class));
        }

        return new BookSearchResultsDTO(numFound, start, offset, query, results);
    }
}
