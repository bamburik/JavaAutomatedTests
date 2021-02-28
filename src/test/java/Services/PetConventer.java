package Services;

import BusinessObjects.Pet;
import BusinessObjects.Tag;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class PetConventer {

    public String ConvertPet(Pet pet) {

        JsonObject jsonObject = new JsonObject();
        if (pet.getId() != null) {
            jsonObject.addProperty("id", pet.getId());
        }
        JsonObject jsonCategory = new JsonObject();
        jsonCategory.addProperty("id", pet.getCategory().getId());
        jsonCategory.addProperty("name", pet.getCategory().getName());
        jsonObject.add("category", jsonCategory);
        jsonObject.addProperty("name", pet.getName());

        JsonArray jsonPhotoUrls = new JsonArray();
        for(String photoUrl : pet.getPhotoUrls()) {
            jsonPhotoUrls.add(photoUrl);
        }
        jsonObject.add("photoUrls", jsonPhotoUrls);

        JsonArray jsonTags = new JsonArray();
        for(Tag tag : pet.getTags()) {
            JsonObject jsonTag = new JsonObject();
            jsonTag.addProperty("id", tag.getId());
            jsonTag.addProperty("name", tag.getName());
            jsonTags.add(jsonTag);
        }
        jsonObject.add("tags", jsonTags);

        jsonObject.addProperty("status", "available");

        return jsonObject.toString();
    }
}
