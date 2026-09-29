package com.example;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("gifpanel")
public interface GifPanelConfig extends Config
{
    @ConfigItem(
            keyName = "gifUrl",
            name = "GIF URL",
            description = "URL of the GIF to display"
    )
    default String gifUrl()
    {
        return "";
    }
}
