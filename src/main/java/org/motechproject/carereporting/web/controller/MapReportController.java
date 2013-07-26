package org.motechproject.carereporting.web.controller;

import org.motechproject.carereporting.domain.AreaEntity;
import org.motechproject.carereporting.service.IndicatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Date;
import java.util.Map;


@RequestMapping("api/map-report")
@Controller
public class MapReportController extends BaseController {

    @Autowired
    private IndicatorService indicatorService;

    @RequestMapping(method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Map<AreaEntity, Integer> getMapReportData(@RequestParam Integer indicatorId,
                                                 @RequestParam(required = false) Integer areaId,
                                                 @RequestParam Date startDate, @RequestParam Date endDate) {
        return indicatorService.getIndicatorTrendForChildAreas(indicatorId, areaId, startDate, endDate);
    }
}
