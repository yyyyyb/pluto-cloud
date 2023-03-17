package com.pluto.service;

import com.pluto.vo.FissureVO;
import com.pluto.vo.SortieVO;

import java.util.List;

public interface WarFrameService {

    /**
     * 根据类型获取warframe官方裂缝信息
     * @param type
     * @return
     */
    List<FissureVO> getFissures(String type);

    /**
     * 获取warframe官方突击信息
     * @return
     */
    SortieVO getSortie();
}
