package not.domain;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.HashMap;
public class Home extends JavaPlugin{
    String plname = ChatColor.BLUE + "[" + "Essentials" + ChatColor.DARK_GREEN + " Hsb " + ChatColor.BLUE + "]"; //name this plugin
    String plversion = ChatColor.DARK_GREEN + "1"+ChatColor.BLUE+"."+ChatColor.DARK_GREEN+"0";
    String pldev = "Hashing";
	public ConsoleCommandSender warn = Bukkit.getConsoleSender(); //warns
	HashMap<Player, Player> playertpa = new HashMap<Player, Player>(); //HashMap
	
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
		 else if(command.getName().equalsIgnoreCase("tpa")) {
			if(args.length == 1) {
				Player target = p.getServer().getPlayer(args[0]);			  
				if(target != null) {
					if(!target.getName().equals(p.getName()))
					{
						playertpa.put(target, p);
					p.sendMessage("Você pediu tpa para " + target.getName());
					target.sendMessage(p.getName()+ " esta le pedindo teleporte use /tpaccept ou /tpadeny");
					}

				}
			}
		}else if(command.getName().equalsIgnoreCase("tpaccept")) {
			if(playertpa.get(p) != null) {
				playertpa.get(p).teleport(p);
				p.sendMessage("Você aceitou o tpa de " + playertpa.get(p).getName());
				playertpa.get(p).sendMessage("Seu tpa foi aceito " + playertpa.get(p).getName());
				playertpa.put(p, null);
			}
		}else if(command.getName().equalsIgnoreCase("tpadeny")) {
			if(playertpa.get(p) != null) {
				p.sendMessage("Você cancelou o pedido de teleporte de " + playertpa.get(p).getName());
				playertpa.get(p).sendMessage("Seu tpa não foi aceito " + playertpa.get(p).getName());
				playertpa.put(p, null);
			}
		}
		return false;
	}
}
