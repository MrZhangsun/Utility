package site.zhangsun.utility.jvm;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Functions: Memory Operation Controller
 *
 * @author Murphy Zhang Sun
 * @date 2019-07-05 17:59
 * @since v4.0.1
 */
@RestController
public class MemoryController {

    private List<Object> memory = new ArrayList<>(10);

    @GetMapping("/heap")
    public void memoryOut() {
        while (true) {
            Map<String, String> map = new HashMap<>(16);
            memory.add(map);
        }
    }
}