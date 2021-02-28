package Services;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.specification.RequestSpecification;

public abstract class BaseService {
    public RequestSpecification createRequest() {
        RequestSpecification request = RestAssured.given();
        request.header(new Header(Constants.accept, Constants.applicationJson));
        request.header(new Header(Constants.contentType, Constants.applicationJson));
        return request;
    }
}
