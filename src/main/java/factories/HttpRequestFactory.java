package factories;

import com.jayway.restassured.builder.RequestSpecBuilder;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.specification.RequestSpecification;
import request.HttpRequest;

public class HttpRequestFactory {

    private String baseUrl;
    private int port;

    public HttpRequestFactory(String baseUrl, String port) {
        this.baseUrl = baseUrl;
        this.port = Integer.parseInt(port);
    }

    public HttpRequest getRequest() {
        RequestSpecification specification = new RequestSpecBuilder()
                .setBaseUri("https://" + baseUrl)
                .setPort(port)
                .setAccept(ContentType.JSON)
                .setContentType(ContentType.URLENC.withCharset("UTF-8"))
                .addHeader("X-Requested-With", "XMLHttpRequest")
                .build()
                .log()
                .all();

        return new HttpRequest(specification);
    }
}
