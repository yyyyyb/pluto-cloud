package com.pluto.service;

import com.pluto.vo.warframe.FissureVO;

import java.util.List;

public interface WarFrameService {

    /**
     * 查询裂缝
     * @param queryType
     * @return
     */
    List<FissureVO> getFissure(String queryType);
}
