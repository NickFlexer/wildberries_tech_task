import com.jayway.restassured.response.Response;
import factories.HttpRequestFactory;
import helpers.JsonPathHelper;
import org.junit.jupiter.api.Test;
import request.HttpRequest;
import utils.ConfigProperties;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static testdata.StatusCodes.ST_OK;

class BasketTests {

    private HttpRequestFactory requestFactory;

    BasketTests() {
        requestFactory = new HttpRequestFactory(ConfigProperties.getProperty("baseUrl"), ConfigProperties.getProperty("port"));
    }

    @Test
    void reculcBasket() {
        int newQuantity = 1;
        int itemId = Integer.parseInt(ConfigProperties.getProperty("itemId"));


        HttpRequest request = requestFactory.getRequest();
        Response response = request.post(
                "/basket/recalc",
                new HashMap<String, Object>(){
                    {
                        put("basketItemId", itemId);
                        put("quantity", newQuantity);
                        put("chrtId", itemId);
                    }
                });

        assertEquals(ST_OK, response.getStatusCode(), "Неверный статус-код");
        assertEquals(
                newQuantity,
                (int) JsonPathHelper.read(response, "$..quantity"),
                "Неверное значение количества товаров в корзине");

        double price = JsonPathHelper.read(response, "$..priceSum");

        // Увеличиваем количество товаров на 1
        response = request.post(
                "/basket/recalc",
                new HashMap<String, Object>(){
                    {
                        put("basketItemId", itemId);
                        put("quantity", newQuantity + 1);
                        put("chrtId", itemId);
                    }
                });

        // Проверка, что значения увеличились
        assertEquals(ST_OK, response.getStatusCode(), "Неверный статус-код");
        assertEquals(
                newQuantity + 1,
                (int) JsonPathHelper.read(response, "$..quantity"),
                "Неверное значение количества товаров в корзине");
        assertEquals(
                price * 2,
                (double) JsonPathHelper.read(response, "$..priceSum"),
                "Неверное значение суммы");
    }
}
