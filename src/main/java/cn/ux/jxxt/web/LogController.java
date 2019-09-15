package cn.ux.jxxt.web;

import cn.ux.jxxt.dto.LogDTO;
import cn.ux.jxxt.service.LogService;
import cn.ux.jxxt.util.TextUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.xml.crypto.Data;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class LogController {
    @Autowired
    private LogService logService;

    @GetMapping(value = "/log/", params = {"page", "rowsPerPage", "sortBy", "descending", "search", "startDate", "endDate"})
    public ResponseEntity<?> getLogByPagination(@RequestParam("page") int page,
                                                @RequestParam("rowsPerPage") int per_page,
                                                @RequestParam("sortBy") String sortBy,
                                                @RequestParam("descending") boolean descending,
                                                @RequestParam("search") String search,
                                                @RequestParam("startDate") String startDate,
                                                @RequestParam("endDate") String endDate) {
        String start = TextUtils.VueDateToString(startDate);
        String end = TextUtils.VueDateToString(endDate);
        LogDTO returnDTO = logService.getLogByPagination(page, per_page, sortBy, descending, search, start, end);
        return ResponseEntity.ok(returnDTO.getLogPagination());
    }
}
