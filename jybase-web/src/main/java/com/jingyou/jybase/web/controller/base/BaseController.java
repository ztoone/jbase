package com.jingyou.jybase.web.controller.base;

import org.springframework.stereotype.Controller;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2016/5/12.
 */
@Controller
@RequestMapping(value = "/b")
public class BaseController {
    @RequestMapping("/r/**")
    public String redirect(HttpServletRequest request) {
        String url = extractPathFromPattern(request);
        return "redirect:http://"+url;
    }

    private static String extractPathFromPattern(
            final HttpServletRequest request) {
        String path = (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
        String bestMatchPattern = (String) request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE);
        return new AntPathMatcher().extractPathWithinPattern(bestMatchPattern, path);
    }
}
