package ch.uzh.ifi.hase.soprafs24.controller;

import ch.uzh.ifi.hase.soprafs24.constant.ToolType;
import ch.uzh.ifi.hase.soprafs24.entity.Tool;
import ch.uzh.ifi.hase.soprafs24.repository.ToolRepository;
import ch.uzh.ifi.hase.soprafs24.service.ToolService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ToolController.class)
public class ToolControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private ToolService toolService;

  @MockBean
  private ToolRepository toolRepository;

  @Test
  public void givenTools_whenGetTools_thenReturnJsonArray() throws Exception {
    // given
    Tool tool = new Tool();
    tool.setType(ToolType.HINT);
    tool.setPrice(30L);
    tool.setDescription("This is a hint tool!");

    List<Tool> allTools = Collections.singletonList(tool);

    given(toolService.getTools()).willReturn(allTools);

    // when
    MockHttpServletRequestBuilder getRequest = get("/tools").contentType(MediaType.APPLICATION_JSON);

    // then
    mockMvc.perform(getRequest).andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(1)))
        .andExpect(jsonPath("$[0].price", is(30)))
        .andExpect(jsonPath("$[0].type", is(tool.getType().toString())));
  }

  @Test
  public void useTool_validInput() throws Exception {
    doNothing().when(toolService).useTool(Mockito.anyLong(), Mockito.anyLong(), Mockito.anyLong());

    // when
    MockHttpServletRequestBuilder postReqest = post("/tools/1/1/1")
            .contentType(MediaType.APPLICATION_JSON);

    // then
    mockMvc.perform(postReqest).andExpect(status().isNoContent());
  }

    @Test
    public void useTool_invalidInputs() throws Exception {
        doThrow(new ResponseStatusException(HttpStatus.NOT_FOUND, "Tool not found!"))
                .when(toolService).useTool(Mockito.anyLong(), Mockito.anyLong(), Mockito.anyLong());

        MockHttpServletRequestBuilder postReqest = post("/tools/1/1/1")
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(postReqest).andExpect(status().isNotFound());
    }

    @Test
    public void getToolByUserId_validInput() throws Exception {
        Tool tool = new Tool();
        tool.setType(ToolType.HINT);
        tool.setPrice(30L);

        List<String> toolList = Collections.singletonList(tool.getType().name());

        given(toolService.getUserTools(Mockito.anyLong())).willReturn(toolList);

        // when
        MockHttpServletRequestBuilder getRequest = get("/tools/1").contentType(MediaType.APPLICATION_JSON);

        // then
        mockMvc.perform(getRequest).andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    public void getToolByUserId_invalidInputs() throws Exception {
        given(toolService.getUserTools(Mockito.anyLong())).willThrow(
                new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found!"));


        MockHttpServletRequestBuilder postReqest = get("/tools/1")
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(postReqest).andExpect(status().isNotFound());
    }



  private String asJsonString(final Object object) {
    try {
      return new ObjectMapper().writeValueAsString(object);
    } catch (JsonProcessingException e) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
          String.format("The request body could not be created.%s", e.toString()));
    }
  }
}