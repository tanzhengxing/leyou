package com.leyou.search.client;

import com.leyou.LySearchApplication;
import com.leyou.item.pojo.Category;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = LySearchApplication.class)
public class CategoryClientTest {

  @Autowired
  private CategoryClient categoryClient;

  @Test
  public void testCategory() {
    List<Category> categories = categoryClient.queryCategoryByIds(Arrays.asList(1L, 2L, 3L));
    List<String> list = categories.stream().map(category -> {
      return category.getName();
    }).collect(Collectors.toList());
    for (String s : list) {
      System.out.println("categoryName: " + s);
    }

  }


}
