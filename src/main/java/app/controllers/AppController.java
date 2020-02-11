package app.controllers;

import app.model.DataRecord;
import app.services.IService;
import app.services.ServiceBMI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class AppController {

    //IService iService=new ServiceBMI();
    /**
     * dwie poniższe linie działającego kodu zastępują następującą linię:
     * * <code> IService iService=new ServiceBMI(); </code>
     * Jest to przykład/realizacja  "INVERSION OF CONTROL"
    Inversion of control zostało osiągnięte dzieki 1) wrzuceniu bean'a do kontenera
     (czyli @Component dla klasy ServiceBMI) oraz 2) automatycznemu wstrzyknięciu
    (@autowired) do zmiennej interfejsowej "iService" zależności od Bean'a
     wrzuconego do kontenera.
     Po wstrzyknięciu do zmiennej interfejsowej "iService" bean'a z kontenera,
     import klasy "BmiService" (linia 5) jest już niepotrzebny i wyszarzał
     Dla porównania implementacja IDao nie jest wstrzykiwana tylko robiona
     przez interfejs ale słabo czyli nawet bez singletna*/
    @Autowired
    IService iService;


    @RequestMapping(value = "/start", method = RequestMethod.GET)
    public String showStartPage() {
        return "startPage";
    }

    @RequestMapping(value = "/enterData", method = RequestMethod.GET)
    public String enterData() {
        return "enterDataPage";
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String searchData(Model model) {
        int maximumListSize=iService.getDataRecordListSize();
        model.addAttribute("maximum", maximumListSize);
        return "searchPage";
    }

    @RequestMapping(value = "/scoreDate", method = RequestMethod.PUT)
    public String scoreData() {
        return "scorePage";
    }

    @RequestMapping(value = "/end", method = RequestMethod.GET)
    public String end() {
        return "endPage";
    }

    @RequestMapping(value="/enterData", method = RequestMethod.POST)
    public String enterData(@RequestParam String hight,
                         @RequestParam String weight,
                         @RequestParam String sex,
                         Model model){

        DataRecord newDataRecord = iService.makeDataRecord(hight,weight,sex);
        model.addAttribute("DataRecord",newDataRecord);

        return "scorePage";
    }

    @RequestMapping(value="/search", method=RequestMethod.POST)
    public String search(@RequestParam String strId, Model model){
        DataRecord searchedDataRecord=iService.findDataRecord(strId);
        model.addAttribute("DataRecord",searchedDataRecord);

        return "scorePage";
    }








}