package vegit.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class vegitController {

    @RequestMapping("main")
    @ResponseBody
    public String naytaViesti(){
        return "Toimii";
    }


}
