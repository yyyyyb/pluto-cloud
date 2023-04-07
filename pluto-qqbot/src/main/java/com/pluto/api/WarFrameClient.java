package com.pluto.api;

import com.pluto.R;
import com.pluto.api.fallback.WarFrameFallBackFactory;
import com.pluto.vo.warframe.FissureVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "pluto-warframe", path = "/api/warFrame", fallbackFactory = WarFrameFallBackFactory.class)
public interface WarFrameClient {

    /**
     * 获取裂缝信息
     * @param queryType (1：普通裂缝；2：钢铁裂缝；3：九重天裂缝；4：安魂裂缝)
     * @return
     */
    @GetMapping("fissure")
    R<List<FissureVO>> getFissure(@RequestParam(value = "queryType") String queryType);
}
