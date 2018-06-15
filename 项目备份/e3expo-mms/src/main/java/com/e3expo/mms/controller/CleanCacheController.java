package com.e3expo.mms.controller;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CleanCacheController extends ParentController {
	//写一个清除缓存的方法，方便缓存的测试

//	@GetMapping("/cleancache")
//	@CacheEvict(value={"User", "UserId", "UserPage"}, allEntries=true,beforeInvocation=true)
//	public String cleanCache() throws Exception {
//		System.out.println("done");
//		return "clean-cache";
//
//	}
}
