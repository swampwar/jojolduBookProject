package wind.yang.jojolduBook.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import wind.yang.jojolduBook.config.auth.LoginUser;
import wind.yang.jojolduBook.config.auth.dto.SessionUser;
import wind.yang.jojolduBook.service.posts.PostsService;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {
    private final PostsService postsService;
    private final HttpSession httpSession;

    @GetMapping("/")
    public String index(@LoginUser SessionUser user, Model model){
        model.addAttribute("posts", postsService.findAllDesc());
        if(user != null) model.addAttribute("userName", user.getName());
        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave(){
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model){
        model.addAttribute("post", postsService.findById(id));
        return "posts-update";
    }
}
