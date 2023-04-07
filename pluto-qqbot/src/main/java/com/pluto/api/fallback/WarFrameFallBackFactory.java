package com.pluto.api.fallback;

import com.pluto.R;
import com.pluto.api.WarFrameClient;
import com.pluto.vo.warframe.FissureVO;
import org.springframework.cloud.openfeign.FallbackFactory;

import java.util.List;

public class WarFrameFallBackFactory implements FallbackFactory<WarFrameClient> {
    @Override
    public WarFrameClient create(Throwable cause) {
        return new WarFrameClient() {
            @Override
            public R<List<FissureVO>> getFissure(String queryType) {
                return R.timeout();
            }
        };
    }
}
