package com.pluto.cache;

/**
 * 用于统一管理和生成缓存的key， 避免多个项目使用的key重复
 * <p>
 */
public interface CacheKeyDefinition {
    /**
     * warframe redis key
     */
    String WARFRAME_SORTIE = "warframe:sortie";
}
