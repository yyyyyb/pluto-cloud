package com.pluto.service;

import com.pluto.vo.warframe.*;

import java.util.List;

public interface WarFrameService {

    /**
     * 裂缝信息
     * @param type
     * @return
     */
    List<FissureVO> getFissures(String type);

    /**
     * 突击信息
     * @return
     */
    SortieVO getSortie();

    /**
     * 午夜电波信息
     * @return
     */
    List<NightWaveVO> getNightWave();

    /**
     * 仲裁信息
     * @return
     */
    ArbitrationVO getArbitration();

    /**
     * 虚空商人信息
     * @return
     */
    VoidTraderVO getVoidTrader();

    /**
     * 入侵信息
     * @return
     */
    InvasionVO getInvasion();

    /**
     * 钢铁奖励信息
     * @return
     */
    SteelPathVO getSteelPath();

    /**
     * 执行官任务
     */
    ArchonVO getArchonHunt();

    /**
     * 平原信息
     */
    List<CycleVO> getCycle();

    /**
     * 赏金信息
     */
    void getSyndicateMissions();
}
