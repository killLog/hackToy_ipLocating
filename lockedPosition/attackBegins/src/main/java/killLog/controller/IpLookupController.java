package killLog.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class IpLookupController {

    private final RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/ipinfo")
    public ResponseEntity<?> getIpInfo(@RequestParam String ip) {
        String url = "http://ip-api.com/json/" + ip + "?lang=zh-CN";

        try {
            Map response = restTemplate.getForObject(url, Map.class);
            if (response == null || !"success".equals(response.get("status"))) {
                return ResponseEntity.badRequest().body("无法获取该 IP 的信息");
            }
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("查询失败：" + e.getMessage());
        }
    }
}


