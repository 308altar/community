package com.csust.community.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author XieHaiBin
 * @Date 2020/6/17 9:52
 * @Version 1.0
 */
@Data
public class PageinationDTO {//index页面所承载的元素
    private List<QuestionDTO> questions;
    private boolean showPrevious; //是否显示上一页 '<'
    private boolean showFirstPage; //是否显示第一页 '<<'
    private boolean showNext; //是否显示下一页 '>'
    private boolean showEndPage; //是否显示最后一页 '>>'
    private List<Integer> pages = new ArrayList<>(); //当前显示的页码数组
    private Integer currentPage; // 当前页面
    private Integer totalPage;

    public void setPageination(Integer totalPage, Integer page) {
        this.currentPage = page;
        this.totalPage = totalPage;

        pages.add(page);
        for (int i = 1; i <= 3; i++) {
            if (page - i > 0) {
                pages.add(0, page - i);
            }
            if (page + i <= totalPage) {
                pages.add(page + i);
            }
        }

        //当为第一页时，'<'和'<<'都不展示
        if (page == 1) {
            showPrevious = false;
            showFirstPage = false;
        } else {
            showPrevious = true;
            showFirstPage = true;
        }
        //当为最后一页时，'>'和'>>'都不展示
        if (page == totalPage) {
            showNext = false;
            showEndPage = false;
        } else {
            showNext = true;
            showEndPage = true;
        }
        //页码数组包含第一页时，不展示'<<',展示'<'
        if (pages.contains(1)) {
            showFirstPage = false;
        } else {
            showFirstPage = true;
        }
        //页码数组包含最后一页时，不展示'>>',展示'>'
        if (pages.contains(totalPage)) {
            showEndPage = false;
        } else {
            showEndPage = true;
        }
    }
}
