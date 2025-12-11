package com.workintech.s17d2.rest;


import model.Developer;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class DeveloperController {
    private Map<Integer, Developer> developers;


}
