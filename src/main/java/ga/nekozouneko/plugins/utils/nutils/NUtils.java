package ga.nekozouneko.plugins.utils.nutils;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
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
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public final class NUtils extends JavaPlugin implements Listener {
    // 起動 / 終了時の表示テキスト

    @Override
    public void onEnable() {
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
        for (Player allOP : getServer().getOnlinePlayers()) {
            if (allOP.isOp()) {
                if (args.length == 1) {
                    allOP.sendMessage("§n§6[§bNUtils§6]§r §l§4>§6>§r " + sender.getName() + " が §7§n/" + command.getName() + " " + args[0] + "§r を使用しました");
                } else {
                    allOP.sendMessage("§n§6[§bNUtils§6]§r §l§4>§6>§r " + sender.getName() + " が §7§n" + command.getName() + " " + args[0] + " " + args[1] +"§rを使用しました");
                }
            }
        }
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
        } else if (command.getName().equalsIgnoreCase("gamemode")) {
            if (args.length == 2) {
                if (args[0].equalsIgnoreCase("adventure") || args[0].equalsIgnoreCase("a") || args[0].equalsIgnoreCase("2")) {
                    getServer().getPlayer(args[1]).setGameMode(GameMode.ADVENTURE);
                    sender.sendMessage("§n§6[§bNUtils§6]§r §l§4>§6>§r ゲームモードを§n§7アドベンチャーモード§rに変更しました");
                    return true;
                } else if (args[0].equalsIgnoreCase("survival") || args[0].equalsIgnoreCase("s") || args[0].equalsIgnoreCase("0")) {
                    getServer().getPlayer(args[1]).setGameMode(GameMode.SURVIVAL);
                    sender.sendMessage("§n§6[§bNUtils§6]§r §l§4>§6>§r ゲームモードを§n§7クリエイティブモード§rに変更しました");
                    return true;
                } else if (args[0].equalsIgnoreCase("creative") || args[0].equalsIgnoreCase("c") || args[0].equalsIgnoreCase("1")) {
                    getServer().getPlayer(args[1]).setGameMode(GameMode.CREATIVE);
                    sender.sendMessage("§n§6[§bNUtils§6]§r §l§4>§6>§r ゲームモードを§n§7クリエイティブモード§rに変更しました");
                    return true;
                } else if (args[0].equalsIgnoreCase("spectator") || args[0].equalsIgnoreCase("sp") || args[0].equalsIgnoreCase("3")) {
                    getServer().getPlayer(args[1]).setGameMode(GameMode.SPECTATOR);
                    sender.sendMessage("§n§6[§bNUtils§6]§r §l§4>§6>§r ゲームモードを§n§7スペクテイターモード§rに変更しました");
                    return true;
                }
            }
            if (args[0].equalsIgnoreCase("adventure") || args[0].equalsIgnoreCase("a") || args[0].equalsIgnoreCase("2")) {
                getServer().getPlayer(sender.getName()).setGameMode(GameMode.ADVENTURE);
                sender.sendMessage("§n§6[§bNUtils§6]§r §l§4>§6>§r ゲームモードを§n§7アドベンチャーモード§rに変更しました");
                return true;
            } else if (args[0].equalsIgnoreCase("survival") || args[0].equalsIgnoreCase("s") || args[0].equalsIgnoreCase("0")) {
                getServer().getPlayer(sender.getName()).setGameMode(GameMode.SURVIVAL);
                sender.sendMessage("§n§6[§bNUtils§6]§r §l§4>§6>§r ゲームモードを§n§7クリエイティブモード§rに変更しました");
                return true;
            } else if (args[0].equalsIgnoreCase("creative") || args[0].equalsIgnoreCase("c") || args[0].equalsIgnoreCase("1")) {
                getServer().getPlayer(sender.getName()).setGameMode(GameMode.CREATIVE);
                sender.sendMessage("§n§6[§bNUtils§6]§r §l§4>§6>§r ゲームモードを§n§7クリエイティブモード§rに変更しました");
                return true;
            } else if (args[0].equalsIgnoreCase("spectator") || args[0].equalsIgnoreCase("sp") || args[0].equalsIgnoreCase("3")) {
                getServer().getPlayer(sender.getName()).setGameMode(GameMode.SPECTATOR);
                sender.sendMessage("§n§6[§bNUtils§6]§r §l§4>§6>§r ゲームモードを§n§7スペクテイターモード§rに変更しました");
                return true;
            }
        }
         return true;
    }
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (!command.getName().equalsIgnoreCase("gamemode")) return super.onTabComplete(sender, command, alias, args);
        if (args.length == 1) {
            if (args[0].length() == 0) { // /testまで
                return Arrays.asList("adventure", "creative", "survival", "spectator", "a", "c", "s", "sp", "0", "1", "2", "3");
            } else if (args[0].startsWith("a")) {
                    return Arrays.asList("adventure", "a");
                } else if (args[0].startsWith("c")) {
                    return Arrays.asList("creative", "c");
                } else if (args[0].startsWith("s")) {
                    return Arrays.asList("survival", "s");
                } else if (args[0].startsWith("sp")) {
                    return Arrays.asList("spectator", "sp");
            }
        }
        //JavaPlugin#onTabComplete()を呼び出す
        return super.onTabComplete(sender, command, alias, args);
    }
}

