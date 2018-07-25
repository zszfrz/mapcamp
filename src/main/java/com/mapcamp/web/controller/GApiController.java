package com.mapcamp.web.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.mapcamp.api.Select;
import com.mapcamp.domain.entity.Post;
import com.mapcamp.domain.service.PostService;
import com.mapcamp.domain.service.StoreService;
import com.mapcamp.web.form.PostForm;

@Controller
public class GApiController {

	private Long postId;

	@Autowired
	private StoreService storeService;

	@Autowired
	private PostService postService;
	
	@Autowired
	private PostController postController;

	@PostMapping("/new/selectShop")
	public ModelAndView gnavinew(ModelAndView mav, @ModelAttribute("selectform") Select select) {
		String acckey = "58a9cc097cca953276da1b8389ed535a";
		String format = "json";
		String gnaviRestUri = "https://api.gnavi.co.jp/RestSearchAPI/20150630/";
		String prmFormat = "?format=" + format;
		String prmKeyid = "&keyid=" + acckey;
		//		String prmLat = "&latitude=" + lat;
		//		String prmLon = "&longitude=" + lon;
		String prmAddress = "&address=" + select.getAddress();
		String prmName = "&name=" + select.getShopname();

		// URI組み立て
		String uri = gnaviRestUri + prmFormat + prmKeyid + prmName + prmAddress;

		storeService.getNodeList(uri);
		mav.addObject("html", "new");
		mav.addObject("response", storeService.getResponse());
		mav.setViewName("posts/select");
		return mav;
	}

	public Long setPostId(Long id) {
		if(this.postId == null) {
			this.postId = Long.parseLong("1");
		}
		if(id != null) {
			this.postId = id;
		}
		return this.postId;
	}

	@PostMapping("/edit/{id}/selectShop")
	public ModelAndView gnaviedit(@PathVariable("id") Long id, ModelAndView mav, @ModelAttribute("selectform") Select select) {
		setPostId(id);

		String acckey = "58a9cc097cca953276da1b8389ed535a";
		String format = "json";
		String gnaviRestUri = "https://api.gnavi.co.jp/RestSearchAPI/20150630/";
		String prmFormat = "?format=" + format;
		String prmKeyid = "&keyid=" + acckey;
		//		String prmLat = "&latitude=" + lat;
		//		String prmLon = "&longitude=" + lon;
		String prmAddress = "&address=" + select.getAddress();
		String prmName = "&name=" + select.getShopname();

		// URI組み立て
		String uri = gnaviRestUri + prmFormat + prmKeyid + prmName + prmAddress;

		storeService.getNodeList(uri);
		mav.addObject("html", "edit");
		mav.addObject("response", storeService.getResponse());
		mav.setViewName("posts/select");
		return mav;
	}

	@GetMapping("/new/select/{id}")
	public ModelAndView newConfirm(ModelAndView mav, @PathVariable("id") String id, PostForm form) {	
		postController.setShop(id);
		mav.addObject("select", storeService.getOneResponse(id));
		Select select = new Select();
		mav.addObject("selectform", select);
		mav.setViewName("posts/new");
		return mav;
	}

	@GetMapping("/edit/select/{id}")
	public ModelAndView editConfirm(ModelAndView mav, @PathVariable("id") String id, PostForm form) {	
		Map<String, String> map = postController.setShop(id);
		System.out.println("sssssssssssssssss" + map.get("name"));
		mav.addObject("select", storeService.getOneResponse(id));
		Select select = new Select();
		mav.addObject("selectform", select);
		Post post = postService.findOne(postId);
		mav.addObject("post", post);
		mav.setViewName("posts/edit");
		return mav;
	}
}
