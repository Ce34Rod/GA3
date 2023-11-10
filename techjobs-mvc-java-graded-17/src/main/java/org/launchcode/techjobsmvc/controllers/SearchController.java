package org.launchcode.techjobsmvc.controllers;

import org.launchcode.techjobsmvc.models.Job;
import org.launchcode.techjobsmvc.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.launchcode.techjobsmvc.controllers.ListController.columnChoices;


/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

//    static HashMap<String, Object> searchTypes = new HashMap<>();
//
//    public SearchController() {
//        searchTypes.put("employer", JobData.getAllEmployers());
//        searchTypes.put("location", JobData.getAllLocations());
//        searchTypes.put("positionType", JobData.getAllPositionTypes());
//        searchTypes.put("coreCompetency", JobData.getAllCoreCompetency());
//    }

    @GetMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", columnChoices);
        return "search";
    }
//@GetMapping("results")
//public String processSearch(Model model){
//    model.addAttribute("columns", columnChoices);
//    model.addAttribute("searchTerm");
//    model.addAttribute("searchType", columnChoices);
//        return "search";
//}

@PostMapping("results")
public String displaySearchResults(Model model, @RequestParam String searchType, @RequestParam(required = true) String searchTerm) {
//    ArrayList<Job> jobs;
//    model.addAttribute("columns", columnChoices);
//    model.addAttribute("searchType", searchTypes);
    List<Job> jobs;

    if (searchTerm.equals("all") || searchTerm.isEmpty()){
      //  jobs = JobData.findByValue(searchTerm);
      //  model.addAttribute("title", "Jobs With All" + ": " + searchTerm);
        jobs = JobData.findAll();
    } else {
        jobs = JobData.findByColumnAndValue(searchType,searchTerm);

    }
    model.addAttribute("columns", columnChoices);
    model.addAttribute("jobs", jobs);


    return "search";
}

    // TODO #3 - Create a handler to process a search request and render the updated search view.

}

