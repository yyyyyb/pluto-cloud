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
import com.pluto.vo.warframe.*;
import com.pluto.warFrame.entity.*;
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

    @Resource
    private RedisUtil redisUtil;

    @Resource
    private SortieService sortieService;

    /**
     * 裂缝信息
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
     * 突击信息
     * @return
     */
    @Override
    public SortieVO getSortie(){
        //如果redis中已经存储，从redis中取
        if (redisUtil.get(CacheKeyDefinition.WARFRAME_SORTIE) == null) {
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
                    sortieService.save(sortieDTO);

                    BeanUtils.copyProperties(sortieDTO, result);
                    //数据放入redis中
                    redisUtil.set(CacheKeyDefinition.WARFRAME_SORTIE, result, DateUtil.getTodayRemainSecond());
                }
            } else {
                BeanUtils.copyProperties(sortieDTO, result);
                //数据放入redis中
                redisUtil.set(CacheKeyDefinition.WARFRAME_SORTIE, result, DateUtil.getTodayRemainSecond());
            }

            return result;
        } else {
            return (SortieVO) redisUtil.get(CacheKeyDefinition.WARFRAME_SORTIE);
        }
    }

    /**
     * 午夜电波信息
     * @return
     */
    @Override
    public List<NightWaveVO> getNightWave() {
        if (redisUtil.get(CacheKeyDefinition.WARFRAME_NIGHT_WAVE) == null) {
            String nightWaveStr = restTemplate.getForObject(WarFrameConstants.NIGHT_WAVE_URL, String.class);
            NightWave nightWave = getObjectFormat(nightWaveStr, NightWave.class);

            List<NightWaveVO> result = new ArrayList<>();
            if (nightWave != null && nightWave.getActive()) {
                List<ActiveChallenge> activeChallenges = nightWave.getActiveChallenges();
                activeChallenges.forEach(t-> {
                    NightWaveVO temp = new NightWaveVO();
                    BeanUtils.copyProperties(t, temp);
                    result.add(temp);
                });

                //数据放入redis中
                redisUtil.set(CacheKeyDefinition.WARFRAME_NIGHT_WAVE, result, DateUtil.getTodayRemainSecond());
            }

            return result;
        } else {
            return (List<NightWaveVO>) redisUtil.get(CacheKeyDefinition.WARFRAME_NIGHT_WAVE);
        }
    }

    /**
     * 获取仲裁信息
     * @return
     */
    @Override
    public ArbitrationVO getArbitration() {
        ArbitrationVO result = null;

        String arbitrationStr = restTemplate.getForObject(WarFrameConstants.ARBITRATION_URL, String.class);
        Arbitration arbitration = getObjectFormat(arbitrationStr, Arbitration.class);

        if (arbitration != null && arbitration.getExpired()) {
            result = new ArbitrationVO();
            BeanUtils.copyProperties(arbitration, result);
        }

        return result;
    }

    /**
     * 获取虚空商人信息
     * @return
     */
    @Override
    public VoidTraderVO getVoidTrader() {
        if (redisUtil.get(CacheKeyDefinition.WARFRAME_VOID_TRADER) == null) {
            String voidTraderStr = restTemplate.getForObject(WarFrameConstants.TRADER_URL, String.class);
            VoidTrader voidTrader = getObjectFormat(voidTraderStr, VoidTrader.class);

            VoidTraderVO result = null;
            if (voidTrader != null) {
                result = new VoidTraderVO();
                List<InventoryVO> inventoryVOS = new ArrayList<>();
                BeanUtils.copyProperties(voidTrader, result);
                voidTrader.getInventory().forEach(t-> {
                    InventoryVO vo = new InventoryVO();
                    BeanUtils.copyProperties(t, vo);
                    inventoryVOS.add(vo);
                });
                result.setInventory(inventoryVOS);

                //数据放入redis中
                redisUtil.set(CacheKeyDefinition.WARFRAME_VOID_TRADER, result, DateUtil.getTodayRemainSecond());
            }

            return result;
        } else {
            return (VoidTraderVO) redisUtil.get(CacheKeyDefinition.WARFRAME_VOID_TRADER);
        }
    }

    /**
     * 获取入侵信息
     * @return
     */
    @Override
    public InvasionVO getInvasion() {
        return null;
    }

    /**
     * 获取钢铁奖励信息
     * @return
     */
    @Override
    public SteelPathVO getSteelPath() {
        SteelPathVO result = null;

        String steelPathStr = restTemplate.getForObject(WarFrameConstants.STEEL_PATH_URL, String.class);
        SteelPath steelPath = getObjectFormat(steelPathStr, SteelPath.class);

        if (steelPath != null) {
            result = new SteelPathVO();
            SteelPathReWardVO steelPathReWardVO = new SteelPathReWardVO();
            BeanUtils.copyProperties(steelPath.getCurrentReward(), steelPathReWardVO);

            result.setCurrent(steelPathReWardVO);
            result.setActivation(steelPath.getActivation());
            result.setExpiry(steelPath.getExpiry());
            result.setRemaining(steelPath.getRemaining());

            int index = 0;
            for (int i = 0; i < steelPath.getRotation().size(); i++) {
                Reward reward = steelPath.getRotation().get(i);
                if (StringUtils.equals(reward.getName(), steelPath.getCurrentReward().getName())) {
                    index = i;
                }
            }

            steelPathReWardVO = new SteelPathReWardVO();
            BeanUtils.copyProperties(steelPath.getRotation().get(index + 1), steelPathReWardVO);
            result.setNext(steelPathReWardVO);

            List<SteelPathReWardVO> resident = new ArrayList<>();
            steelPath.getEvergreens().forEach(t -> {
                SteelPathReWardVO temp = new SteelPathReWardVO();
                BeanUtils.copyProperties(t, temp);
                resident.add(temp);
            });
            result.setResident(resident);
        }

        return result;
    }

    /**
     * 执行官任务
     */
    @Override
    public ArchonVO getArchonHunt() {
        if (redisUtil.get(CacheKeyDefinition.WARFRAME_ARCHON) == null) {
            String archonStr = restTemplate.getForObject(WarFrameConstants.ARCHON_HUNT_URL, String.class);
            Archon archon = getObjectFormat(archonStr, Archon.class);

            ArchonVO result = new ArchonVO();
            if (archon != null && archon.getActive()) {
                BeanUtils.copyProperties(archon, result);

                List<MissionVO> missions = new ArrayList<>();
                archon.getMissions().forEach(t -> {
                    MissionVO mission = new MissionVO();
                    BeanUtils.copyProperties(t, mission);
                    missions.add(mission);
                });
                result.setMissions(missions);

                redisUtil.set(CacheKeyDefinition.WARFRAME_ARCHON, result, DateUtil.getTodayRemainSecond());
                return result;
            }
        } else {
            return (ArchonVO) redisUtil.get(CacheKeyDefinition.WARFRAME_ARCHON);
        }
        return null;
    }

    /**
     * 获取平原信息
     */
    @Override
    public List<CycleVO> getCycle() {
        List<CycleVO> result = new ArrayList<>();
        CycleVO cycleVO;

        //地球
        String earthStr = restTemplate.getForObject(WarFrameConstants.EARTH_CYCLE_URL, String.class);
        Cycle earth = getObjectFormat(earthStr, Cycle.class);
        if (earth != null) {
            cycleVO = new CycleVO();
            BeanUtils.copyProperties(earth, cycleVO);
            result.add(cycleVO);
        }

        //希图斯
        String centusStr = restTemplate.getForObject(WarFrameConstants.CETUS_CYCLE_URL, String.class);
        Cycle centus = getObjectFormat(centusStr, Cycle.class);
        if (centus != null) {
            cycleVO = new CycleVO();
            BeanUtils.copyProperties(centus, cycleVO);
            result.add(cycleVO);
        }

        //福尔图娜
        String vallisStr = restTemplate.getForObject(WarFrameConstants.VALLIS_CYCLE_URL, String.class);
        Cycle valli = getObjectFormat(vallisStr, Cycle.class);
        if (valli != null) {
            cycleVO = new CycleVO();
            BeanUtils.copyProperties(valli, cycleVO);
            result.add(cycleVO);
        }

        //火卫2
        String cambionStr = restTemplate.getForObject(WarFrameConstants.CAMBION_CYCLE_URL, String.class);
        Cycle cambion = getObjectFormat(cambionStr, Cycle.class);
        if (cambion != null) {
            cycleVO = new CycleVO();
            BeanUtils.copyProperties(cambion, cycleVO);
            result.add(cycleVO);
        }

        //扎里曼
        String zarimanStr = restTemplate.getForObject(WarFrameConstants.ZARIMAN_CYCLE_URL, String.class);
        Cycle zariman = getObjectFormat(zarimanStr, Cycle.class);
        if (zariman != null) {
            cycleVO = new CycleVO();
            BeanUtils.copyProperties(zariman, cycleVO);
            result.add(cycleVO);
        }

        return result;
    }

    /**
     * 获取赏金任务信息
     */
    @Override
    public void getSyndicateMissions() {

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
            e.printStackTrace();
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
            e.printStackTrace();
            log.error("json转换失败, json={}", jsonValue);
        }

        return null;
    }
}
