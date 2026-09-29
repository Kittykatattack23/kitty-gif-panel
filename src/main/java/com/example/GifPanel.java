package com.example;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import net.runelite.client.ui.PluginPanel;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.net.URL;

public class GifPanel extends PluginPanel
{
    private final GifPanelConfig config;
    private final JLabel gifLabel = new JLabel();

    public GifPanel(GifPanelConfig config)
    {
        this.config = config;

        gifLabel.setHorizontalAlignment(JLabel.CENTER);
        gifLabel.setPreferredSize(new Dimension(180, 180));

        add(gifLabel);

        loadGif();
    }

    private void loadGif()
    {
        String urlString = config.gifUrl();

        if (urlString == null || urlString.trim().isEmpty())
        {
            gifLabel.setText("Set a GIF URL in settings");
            return;
        }

        try
        {
            URL url = new URL(urlString);

            try (InputStream inputStream = url.openStream())
            {
                BufferedImage image = ImageIO.read(inputStream);

                if (image != null)
                {
                    gifLabel.setIcon(new ImageIcon(image));
                    gifLabel.setText("");
                }
                else
                {
                    gifLabel.setText("Could not load GIF");
                }
            }
        }
        catch (Exception e)
        {
            gifLabel.setText("Error loading GIF");
        }
    }
}
