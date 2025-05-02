package com.example.travelagency.web;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

@ControllerAdvice
@Controller
public class ExceptionHandlingController implements ErrorController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionHandlingController.class);

    // 에러 페이지 정의
    private final String ERROR_PAGE_PATH = "/error/error";

    @RequestMapping(value = "/error")
    public String handleError(HttpServletRequest request, Model model) {

        // 에러
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        // 에러 코드
        int statusCode = Integer.valueOf(status.toString());

        // 에러 코드에 대한 상태 정보
        HttpStatus httpStatus = HttpStatus.valueOf(Integer.valueOf(status.toString()));

        LOGGER.info("statusCode: {}, httpStatus: {}", statusCode, httpStatus);

        model.addAttribute("status", httpStatus);
        model.addAttribute("statusCode", statusCode);
        model.addAttribute("msg", httpStatus.getReasonPhrase());
        model.addAttribute("timestamp", new Date());

        return ERROR_PAGE_PATH;
    }

}
