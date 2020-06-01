package com.modzo.ors.web.radio.stations;

import com.modzo.ors.web.components.common.model.RadioStationModel;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.HttpHeaders.CONTENT_DISPOSITION;

@Controller
public class RadioStationDownloadController {

    private final RadioStationService radioStationService;

    private final RadioStationStreamService stationStreamService;

    public RadioStationDownloadController(RadioStationService radioStationService,
                                          RadioStationStreamService stationStreamService) {
        this.radioStationService = radioStationService;
        this.stationStreamService = stationStreamService;
    }

    @GetMapping(value = "/radio-stations/{radioStationSeoTitle}/{id}/streams/{streamId}/pls", produces = "audio/x-scpls")
    public ModelAndView radioStationAsPls(
            @PathVariable("radioStationSeoTitle") String radioStationSeoTitle,
            @PathVariable("id") Long id,
            @PathVariable("streamId") Long streamId,
            HttpServletResponse response) {
        RadioStationModel radioStation = radioStationService.retrieve(id);

        if (!StringUtils.pathEquals(radioStationSeoTitle, radioStation.getSeoTitle())) {
            throw new IllegalArgumentException("Radio station not found");
        }

        response.setContentType("audio/x-scpls");

        String contentDispositionValue = String.format(
                "attachment; filename=\"%s-%s.pls\"",
                radioStation.getSeoTitle(),
                streamId
        );
        response.setHeader(CONTENT_DISPOSITION, contentDispositionValue);

        Map<String, Object> items = new HashMap<>();
        items.put("radioStation", radioStation);
        items.put("radioStationStream", stationStreamService.retrieve(id, streamId));
        return new ModelAndView("radio-station/download/pls", items);
    }
}
