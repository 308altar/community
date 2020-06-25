package com.csust.community.dto;

import lombok.Data;

/**
 * @Author XieHaiBin
 * @Date 2020/6/25 13:44
 * @Version 1.0
 */
@Data
public class HotTagDTO implements Comparable {
    private String name;
    private Integer priority;

    @Override
    public int compareTo(Object o) {
        return this.getPriority() - ((HotTagDTO) o).getPriority();
    }
}
