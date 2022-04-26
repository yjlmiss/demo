package com.example.demo;

import com.example.demo.utils.FormatNameUtils;
import org.junit.jupiter.api.Test;
import org.python.util.PythonInterpreter;

import java.util.Arrays;
import java.util.List;

public class formatFileName {
    @Test
    void executePythonFunction() {
        String path = "/Users/lushaowei/code/AV_Data_Capture/number_parser.py";
        PythonInterpreter interpreter = new PythonInterpreter();
        interpreter.execfile(path);
    }

    @Test
    void executeFormatName() {
        String filename = "sbw99.cc@heyzo_hd_2636_full.mp4";
        String str = null;
        List<String> testcases = Arrays.asList("MEYD-594-C.mp4",
                "SSIS-001_C.mp4",
                "SSIS100-C.mp4",
                "SSIS101_C.mp4",
                "ssni984.mp4",
                "ssni666.mp4",
                "SDDE-625_uncensored_C.mp4",
                "SDDE-625_uncensored_leak_C.mp4",
                "SDDE-625_uncensored_leak_C_cd1.mp4",
                "Tokyo Hot n9001 FHD.mp4",
                "TokyoHot-n1287-HD SP2006 .mp4",
                "caribean-020317_001.nfo",
                "257138_3xplanet_1Pondo_080521_001.mp4",
                "ADV-R0624-CD3.wmv",
                "XXX-AV   22061-CD5.iso",
                "xxx-av 20589.mp4",
                "Muramura-102114_145-HD.wmv",
                "heydouga-4102-023-CD2.iso",
                "HeyDOuGa4236-1048 Ai Qiu - .mp4",
                "pacopacomama-093021_539-FHD.mkv",
                "sbw99.cc@heyzo_hd_2636_full.mp4",
                "hhd800.com@STARS-566.mp4",
                "jav20s8.com@GIGL-677.mp4",
                "sbw99.cc@iesp-653.mp4");

//        System.out.println(str!=null);
//        System.out.println(filename+ "  *****-||-*****    "  +FormatNameUtils.formatByDict(filename));

        for (String testcase :testcases){
          System.out.println(FormatNameUtils.getFeatured(testcase)+ " # "  +testcase);
            //# XXX-AV   22061-CD5.iso
            // xxx-av 20589.mp4
            //heydouga-4102-023-CD2.iso
            //HeyDOuGa4236-1048 Ai Qiu - .mp4
            //sbw99.cc@heyzo_hd_2636_full.mp4
        }
    }
}
