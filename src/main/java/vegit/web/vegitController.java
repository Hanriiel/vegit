package vegit.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class vegitController {

    @RequestMapping("/")
    public String index(){
        return "index";
    }


}
