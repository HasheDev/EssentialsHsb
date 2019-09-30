package not.domain;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
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
		 else if(command.getName().equalsIgnoreCase("gm")) {
				if(args.length == 1) {
					  try {
						  int Gm = Integer.parseInt(args[0]);
						  if(Gm >= 0) {
								if(Gm == 0) {
									if(p.getGameMode() != GameMode.SURVIVAL) {
									p.sendMessage("Voc� trocou de modo de jogo de " + p.getGameMode() + " para Survival");
									p.setGameMode(GameMode.SURVIVAL);
									}
									else
									p.sendMessage("Voc� j� esta no modo de jogo Survival");
								}else if(Gm == 1) {
									if(p.getGameMode() != GameMode.CREATIVE) {
									p.sendMessage("Voc� trocou de modo de jogo de " + p.getGameMode() + " para Criativo");
									p.setGameMode(GameMode.CREATIVE);
									}
									else
									p.sendMessage("Voc� j� esta no modo de jogo Criativo");
								}else if(Gm == 2 | Gm == 3) {
									if(p.getGameMode() != GameMode.SPECTATOR) {
									p.sendMessage("Voc� trocou de modo de jogo de " + p.getGameMode() + " para Espectador");
									p.setGameMode(GameMode.SPECTATOR);
									}
									else
									p.sendMessage("Voc� j� esta no modo de jogo Espectador");
								}
						}
					  }catch (NumberFormatException ex) {
						  p.sendMessage("/gm "+args[0]+" N�o � um modo de jogo");
					  }
				}
			}
		 else if(command.getName().equalsIgnoreCase("tpa")) {
			if(args.length == 1) {
				Player target = p.getServer().getPlayer(args[0]);			  
				if(target != null) {
					if(!target.getName().equals(p.getName()))
					{
						playertpa.put(target, p);
					p.sendMessage("Voc� pediu tpa para " + target.getName());
					target.sendMessage(p.getName()+ " esta le pedindo teleporte use /tpaccept ou /tpadeny");
					}

				}
			}
		}else if(command.getName().equalsIgnoreCase("tpaccept")) {
			if(playertpa.get(p) != null) {
				playertpa.get(p).teleport(p);
				p.sendMessage("Voc� aceitou o tpa de " + playertpa.get(p).getName());
				playertpa.get(p).sendMessage("Seu tpa foi aceito " + playertpa.get(p).getName());
				playertpa.put(p, null);
			}
		}else if(command.getName().equalsIgnoreCase("tpadeny")) {
			if(playertpa.get(p) != null) {
				p.sendMessage("Voc� cancelou o pedido de teleporte de " + playertpa.get(p).getName());
				playertpa.get(p).sendMessage("Seu tpa n�o foi aceito " + playertpa.get(p).getName());
				playertpa.put(p, null);
			}
		}
		return false;
	}
}
