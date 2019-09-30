package not.domain;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
public class Home extends JavaPlugin{
    String plname = ChatColor.BLUE + "[" + "Essentials" + ChatColor.DARK_GREEN + " Hsb " + ChatColor.BLUE + "]"; //name this plugin
    String plversion = ChatColor.DARK_GREEN + "1"+ChatColor.BLUE+"."+ChatColor.DARK_GREEN+"0";
    String pldev = "Hashing";
	public ConsoleCommandSender warn = Bukkit.getConsoleSender(); //warns
	
	@Override
	public void onEnable() {
		warn.sendMessage(plname);
		warn.sendMessage(ChatColor.BLUE+"*"+ChatColor.DARK_GREEN + " ativado com sucesso");
		warn.sendMessage(plversion);
	}
	@Override
	public void onDisable() {
		warn.sendMessage(plname);
		warn.sendMessage(ChatColor.BLUE+"* "+ ChatColor.RED + "desligado com sucesso");
	}
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		 Player p = (Player) sender;
		 if(command.getName().equalsIgnoreCase("essentialshsb")) {
				p.sendMessage(plname);
				p.sendMessage(plversion);
				p.sendMessage(ChatColor.BLUE+ " [Download] > " + ChatColor.DARK_GREEN + "https://github.com/HasheDev/EssentialsHsb/releases/tag/1.0");
			}
		return false;
	}
}
