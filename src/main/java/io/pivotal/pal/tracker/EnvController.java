package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class EnvController {

    String port;
    String memoryLimit;
    String cfInstance;;
    String cfInstanceAdd;

    public EnvController(@Value("${port: NOT SET}")String port, @Value("${MEMORY_LIMIT: NOT SET}")String memoryLimit, @Value("${CF_INSTANCE_INDEX: NOT SET}")
            String cfInstance, @Value("${CF_INSTANCE_ADDR: NOT SET}")String cfInstanceAdd) {


        this.port = port;
        this.memoryLimit=memoryLimit;
        this.cfInstance=cfInstance;
        this.cfInstanceAdd = cfInstanceAdd;
    }
    @GetMapping("/env")
  public Map<String,String> getEnv(){

        Map<String,String> env = new HashMap<String,String>();
        env.put("PORT",port);
          env.put("MEMORY_LIMIT",memoryLimit);
          env.put("CF_INSTANCE_INDEX",cfInstance);
          env.put("CF_INSTANCE_ADDR",cfInstanceAdd);
        return env;
    }
}
