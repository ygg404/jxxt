package cn.ux.jxxt.util;


import cn.ux.jxxt.vo.Pagination;

import java.util.List;

public class PaginationUtil {
    public static <T> Pagination<T> paginate(int page, int per_page, long totalNumber, List<T> data) {
        Pagination<T> pagination = new Pagination<T>();
        // set per_page
        pagination.setPer_page(per_page);
        // set total
        pagination.setTotal(totalNumber);
        // set pages
        Integer pages = (int) (Math.ceil((double) totalNumber / per_page));
        if (totalNumber > 0 && pages == 0) {
            pages = 1;
        }
        pagination.setPages(pages);
        // set page
        if (pages > 0 && page > pages) {
            page = pages;
        }
        pagination.setPage(page);
        // set data
        pagination.setData(data);
        // set next
        if (page < pages) {
            pagination.setHas_next(true);
        } else {
            pagination.setHas_next(false);
        }
        // set prev
        if (pages > 1 && page > 1) {
            pagination.setHas_prev(true);
        } else {
            pagination.setHas_prev(false);
        }
        return pagination;
    }
}
