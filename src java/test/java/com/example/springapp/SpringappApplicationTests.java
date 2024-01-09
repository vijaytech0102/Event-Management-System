package com.example.springapp;
 
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
 
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.util.NestedServletException;
 
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;
import org.junit.runners.MethodSorters;
 
import java.lang.reflect.Field;
import javax.persistence.OneToMany;
 
@SpringBootTest(classes = SpringappApplication.class)
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class SpringappApplicationTests {
 
    @Autowired
    private ObjectMapper objectMapper;
 
    @Autowired
    private MockMvc mockMvc;
 
    private String generatedToken;
 
    @Test
    @Order(1)
    void testRegisterUser() throws Exception {
        String requestBody = "{ \"userId\": 1,\"email\": \"abcd@gmail.com\", \"password\": \"abc\", \"username\": \"abcd\", \"userRole\": \"USER\"}";
 
        mockMvc.perform(MockMvcRequestBuilders.post("/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
    }
 
    @Test
    @Order(2)
    void testRegisterAdmin() throws Exception {
        String requestBody = "{\"userId\": 2,\"email\": \"abc@gmail.com\", \"password\": \"abc\", \"username\": \"abc\", \"userRole\": \"ADMIN\"}";
 
        mockMvc.perform(MockMvcRequestBuilders.post("/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
    }
 
    @Test
    @Order(3)
    public void testLoginUser() throws Exception {
        testRegisterUser();
        String requestBody = "{\"email\": \"abcd@gmail.com\", \"password\": \"abc\", \"userRole\": \"USER\"}";
 
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
 
        String responseString = result.getResponse().getContentAsString();
        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> responseMap = mapper.readValue(responseString, Map.class);
 
        String token = responseMap.get("token");
        generatedToken = token;
 
        assertNotNull(token);
    }
 
    @Test
    @Order(4)
    void testLoginAdmin() throws Exception {
        testRegisterAdmin();
        String requestBody = "{\"email\": \"abc@gmail.com\", \"password\": \"abc\", \"userRole\": \"ADMIN\"}";
 
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
 
        String responseString = result.getResponse().getContentAsString();
        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> responseMap = mapper.readValue(responseString, Map.class);
 
        String token = responseMap.get("token");
        generatedToken = token;
 
        assertNotNull(token);
    }
 
    @Test
    @Order(5)
    void testAddEvent() throws Exception {
        
        String requestBody = "{"
                + "\"eventType\": \"TestEvent\","
                + "\"description\": \"Test description\","
                + "\"participantsCount\": 50,"
                + "\"eventCharges\": 100"
                + "}";
 
        MvcResult result = mockMvc.perform(post("/api/event")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
 
    }
 
    @Test
    @Order(6)
    void testGetAllEventsByAdmin() throws Exception {
        mockMvc.perform(get("/api/event")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
 
    }
 
    @Test
    @Order(7)
    void testGetAllEventsByuser() throws Exception {
       testLoginUser();
        mockMvc.perform(get("/api/user/event")
                .header("Authorization", "Bearer " + generatedToken)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
 
    }
 
    @Test
    @Order(8)
    void testAddorganizer() throws Exception {
      testLoginUser();
        String requestBody = "{"
 
                + "    \"organizerId\": 1,"
                + "    \"mobileNumber\": 50"
                + "}";
 
        MvcResult result = mockMvc.perform(post("/api/registration/organizer")
                .header("Authorization", "Bearer " + generatedToken)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
 
    }
 
    @Test
    @Order(9)
    void testGetAllBookingByAdmin() throws Exception {
 
        mockMvc.perform(get("/api/registration/organizer")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
 
    }
 
    @Test
    @Order(10)
    void testAddEfacility() throws Exception {
 
        String requestBody = "{"
 
                + "    \"facilityId\": 1,"
                + "    \"facilityDescription\": \"TestFacility\","
                + "    \"price\": 50"
                + "}";
 
        MvcResult result = mockMvc.perform(post("/api/facility")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
 
    }
 
    @Test
    @Order(11)
    void testGetAllfacilitybyadmin() throws Exception {
      
        mockMvc.perform(get("/api/facility")
             
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
 
    }
 
    @Test
    @Order(12)
    public void testEventHasOneToManyAnnotation() {
        try {
            // Use reflection to get the Class object for the Course class
            Class<?> courseClass = Class.forName("com.example.springapp.model.Event");
 
            // Get all declared fields in the Course class
            Field[] declaredFields = courseClass.getDeclaredFields();
 
            // Check each field for the @OneToMany annotation
            boolean hasOneToMany = false;
            for (Field field : declaredFields) {
                if (field.isAnnotationPresent(OneToMany.class)) {
                    hasOneToMany = true;
                    break; // Stop checking once we find one field with @OneToMany
                }
            }
 
            // If no field with @OneToMany is found, fail the test
            if (!hasOneToMany) {
                fail("No field with @OneToMany annotation found in event class.");
            }
 
        } catch (ClassNotFoundException e) {
            // If the class is not found, fail the test
            fail("Class not found: " + e.getMessage());
        }
    }
 
}