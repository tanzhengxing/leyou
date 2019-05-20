package com.leyou.search.client;

import com.leyou.item.api.CategoryApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author tan
 * @date 2019/5/20 20:00
 */
@FeignClient(value = "item-service")
public interface CategoryClient extends CategoryApi {

}
