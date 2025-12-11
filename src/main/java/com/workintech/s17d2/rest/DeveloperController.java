package com.workintech.s17d2.rest;


import jakarta.annotation.PostConstruct;
import model.Developer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tax.Taxable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class DeveloperController {
    private Map<Integer, Developer> developers;
    private Taxable taxable;
    @PostConstruct
    public void initDevelopers()
    {
        this.developers = new HashMap<>();
    }


    public Map<Integer, Developer> getDevelopers() {
        return this.developers;
    }

    @Autowired
    public DeveloperController(@Qualifier("developerTax") Taxable developerTax)
    {
        this.taxable = developerTax;
    }


    @GetMapping("/developers")
    public List<Developer> getDevelopersValues()
    {
        List<Developer> valuesList = new ArrayList<>((this.getDevelopers()).values());
        return valuesList;
    }

    @GetMapping("/getDeveloper")
    public






}
