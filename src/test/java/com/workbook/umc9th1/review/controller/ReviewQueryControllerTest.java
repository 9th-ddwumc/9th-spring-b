package com.workbook.umc9th1.review.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
@Sql(scripts = "/testdata.sql")
class ReviewQueryControllerTest {

    @Autowired MockMvc mvc;

    @Test
    void myReviews_filter() throws Exception {
        mvc.perform(get("/api/reviews/my-review")
                        .param("memberId", "1")
                        .param("storeName", "반이학생마라탕마라반")
                        .param("rating", "4")
                        .param("page", "0")
                        .param("size", "10"))
                .andExpect(status().isOk());
    }
}