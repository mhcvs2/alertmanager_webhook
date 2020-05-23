package com.mhc.test.alert_manager_hook.controller;

import com.mhc.test.alert_manager_hook.req.AlertManagerHookReq;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/alert")
public class AlertManagerController {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

    public static void print(String key, String value) {
        System.out.println(key + ": " + value);
    }

    @PostMapping("/receive")
    public String receiveAlertHook(@RequestBody AlertManagerHookReq alertManagerHookReq){
        System.out.println("========================================================");
        print("status", alertManagerHookReq.getStatus());
        print("groupKey", alertManagerHookReq.getGroupKey());
        print("version", alertManagerHookReq.getVersion());
        print("receiver", alertManagerHookReq.getReceiver());
        print("externalURL", alertManagerHookReq.getExternalURL());
        List<AlertManagerHookReq.Alert> alerts = alertManagerHookReq.getAlerts();
        for (int i=0; i<alerts.size(); i++){
            System.out.println("第" + i + "个告警================");
            AlertManagerHookReq.Alert alert = alerts.get(i);
            print("status", alert.getStatus());
            print("startsAt", alert.getStartsAt());
            print("startAtTime", Optional.ofNullable(alert.getStartsAtTime()).map(sdf::format).orElse("no time"));
            print("endsAt", alert.getEndsAt());
            print("endsAtTime", Optional.ofNullable(alert.getEndsAtTime()).map(sdf::format).orElse("no time"));
            print("fingerprint", alert.getFingerprint());
            System.out.println("---labels---");
            for (Map.Entry<String, String> entry: alert.getLabels().entrySet()) {
                print(entry.getKey(), entry.getValue());
            }
            System.out.println("---annotations---");
            for (Map.Entry<String, String> entry: alert.getAnnotations().entrySet()) {
                print(entry.getKey(), entry.getValue());
            }
        }
        return "ok";
    }

}
