package request;

import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;
import utils.ConfigProperties;

import java.util.Map;

import static com.jayway.restassured.RestAssured.given;

public class HttpRequest {

    private RequestSpecification specification;

    public HttpRequest(RequestSpecification specification) {
        this.specification = specification;
    }

    public Response post(String resource, Map<String, Object> formParams) {
        Response response = given()
                .spec(specification)
                .formParams(formParams)
                .cookie("BasketUID", ConfigProperties.getProperty("cookieBasketUID"))
                .when()
                .post(resource);

        response.then().log().all();

        return response;
    }
}
