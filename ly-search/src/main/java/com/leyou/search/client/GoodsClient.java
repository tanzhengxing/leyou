package com.leyou.search.client;

import com.leyou.item.api.GoodsApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author tan
 * @date 2019/5/20 20:04
 */
@FeignClient(value = "item-service")
public interface GoodsClient extends GoodsApi {
}
