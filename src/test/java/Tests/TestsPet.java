package Tests;

import BusinessObjects.Category;
import BusinessObjects.Pet;
import BusinessObjects.Status;
import BusinessObjects.Tag;
import Configuration.Config;
import Configuration.ConfigItem;
import Services.IPetStoreService;
import Services.PetStoreService;
import com.jayway.jsonpath.JsonPath;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Arrays;

public class TestsPet {
    Pet pet;
    IPetStoreService service;
    boolean petExist = false;

    @BeforeClass
    public void preparation() {
        RestAssured.baseURI = Config.get(ConfigItem.URL);
        pet = new Pet(
                new Category(2, "dogs"),
                "volf",
                Arrays.asList("photo.jpg"),
                Arrays.asList(new Tag(1,"white"), new Tag(2, "dog")),
                Status.available
                );
        service = new PetStoreService();
    }

    @Test(priority = 1)
    public void testCreate() {
        Response response = service.createPet(pet);
        response.then().statusCode(200);
        petExist = true;
        String body = response.getBody().print();
        pet.setId((long)JsonPath.read(body, "$.id"));
        SoftAssert softAssert = new SoftAssert();
        CheckPet(response.getBody().print(), softAssert, pet);
    }

    @Test(dependsOnMethods = {"testCreate"}, priority = 2)
    public void testUpdate() {
        pet.setName("volff");
        Response response = service.updatePet(pet);
        response.then().statusCode(200);
        SoftAssert softAssert = new SoftAssert();
        CheckPet(response.getBody().print(), softAssert, pet);
    }

    @Test(dependsOnMethods = {"testCreate"}, priority = 3)
    public void testDelete() {
        Response response = service.deletePet(pet);
        response.then().statusCode(200);
        petExist = false;
        service.getPet(pet.getId()).then().statusCode(404);
    }

    @AfterClass
    public void CleanUp(){
        if (petExist) {
            service.deletePet(pet);
        }
    }

    private void CheckPet(String body, SoftAssert softAssert, Pet pet) {
        softAssert.assertEquals((int)JsonPath.read(body, "$.category.id"), pet.getCategory().getId());
        softAssert.assertEquals(JsonPath.read(body, "$.category.name"), pet.getCategory().getName());
        softAssert.assertEquals(JsonPath.read(body, "$.name"), pet.getName());
        for (int i = 0; i<pet.getPhotoUrls().size();i++) {
            softAssert.assertEquals(JsonPath.read(body, String.format("$.photoUrls[%d]", i)), pet.getPhotoUrls().get(i));
        }
        for (int i = 0; i<pet.getTags().size();i++) {
            softAssert.assertEquals((int)JsonPath.read(body, String.format("$.tags[%d].id", i)), pet.getTags().get(i).getId());
            softAssert.assertEquals(JsonPath.read(body, String.format("$.tags[%d].name", i)), pet.getTags().get(i).getName());
        }
        softAssert.assertEquals(JsonPath.read(body, "$.status"), pet.getStatus().toString());
        softAssert.assertAll();
    }
}
