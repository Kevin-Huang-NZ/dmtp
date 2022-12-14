package cn.gov.mca.dmtp.cache;

import lombok.extern.slf4j.Slf4j;
import org.ehcache.event.CacheEvent;
import org.ehcache.event.CacheEventListener;

@Slf4j
public class CacheEventLogger implements CacheEventListener<Object, Object> {
  public void onEvent(CacheEvent<? extends Object, ? extends Object> cacheEvent) {
    log.info("Ehcache event: {}, key: {}, old value: {}, new value: {}.",
        cacheEvent.getType(),
        cacheEvent.getKey(),
        cacheEvent.getOldValue(),
        cacheEvent.getNewValue()
    );
  }
}
