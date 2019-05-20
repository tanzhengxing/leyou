package com.leyou.search.client;

import com.leyou.item.api.BrandApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author tan
 * @date 2019/5/20 20:03
 */
@FeignClient(value = "item-service")
public interface BrandClient extends BrandApi {
}
