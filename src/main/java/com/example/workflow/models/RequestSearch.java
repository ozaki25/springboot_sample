package com.example.workflow;

import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;

public class RequestSearch {
    public int page;
    public String reqId;
    public String title;
    public Long applicantId;
    public Long authorizerId;
    public Long statusId;
    public Long categoryId;
    public Long divisionId;

    public RequestSearch() { };

    public RequestSearch(MultiValueMap<String, String> query) {
        String pageString = query.toSingleValueMap().get("page");
        String reqId = query.toSingleValueMap().get("reqId");
        String title = query.toSingleValueMap().get("title");
        String applicantIdString = query.toSingleValueMap().get("applicantId");
        String authorizerIdString = query.toSingleValueMap().get("authorizerId");
        String statusIdString = query.toSingleValueMap().get("statusId");
        String categoryIdString = query.toSingleValueMap().get("categoryId");
        String divisionIdString = query.toSingleValueMap().get("divisionId");

        try {
            this.page = StringUtils.hasLength(pageString) ? Integer.parseInt(pageString) : 0;
        } catch(NumberFormatException e) {
            this.page = 0;
        }
        this.reqId = StringUtils.hasLength(reqId) ? reqId : null;
        this.title = StringUtils.hasLength(title) ? title : null;
        try {
            this.applicantId = Long.parseLong(applicantIdString);
        } catch(NumberFormatException e) {
            this.applicantId = null;
        }
        try {
            this.authorizerId = Long.parseLong(authorizerIdString);
        } catch(NumberFormatException e) {
            this.authorizerId = null;
        }
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
