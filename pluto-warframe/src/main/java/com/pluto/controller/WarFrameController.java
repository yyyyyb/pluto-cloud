package com.pluto.controller;

import com.pluto.R;
import com.pluto.service.WarFrameService;
import com.pluto.vo.FissureVO;
import com.pluto.warFrame.entity.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 获取warFrame官方的一些信息
 */
@RestController
@RequestMapping("api/warFrame")
public class WarFrameController {
    @Resource
    private WarFrameService warFrameService;

    /**
     * 获取裂缝信息
     * @param queryType (1：普通裂缝；2：钢铁裂缝；3：九重天裂缝；4：安魂裂缝)
     * @return
     */
    @GetMapping("fissure")
    public R<List<FissureVO>> getFissure(@RequestParam String queryType) {
        if (StringUtils.isBlank(queryType)) {
            return R.fail("请输入裂缝查询类型(01：普通裂缝；02：钢铁裂缝；03：九重天裂缝；04：安魂裂缝)");
        }

        try {
            Long.parseLong(queryType);
        } catch (Exception e) {
            return R.fail("查询类型参数不符合(01：普通裂缝；02：钢铁裂缝；03：九重天裂缝；04：安魂裂缝)");
        }
        return R.success(warFrameService.getFissures(queryType));
    }

    /**
     * 获取突击信息
     * @return
     */
    @GetMapping("sortie")
    public R<Sortie> getSortie() {
        return R.success(null);
    }

    /**
     * 获取午夜电波信息
     * @return
     */
    @GetMapping("nightWave")
    public R<List<NightWave>> getNightWave() {
        return R.success(null);
    }

    /**
     * 获取仲裁信息
     * @return
     */
    @GetMapping("arbitration")
    public R<Arbitration> getArbitration() {
        return R.success(null);
    }

    /**
     * 获取虚空商人信息
     * @return
     */
    @GetMapping("voidTrader")
    public R<VoidTrader> getVoidTrader() {
        return R.success(null);
    }

    /**
     * 获取入侵信息
     * @return
     */
    @GetMapping("invasion")
    public R<List<Invasion>> getInvasion() {
        return R.success(null);
    }


    /**
     * 获取钢铁奖励信息
     * @return
     */
    @GetMapping("steelPath")
    public R<List<SteelPath>> getSteelPath() {
        return R.success(null);
    }
}
