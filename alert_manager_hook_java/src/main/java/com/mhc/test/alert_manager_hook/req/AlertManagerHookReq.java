package com.mhc.test.alert_manager_hook.req;

import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
public class AlertManagerHookReq {

    private String status;
    private String groupKey;
    private String version;
    private String receiver;
    private String externalURL;
    private List<Alert> alerts;

    @Data
    public static class Alert {
        private String status;
        private Map<String, String> labels;
        private String startsAt;
        private String endsAt;
        private String fingerprint;
        private Map<String, String> annotations;

        public Date getStartsAtTime() {
            return Utils.parseAlertManagerTime(startsAt);
        }

        public Date getEndsAtTime() {
            return Utils.parseAlertManagerTime(endsAt);
        }
    }



}
