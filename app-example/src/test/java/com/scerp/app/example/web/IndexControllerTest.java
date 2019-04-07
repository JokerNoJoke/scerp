package com.scerp.app.example.web;

import com.scerp.app.example.AppExampleApplicationRESTDoc;
import org.junit.Test;
import org.springframework.http.MediaType;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class IndexControllerTest extends AppExampleApplicationRESTDoc {

    @Test
    public void index() throws Exception {
        this.mockMvc.perform(get("/").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(document("index"));
    }

    @Test
    public void remote() throws Exception {
        this.mockMvc.perform(get("/remote").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(document("remote"));
    }

}
