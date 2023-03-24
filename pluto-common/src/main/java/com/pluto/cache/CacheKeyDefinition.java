package com.pluto.cache;

/**
 * 用于统一管理和生成缓存的key， 避免多个项目使用的key重复
 * <p>
 */
public interface CacheKeyDefinition {

    /**
     * warframe redis key
     * 突击key
     */
    String WARFRAME_SORTIE = "warframe:sortie";

    /**
     * warframe redis key
     * 午夜电波key
     */
    String WARFRAME_NIGHT_WAVE = "warframe:nightWave";

    /**
     * warframe redis key
     * 虚空商人key
     */
    String WARFRAME_VOID_TRADER = "warframe:voidTrader";
}
