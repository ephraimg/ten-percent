package com.ephraimglick.tenpercent.controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.ephraimglick.tenpercent.constants.Constants.Uri.API_V1;

@RequestMapping(value = API_V1, produces = MediaType.APPLICATION_JSON_VALUE)
public interface BaseApi {
}
