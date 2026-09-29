package com.example;

import com.google.inject.Provides;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.ClientToolbar;
import net.runelite.client.ui.NavigationButton;

@Slf4j
@PluginDescriptor(
        name = "GIF Panel"
)
public class GifPanelPlugin extends Plugin
{
    @Inject
    private ClientToolbar clientToolbar;

    @Inject
    private GifPanelConfig config;

    private NavigationButton navigationButton;
    private GifPanel panel;

    @Override
    protected void startUp()
    {
        panel = new GifPanel(config);

        BufferedImage icon = new BufferedImage(
                32,
                32,
                BufferedImage.TYPE_INT_ARGB
        );

        Graphics2D graphics = icon.createGraphics();
        graphics.setColor(new Color(255, 105, 180));
        graphics.fillOval(2, 2, 28, 28);
        graphics.setColor(Color.WHITE);
        graphics.drawString("G", 11, 21);
        graphics.dispose();

        navigationButton = NavigationButton.builder()
                .tooltip("GIF Panel")
                .icon(icon)
                .priority(10)
                .panel(panel)
                .build();

        clientToolbar.addNavigation(navigationButton);

        log.debug("GIF Panel started!");
    }

    @Override
    protected void shutDown()
    {
        if (navigationButton != null)
        {
            clientToolbar.removeNavigation(navigationButton);
            navigationButton = null;
        }

        panel = null;

        log.debug("GIF Panel stopped!");
    }

    @Provides
    GifPanelConfig provideConfig(ConfigManager configManager)
    {
        return configManager.getConfig(GifPanelConfig.class);
    }
}
