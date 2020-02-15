package io.github.willqi.welcomemessage;

import cn.nukkit.utils.Config;
import java.util.LinkedHashMap;

public class WelcomeMessageConfig {

    private WelcomeMessagePlugin plugin;

    private boolean enableTitleAndSubtitle;

    private boolean enableMOTD;

    private String title;

    private String subtitle;

    private String motd;

    public WelcomeMessageConfig (WelcomeMessagePlugin plugin) {
        this.plugin = plugin;

        LinkedHashMap<String, Object> defaultConfig = new LinkedHashMap<String, Object>();
        defaultConfig.put("enableTitleAndSubtitle", false);
        defaultConfig.put("enableMOTD", false);
        defaultConfig.put("title", "Welcome to %server_name%!");
        defaultConfig.put("subtitle", "There are %player_count% players online!");
        defaultConfig.put("motd", "This message is displayed in chat every time you join the world!");

        plugin.saveDefaultConfig();
        Config configurations = plugin.getConfig();
        configurations.setDefault(defaultConfig);
        configurations.save();

        this.enableTitleAndSubtitle = configurations.getBoolean("enableTitleAndSubtitle");
        this.enableMOTD = configurations.getBoolean("enableMOTD");
        this.title = configurations.getString("title");
        this.subtitle = configurations.getString("subtitle");
        this.motd = configurations.getString("motd");

    }

    public boolean isTitleAndSubtitleEnabled () {
        return this.enableTitleAndSubtitle;
    }

    public boolean isMOTDEnabled () {
        return this.enableMOTD;
    }

    public String getTitle () {
        return replaceVariables(this.title);
    }

    public String getSubtitle () {
        return replaceVariables(this.subtitle);
    }

    public String getMOTD () {
        return replaceVariables(this.motd);
    }

    private String replaceVariables (String input) {
        return input
                .replaceAll("%server_name%", this.plugin.getServer().getName())
                .replaceAll("%player_count%", String.valueOf(this.plugin.getServer().getOnlinePlayers().size()))
                .replaceAll("%level_name%", this.plugin.getServer().getDefaultLevel().getName());
    }

}
