package deserializer;

import com.google.gson.*;
import dtos.AuthorDTO;
import dtos.BookDTO;
import dtos.SubjectDTO;
import facades.SearchFacade;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class BookDTODeserializerFromSearchResult implements JsonDeserializer<BookDTO> {

    private final SearchFacade searchFacade = SearchFacade.getSearchFacade();

    @Override
    public BookDTO deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        String title = jsonObject.get("title").getAsString();

        // It's extremely tiresome having to check if every value exists.
        // An easier solution is to just pass on the JsonElement
        // Or write helper methods to check if an Element with a type exists and return the value.
        // this will desperately need a refactor
        // TODO: refactor

        // same as below
        int first_publish_year = 0;
        JsonElement firstPublishYearElement = jsonObject.get("first_publish_year");
        if (firstPublishYearElement != null && !firstPublishYearElement.isJsonNull()) {
            first_publish_year = firstPublishYearElement.getAsInt();
        }

        // not very useful to show a page count of 0.
        // Consider making it an Integer, so you can nullify it, or a String with a default value if 0.
        int number_of_pages_median = 0;
        JsonElement medianPagesElement = jsonObject.get("number_of_pages_median");
        if (medianPagesElement != null && !medianPagesElement.isJsonNull()) {
            number_of_pages_median = medianPagesElement.getAsInt();
        }

        // same deal as above, except returning an empty List does make sense
        int coverId = 0;
        JsonElement coverIdElement = jsonObject.get("cover_i");
        if (coverIdElement != null && !coverIdElement.isJsonNull()) {
            coverId = coverIdElement.getAsInt();
        }
        List<String> thumbnail_urls = new ArrayList<>();
        if (coverId != 0) {
            thumbnail_urls.addAll(searchFacade.getCoverUrlsById(coverId));
        }

        JsonArray subjectKeys = null;
        JsonElement subjectKeysElement = jsonObject.get("subject_key");
        if (subjectKeysElement != null && !subjectKeysElement.isJsonNull()) {
            subjectKeys = subjectKeysElement.getAsJsonArray();
        }
        JsonArray subjectNames = null;
        JsonElement subjectNamesElement = jsonObject.get("subject_facet");
        if (subjectNamesElement != null && !subjectNamesElement.isJsonNull()) {
            subjectNames = subjectNamesElement.getAsJsonArray();
        }
        List<SubjectDTO> subjects = new ArrayList<>();
        if (subjectKeys != null && subjectNames != null) {
            subjects.addAll(getSubjects(subjectKeys, subjectNames));
        }


        JsonArray authorKeys = null;
        JsonElement authorKeysElement = jsonObject.get("author_key");
        if (authorKeysElement != null && !authorKeysElement.isJsonNull()) {
            authorKeys = authorKeysElement.getAsJsonArray();
        }
        JsonArray authorNames = null;
        JsonElement authorNamesElement = jsonObject.get("author_name");
        if (authorNamesElement != null && !authorNamesElement.isJsonNull()) {
            authorNames = authorNamesElement.getAsJsonArray();
        }
        List<AuthorDTO> authors = new ArrayList<>();
        if (authorKeys != null && authorNames != null) {
            authors.addAll(getAuthors(authorKeys, authorNames));
        }



        // description is retrieved from the "work" of the book
        // TODO: Implement getting the work in SearchFacade
        String description = null;
        JsonElement descriptionElement = jsonObject.get("description");
        if (descriptionElement != null && !descriptionElement.isJsonNull()) {
            if (descriptionElement.isJsonPrimitive()) {
                if (descriptionElement.getAsJsonPrimitive().isString()) {
                    description = descriptionElement.getAsString();
                }
            }
            else {
                description = descriptionElement.getAsJsonObject().get("value").getAsString();
            }
        }

        return new BookDTO(title, first_publish_year, number_of_pages_median, thumbnail_urls, authors, subjects);
    }

    // TODO: The subjects don't match their key because the keys are sorted alphabetically.
    //  So are the facets but lowercase are sorted last which desyncs the list.
    //  The list called "subjects" is not sorted at all so it won't match the keys.
    //  You might have to just omit the key and search by name later.
    private List<SubjectDTO> getSubjects(JsonArray keys, JsonArray names) {
        List<SubjectDTO> subjects = new ArrayList<>();
        for (int i = 0; i < keys.size(); i++) {
            subjects.add(new SubjectDTO(keys.get(i).getAsString(), names.get(i).getAsString()));
        }
        return subjects;
    }

    private List<AuthorDTO> getAuthors(JsonArray keys, JsonArray names) {
        List<AuthorDTO> authors = new ArrayList<>();
        for (int i = 0; i < keys.size(); i++) {
            authors.add(new AuthorDTO(keys.get(i).getAsString(), names.get(i).getAsString()));
        }
        return authors;
    }
}
