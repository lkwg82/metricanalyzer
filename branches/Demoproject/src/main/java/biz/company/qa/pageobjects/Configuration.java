/*
 * (C) 2001 - 2011 company.biz AG
 */
package biz.company.qa.pageobjects;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * a data class for the test configurations
 * 
 
 */
public class Configuration<KEYS extends Enum<?>> {
    private final Map<KEYS, String> map = new HashMap<KEYS, String>();
    /**
     * read/write map
     */
    private final Map<KEYS, Boolean> permissions = new HashMap<KEYS, Boolean>();

    public String getValue(final KEYS key) {
        return map.get(key);
    }

    /**
     * setting a configuration (default : readonly)
     * 
     * @param key
     * @param value
     */
    public void setValue(final KEYS key, final String value) {
        if (permissions.containsKey(key) && permissions.get(key)) {
            throw new SecurityException("config key " + key + " is readonly");
        } else {
            map.put(key, value);
            permissions.put(key, true);
        }
    }

    /**
     * try to set if not readonly
     * 
     * @param key
     * @param value
     */
    public void trySetValue(final KEYS key, final String value) {
        trySetValue(key, value, true);
    }

    /**
     * try to set if not readonly
     * 
     * @param key
     * @param value
     */
    public void trySetValue(final KEYS key, final String value, final boolean readonly) {
        if (!permissions.containsKey(key) || !permissions.get(key)) {
            map.put(key, value);
            permissions.put(key, readonly);
        }
    }

    /**
     * setting a configration with explicit permission
     * 
     * @param key
     * @param value
     * @param readOnly
     */
    public void setValue(final KEYS key, final String value, final boolean readOnly) {
        setValue(key, value);
        permissions.put(key, readOnly);
    }

    @Override
    public String toString() {
        StringBuffer b = new StringBuffer();

        /*
         * find max size
         */
        int width = 10;
        for (KEYS k : map.keySet()) {
            width = k.name().length() > width ? k.name().length() : width;
        }

        for (Entry<KEYS, String> e : map.entrySet()) {
            b.append(String.format("%" + width + "s : %s %s\n", e.getKey(), e.getValue(),
                    permissions.get(e.getKey()) ? "RO" : "RW"));
        }

        return b.toString();
    }

    /**
     * clone object
     * 
     * @return
     */
    public Configuration<CONFIG_KEYS> getCopy() {
        Configuration<CONFIG_KEYS> freshCopy = new Configuration<CONFIG_KEYS>();

        for (CONFIG_KEYS key : CONFIG_KEYS.values()) {
            if (map.containsKey(key)) {
                freshCopy.map.put(key, map.get(key));
            }
        }

        return freshCopy;
    }
}
