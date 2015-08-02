package mari.controllers

import mari.Main
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody

import static mari.Main.currentPage

@Controller
@SuppressWarnings("UnusedDeclaration")
public class IndexController {
    private final static CODES = ["С днем рождения",
                                  "2",
                                  "3",
                                  "4",
                                  "5",
                                  "6"]

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showIndex() {
        println "Current page: ${currentPage.get()}"
        return "forward:/views/${currentPage.get()}.html"
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String readInput(String cod) {
        if(normalize(cod) == normalize(CODES[currentPage.get()-1]) && currentPage.get()<= CODES.size()){
            currentPage.incrementAndGet()
        }
        return "redirect:/"
    }

    @RequestMapping(value = "/reset")
    public String readInput() {
        currentPage.set(1)
        return showIndex()
    }



    private String normalize(String text){
        text.replace(' ',"").toLowerCase()
    }
}

