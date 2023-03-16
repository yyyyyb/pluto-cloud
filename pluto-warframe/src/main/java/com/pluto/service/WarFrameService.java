package com.pluto.service;

import com.pluto.vo.FissureVO;

import java.util.List;

public interface WarFrameService {

    /**
     * 根据类型获取warframe官方裂缝信息
     * @param type
     * @return
     */
    List<FissureVO> getFissures(String type);
}
