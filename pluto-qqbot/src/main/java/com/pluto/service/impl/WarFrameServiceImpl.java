package com.pluto.service.impl;

import com.pluto.R;
import com.pluto.api.WarFrameClient;
import com.pluto.service.WarFrameService;
import com.pluto.vo.warframe.FissureVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
public class WarFrameServiceImpl implements WarFrameService {
    @Resource
    private WarFrameClient warFrameClient;

    /**
     * 裂缝信息
     * @param queryType
     * @return
     */
    @Override
    public List<FissureVO> getFissure(String queryType) {
        R<List<FissureVO>> fissureInfo = warFrameClient.getFissure(queryType);
        if (fissureInfo.getCode() == 0) {
            List<FissureVO> data = fissureInfo.getData();
            return data;
        }
        return null;
    }

}
