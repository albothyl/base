package base.support;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Ignore;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.nio.charset.StandardCharsets;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class BaseTestControllerSupport {

    @Autowired
    protected MockMvc mockMvc;
    protected final MediaType JSON_UTF8_MEDIA_TYPE = new MediaType(MediaType.APPLICATION_JSON, StandardCharsets.UTF_8);

    @Autowired
    protected ObjectMapper objectMapper;

    protected <T> ResultActions postResource(String url, T body) throws Exception {
        return mockMvc.perform(post(url)
                .contentType(JSON_UTF8_MEDIA_TYPE)
                .accept(JSON_UTF8_MEDIA_TYPE)
                .content(objectMapper.writeValueAsString(body)));
    }

    protected <T> ResultActions putResource(String url, T body) throws Exception {
        return mockMvc.perform(put(url)
                .contentType(JSON_UTF8_MEDIA_TYPE)
                .accept(JSON_UTF8_MEDIA_TYPE)
                .content(objectMapper.writeValueAsString(body)));
    }

    protected <T> ResultActions patchResource(String url, T body) throws Exception {
        return mockMvc.perform(patch(url)
                .contentType(JSON_UTF8_MEDIA_TYPE)
                .accept(JSON_UTF8_MEDIA_TYPE)
                .content(objectMapper.writeValueAsString(body)));
    }

    protected <T> ResultActions deleteResource(String url, T body) throws Exception {
        return mockMvc.perform(delete(url)
                .content(objectMapper.writeValueAsString(body))
                .contentType(JSON_UTF8_MEDIA_TYPE)
                .accept(JSON_UTF8_MEDIA_TYPE));
    }
}