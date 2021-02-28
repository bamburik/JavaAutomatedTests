package Services;

import BusinessObjects.Pet;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PetStoreService extends  BaseService implements IPetStoreService{

    @Override
    public Response getPet(long id) {
        RequestSpecification request = createRequest();
        return request.get(String.format(Constants.Uri.petIdFormat, id));
    }

    @Override
    public Response createPet(Pet pet) {
        RequestSpecification request = createRequest();
        request.body(new PetConventer().ConvertPet(pet));
        return request.post(Constants.Uri.pet);
    }

    @Override
    public Response updatePet(Pet pet) {
        RequestSpecification request = createRequest();
        request.body(new PetConventer().ConvertPet(pet));
        return request.put(Constants.Uri.pet);
    }

    @Override
    public Response deletePet(Pet pet) {
        RequestSpecification request = createRequest();
        return request.delete(String.format(Constants.Uri.petIdFormat, pet.getId()));
    }
}
