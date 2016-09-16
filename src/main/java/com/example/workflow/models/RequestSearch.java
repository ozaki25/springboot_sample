package com.example.workflow;

import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;

public class RequestSearch {
    public int page;
    public int year;
    public String title;
    public String team;
    public String name;
    public Long statusId;
    public Long categoryId;
    public Long divisionId;

    public RequestSearch() { };

    public RequestSearch(MultiValueMap<String, String> query) {
        String pageString = query.toSingleValueMap().get("page");
        String yearString = query.toSingleValueMap().get("year");
        String title = query.toSingleValueMap().get("title");
        String team = query.toSingleValueMap().get("team");
        String name = query.toSingleValueMap().get("name");
        String statusIdString = query.toSingleValueMap().get("statusId");
        String categoryIdString = query.toSingleValueMap().get("categoryId");
        String divisionIdString = query.toSingleValueMap().get("divisionId");
        try {
            this.page = StringUtils.hasLength(pageString) ? Integer.parseInt(pageString) : 0;
        } catch(NumberFormatException e) {
            this.page = 0;
        }
        try {
            this.year = StringUtils.hasLength(yearString) ? Integer.parseInt(yearString) : 0;
        } catch(NumberFormatException e) {
            this.year = 0;
        }
        this.title = StringUtils.hasLength(title) ? title : null;
        this.team = StringUtils.hasLength(team) ? team : null;
        this.name = StringUtils.hasLength(name) ? name : null;
        try {
            this.statusId = Long.parseLong(statusIdString);
        } catch(NumberFormatException e) {
            this.statusId = null;
        }
        try {
            this.categoryId = Long.parseLong(categoryIdString);
        } catch(NumberFormatException e) {
            this.categoryId = null;
        }
        try {
            this.divisionId = Long.parseLong(divisionIdString);
        } catch(NumberFormatException e) {
            this.divisionId = null;
        }
    }
}
