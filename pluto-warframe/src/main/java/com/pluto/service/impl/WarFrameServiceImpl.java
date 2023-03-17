package com.pluto.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pluto.cache.CacheKeyDefinition;
import com.pluto.entity.SortieDTO;
import com.pluto.service.SortieService;
import com.pluto.service.WarFrameService;
import com.pluto.util.ChineseUtil;
import com.pluto.util.DateUtil;
import com.pluto.util.RedisUtil;
import com.pluto.vo.FissureVO;
import com.pluto.vo.SortieVO;
import com.pluto.warFrame.entity.Fissure;
import com.pluto.warFrame.constants.WarFrameConstants;
import com.pluto.warFrame.entity.Sortie;
import com.pluto.warFrame.entity.Variant;
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

    @Resource
    private RedisUtil redisUtil;

    @Resource
    private SortieService sortieService;

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

            TypeReference<List<Fissure>> typeRef = new TypeReference<>() {};
            List<Fissure> fissures = getListFormat(fissureStr, typeRef);
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

    /**
     * 获取突击信息
     * @return
     */
    @Override
    public SortieVO getSortie(){
        //如果redis中已经存储，从redis中取
        if (redisUtil.get(CacheKeyDefinition.WARFRAME_SORTIE) == null
                || StringUtils.isBlank((String)redisUtil.get(CacheKeyDefinition.WARFRAME_SORTIE))) {
            SortieVO result = new SortieVO();

            //查询数据库获取当日有没有
            LambdaQueryWrapper<SortieDTO> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(SortieDTO::getSortieDate, DateUtil.getNowDateY());
            SortieDTO sortieDTO = sortieService.getOne(queryWrapper);

            //如果未获取到，重新去查
            if (sortieDTO == null) {
                String sortieStr = restTemplate.getForObject(WarFrameConstants.SORTIE_URL, String.class);
                Sortie sortie = getObjectFormat(sortieStr, Sortie.class);

                if (sortie != null && sortie.getActive()) {
                    sortieDTO = SortieDTO.builder()
                            .boss(sortie.getBoss())
                            .faction(sortie.getFaction())
                            .sortieDate(DateUtil.getNowDateY())
                            .build();

                    int size = sortie.getVariants().size();
                    for (int i = 0; i < size; i++) {
                        Variant temp = sortie.getVariants().get(i);
                        if (i == 0) {
                            sortieDTO.setSortie1Work(temp.getMissionType());
                            sortieDTO.setSortie1Address(temp.getNode());
                            sortieDTO.setSortie1Desc(temp.getModifier());
                        }
                        if (i == 1) {
                            sortieDTO.setSortie2Work(temp.getMissionType());
                            sortieDTO.setSortie2Address(temp.getNode());
                            sortieDTO.setSortie2Desc(temp.getModifier());
                        }
                        if (i == 2) {
                            sortieDTO.setSortie3Work(temp.getMissionType());
                            sortieDTO.setSortie3Address(temp.getNode());
                            sortieDTO.setSortie3Desc(temp.getModifier());
                        }
                    }
                }
                sortieService.save(sortieDTO);

                BeanUtils.copyProperties(sortieDTO, result);
            } else {
                BeanUtils.copyProperties(sortieDTO, result);
            }
            //数据放入redis中
            redisUtil.set(CacheKeyDefinition.WARFRAME_SORTIE, result, DateUtil.getTodayRemainSecond());

            return result;
        } else {
            return (SortieVO) redisUtil.get(CacheKeyDefinition.WARFRAME_SORTIE);
        }
    }

    /**
     * json
     * @param jsonValue
     * @param t
     * @param <T>
     * @return
     */
    private <T> T getObjectFormat(String jsonValue, Class<T> t) {
        try {
            jsonValue = ChineseUtil.convertToSimplifiedChinese(jsonValue);
            T temp = new ObjectMapper().readValue(jsonValue, t);
            return temp;
        } catch (Exception e) {
            log.error("json转换失败, json={}", jsonValue);
        }

        return null;
    }

    private <T> T getListFormat(String jsonValue, TypeReference<T> typeReference) {
        try {
            jsonValue = ChineseUtil.convertToSimplifiedChinese(jsonValue);
            T t = new ObjectMapper().readValue(jsonValue, typeReference);
            return t;
        } catch (Exception e) {
            log.error("json转换失败, json={}", jsonValue);
        }

        return null;
    }
}
