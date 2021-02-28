package Services;

import BusinessObjects.Pet;
import io.restassured.response.Response;

public interface IPetStoreService {
    Response getPet(long id);
    Response createPet(Pet pet);
    Response updatePet(Pet pet);
    Response deletePet(Pet pet);
}
