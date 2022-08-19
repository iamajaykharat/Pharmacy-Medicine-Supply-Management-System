package com.cognizant.mfpe.pharmacy_supply.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApiResponseTest {

    @Mock
    public ApiResponse apiResponse;

    @Before
    public void setUp() throws Exception {
        apiResponse = new ApiResponse();
        apiResponse.setSuccess(false);
        apiResponse.setMessage("Token Invalid");
    }

    @Test
    public void AllArgConstTest() {
        ApiResponse auth = new ApiResponse(false, "Token Invalid");
        assertEquals(apiResponse.getMessage(), auth.getMessage());
        assertEquals(false, auth.isSuccess());
    }

    @Test
    public void testToStringMethod() {
        assertEquals("ApiResponse(success=" + apiResponse.isSuccess()
                + ", message=" + apiResponse.getMessage() + ")", apiResponse.toString());
    }

    @Test
    public void testNoArgsConstructor() {
        ApiResponse apiResponse = new ApiResponse();
        assertEquals(null, apiResponse.getMessage());
    }

    @Test
    public void testSetters() {
        apiResponse.setMessage("admin");
        apiResponse.setSuccess(true);
        assertEquals("admin", apiResponse.getMessage());
    }

    @Test
    public void testEqualsMethod() {
        boolean equals = apiResponse.equals(apiResponse);
        assertTrue(equals);
    }

    @Test
    public void testHashCodeMethod() {
        int hashCode = apiResponse.hashCode();
        assertEquals(hashCode, apiResponse.hashCode());
    }
}
