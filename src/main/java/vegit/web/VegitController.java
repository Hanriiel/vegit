package vegit.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class VegitController {


    @GetMapping("/index")
    public String index(){
        return "index";
    }


}
