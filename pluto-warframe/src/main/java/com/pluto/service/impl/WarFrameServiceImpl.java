package com.pluto.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pluto.service.WarFrameService;
import com.pluto.util.ChineseUtil;
import com.pluto.vo.FissureVO;
import com.pluto.warFrame.entity.Fissure;
import com.pluto.warFrame.constants.WarFrameConstants;
import com.pluto.warFrame.enumeration.FissureType;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class WarFrameServiceImpl implements WarFrameService {
    @Resource
    private RestTemplate restTemplate;

    /**
     * 根据类型获取warframe官方裂缝信息
     * @param type
     * @return
     */
    @Override
    public List<FissureVO> getFissures(String type){
        List<FissureVO> result = new ArrayList<>();
        try {
            String fissureStr = restTemplate.getForObject(WarFrameConstants.FISSURE_URL, String.class);
            ObjectMapper objectMapper = new ObjectMapper();
            TypeReference<List<Fissure>> typeRef = new TypeReference<>() {};
            List<Fissure> fissures = objectMapper.readValue(ChineseUtil.convertToSimplifiedChinese(fissureStr), typeRef);
            fissures.forEach(t -> {
                FissureVO fissureVO = FissureVO.builder().build();
                if (StringUtils.equals(FissureType.NORMAL.getCode(), type)) {
                    if (!t.getIsStorm() && !t.getIsHard() && !"5".equals(t.getTierNum())) {
                        BeanUtils.copyProperties(t, fissureVO);
                        result.add(fissureVO);
                    }
                } else if (StringUtils.equals(FissureType.HARD.getCode(), type)) {
                    if (t.getIsHard()) {
                        BeanUtils.copyProperties(t, fissureVO);
                        result.add(fissureVO);
                    }
                } else if (StringUtils.equals(FissureType.STORM.getCode(), type)) {
                    if (t.getIsStorm()) {
                        BeanUtils.copyProperties(t, fissureVO);
                        result.add(fissureVO);
                    }
                } else if (StringUtils.equals(FissureType.AN_HUN.getCode(), type)) {
                    if ("5".equals(t.getTierNum())) {
                        BeanUtils.copyProperties(t, fissureVO);
                        result.add(fissureVO);
                    }
                } else {
                }
            });
        } catch (Exception e) {
            log.error("查询裂缝信息出错" + System.currentTimeMillis());
        }

        return result;
    }
}
