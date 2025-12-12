package com.workintech.s17d2.rest;


import jakarta.annotation.PostConstruct;
import com.workintech.s17d2.model.Developer;
import com.workintech.s17d2.model.Experience;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import com.workintech.s17d2.tax.Taxable;

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

//        this.developers.put(1, new Developer(1, "Initial Developer", 5000.0, Experience.JUNIOR));
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

    @GetMapping("/developers/{id}")
    public Developer getDeveloperById(@PathVariable Integer id)
    {
        return this.getDevelopers().get(id);
    }

    @PostMapping("/developers")
    public void putDevelopers(@RequestBody Integer id, @RequestBody String name, @RequestBody double salary, @RequestBody String experience)
    {
        Experience experience1 = null;
        double salary1 = 0;
        switch (experience){
            case "JUNIOR":
                experience1 = Experience.JUNIOR;
                salary1 = salary - taxable.getSimpleTaxRate();
            case "MID":
                experience1 = Experience.MID;
                salary1 = salary - taxable.getMiddleTaxRate();
            case "SENIOR":
                experience1 = Experience.SENIOR;
                salary1 = salary - taxable.getUpperTaxRate();
            default:
                break;
        }
        this.developers.put(id, new Developer(id, name, salary1, experience1));

    }


    @PutMapping("/developers/{id}")
    public Developer update(@PathVariable Integer id, @RequestBody Developer developer)
    {
        developer.setId(id);
        this.developers.put(id, developer);
        return developer;
    }

    @DeleteMapping("/developers/{id}")
    public Developer delete(@PathVariable Integer id)
    {
        Developer dev = this.developers.remove(id);
        return dev;
    }





}
