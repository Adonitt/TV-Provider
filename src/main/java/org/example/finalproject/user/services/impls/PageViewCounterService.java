package org.example.finalproject.user.services.impls;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class PageViewCounterService {
    private final Map<String, Long> pageCounters = new HashMap<>();

    public long incrementCounter(String pageName) {
        // Get the current count for the page, default to 0 if not found
        long currentCount = pageCounters.getOrDefault(pageName, 0L);
        // Increment and update the map
        pageCounters.put(pageName, currentCount + 1);
        return currentCount + 1;
    }

    public long getCounter(String pageName) {
        return pageCounters.getOrDefault(pageName, 0L);
    }
}
