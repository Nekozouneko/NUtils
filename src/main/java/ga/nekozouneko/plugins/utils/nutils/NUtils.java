package ga.nekozouneko.plugins.utils.nutils;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.spigotmc.*;
import com.google.common.cache.AbstractCache;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.plugin.java.JavaPlugin;
import java.io.*;
import java.util.List;


public class NUtils extends JavaPlugin implements Listener {


    // 起動 / 終了時の表示テキスト

    @Override
    public void onEnable() {
        saveDefaultConfig();
        FileConfiguration config = getConfig();
        getLogger().info("§n§6[§bNUtils§6]§r §l§4>§6>§r NUtils for Spigot Edition Running on " + getServer().getName());
        getLogger().info("§n§6[§bNUtils§6]§r §l§4>§6>§r §aEnabled !");
    }

    @Override
    public void onDisable() {
        getLogger().info("§n§6[§bNUtils§6]§r §l§4>§6>§r NUtils for Spigot Edition Running on " + getServer().getName());
        getLogger().info("§n§6[§bNUtils§6]§r §l§4>§6>§r §4Disabled !");
    }

    // コマンド作成

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("nickname")) {
            String cn = args[0].replace("&", "§");
            getServer().getPlayer(sender.getName()).setDisplayName(cn);
            sender.sendMessage("§n§6[§bNUtils§6]§r §l§4>§6>§r ニックネームを [" + getServer().getPlayer(sender.getName()).getDisplayName() + "&r] に変更しました");
        } else if (command.getName().equalsIgnoreCase("nfly")) {
            if (getServer().getPlayer(sender.getName()).getGameMode().name().equalsIgnoreCase("CREATIVE")) {
                sender.sendMessage("§n§6[§bNUtils§6]§r §l§4>§6>§r §4すでに浮遊可能状態です");
                return true;
            } else if (getServer().getPlayer(sender.getName()).getGameMode().name().equalsIgnoreCase("SPECTATOR")) {
                sender.sendMessage("§n§6[§bNUtils§6]§r §l§4>§6>§r §4すでに浮遊可能状態です");
                return true;
            }
            if (getServer().getPlayer(sender.getName()).getAllowFlight()) {
                getServer().getPlayer(sender.getName()).setAllowFlight(false);
                sender.sendMessage("§n§6[§bNUtils§6]§r §l§4>§6>§r 浮遊を無効化しました。");
            } else {
                getServer().getPlayer(sender.getName()).setAllowFlight(true);
                sender.sendMessage("§n§6[§bNUtils§6]§r §l§4>§6>§r 浮遊を有効化しました。");
            }
        }
        return true;
    }
}

