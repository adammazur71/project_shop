package org.shop.invoice;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static com.github.tomakehurst.wiremock.client.WireMock.*;

@SpringBootTest
@AutoConfigureMockMvc
@Testcontainers
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@WireMockTest(httpPort = 8888)
public class CustomerAndItemAndInvoiceRepositoryIntegrationTests {

    @Autowired
    MockMvc mockMvc;

    @Container
    public static MySQLContainer mySQLContainer = new MySQLContainer("mysql:8.0");

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry registry) {
        registry.add("ksefUrl", () -> "http://localhost:8888");
        registry.add("spring.datasource.url", mySQLContainer::getJdbcUrl);
        registry.add("spring.datasource.username", mySQLContainer::getUsername);
        registry.add("spring.datasource.password", mySQLContainer::getPassword);
        registry.add("spring.jpa.hibernate.ddl-auto", () -> "create-drop");
    }

    @Test
    @Order(1)
    public void negativeSearchCustomerById() throws Exception {
        //searching for customer by id that not exists
        //WHEN
        ResultActions resultById = mockMvc.perform(get("/customers/search/id/1"));
        //THEN
        resultById.andExpect(status().isNotFound());
        resultById.andExpect(content().json("{'message': 'Id 1 does not exist'}"));
    }

    @Test
    @Order(1)
    public void negativeSearchCustomerByName() throws Exception {
        //searching for customer by name that not exists
        //WHEN
        ResultActions resultByName = mockMvc.perform(get("/customers/search/name/Karol222"));
        //THEN
        resultByName.andExpect(status().isNotFound());
        resultByName.andExpect(content().json("{'message': 'Customers name containing: Karol222 is not found'}"));
    }

    @Test
    @Order(1)
    public void negativeSearchCustomerByNip() throws Exception {
        //searching for customer by NIP that not exists
        //WHEN
        ResultActions resultByNip = mockMvc.perform(get("/customers/search/nip/123"));
        //THEN
        resultByNip.andExpect(status().isNotFound());
        resultByNip.andExpect(content().json("{'message': 'Customers NIP containing: 123 is not found'}"));
    }

    @Test
    @Order(2)
    public void positiveAddNewCustomer() throws Exception {
        //add new correct customer
        //WHEN
        ResultActions resultActions = mockMvc.perform(post("/customers/")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"customerName\": \"Karol222\",\n" +
                        "    \"nip\": \"123456789\",\n" +
                        "    \"street\": \"lebzingerstr\",\n" +
                        "    \"buildingNo\": \"36\",\n" +
                        "    \"apartmentNo\": \"25a\",\n" +
                        "    \"postal\": \"98456\",\n" +
                        "    \"city\": \"Berlin\",\n" +
                        "    \"country\": \"Niemcy\",\n" +
                        "    \"phoneNo\": \"123456789\",\n"  +
                        "    \"email\": \"123456789\",\n" +
                        "    \"altPhoneNo\": \"987654321\",\n" +
                        "    \"role\": \"2\"\n" +
                        "}"));
        //THEN
        resultActions.andExpect(status().isCreated());
        resultActions.andExpect(content().json("{" +
                "'customerId':1," +
                "'customerName':'Karol222'," +
                "'nip':'123456789'," +
                "'street':'lebzingerstr'," +
                "'buildingNo':'36'," +
                "'apartmentNo':'25a'," +
                "'postal':98456," +
                "'city':'Berlin'," +
                "'country':'Niemcy'," +
                "'phoneNo':'123456789'," +
                "'email':'123456789'," +
                "'role':2}"));
    }

    @Test
    @Order(3)
    public void positiveAddNewItemType() throws Exception {
        //add new correct item type
        //WHEN
        ResultActions resultActions = mockMvc.perform(post("/product/type")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"itemTypeName\": \"first type\"}"));
        //THEN
        resultActions.andExpect(status().isCreated());
        resultActions.andExpect(content().json("{'itemTypeId':1,'itemTypeName':'first type'}"));
    }

    @Test
    @Order(4)
    public void positiveAddNewItem() throws Exception {
        //add new correct item type
        //WHEN
        ResultActions resultActions = mockMvc.perform(post("/product/")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"itemType\": {\"itemTypeId\": 1},\n" +
                        "    \"itemName\": \"first product\"\n" +
                        "}"));
        //THEN
        resultActions.andExpect(status().isCreated());
        resultActions.andExpect(content().json("{\"itemType\": {\"itemTypeId\": 1},\n" +
                "    \"itemName\": \"first product\"\n" +
                "}"));
    }
    @Test
    @Order(5)
    public void positiveImportNewPurchaseInvoice() throws Exception {
        //add new correct selling invoice
        //WHEN
        ResultActions resultActions = mockMvc.perform(post("/invoices/")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"invoiceType\": \"0\",\n" +
                        "    \"invoiceNo\": \"Invoice 5/2024\",\n" +
                        "    \"ksefStatus\": \"0\",\n" +
                        "    \"isPaid\": \"0\",\n" +
                        "    \"customer\": {\n" +
                        "        \"customerId\": 1\n" +
                        "                },\n" +
                        "    \"invoiceItem\": [\n" +
                        "        {\n" +
                        "            \"itemId\": \"1\",\n" +
                        "            \"qtySold\": \"2\",\n" +
                        "            \"netValue\": \"152.8\",\n" +
                        "            \"vatStake\": \"0.23\"\n" +
                        "    }]\n" +
                        "}"));
        //THEN
        resultActions.andExpect(status().isOk());
        resultActions.andExpect(content().json("{'invoiceId':1,'invoiceType':0,'invoiceNo':'Invoice 5/2024'," +
                "'isPaid':0,'shipments':null,'ksefId':null,'netAmount':305.6,'grossAmount':375.88,'customer':" +
                "{'customerId':1,'customerName':'Karol222','nip':'123456789','street':'lebzingerstr','buildingNo':'36'," +
                "'apartmentNo':'25a','postal':98456,'city':'Berlin','country':'Niemcy','phoneNo':'123456789'," +
                "'email':'123456789','role':2}," +
                "'invoiceItems':[{'invoiceItemId':1,'itemId':1,'invoiceId':1,'qtySold':2,'netValue':152.8,'vatStake':0.23" +
                ",'grossValue':187.94}]}"));
    }
    @Test
    @Order(6)
    public void positiveImportNewSellingInvoice() throws Exception {
        //add new correct selling invoice
        //WHEN
        ResultActions resultActions = mockMvc.perform(post("/invoices/")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"invoiceType\": \"1\",\n" +
                        "    \"invoiceNo\": \"Invoice 6/05/2024\",\n" +
                        "    \"ksefStatus\": \"0\",\n" +
                        "    \"isPaid\": \"0\",\n" +
                        "    \"customer\": {\n" +
                        "        \"customerId\": 1\n" +
                        "                },\n" +
                        "    \"invoiceItem\": [\n" +
                        "        {\n" +
                        "            \"itemId\": \"1\",\n" +
                        "            \"qtySold\": \"2\",\n" +
                        "            \"netValue\": \"152.8\",\n" +
                        "            \"vatStake\": \"0.23\"\n" +
                        "    }]\n" +
                        "}"));
        //THEN
        resultActions.andExpect(status().isOk());
        resultActions.andExpect(content().json("{'invoiceId':2,'invoiceType':1,'invoiceNo':'Invoice 6/05/2024'," +
                "'isPaid':0,'shipments':null,'ksefId':null,'netAmount':305.6,'grossAmount':375.88,'customer':" +
                "{'customerId':1,'customerName':'Karol222','nip':'123456789','street':'lebzingerstr','buildingNo':'36'," +
                "'apartmentNo':'25a','postal':98456,'city':'Berlin','country':'Niemcy','phoneNo':'123456789'," +
                "'email':'123456789','role':2}," +
                "'invoiceItems':[{'invoiceItemId':2,'itemId':1,'invoiceId':2,'qtySold':2,'netValue':152.8,'vatStake':0.23" +
                ",'grossValue':187.94}]}"));
    }
    @Test
    @Order(7)
    public void positiveSendCorrectInvoiceToKsef() throws Exception {
        //send correct invoice to KSeF
        //GIVEN
        String responseFromKsef = Files.readString(Path.of("src/test/resources/responseFromKsef.json"));
        stubFor(WireMock.post(urlEqualTo("/")).willReturn(
                aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(responseFromKsef)
        ));
        //WHEN
        ResultActions resultActions = mockMvc.perform(post("/invoices/ksef/2"));
        //THEN
        resultActions.andExpect(status().isOk());
        resultActions.andExpect(content().json(responseFromKsef));
    }


}
