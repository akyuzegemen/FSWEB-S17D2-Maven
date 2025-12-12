package com.workintech.s17d2.rest;


import com.workintech.s17d2.model.*;
import jakarta.annotation.PostConstruct;
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

    }


    public Map<Integer, Developer> getDevelopers() {
        return this.developers;
    }

    @Autowired
    public DeveloperController(Taxable taxable)
    {
        this.taxable = taxable;
    }


    @GetMapping("/developers")
    public List<Developer> getAllDevelopers(){
//        List<Developer> valuesList = new ArrayList<>((this.getDevelopers()).values());
        return this.getDevelopers().values().stream().toList();
    }

    @GetMapping("/developers/{id}")
    public Developer getDeveloperById(@PathVariable Integer id)
    {
        return this.getDevelopers().get(id);
    }

    @PostMapping("/developers")
    public Developer createDeveloper(@RequestBody Developer developer)
    {
        Developer newDeveloper = null;
        double vergi;
        double salary = developer.getSalary();
        switch (developer.getExperience())
        {
            case JUNIOR:
                vergi = developer.getSalary() * taxable.getSimpleTaxRate()/100;
                newDeveloper = new JuniorDeveloper(developer.getId(), developer.getName(), developer.getSalary() - vergi);
            case MID:
                vergi = developer.getSalary() * taxable.getMiddleTaxRate()/100;
                newDeveloper = new MidDeveloper(developer.getId(), developer.getName(), developer.getSalary()- vergi);

            case SENIOR:
                vergi = developer.getSalary() * taxable.getUpperTaxRate()/100;
                newDeveloper = new SeniorDeveloper(developer.getId(), developer.getName(), developer.getSalary() - vergi);

        }
        this.developers.put(newDeveloper.getId(), newDeveloper);
        return newDeveloper;
    }


    @PutMapping("/developers/{id}")
    public Developer update(@PathVariable Integer id, @RequestBody Developer developer)
    {
        developer.setId(id);

        Developer newDeveloper = null;
        double vergi;
        double salary = developer.getSalary();
        switch (developer.getExperience())
        {
            case JUNIOR:
                vergi = developer.getSalary() * taxable.getSimpleTaxRate()/100;
                newDeveloper = new JuniorDeveloper(developer.getId(), developer.getName(), developer.getSalary() - vergi);
            case MID:
                vergi = developer.getSalary() * taxable.getMiddleTaxRate()/100;
                newDeveloper = new MidDeveloper(developer.getId(), developer.getName(), developer.getSalary()- vergi);

            case SENIOR:
                vergi = developer.getSalary() * taxable.getUpperTaxRate()/100;
                newDeveloper = new SeniorDeveloper(developer.getId(), developer.getName(), developer.getSalary() - vergi);

        }
        this.developers.put(newDeveloper.getId(), newDeveloper);
        return newDeveloper;
    }

    @DeleteMapping("/developers/{id}")
    public Developer delete(@PathVariable Integer id)
    {
        Developer dev = this.developers.remove(id);
        return dev;
    }





}
